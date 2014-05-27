package database;

import java.net.UnknownHostException;

import upload.IUploader;
import upload.Uploader;

import com.mongodb.DB;
import com.mongodb.DBAddress;
import com.mongodb.Mongo;

public class MongoConnection implements DBConnection {

	
	DB db;
	
	public MongoConnection(String hostName, int port, String dbName) 
	{
		try
		{
		db = Mongo.connect(new DBAddress(hostName, port, dbName));
		}
		catch (UnknownHostException e)
		{
			throw new RuntimeException("Unable to connect to MongoDB");
		}
	}


    public DB getDatabase()
    {
        return db;
    }
	@Override
	public ICollection getCollection() {
		return new MongoCollection(db);
		
	}
	
	@Override
	public IRawCollection getRawCollection() {
		return new RawMongoCollection(db);
		
	}
	
	@Override
	public IUploader getUploader() {
		// TODO Auto-generated method stub
		return new Uploader(this);
	}
	
	

}
