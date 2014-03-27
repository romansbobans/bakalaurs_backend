package database;

import upload.IUploader;

public interface DBConnection {
		
	ICollection getCollection();
	
	IUploader getUploader();

	IRawCollection getRawCollection();
	
	
	

}
