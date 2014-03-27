package controllers.raw;

import play.mvc.Controller;
import play.mvc.Result;
import database.DBConnectionFactory;
import database.IRawCollection;

public class Get extends Controller {
	
	static IRawCollection coll = DBConnectionFactory.getDatabase()
			.getRawCollection();
	
	
	public static Result getCategories()
	{
		return ok("sd");
	}

}
