import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.dao.CategoryDao;

public class Amain {

	public static void main(String[] args) {
		CategoryDao dao = new CategoryDao();
		try {
			dao.addCategory(new Category("Clothes"));
		} catch (FinanceTrackerException e) {
			e.printStackTrace();
		}

	}

}
