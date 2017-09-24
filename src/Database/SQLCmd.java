package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


/*
 * SQLCmd -> implements command and template patterns
 */
public abstract class SQLCmd {
	
	public ArrayList<Object> result = new ArrayList<Object>();
	public ResultSet res;
	public String selectStatement = "";
	public Connection conn;
	public ArrayList<Object> getResult(){return result;};
	public abstract void queryDB();
	public abstract void processResult();
	
	public void execute(Connection conn) throws SQLException{
		try{
			this.conn = conn;
			queryDB();
			processResult();
		}
		catch(Exception e){
			conn.close();
		}
	}
}

