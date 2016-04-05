import java.util.Date;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.exception.PasswordException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.Currency;
import com.finance.tracker.model.FinanceOperation;
import com.finance.tracker.model.FinanceOperationType;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.ICategory;
import com.finance.tracker.model.IFinanceOperation;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.RepeatType;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.User;
import com.finance.tracker.model.dao.AccountDAO;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.ICategoryDao;
import com.finance.tracker.model.dao.IFinanceOperationDao;
import com.finance.tracker.model.dao.ITagDao;
import com.finance.tracker.model.dao.IUserDAO;
import com.finance.tracker.model.dao.TagDao;
import com.finance.tracker.model.dao.UserDAO;

public class Amain {

	public static void main(String[] args) throws FinanceTrackerException, PasswordException {
		IFinanceOperationDao financeDao = new FinanceOperationDao();
		IUserDAO userDao = new UserDAO();
		IUser user = new User("Pesho", "FFF", "fff2@abv.bg", "i9W",Currency.BGN, false, new Date(2016, 1, 1));
		userDao.createUser(user);
		IAccount account = new Account("Keshove", 5000, (User) user);
		new AccountDAO().createAccount(account);
		ICategoryDao categoryDao = new CategoryDao();
		ICategory cat = new Category("Extra11");
		Tag tag = new Tag("Tagyt11", (Category) cat);
		ITagDao tagDao = new TagDao();
		categoryDao.addCategory(cat);
		tagDao.addTag(tag);
		IFinanceOperation finance = new FinanceOperation();
		finance.setSum(500);
		finance.setOperationType(FinanceOperationType.EXPENCES);
		finance.setDescription("Go Go Go 11");
		finance.setDate(new Date(2016, 1, 1));
		finance.setRepeatType(RepeatType.MONTHLY);
		finance.setCategory((Category) cat);
		finance.setAccount((Account) account);
		finance.addTag(tag);
		financeDao.addFinanceOperation(finance);
		
	}

}
