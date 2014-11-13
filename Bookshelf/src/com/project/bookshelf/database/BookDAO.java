/**
 * 
 */
package com.project.bookshelf.database;

import java.util.ArrayList;
import java.util.List;

import com.project.bookshelf.model.Book;
import com.project.bookshelf.model.Category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * BookDAO is a data access object which handles connection to the database and
 * access and modification of data relating to books. Database objects are
 * converted to/from Book objects so that calling code does not need to know the
 * database structure.
 * 
 * To use BookDAO in an activity, create it as an object and pass it the
 * activity's context. First, call the open method to get a handle to the
 * database, then call any of the other methods you wish to interact with the
 * database. Call the close method when you are done to destroy the database
 * handle.
 * 
 * Example usage to retrieve a list of all Books contained in the database:
 * datasource = new BookDAO(this); datasource.open(); List<Book> books =
 * datasource.getAllBooks(); datasource.close();
 * 
 * @author Clay
 * 
 */
public class BookDAO extends BookshelfDBDAO {

	// where clauses for database queries
	private static final String WHERE_ID_EQUALS = MySQLiteHelper.COLUMN_ID
			+ " =?";
	private static final String WHERE_TITLE_EQUALS = MySQLiteHelper.BOOK_TITLE
			+ " =?";
	private static final String WHERE_AUTHOR_EQUALS = MySQLiteHelper.BOOK_AUTHOR
			+ " =?";
	private static final String WHERE_BOOK_ID_EQUALS = MySQLiteHelper.MAPPING_BOOK_ID
			+ " =?";

	// raw query to find all the books a category contains
	private static final String RAW_BOOKS_MAPPED_TO_CATEGORY = "SELECT book."
			+ MySQLiteHelper.COLUMN_ID + ", book."
			+ MySQLiteHelper.BOOK_FILENAME + ", book."
			+ MySQLiteHelper.BOOK_TITLE + ", book."
			+ MySQLiteHelper.BOOK_AUTHOR + ", book."
			+ MySQLiteHelper.BOOK_BOOKMARK + " FROM "
			+ MySQLiteHelper.TABLE_MAPPING + " mapping INNER JOIN "
			+ MySQLiteHelper.TABLE_BOOK + " book ON " + " mapping."
			+ MySQLiteHelper.MAPPING_BOOK_ID + "=book."
			+ MySQLiteHelper.COLUMN_ID + " WHERE mapping."
			+ MySQLiteHelper.MAPPING_CATEGORY_ID + "=?";

	// Database fields
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.BOOK_FILENAME, MySQLiteHelper.BOOK_TITLE,
			MySQLiteHelper.BOOK_AUTHOR, MySQLiteHelper.BOOK_BOOKMARK };

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
	public long insertBook(Book book) {
		ContentValues values = new ContentValues();
		// specify the book's id if it was set, otherwise let it autoincrement
		if (book.getId() != -1) {
			values.put(MySQLiteHelper.COLUMN_ID, book.getId());
		}
		values.put(MySQLiteHelper.BOOK_FILENAME, book.getFileName());
		values.put(MySQLiteHelper.BOOK_TITLE, book.getTitle());
		values.put(MySQLiteHelper.BOOK_AUTHOR, book.getAuthor());
		values.put(MySQLiteHelper.BOOK_BOOKMARK, book.getBookmark());

		return database.insert(MySQLiteHelper.TABLE_BOOK, null, values);
	}

	/**
	 * Updates a book in the table.
	 * 
	 * @param book
	 *            the book to be updated
	 * @return the number of rows updated
	 */
	public long updateBook(Book book) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.BOOK_FILENAME, book.getFileName());
		values.put(MySQLiteHelper.BOOK_TITLE, book.getTitle());
		values.put(MySQLiteHelper.BOOK_AUTHOR, book.getAuthor());
		values.put(MySQLiteHelper.BOOK_BOOKMARK, book.getBookmark());

		long result = database.update(MySQLiteHelper.TABLE_BOOK, values,
				WHERE_ID_EQUALS, new String[] { String.valueOf(book.getId()) });
		Log.d("Update Result:", "=" + result);
		return result;
	}

	/**
	 * Deletes a book from the table and removes its corresponding mappings to
	 * categories.
	 * 
	 * @param book
	 *            the book to be deleted
	 * @return the number of rows deleted
	 */
	public long deleteBook(Book book) {

		// delete mappings to categories for this book
		database.delete(MySQLiteHelper.TABLE_MAPPING, WHERE_BOOK_ID_EQUALS,
				new String[] { book.getId() + "" });

		// delete book
		return database.delete(MySQLiteHelper.TABLE_BOOK, WHERE_ID_EQUALS,
				new String[] { book.getId() + "" });
	}

	/**
	 * Add a Book to a Category by adding an entry into the mapping table.
	 * 
	 * @param book
	 *            the book to be mapped to a category
	 * @param category
	 *            the category to be mapped to a book
	 * @return the ID of the new mapping entry, or -1 if an error occurred
	 */
	public long addBookToCategory(Book book, Category category) {
		ContentValues values = new ContentValues();

		if (book.getId() != -1 && category.getId() != -1) {
			values.put(MySQLiteHelper.MAPPING_BOOK_ID, book.getId());
			values.put(MySQLiteHelper.MAPPING_CATEGORY_ID, category.getId());
		} else {
			return -1;
		}
		return database.insert(MySQLiteHelper.TABLE_MAPPING, null, values);
	}

	/**
	 * Get a list of books by the given author
	 * 
	 * @param author
	 *            the author of the books to get
	 * @return the list of books by the given author
	 */
	public List<Book> getBooksByAuthor(String author) {
		List<Book> books = new ArrayList<Book>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOK, allColumns,
				WHERE_AUTHOR_EQUALS, new String[] { author + "" }, null, null,
				null);

		// fill the list with Books created from rows in the query
		while (cursor.moveToNext()) {
			Book book = cursorToBook(cursor);
			books.add(book);
		}
		cursor.close();
		return books;
	}

	/**
	 * Get a list of books with the given title
	 * 
	 * @param title
	 *            the title of the books to get
	 * @return the list of books with the given title
	 */
	public List<Book> getBooksByTitle(String title) {
		List<Book> books = new ArrayList<Book>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOK, allColumns,
				WHERE_TITLE_EQUALS, new String[] { title + "" }, null, null,
				null);

		// fill the list with Books created from rows in the query
		while (cursor.moveToNext()) {
			Book book = cursorToBook(cursor);
			books.add(book);
		}
		cursor.close();
		return books;
	}

	/**
	 * Get a list of books in the given category
	 * 
	 * @param name
	 *            the name of the category
	 * @return the list of books in the given category
	 */
	public List<Book> getBooksByCategory(Category category) {
		List<Book> books = new ArrayList<Book>();

		Cursor cursor = database.rawQuery(RAW_BOOKS_MAPPED_TO_CATEGORY,
				new String[] { category.getId() + "" });

		// fill the list with Categories created from rows in the query
		while (cursor.moveToNext()) {
			Book book = cursorToBook(cursor);
			books.add(book);
		}
		cursor.close();

		return books;
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

		// fill the list with Books created from rows in the query
		while (cursor.moveToNext()) {
			Book book = cursorToBook(cursor);
			books.add(book);
		}
		cursor.close();

		return books;
	}

	/**
	 * Creates a Book object from the current cursor location
	 * 
	 * @param cursor
	 *            a cursor pointing at a row in the Book table
	 * @return a Book object filled in with the information from the table row
	 */
	private Book cursorToBook(Cursor cursor) {

		long id = cursor.getLong(MySQLiteHelper.COLUMN_ID_INDEX);
		String fileName = cursor.getString(MySQLiteHelper.BOOK_FILENAME_INDEX);
		String title = cursor.getString(MySQLiteHelper.BOOK_TITLE_INDEX);
		String author = cursor.getString(MySQLiteHelper.BOOK_AUTHOR_INDEX);
		long bookmark = cursor.getLong(MySQLiteHelper.BOOK_BOOKMARK_INDEX);

		Book book = new Book(id, fileName, title, author);
		book.setBookmark(bookmark);

		return book;
	}

}
