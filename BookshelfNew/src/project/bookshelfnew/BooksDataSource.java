/**
 * 
 */
package project.bookshelfnew;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * BooksDataSource is the DAO which maintains the database connection and supports adding new
 * books and fetching all books.  This essentially is the BookLibrary from the class diagram and handles 
 * high-level operations on Books in the database.
 * @author Clay
 *
 */
public class BooksDataSource {
	
	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.BOOK_TITLE, MySQLiteHelper.BOOK_AUTHOR,
			MySQLiteHelper.BOOK_BOOKMARK};

	/**
	 * Constructor
	 * @param context
	 */
	public BooksDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	/**
	 * Opens the database
	 * @throws SQLException
	 */
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	/**
	 * Closes the database
	 */
	public void close() {
		dbHelper.close();
	}
	
	/**
	 * Creates a Book object, inserts it into the database, and returns the new Book object
	 * NOTE: This will need to change once we discover how to store books
	 * @return the newly created and inserted Book object
	 */
	public Book createBook(String title, String author) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.BOOK_TITLE, title);
		values.put(MySQLiteHelper.BOOK_AUTHOR, author);
		long insertId = database.insert(MySQLiteHelper.TABLE_BOOK, null, values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOK, allColumns, 
				MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Book newBook = cursorToBook(cursor);
		cursor.close();
		return newBook;
	}
	
	/**
	 * Deletes a Book from the database
	 * @param book the book to be deleted
	 */
	public void deleteBook(Book book) {
		long id = book.getId();
		database.delete(MySQLiteHelper.TABLE_BOOK, MySQLiteHelper.COLUMN_ID + " = " + id, null);
		System.out.println("Book deleted with id: " + id);
	}
	
	/**
	 * Fetches all Books in the database into a list
	 * @return a list of all books in the database
	 */
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOK, allColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Book book = cursorToBook(cursor);
			books.add(book);
			cursor.moveToNext();
		}
		
		cursor.close();
		return books;
	}
	
	/**
	 * Returns the Book object pointed at by the cursor.
	 * @param cursor the cursor pointing at the Book table in the database
	 * @return the Book pointed at by the cursor
	 */
	private Book cursorToBook(Cursor cursor) {
		Book book = new Book();
		book.setId(cursor.getLong(MySQLiteHelper.COLUMN_ID_INDEX));
		book.setTitle(cursor.getString(MySQLiteHelper.COLUMN_TITLE_INDEX));
		book.setAuthor(cursor.getString(MySQLiteHelper.COLUMN_AUTHOR_INDEX));
		book.setBookmark(cursor.getLong(MySQLiteHelper.COLUMN_BOOKMARK_INDEX));
		return book;
	}

}
