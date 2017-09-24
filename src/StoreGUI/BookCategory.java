package StoreGUI;

import java.util.Stack;

/*
 * BookCategory -> implements template pattern
 */
public class BookCategory extends Category {

	public String message = "";
	
	public String getBookName(){
		return this.Category1;
	}
	public void setBookName(String bookName){
		this.Category1 = bookName;
	}
	
	public String getBookISBN(){
		return this.Category2;
	}
	public void setBookISBN(String bookISBN){
		this.Category2 = bookISBN;
	}
	
	public String getBookAuthor(){
		return this.Category3;
	}
	public void setBookAuthor(String bookAuthor){
		this.Category3 = bookAuthor;
	}
	
	public String getBookID(){
		return this.Category4;
	}
	public void setBookID(String bookID){
		this.Category4 = bookID;
	}
}
