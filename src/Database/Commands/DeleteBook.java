package Database.Commands;

import java.sql.PreparedStatement;

import Database.SQLCmd;
import StoreGUI.BookCategory;

/*
 * DeleteBook -> implements command, template and factory method patterns
 */
public class DeleteBook extends SQLCmd {
	
	public BookCategory bkCat = new BookCategory();
	
	public DeleteBook(BookCategory bk){
		bkCat = bk;
	}
	
	@Override
	public void queryDB(){
		try{
				String command = new SQLCommands().getDeleteBooksQuery();   
				PreparedStatement statement = conn.prepareStatement(command); 
				statement.setString(1,bkCat.getBookName());
				statement.setString(2,bkCat.getBookISBN());
				statement.setString(3,bkCat.getBookAuthor());
				statement.setString(4,bkCat.getBookID());
				statement.executeUpdate();
				bkCat.message = "Undo Operation was successful";
			}
		catch (Exception e){
			bkCat.message = "Error In DeleteBook command";	
		}
		
	}
	
	@Override
	public void processResult(){
		result.add(bkCat);
	}
}
