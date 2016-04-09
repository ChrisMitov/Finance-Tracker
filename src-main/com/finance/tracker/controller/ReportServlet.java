package com.finance.tracker.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Expense;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.Income;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.UserDAO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/report")
public class ReportServlet extends BaseServlet {
	private static final String PATH_OF_IMAGE = "/resources/images/report.png";
	private static final int SIZE_OF_TAGS = 11;
	private static final int SIZE_OF_CELLS = 14;
	private static final int SIZE_OF_OPERATIONS = 18;
	private static final int SIZE_OF_HEADER = 24;
	private static final float ABSOLUTE_Y = 10f;
	private static final float ABSOLUTE_X = 450f;
	private static final String TITLE = "Report";
	private static final String AUTHOR_NAME = "FinanceTrackerOOD";
	private static final String CREATOR_NAME = "FinanceTracker.Chris";
	private static final int COLUMN_WIDTH = 200;
	private static final int NUMBER_OF_CELLS = 6;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./login");
			return;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseServlet.LOGGED_USER_ATTRIBUTE_NAME);
		response.setContentType("application/pdf");
		Document doc = new Document();
		Font bfBold24 = new Font(FontFamily.TIMES_ROMAN, SIZE_OF_HEADER, Font.BOLD, new BaseColor(0, 0, 0));
		Font bfBold18 = new Font(FontFamily.TIMES_ROMAN, SIZE_OF_OPERATIONS, Font.BOLD, new BaseColor(0, 0, 0));
		Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, SIZE_OF_CELLS, Font.BOLDITALIC, new BaseColor(150, 0, 0));
		Font bf12 = new Font(FontFamily.TIMES_ROMAN, SIZE_OF_TAGS, Font.ITALIC, new BaseColor(204, 102, 0));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try (ServletOutputStream os = response.getOutputStream();) {
			PdfWriter.getInstance(doc, os);
			doc.addAuthor(AUTHOR_NAME);
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator(CREATOR_NAME);
			doc.addTitle(TITLE);
			doc.setPageSize(PageSize.LETTER);
			doc.open();
			Paragraph paragraph2 = new Paragraph();
			paragraph2.setSpacingAfter(25);
			paragraph2.setSpacingBefore(25);
			paragraph2.setAlignment(Element.ALIGN_CENTER);
			paragraph2.setIndentationLeft(50);
			paragraph2.setIndentationRight(50);
			paragraph2.setFont(bfBold24);
			paragraph2.add("Your report:\n \n" + new UserDAO().getEmailById(user.getUserId()));
			doc.add(paragraph2);
			Collection<IAccount> accounts = new UserDAO().getAllAccounts(user.getUserId());
			Collection<Expense> expenseAccount = new ArrayList<Expense>();
			Collection<Income> incomesAccount = new ArrayList<Income>();
			addFinanceOperations(accounts, expenseAccount, incomesAccount);
			Collection<IFinanceOperation> expenses = new ArrayList<IFinanceOperation>();
			expenses.addAll(expenseAccount);
			Collections.sort((List<IFinanceOperation>) expenses, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));
			Collection<IFinanceOperation> incomes = new ArrayList<IFinanceOperation>();
			incomes.addAll(incomesAccount);
			Collections.sort((List<IFinanceOperation>) incomes, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));
			doc.add(new Paragraph("Incomes: \n", bfBold18));
			createTable(doc, incomes, dateFormat, bfBold12, bf12);
			doc.add(new Paragraph("Expenses: \n", bfBold18));
			createTable(doc, expenses, dateFormat, bfBold12, bf12);
			String path = PATH_OF_IMAGE;
			String absoluteDiskPath = getServletContext().getRealPath(path);
			Image image = Image.getInstance(absoluteDiskPath);;
			image.setAbsolutePosition(ABSOLUTE_X, ABSOLUTE_Y);
			doc.add(image);
			doc.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private void createTable(Document doc, Collection<IFinanceOperation> incomes, DateFormat dateFormat, Font font,
			Font tagFont) throws DocumentException {
		PdfPTable table = new PdfPTable(NUMBER_OF_CELLS);
		table.setWidths(
				new int[] { COLUMN_WIDTH, COLUMN_WIDTH / 2, COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_WIDTH });
		PdfPCell defaultCell = table.getDefaultCell();
		defaultCell.setBorder(PdfPCell.NO_BORDER);
		defaultCell.setPadding(5);
		table.addCell(new Paragraph("Description", font));
		table.addCell(new Paragraph("Sum", font));
		table.addCell(new Paragraph("Account", font));
		table.addCell(new Paragraph("Category", font));
		table.addCell(new Paragraph("Tags", font));
		table.addCell(new Paragraph("Date", font));
		for (IFinanceOperation income : incomes) {
			try {
				addOperation(table, dateFormat, income, tagFont);
			} catch (FinanceTrackerException e) {
				e.printStackTrace();
			}
		}
		doc.add(table);
	}

	private void addOperation(PdfPTable table, DateFormat dateFormat, IFinanceOperation operation, Font tagFont)
			throws FinanceTrackerException {
		Collection<Tag> tags = operation.getAllTags();
		table.addCell(new Paragraph(operation.getDescription()));
		table.addCell(new Paragraph(String.valueOf(operation.getSum())));
		table.addCell(new Paragraph(operation.getAccount().getTitle()));
		table.addCell(new Paragraph(operation.getCategory().getName()));
		com.itextpdf.text.List unorderedList = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
		for (Tag tag : tags) {
			unorderedList.add(new ListItem(tag.getName(), tagFont));
		}
		PdfPCell cell = new PdfPCell();
		cell.setBorder(0);
		cell.setPaddingTop(-2);
		cell.addElement(unorderedList);
		table.addCell(cell);
		table.addCell(new Paragraph(String.valueOf(dateFormat.format(operation.getDate()))));
	}

	private void addFinanceOperations(Collection<IAccount> accounts, Collection<Expense> expenseAccount,
			Collection<Income> incomesAccount) {
		for (IAccount iAccount : accounts) {
			
			expenseAccount.addAll(new FinanceOperationDao().getAllExpencesByAccount((Account) iAccount));
			incomesAccount.addAll(new FinanceOperationDao().getAllIncomeByAccount((Account) iAccount));
		}
	}
}
