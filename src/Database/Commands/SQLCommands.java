package Database.Commands;

public class SQLCommands {
	
	public String InsertBooks;
	public String DeleteBooks;
	public String SelectBooks;
	
	public String getInsertBooksQuery(){
		return "INSERT INTO Books (Name, ISBN, Author, ID) values(?,?,?,?)";
	}
	public String getDeleteBooksQuery(){
		return "DELETE FROM Books WHERE NAME = ? AND ISBN = ? AND AUTHOR = ? AND ID = ? LIMIT 1";
	}
	public String getSelectBookQuery(){
		return "SELECT * FROM BOOKS WHERE NAME = ? AND ISBN = ? AND AUTHOR = ? AND ID = ?";
	}
}
