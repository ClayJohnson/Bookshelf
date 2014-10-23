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
 * books and fetching all books.  This essentially is the BookLibrary from the class diagram.
 * @author Clay
 *
 */
public class BooksDataSource {
	
	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelperBook dbHelper;
	private String[] allColumns = { MySQLiteHelperBook.COLUMN_ID,
			MySQLiteHelperBook.COLUMN_TITLE, MySQLiteHelperBook.COLUMN_AUTHOR,
			MySQLiteHelperBook.COLUMN_BOOKMARK};

	/**
	 * Constructor
	 */
	public BooksDataSource(Context context) {
		dbHelper = new MySQLiteHelperBook(context);
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
		values.put(MySQLiteHelperBook.COLUMN_TITLE, title);
		values.put(MySQLiteHelperBook.COLUMN_AUTHOR, author);
		long insertId = database.insert(MySQLiteHelperBook.TABLE_BOOKS, null, values);
		Cursor cursor = database.query(MySQLiteHelperBook.TABLE_BOOKS, allColumns, 
				MySQLiteHelperBook.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Book newBook = cursorToBook(cursor);
		cursor.close();
		return newBook;  // NYI
	}
	
	/**
	 * Deletes a Book from the database
	 * @param book
	 */
	public void deleteBook(Book book) {
		long id = book.getId();
		database.delete(MySQLiteHelperBook.TABLE_BOOKS, MySQLiteHelperBook.COLUMN_ID + " = " + id, null);
		System.out.println("Book deleted with id: " + id);
	}
	
	/**
	 * Fetches all Books in the database into a list
	 * @return a list of all books in the database
	 */
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		
		Cursor cursor = database.query(MySQLiteHelperBook.TABLE_BOOKS, allColumns, null, null, null, null, null);
		
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
		book.setId(cursor.getLong(MySQLiteHelperBook.COLUMN_ID_INDEX));
		book.setTitle(cursor.getString(MySQLiteHelperBook.COLUMN_TITLE_INDEX));
		book.setAuthor(cursor.getString(MySQLiteHelperBook.COLUMN_AUTHOR_INDEX));
		book.setBookmark(cursor.getLong(MySQLiteHelperBook.COLUMN_BOOKMARK_INDEX));
		return book;
	}

}
