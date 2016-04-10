import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.FinanceOperation;
import com.finance.tracker.model.FinanceOperationType;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.IFinanceOperationDao;
import com.finance.tracker.model.dao.UserDAO;

public class Main {
	public static void main(String[] args) throws ParseException {
		int [] p = new int [1];
		System.out.println(p.length);
		
//		String d = "10-12-2016";
//		String d2 = "20-12-2016";
//		Date dd = new SimpleDateFormat("yyyy-MM-dd").parse(d);
//		Date dd2 = new SimpleDateFormat("yyyy-MM-dd").parse(d2);
//		dateDifference(dd, dd2);

		// try {
		// IFinanceOperation fo = new FinanceOperation(100, new Date(2016, 03,
		// 03), "More money", "",
		// new CategoryDao().foundById(352), RepeatType.NO_REPEAT,
		// FinanceOperationType.EXPENCES, (Account) new
		// AccountDAO().getAccount(701));
		// new FinanceOperationDao().addFinanceOperation(fo);
		//
		// } catch (FinanceTrackerException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// FinanceOperationDao dao = new FinanceOperationDao();
		// dao.getAllExpencesByCategory((Account) new
		// AccountDAO().getAccount(1151));
	}

	public static void dateDifference(Date d, Date d2) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		String inputString1 = "23 01 1997";
		String inputString2 = "27 01 1997";

		try {
		    Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    long diff = date2.getTime() - date1.getTime();
		    System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}
}
