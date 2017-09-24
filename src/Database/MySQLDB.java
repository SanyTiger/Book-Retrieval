package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Stack;

import Database.Commands.SQLCommands;
import StoreController.StackOperation;
import StoreGUI.BookCategory;
import StoreGUI.Category;


/*
 * Class : MySQLDB -> implements factory method and bridge patterns
 */
public class MySQLDB implements DBInterface {

	public Connection conn;
	
	@Override
	public void connectDB(){
		try
	    {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    
		    Properties properties = new Properties();
		    String jdbcURL = "jdbc:mysql://localhost:3306/sdp";
		    properties.setProperty("user", "SanjayBhat");
		    properties.setProperty("password", "Apple2016");
		    properties.setProperty("useSSL", "false");
		    properties.setProperty("autoReconnect", "true");
		    conn = DriverManager.getConnection(jdbcURL, properties);
	    }
	    catch (Exception e){
	    	//bk.message = "Error in DB Connection";
	    }
	}
	
	/*
	 * Class : MySQLDB -> implements factory method and bridge patterns
	 */
	@Override
	public String store(Category category, StackOperation stackBooks) {
		try{
			if(category instanceof BookCategory){
				String selectMessage = retrieve((BookCategory)category);
				if(selectMessage.equalsIgnoreCase("Data Doesn't Exist")){
					SQLCmd cmd = new Database.Commands.StoreBook((BookCategory)category);
					connectDB();
					cmd.execute(conn);  
					disconnectDB();
					stackBooks.stackUndo.push((BookCategory)category);
				}
				else
					((BookCategory)category).message = "Data Already Exists";
			}
		}
		catch(Exception ex){
			((BookCategory)category).message = "Error in Store Operation";
		}
		return ((BookCategory)category).message;
	}

	/*
	 * Class : MySQLDB -> implements factory method and bridge patterns
	 */
	@Override
	public String retrieve(Category category) {
		try{
			if(category instanceof BookCategory){
				SQLCmd cmd = new Database.Commands.RetrieveBook((BookCategory)category);
				connectDB();
				cmd.execute(conn);  
				disconnectDB();
				if(((BookCategory)category).message == "")
					((BookCategory)category).message = "Data Doesn't Exist";
			}
		}
		catch(Exception ex){
			((BookCategory)category).message = "Error in Retrieve Operation";
		}
		return ((BookCategory)category).message;
	}

	/*
	 * Class : MySQLDB -> implements factory method and bridge patterns
	 */
	@Override
	public String redoPreviousOperation(Category category, StackOperation stackBooks) {
		try{
			if(category instanceof BookCategory){
				if(!(stackBooks.stackRedo.size() == 0)){
				BookCategory redoPop = stackBooks.stackRedo.pop();
					((BookCategory)category).message = store(redoPop, stackBooks);
					if(((BookCategory)category).message.equalsIgnoreCase("Book Stored Successfully"))
						stackBooks.message = "Redo Operation was successful";
					else
						stackBooks.message = "FAILED Redo Operation";
				}
				else
					stackBooks.message = "No items to redo";
			}
		}
		catch(Exception ex){
			stackBooks.message = "Error in Redo Operation";
		}
		return stackBooks.message;
	}

	/*
	 * Class : MySQLDB -> implements factory method and bridge patterns
	 */
	@Override
	public String undoPreviousOperation(Category category, StackOperation stackBooks) {
		try{
			if(category instanceof BookCategory){
				if(!(stackBooks.stackUndo.size() == 0)){
				BookCategory undoPop = stackBooks.stackUndo.pop();
					SQLCmd cmd = new Database.Commands.DeleteBook(undoPop);
					connectDB();
					cmd.execute(conn);  
					disconnectDB();  
					stackBooks.message = "Undo Operation was successful";
					stackBooks.stackRedo.push(undoPop);
				}
				else
					stackBooks.message = "No previous items to undo";
			}
		}
		catch(Exception ex){
			stackBooks.message = "Error in Undo Operation";
		}
		return stackBooks.message;
	}

	@Override
	public void disconnectDB() throws SQLException {
		try{
			conn.close();
		}
		catch(Exception e){
			//System.out.println(e);
		}
	}
}

