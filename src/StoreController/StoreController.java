package StoreController;

import java.sql.SQLException;

import Database.DBMgr;
import StoreGUI.BookCategory;
import StoreGUI.Category;

/*
 * StoreController -> implements controller pattern
 */
public class StoreController {
	
	private String message = "Something went wrong in Book controller. Please check.";
	DBMgr db = new DBMgr();
	
	public StoreController(){}

	public String store(Category category, StackOperation stackBooks) throws SQLException{
		if(category instanceof BookCategory)
			return db.store((BookCategory)category, stackBooks);
		return this.message;
	}
	public String retrieve(Category category) throws SQLException{
		if(category instanceof BookCategory)
			return db.retreieve((BookCategory)category);
		return this.message;		
	}
	public String redoPreviousOperation(Category category, StackOperation stackBooks) throws SQLException{
		if(category instanceof BookCategory)
			return db.redoPreviousOperation((BookCategory)category, stackBooks);
		return this.message;
	}

	public String undoPreviousOperation(Category category, StackOperation stackBooks) throws SQLException{
		if(category instanceof BookCategory)
			return db.undoPreviousOperation((BookCategory)category, stackBooks);
		return this.message;		
	}
}
