/**
 * 
 */
package com.project.bookshelf.database;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * BookshelfDBDAO contains methods which maintain the database connection.  It is extended by 
 * the other DAOs and is not called directly.
 * @author Clay
 *
 */
public class BookshelfDBDAO {

	protected SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private Context mContext;
	
	/**
	 * 
	 */
	public BookshelfDBDAO(Context context) {
		this.mContext = context;
		dbHelper = MySQLiteHelper.getHelper(mContext);
		open();
	}
	
	public void open() throws SQLException {
		if (dbHelper == null) {
			dbHelper = MySQLiteHelper.getHelper(mContext);
		}
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
		database = null;
	}
	
}
