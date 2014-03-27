package upload;

import Holders.CategoryGroup;

import com.mongodb.DB;

import database.DBConnection;
import database.DBConstants;
import database.ICollection;

public class Uploader implements IUploader{

	DBConnection connection;
	
	public Uploader(DBConnection connection)
	{
		this.connection = connection;
	}
	
	@Override
	public boolean uploadCategories(CategoryGroup category) {
		
		ICollection collection = connection.getCollection();
		collection.getAllCategories();//(category);
		return false;
	}

}
