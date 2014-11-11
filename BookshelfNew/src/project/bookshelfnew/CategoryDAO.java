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

	// raw query to find all the categories a book belongs to
	private static final String RAW_CATEGORIES_MAPPED_TO_BOOK = "SELECT "
			+ MySQLiteHelper.COLUMN_ID + "," + MySQLiteHelper.CATEGORY_NAME
			+ " FROM " + MySQLiteHelper.TABLE_MAPPING + " m INNER JOIN "
			+ MySQLiteHelper.TABLE_CATEGORY + " c ON " + " m."
			+ MySQLiteHelper.MAPPING_CATEGORY_ID + "=c."
			+ MySQLiteHelper.COLUMN_ID + " WHERE m."
			+ MySQLiteHelper.MAPPING_BOOK_ID + "=?";

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
				WHERE_ID_EQUALS,
				new String[] { String.valueOf(category.getId()) });
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

	/**
	 * Add a Category for a Book by adding an entry into the mapping table.
	 * @param category the category to be mapped to a book
	 * @param book the book to be mapped to a category
	 * @return the ID of the new mapping entry, or -1 if an error occurred
	 */
	public long addCategoryForBook(Category category, Book book) {
		ContentValues values = new ContentValues();	
		values.put(MySQLiteHelper.MAPPING_CATEGORY_ID, category.getId());
		values.put(MySQLiteHelper.MAPPING_BOOK_ID, book.getId());
		
		return database.insert(MySQLiteHelper.TABLE_MAPPING, null, values);
	}
	
	/**
	 * Fetches all Categories a Book belongs to
	 * 
	 * @param book
	 *            the Book to fetch the categories for
	 * @return a list of all Categories the Book belongs to
	 */
	public List<Category> getCategoriesByBook(Book book) {
		List<Category> categories = new ArrayList<Category>();

		Cursor cursor = database.rawQuery(RAW_CATEGORIES_MAPPED_TO_BOOK,
				new String[] { book.getId() + "" });

		// fill the list with Categories created from rows in the query
		while (cursor.moveToNext()) {
			Category category = cursorToCategory(cursor);
			categories.add(category);
		}
		cursor.close();

		return categories;
	}

	/**
	 * Fetches all Categories in the database into a list
	 * 
	 * @return a list of all categories in the database
	 */
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_CATEGORY,
				allColumns, null, null, null, null, null);

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
	 * @return a Category object filled in with the information from the table
	 *         row
	 */
	private Category cursorToCategory(Cursor cursor) {
		Category category = new Category();
		category.setId(cursor.getLong(MySQLiteHelper.COLUMN_ID_INDEX));
		category.setName(cursor.getString(MySQLiteHelper.CATEGORY_NAME_INDEX));
		return category;
	}

}
