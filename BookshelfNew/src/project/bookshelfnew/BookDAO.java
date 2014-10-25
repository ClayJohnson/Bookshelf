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
import android.util.Log;

/**
 * BookDAO is a DAO which extends BookshelfDBDAO to implement CRUD operations.
 * This essentially is the BookLibrary from the class diagram and handles
 * high-level operations on Books in the database.
 * 
 * @author Clay
 * 
 */
public class BookDAO extends BookshelfDBDAO {

	private static final String WHERE_ID_EQUALS = MySQLiteHelper.COLUMN_ID
			+ " =?";

	// Database fields
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.BOOK_TITLE, MySQLiteHelper.BOOK_AUTHOR,
			MySQLiteHelper.BOOK_BOOKMARK };

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public BookDAO(Context context) {
		super(context);
	}

	/**
	 * Inserts a book into the table.
	 * 
	 * @param book
	 *            the book to be inserted
	 * @return the ID of the newly inserted book, or -1 if an error occurred
	 */
	public long save(Book book) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.BOOK_TITLE, book.getTitle());
		values.put(MySQLiteHelper.BOOK_AUTHOR, book.getAuthor());

		return database.insert(MySQLiteHelper.TABLE_BOOK, null, values);
	}

	/**
	 * Updates a book in the table.
	 * 
	 * @param book
	 *            the book to be updated
	 * @return the number of rows updated
	 */
	public long update(Book book) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.BOOK_TITLE, book.getTitle());
		values.put(MySQLiteHelper.BOOK_AUTHOR, book.getAuthor());

		long result = database.update(MySQLiteHelper.TABLE_BOOK, values,
				WHERE_ID_EQUALS, new String[] { String.valueOf(book.getId()) });
		Log.d("Update Result:", "=" + result);
		return result;
	}

	/**
	 * Deletes a book from the table.
	 * 
	 * @param book
	 *            the book to be deleted
	 * @return the number of rows deleted
	 */
	public int deleteBook(Book book) {

		// NYI DELETE MAPPINGS IN MAPPING TABLE FOR THIS BOOK

		return database.delete(MySQLiteHelper.TABLE_BOOK, WHERE_ID_EQUALS,
				new String[] { book.getId() + "" });
	}

	/**
	 * Fetches all Books in the database into a list
	 * 
	 * @return a list of all books in the database
	 */
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOK, allColumns,
				null, null, null, null, null);

		while (cursor.moveToNext()) {
			Book book = new Book();
			book.setId(cursor.getLong(MySQLiteHelper.COLUMN_ID_INDEX));
			book.setTitle(cursor.getString(MySQLiteHelper.COLUMN_TITLE_INDEX));
			book.setAuthor(cursor.getString(MySQLiteHelper.COLUMN_AUTHOR_INDEX));
			book.setBookmark(cursor
					.getLong(MySQLiteHelper.COLUMN_BOOKMARK_INDEX));
			books.add(book);
		}
		cursor.close();

		return books;
	}

}
