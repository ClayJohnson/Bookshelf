/**
 * 
 */
package com.project.bookshelf.test;

import java.util.List;

import com.project.bookshelf.database.BookDAO;
import com.project.bookshelf.database.CategoryDAO;
import com.project.bookshelf.model.Book;
import com.project.bookshelf.model.Category;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

/**
 * @author Clay
 *
 */
public class CategoryDAOTest extends AndroidTestCase {

	private CategoryDAO categoryDAO;
	
	/**
	 * @param name
	 */
	public CategoryDAOTest() {
		super();
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "test_");
		categoryDAO = new CategoryDAO(context);
		categoryDAO.open();
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		categoryDAO.close();
		super.tearDown();
	}

	public void testPreconditions() {
		assertNotNull(categoryDAO);
	}

	/**
	 * Test method for {@link com.project.bookshelf.database.CategoryDAO#insertCategory(com.project.bookshelf.model.Category)}.
	 */
	public void testInsertCategory() {
		Category goodCategory = new Category();
		goodCategory.setName("name");

		// try insert good category
		long insertedRows = 0;
		try {
			insertedRows = categoryDAO.insertCategory(goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertTrue("insertBook should return a positive number of inserted rows when successful", 
				insertedRows > 0);
	}

	/**
	 * Test method for {@link com.project.bookshelf.database.CategoryDAO#updateCategory(com.project.bookshelf.model.Category)}.
	 */
	public void testUpdateCategory() {
		Category goodCategory = new Category();
		goodCategory.setName("name");

		// try update good category
		long updatedRows = 0;
		try {
			updatedRows = categoryDAO.updateCategory(goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertTrue("updateCategory should return a positive number of updated rows when successful", 
				updatedRows > 0);
	}

	/**
	 * Test method for {@link com.project.bookshelf.database.CategoryDAO#deleteCategory(com.project.bookshelf.model.Category)}.
	 */
	public void testDeleteCategory() {
		Category goodCategory = new Category();
		goodCategory.setName("name");

		// try delete good category
		long deletedRows = 0;
		try {
			deletedRows = categoryDAO.deleteCategory(goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertTrue("deleteCategory should return a positive number of deleted rows when successful", 
				deletedRows > 0);
	}

	/**
	 * Test method for {@link com.project.bookshelf.database.CategoryDAO#addCategoryForBook(com.project.bookshelf.model.Category, com.project.bookshelf.model.Book)}.
	 */
	public void testAddCategoryForBook() {
		Book goodBook = new Book();
		goodBook.setAuthor("author");
		goodBook.setFileName("fileName");
		goodBook.setTitle("title");
		goodBook.setBookmark(-1);

		Category goodCategory = new Category();
		goodCategory.setName("name");

		long id = -1;
		try {
			id = categoryDAO.addCategoryForBook(goodCategory, goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse("addCategoryForBook should return the id of the mapping if successful", 
				id == -1);
	}

	/**
	 * Test method for {@link com.project.bookshelf.database.CategoryDAO#getCategoriesByBook(com.project.bookshelf.model.Book)}.
	 */
	public void testGetCategoriesByBook() {
		Book goodBook = new Book();
		goodBook.setAuthor("author");
		goodBook.setFileName("fileName");
		goodBook.setTitle("title");
		goodBook.setBookmark(-1);

		List<Category> categories = null;
		try {
			categories = categoryDAO.getCategoriesByBook(goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getCategoriesByBook should return a list of Categories", categories);
	}

	/**
	 * Test method for {@link com.project.bookshelf.database.CategoryDAO#getAllCategories()}.
	 */
	public void testGetAllCategories() {
		List<Category> categories = null;
		try {
			categories = categoryDAO.getAllCategories();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getAllCategories should return a list of Categories", categories);
	}

}
