package database;


import com.mongodb.DB;

public class DBConnectionFactory {
	
	public static DBConnection getDatabase()
	{
		
		return new MongoConnection(DBConstants.HOST_NAME, DBConstants.MONGO_PORT, DBConstants.MONGO_DB_NAME);
	}

    public static RawConnection getRawDatabase() {
        return new MongoRawConnection(DBConstants.HOST_NAME, DBConstants.MONGO_PORT, DBConstants.MONGO_DB_NAME);
    }
}
