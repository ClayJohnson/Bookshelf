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
 * Class responsible for creating and upgrading the Categories table in the
 * database and encapsulates low-level information such as the column and
 * database names.
 * 
 * @author Clay
 * 
 */
public class MySQLiteHelperCategory extends SQLiteOpenHelper {

	// constants for column names and indexes
	public static final String TABLE_CATEGORIES = "categories";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final int COLUMN_ID_INDEX = 0;
	public static final int COLUMN_NAME_INDEX = 1;

	// database name and version that contains the categories table
	private static final String DATABASE_NAME = "bookshelf.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_CATEGORIES + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not null);";

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public MySQLiteHelperCategory(Context context) {
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
		Log.w(MySQLiteHelperCategory.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
		onCreate(db);
	}

}