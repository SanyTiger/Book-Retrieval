package StoreGUI;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import StoreController.StoreController;
import StoreController.StackOperation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class LocalStoreGUI {

	private JFrame frmLocalStore;
	private JTextField bookNameTextBox;
	private JTextField isbnTextBox;
	private JTextField authorTextBox;
	private JTextField bookIDTextBox;
	private StoreController bkController;
	BookCategory bkCat = new BookCategory();
	StackOperation stackBooks = new StackOperation();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocalStoreGUI window = new LocalStoreGUI();
					window.frmLocalStore.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LocalStoreGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLocalStore = new JFrame();
		frmLocalStore.setTitle("Local Store");
		frmLocalStore.setBounds(100, 100, 1295, 714);
		frmLocalStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLocalStore.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1273, 658);
		frmLocalStore.getContentPane().add(tabbedPane);
		
		JPanel bookPanel = new JPanel();
		tabbedPane.addTab("Books", null, bookPanel, null);
		bookPanel.setLayout(null);
		
		JTextArea bookTextArea = new JTextArea();
		bookTextArea.setEditable(false);
		bookTextArea.setLineWrap(true);
		bookTextArea.setBounds(132, 328, 1003, 280);
		bookPanel.add(bookTextArea);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOutput.setBounds(133, 294, 122, 30);
		bookPanel.add(lblOutput);
		
		JButton btnBookStore = new JButton("Store");
		btnBookStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bookNameTextBox.getText().isEmpty() || isbnTextBox.getText().isEmpty() || authorTextBox.getText().isEmpty() || bookIDTextBox.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Oops! You missed some fields.\nPlease fill all fields", "Missing Field", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					bkController = new StoreController();
					bkCat.setBookName(bookNameTextBox.getText());
					bkCat.setBookISBN(isbnTextBox.getText());
					bkCat.setBookAuthor(authorTextBox.getText());
					bkCat.setBookID(bookIDTextBox.getText());
					
					try {
						bkCat.message = bkController.store(bkCat, stackBooks);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						bkCat.message = "Error in StoreGUI";
					}
					bookTextArea.setText(bkCat.message);
					bookNameTextBox.setText("");
					isbnTextBox.setText("");
					authorTextBox.setText("");
					bookIDTextBox.setText("");
					bkCat = new BookCategory();
				}
			}
		});
		btnBookStore.setBounds(1138, 16, 115, 29);
		bookPanel.add(btnBookStore);
		
		JButton btnBookRetrieve = new JButton("Retrieve");
		btnBookRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTextArea.setText("");
				if(bookNameTextBox.getText().isEmpty() || isbnTextBox.getText().isEmpty() || authorTextBox.getText().isEmpty() || bookIDTextBox.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Oops! You missed some fields.\nPlease fill all fields", "Missing Field", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					bkController = new StoreController();
					bkCat.setBookName(bookNameTextBox.getText());
					bkCat.setBookISBN(isbnTextBox.getText());
					bkCat.setBookAuthor(authorTextBox.getText());
					bkCat.setBookID(bookIDTextBox.getText());
					
					try {
						bkCat.message = bkController.retrieve(bkCat);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						bkCat.message = "Error in StoreGUI";
					}
					bookTextArea.setText(bkCat.message);
					bookNameTextBox.setText("");
					isbnTextBox.setText("");
					authorTextBox.setText("");
					bookIDTextBox.setText("");
					bkCat = new BookCategory();
				}
			}
		});
		btnBookRetrieve.setBounds(1138, 85, 115, 29);
		bookPanel.add(btnBookRetrieve);
		
		JButton btnBookUndo = new JButton("Undo");
		btnBookUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTextArea.setText("");
				bkController = new StoreController();
				try {
					bkCat.message = bkController.undoPreviousOperation(bkCat, stackBooks);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					bkCat.message = "Error in StoreGUI";
				}
				bookTextArea.setText(stackBooks.message);
			}
		});
		btnBookUndo.setBounds(1138, 157, 115, 29);
		bookPanel.add(btnBookUndo);
		
		JButton btnBookRedo = new JButton("Redo");
		btnBookRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTextArea.setText("");
				bkController = new StoreController();
				try {
					bkCat.message = bkController.redoPreviousOperation(bkCat, stackBooks);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					bkCat.message = "Error in StoreGUI";
				}
				bookTextArea.setText(stackBooks.message);
			}
		});
		btnBookRedo.setBounds(1138, 232, 115, 29);
		bookPanel.add(btnBookRedo);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(132, 20, 100, 20);
		bookPanel.add(lblBookName);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(132, 89, 69, 20);
		bookPanel.add(lblIsbn);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(132, 161, 69, 20);
		bookPanel.add(lblAuthor);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setBounds(132, 232, 69, 20);
		bookPanel.add(lblBookId);
		
		bookNameTextBox = new JTextField();
		bookNameTextBox.setBounds(247, 19, 616, 26);
		bookPanel.add(bookNameTextBox);
		bookNameTextBox.setColumns(10);
		
		isbnTextBox = new JTextField();
		isbnTextBox.setColumns(10);
		isbnTextBox.setBounds(247, 86, 616, 26);
		bookPanel.add(isbnTextBox);
		
		authorTextBox = new JTextField();
		authorTextBox.setColumns(10);
		authorTextBox.setBounds(247, 158, 616, 26);
		bookPanel.add(authorTextBox);
		
		bookIDTextBox = new JTextField();
		bookIDTextBox.setColumns(10);
		bookIDTextBox.setBounds(247, 232, 616, 26);
		bookPanel.add(bookIDTextBox);
	}
}
