/**
 * 
 */
package com.project.bookshelf.test;

import java.util.Iterator;
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
		// wipe the database clean
//		List<Category> categories = categoryDAO.getAllCategories();
//		for (Category category : categories) {
//			categoryDAO.deleteCategory(category);
//		}		
		categoryDAO.close();
		categoryDAO = null;
		super.tearDown();
	}

	public void testPreconditions() {
		assertNotNull(categoryDAO);
	}
	
	public void testInsertUpdateDeleteCategory() {
		Category goodCategory = new Category("name");
		
		// try to insert a good category
		long insertedCategoryId = -1;
		try {
			insertedCategoryId = categoryDAO.insertCategory(goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse("insertCategory should not return -1 after inserting a good category", 
				insertedCategoryId == -1);	
		List<Category> categories = categoryDAO.getAllCategories();
		assertTrue("The database should contain one category after the first insert", categories.size() == 1);
		assertEquals("The name of the category in the database should match the inserted category", 
				categories.get(0).getName(), goodCategory.getName());
		
		// try to update the category
		goodCategory = categories.get(0);
		goodCategory.setName("newName");
		long updatedRows = 0;
		try {
			updatedRows = categoryDAO.updateCategory(goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertTrue("updateCategory should return 1 after updating 1 row", 
				updatedRows == 1);
		categories = categoryDAO.getAllCategories();
		assertTrue("The database should contain one category after the first update", 
				categories.size() == 1);
		assertEquals("The name of the category in the database should match the updated category", 
				categories.get(0).getName(), goodCategory.getName());
		
		// try to delete the category
		goodCategory = categories.get(0);
		long deletedRows = 0;
		try {
			deletedRows = categoryDAO.deleteCategory(goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertTrue("deleteCategory should return 1 after deleting 1 row",
				deletedRows == 1);
		categories = categoryDAO.getAllCategories();
		assertTrue("The database should contain no categories after deleting the only category", 
				categories.size() == 0);
	}

	/**
	 * Test method for {@link com.project.bookshelf.database.CategoryDAO#addCategoryForBook(com.project.bookshelf.model.Category, com.project.bookshelf.model.Book)}.
	 */
	public void testAddCategoryForBook() {
		Book goodBook = new Book("fileName", "title", "author");
		Category goodCategory = new Category("name");

		long id = -1;
		try {
			id = categoryDAO.addCategoryForBook(goodCategory, goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse("addCategoryForBook should return the id of the mapping if successful", 
				id == -1);
		
		fail("not yet implemented");
	}

	/**
	 * Test method for {@link com.project.bookshelf.database.CategoryDAO#getCategoriesByBook(com.project.bookshelf.model.Book)}.
	 */
	public void testGetCategoriesByBook() {
		Book goodBook = new Book("fileName", "title", "author");

		List<Category> categories = null;
		try {
			categories = categoryDAO.getCategoriesByBook(goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getCategoriesByBook should return a list of Categories", categories);
		
		fail("not yet implemented");
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
		
		fail("not yet implemented");
	}

}
