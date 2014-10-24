/**
 * 
 */
package project.bookshelfnew;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class responsible for creating and upgrading the Books table in the database
 * and encapsulates low-level information such as the column and database names.
 * 
 * @author Clay
 * 
 */
public class MySQLiteHelperBook extends SQLiteOpenHelper {

	// constants for column names and indexes
	public static final String TABLE_BOOKS = "books";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_AUTHOR = "author";
	public static final String COLUMN_BOOKMARK = "bookmark";
	public static final int COLUMN_ID_INDEX = 0;
	public static final int COLUMN_TITLE_INDEX = 1;
	public static final int COLUMN_AUTHOR_INDEX = 2;
	public static final int COLUMN_BOOKMARK_INDEX = 3;

	// database name and version that contains the books table
	private static final String DATABASE_NAME = "bookshelf.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table " + TABLE_BOOKS
			+ "(" + COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_TITLE + " text not null, " + COLUMN_AUTHOR
			+ " text not null, " + COLUMN_BOOKMARK + " integer);";

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public MySQLiteHelperBook(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelperBook.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
		onCreate(db);
	}

}
