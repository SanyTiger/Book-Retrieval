package Database;

import java.sql.SQLException;

import StoreController.StackOperation;
import StoreGUI.BookCategory;
import StoreGUI.Category;

/*
 * DBInterface -> implements bridge pattern
 */
public interface DBInterface {

	public String store(Category category, StackOperation stackBooks) throws SQLException;

	public String retrieve(Category category) throws SQLException;	
	
	public String redoPreviousOperation(Category category, StackOperation stackBooks) throws SQLException;	
	
	public String undoPreviousOperation(Category category, StackOperation stackBooks) throws SQLException;
	
	public void connectDB() throws SQLException;
	
	public void disconnectDB() throws SQLException;
}
