/**
 * 
 */
package project.bookshelfnew;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class responsible for creating and upgrading the tables in the database and
 * encapsulates low-level information such as the column and database names.
 * 
 * @author Clay
 * 
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

	// database name and version
	private static final String DATABASE_NAME = "bookshelf.db";
	private static final int DATABASE_VERSION = 1;

	// constants for table names
	public static final String TABLE_BOOK = "book";
	public static final String TABLE_CATEGORY = "category";

	// constants for column names
	public static final String COLUMN_ID = "_id";
	public static final String BOOK_TITLE = "title";
	public static final String BOOK_AUTHOR = "author";
	public static final String BOOK_BOOKMARK = "bookmark";
	public static final String CATEGORY_NAME = "name";

	// constants for column indexes (do we need this?)
	public static final int COLUMN_ID_INDEX = 0;
	public static final int COLUMN_TITLE_INDEX = 1;
	public static final int COLUMN_NAME_INDEX = 1;
	public static final int COLUMN_AUTHOR_INDEX = 2;
	public static final int COLUMN_BOOKMARK_INDEX = 3;

	private static final String CREATE_BOOK_TABLE = "CREATE TABLE "
			+ TABLE_BOOK + "(" + COLUMN_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + BOOK_TITLE
			+ " TEXT NOT NULL, " + BOOK_AUTHOR + " TEXT NOT NULL, "
			+ BOOK_BOOKMARK + " INTEGER);";

	private static final String CREATE_CATEGORY_TABLE = "CREATE TABLE "
			+ TABLE_CATEGORY + "(" + COLUMN_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY_NAME
			+ " TEXT NOT NULL);";

	private static MySQLiteHelper instance;

	/**
	 * Return the instance of MySQLiteHelper, creating it if necessary
	 * 
	 * @param context
	 * @return the instance of MySQLiteHelper
	 */
	public static synchronized MySQLiteHelper getHelper(Context context) {
		if (instance == null) {
			instance = new MySQLiteHelper(context);
		}
		return instance;
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
		db.execSQL(CREATE_BOOK_TABLE);
		db.execSQL(CREATE_CATEGORY_TABLE);
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
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
		onCreate(db);
	}

	private MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

}
