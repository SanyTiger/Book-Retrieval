package Database.Commands;

import java.sql.PreparedStatement;

import Database.SQLCmd;
import StoreGUI.BookCategory;

/*
 * RetrieveBook -> implements command, template and factory method patterns
 */
public class RetrieveBook  extends SQLCmd {
	
	public BookCategory bkCat = new BookCategory();
	
	public RetrieveBook(BookCategory bk){
		bkCat = bk;
	}
	
	@Override
	public void queryDB(){
		try{
			String command = new SQLCommands().getSelectBookQuery();  
				PreparedStatement statement = conn.prepareStatement(command); 
				statement.setString(1,bkCat.getBookName());
				statement.setString(2,bkCat.getBookISBN());
				statement.setString(3,bkCat.getBookAuthor());
				statement.setString(4,bkCat.getBookID());
				res = statement.executeQuery();
				int i = 0;
				 while (res.next()){
					 selectStatement += "Result : [" + (++i) + "]";
					 selectStatement += "\n Name: 	" + res.getString("NAME");
					 selectStatement += "\n ISBN: 	" + res.getString("ISBN");
					 selectStatement += "\n Author: 	" + res.getString("AUTHOR");
					 selectStatement += "\n ID: 	" + res.getString("ID") + "\n";
				 }
				bkCat.message = selectStatement;
			}
		catch (Exception e){
			bkCat.message = "Error In RetrieveBook command";	
		}
		
	}
	
	@Override
	public void processResult(){
		result.add(bkCat);
	}
}
