package com.finance.tracker.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.BudgetDao;
import com.finance.tracker.model.dao.IBudgetDao;

@WebServlet("/editBudget")
public class EditBudget extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private boolean dateCorrectness = true;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME) == null) {
			response.sendRedirect("./login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		IBudget budget = new BudgetDao().foundById(id);
		session.setAttribute("budgetId", id);
		session.setAttribute("budget", budget);
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		Collection<IAccount> accounts = new AccountDAO().getAllAccountsByUser(user);
		request.setAttribute("accounts", accounts);
		RequestDispatcher rd = request.getRequestDispatcher("./jsp/editBudget.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("budgetId");
		IBudget budget = new BudgetDao().foundById(id);
		budget = validateUpdates(request, response);
		IBudgetDao dao = new BudgetDao();
		dao.updateBudget(budget);
		System.out.println("Datecorrectness: " + dateCorrectness);
		if (dateCorrectness) {
			response.sendRedirect("./budget");
			return;
		} else {
			User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
			Collection<IAccount> accounts = new AccountDAO().getAllAccountsByUser(user);
			request.setAttribute("dates", "Start date must be before end date!");
			request.setAttribute("accounts", accounts);
			RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/editBudget.jsp");
			dispatcher.forward(request, response);
		}
	}

	private IBudget validateUpdates(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("budgetId");
//		String sum = request.getParameter("newSum");
		String date = request.getParameter("newStart");
		String end = request.getParameter("newEnd");
		String title = (String) request.getParameter("newTitle");
		// String type = (String) request.getParameter("newRepeat");
		IBudget budget = (IBudget) request.getSession().getAttribute("budget");
		String selects[] = request.getParameterValues("selected");
		double sum =0.0;
		if (!checkSelect(selects)) {
			budget.deleteAllAccounts();
			System.out.println("Accounts");
			for (int select = 0; select < selects.length; select++) {
				try {
					int idAcc = Integer.parseInt(selects[select]);
					Account a = (Account) new AccountDAO().getAccount(idAcc);
					sum+= new AccountDAO().getAccount(idAcc).getSum();
					System.out.println("Account " + a.getId());
					System.out.println(a.getTitle());
					budget.addAccount(a);
				} catch (FinanceTrackerException e) {
					e.printStackTrace();
				}
			}
			budget.setTotalAmount(sum);
			new BudgetDao().updateBudget(budget);

		}
//		if (sum != null && sum.length() > 0 && !sum.equals("")) {
//			double newSum = Double.parseDouble(sum);
//			budget.setTotalAmount(newSum);
//		}

		if (!(date.equals("")) && date != null && date.length() > 0) {
			try {
				Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				budget.setStartDate(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (FinanceTrackerException e) {
				e.printStackTrace();
			}
		}
		if (!(end.equals("")) && end != null && end.length() > 0) {
			try {
				Date startDate = new Date();
				Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
				if (date != null && !date.equals("") && date.length() > 0) {
					startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				} else {
					startDate = budget.getStartDate();
				}

				if (!checkDates(startDate, endDate)) {
					budget.setEndDate(endDate);
				} else {
					dateCorrectness = false;
				}

			} catch (ParseException e) {
				e.printStackTrace();
			} catch (FinanceTrackerException e) {
				e.printStackTrace();
			}
		}
		if (title != null && title.length() > 0 && !title.equals(""))

		{
			try {
				budget.setTitle(title);
			} catch (FinanceTrackerException e) {
				e.printStackTrace();
			}
		}
		// if (!(type.equals("blanck"))) {
		// RepeatType newType = RepeatType.valueOf(type);
		// System.out.println(newType + "!!");
		// budget.setRepeatType(newType);
		// }

		budget.setId(id);
		return budget;
	}

	private boolean checkSelect(String select[]) {
		if (select == null) {
			return true;
		}
		return false;
	}

	private boolean checkDates(Date d, Date d2) {
		return (d.after(d2) && d2.before(d));
	}

}
