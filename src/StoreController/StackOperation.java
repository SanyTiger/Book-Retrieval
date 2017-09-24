package StoreController;

import java.util.Stack;

import StoreGUI.BookCategory;

public class StackOperation {
	public String message;
	public Stack<BookCategory> stackUndo = new Stack<BookCategory>();
	public Stack<BookCategory> stackRedo = new Stack<BookCategory>();
	
	public Stack<BookCategory> stackUndoShirt = new Stack<BookCategory>();
	public Stack<BookCategory> stackRedoShirt = new Stack<BookCategory>();
}
