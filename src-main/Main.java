import java.util.Date;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.FinanceOperation;
import com.finance.tracker.model.FinanceOperationType;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.IFinanceOperationDao;
import com.finance.tracker.model.dao.UserDAO;

public class Main {
	public static void main(String[] args) {
		
		IUser user = new UserDAO().getUser(1101);
		int sum = ConvertCurrency.convert(200, Currency.EUR, user.getCurrency());
		System.out.println(sum);
	}
}
