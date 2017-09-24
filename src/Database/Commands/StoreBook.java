package Database.Commands;

import java.sql.PreparedStatement;

import Database.SQLCmd;
import StoreGUI.BookCategory;

/*
 * StoreBook -> implements command, template and factory method patterns 
 */
public class StoreBook extends SQLCmd{
	
	public BookCategory bkCat = new BookCategory();
	
	public StoreBook(BookCategory bk){
		bkCat = bk;
	}
	
	@Override
	public void queryDB(){
		try{
				String command = new SQLCommands().getInsertBooksQuery();   
				PreparedStatement statement = conn.prepareStatement(command); 
				statement.setString(1,bkCat.getBookName());
				statement.setString(2,bkCat.getBookISBN());
				statement.setString(3,bkCat.getBookAuthor());
				statement.setString(4,bkCat.getBookID());
				statement.executeUpdate();
				bkCat.message = "Book Stored Successfully";
			}
		catch (Exception e){
			bkCat.message = "Error In StoreBook command";	
		}
		
	}
	
	@Override
	public void processResult(){
		result.add(bkCat);
	}
}
