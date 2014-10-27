/**
 * 
 */
package project.bookshelfnew;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * CategoryDAO is a DAO which extends BookshelfDBDAO to implement CRUD
 * operations for Categories.
 * 
 * @author Clay
 * 
 */
public class CategoryDAO extends BookshelfDBDAO {

	// where clauses for database queries
	private static final String WHERE_ID_EQUALS = MySQLiteHelper.COLUMN_ID
			+ " =?";
	private static final String WHERE_CATEGORY_ID_EQUALS = MySQLiteHelper.MAPPING_CATEGORY_ID
			+ " =?";

	// Database fields
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.CATEGORY_NAME };

	/**
	 * @param context
	 */
	public CategoryDAO(Context context) {
		super(context);
	}
	
	/**
	 * Inserts a category into the table.
	 * 
	 * @param category
	 *            the category to be inserted
	 * @return the ID of the newly inserted category, or -1 if an error occurred
	 */
	public long save(Category category) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.CATEGORY_NAME, category.getName());

		return database.insert(MySQLiteHelper.TABLE_CATEGORY, null, values);
	}
	
	/**
	 * Updates a category in the table.
	 * 
	 * @param category
	 *            the category to be updated
	 * @return the number of rows updated
	 */
	public long update(Category category) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.CATEGORY_NAME, category.getName());

		long result = database.update(MySQLiteHelper.TABLE_CATEGORY, values,
				WHERE_ID_EQUALS, new String[] { String.valueOf(category.getId()) });
		Log.d("Update Result:", "=" + result);
		return result;
	}
	
	/**
	 * Deletes a category from the table.
	 * 
	 * @param category
	 *            the category to be deleted
	 * @return the number of rows deleted
	 */
	public int deleteCategory(Category category) {

		// delete mappings to books for this category
		database.delete(MySQLiteHelper.TABLE_MAPPING, WHERE_CATEGORY_ID_EQUALS,
				new String[] { category.getId() + "" });

		// delete category
		return database.delete(MySQLiteHelper.TABLE_CATEGORY, WHERE_ID_EQUALS,
				new String[] { category.getId() + "" });
	}
	
	public List<Category> getCategoriesByBook(Book book) {
		List<Category> categories = new ArrayList<Category>();

		// NYI, query category table for categories mapped to given book in mapping table
		// book.COLUMN_ID occurrences in mapping table give the corresponding category.COLUMN_IDs

		return categories;
	}
	
	/**
	 * Fetches all Categories in the database into a list
	 * 
	 * @return a list of all categories in the database
	 */
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_CATEGORY, allColumns,
				null, null, null, null, null);

		// fill the list with Categories created from rows in the query
		while (cursor.moveToNext()) {
			Category category = cursorToCategory(cursor);
			categories.add(category);
		}
		cursor.close();

		return categories;
	}

	/**
	 * Creates a Category object from the current cursor location
	 * 
	 * @param cursor
	 *            a cursor pointing at a row in the Category table
	 * @return a Category object filled in with the information from the table row
	 */
	private Category cursorToCategory(Cursor cursor) {
		Category category = new Category();
		category.setId(cursor.getLong(MySQLiteHelper.COLUMN_ID_INDEX));
		category.setName(cursor.getString(MySQLiteHelper.CATEGORY_NAME_INDEX));
		return category;
	}

}
