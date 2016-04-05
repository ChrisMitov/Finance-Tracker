import java.util.Date;

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
import com.finance.tracker.model.dao.UserDAO;

public class Main {
	public static void main(String[] args) {
		try {
			IFinanceOperation fo = new FinanceOperation(100, new Date(2016, 03, 03), "More money", "",
					new CategoryDao().foundById(352), RepeatType.NO_REPEAT, FinanceOperationType.EXPENCES, (Account) new AccountDAO().getAccount(701));
			new FinanceOperationDao().addFinanceOperation(fo);
			
		} catch (FinanceTrackerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
