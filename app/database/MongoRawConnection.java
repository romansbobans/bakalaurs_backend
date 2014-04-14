package database;

import com.mongodb.*;
import upload.IUploader;
import upload.Uploader;
import utils.MongoFieldNames;

import java.net.UnknownHostException;

public class MongoRawConnection implements RawConnection {


	DB db;

	public MongoRawConnection(String hostName, int port, String dbName)
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


    @Override
    public String[] getCategories() {
        DBCollection collection = db.getCollection(DBConstants.CATEGORIES_COLLECTION);
        DBCursor cursor = collection.find();

        String[] values = new String[cursor.count()];
        int count = 0;

        while (cursor.hasNext())
        {
            String val = cursor.next().toString();
            values[count++] = val;

        }
        return values;
    }

    @Override
    public String[] getViews(String id) {
        DBCollection collection = db.getCollection(DBConstants.VIEWS_COLLECTION);
        DBCursor cursor = collection.find(new BasicDBObject(MongoFieldNames.CATEGORY_ID, id));

        String[] values = new String[cursor.count()];
        int count = 0;

        while (cursor.hasNext())
        {
            String val = cursor.next().toString();
            values[count++] = val;

        }
        return values;
    }
}
