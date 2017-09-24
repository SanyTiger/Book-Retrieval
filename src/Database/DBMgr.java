package Database;

import java.sql.SQLException;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import StoreController.StackOperation;
import StoreGUI.BookCategory;
import StoreGUI.Category;

/*
 * DBMgr -> implements bridge pattern
 */
public class DBMgr {
	
	private DBInterface imp = new MySQLDB();
	private String message = "Something went wrong in Database controller. Please check.";
	
	public String store(Category category, StackOperation stackBooks) throws SQLException{
		if(category instanceof BookCategory)
			return imp.store((BookCategory)category, stackBooks);
		return this.message;
	}
	public String retreieve(Category category) throws SQLException{
		if(category instanceof BookCategory)
			return imp.retrieve((BookCategory)category);
		return this.message;		
	}
	public String redoPreviousOperation(Category category, StackOperation stackBooks) throws SQLException{
		if(category instanceof BookCategory)
			return imp.redoPreviousOperation((BookCategory)category, stackBooks);
		return this.message;
	}

	public String undoPreviousOperation(Category category, StackOperation stackBooks) throws SQLException{
		if(category instanceof BookCategory)
			return imp.undoPreviousOperation((BookCategory)category, stackBooks);
		return this.message;		
	}
}
