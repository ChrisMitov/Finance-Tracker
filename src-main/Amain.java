import java.util.List;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Category;
import com.finance.tracker.model.Tag;
import com.finance.tracker.model.dao.CategoryDao;
import com.finance.tracker.model.dao.FinanceOperationDao;
import com.finance.tracker.model.dao.ICategoryDao;
import com.finance.tracker.model.dao.ITagDao;
import com.finance.tracker.model.dao.TagDao;

public class Amain {

	public static void main(String[] args) {
		ITagDao dao = new TagDao();
		FinanceOperationDao daoto = new FinanceOperationDao();
		ICategoryDao cat = new CategoryDao();
		List<Tag> tags =(List<Tag>) dao.getAllTagsByCategory(cat.foundById(1));
		for (Tag tag : tags) {
			System.out.println(tag.getName());
		}
	}

}
