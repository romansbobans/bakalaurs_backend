package database;


public class DBConnectionFactory {
	
	public static DBConnection getDatabase()
	{
		
		return new MongoConnection(DBConstants.HOST_NAME, DBConstants.MONGO_PORT, DBConstants.MONGO_DB_NAME);
	}

}
