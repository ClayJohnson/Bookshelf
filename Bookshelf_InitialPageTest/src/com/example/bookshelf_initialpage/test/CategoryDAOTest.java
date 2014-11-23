/**
 * 
 */
package com.example.bookshelf_initialpage.test;

import java.util.List;

import com.example.bookshelf_initialpage.database.Book;
import com.example.bookshelf_initialpage.database.BookDAO;
import com.example.bookshelf_initialpage.database.Category;
import com.example.bookshelf_initialpage.database.CategoryDAO;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

/**
 * @author Clay
 *
 */
public class CategoryDAOTest extends AndroidTestCase {

	private CategoryDAO categoryDAO;
	private BookDAO bookDAO;
	
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
				getContext(), "CategoryDAOtest_");
		categoryDAO = new CategoryDAO(context);
		categoryDAO.open();
		bookDAO = new BookDAO(context);
		bookDAO.open();
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {		
		super.tearDown();
		// wipe the database clean
		List<Category> categories = categoryDAO.getAllCategories();
		for (Category category : categories) {
			categoryDAO.deleteCategory(category);
		}	
		List<Book> books = bookDAO.getAllBooks();
		for (Book book : books) {
			bookDAO.deleteBook(book);
		}
		categoryDAO.close();
		categoryDAO = null;
		bookDAO.close();
		bookDAO = null;
	}

	public void testPreconditions() {
		assertNotNull(categoryDAO);
	}
	
	public void testInsertUpdateDeleteCategoryWithoutId() {
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
	
	public void testInsertUpdateDeleteCategoryWithId() {
		long id = 5;
		Category goodCategory = new Category(id, "name");
		
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
		assertTrue("insertCategory should return the correct id, returned id: " 
				+ insertedCategoryId + ", original id: " + id,insertedCategoryId == id);
		List<Category> categories = categoryDAO.getAllCategories();
		assertTrue("The database should contain one category after the first insert", categories.size() == 1);
		assertEquals("The name of the category in the database should match the inserted category", 
				categories.get(0).getName(), goodCategory.getName());
		assertEquals("The id of the category in the database should match the inserted category",
				categories.get(0).getId(), goodCategory.getId());
		
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
	public void testAddCategoryForBookGetCategoriesByBookWithId() {
		Book goodBook = new Book(1, "fileName", "title", "author");
		Category goodCategory = new Category(2, "name");

		// add book and category to database
		try {
			bookDAO.insertBook(goodBook);
			categoryDAO.insertCategory(goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		// add the mapping between book and category
		long id = -1;
		try {
			id = categoryDAO.addCategoryForBook(goodCategory, goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse("addCategoryForBook should return the id of the mapping if successful", 
				id == -1);
		
		// get the category back for the book
		List<Category> categories = null;
		try {
			categories = categoryDAO.getCategoriesByBook(goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getCategoriesByBook should return a list of Categories", categories);
		assertTrue("getCategoriesByBook should return the right number of categories, actual number:" 
		   + categories.size(), categories.size() == 1);
		assertEquals("getCategoriesByBook should return the right categories", goodCategory, categories.get(0));
	}

	/**
	 * Test method for {@link com.project.bookshelf.database.CategoryDAO#getAllCategories()}.
	 */
	public void testGetAllCategories() {
		Category category1 = new Category("name1");
		Category category2 = new Category("name2");
		Category category3 = new Category("name3");
		
		try {
			categoryDAO.insertCategory(category1);
			categoryDAO.insertCategory(category2);
			categoryDAO.insertCategory(category3);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		List<Category> categories = null;
		try {
			categories = categoryDAO.getAllCategories();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getAllCategories should return a list of Categories", categories);
		assertTrue("getAllCategories should return the correct number of Categories",
				categories.size() == 3);
	}

}
