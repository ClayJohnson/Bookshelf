/**
 * 
 */
package com.project.bookshelf.test;

import java.util.List;

import com.project.bookshelf.database.BookDAO;
import com.project.bookshelf.model.Book;
import com.project.bookshelf.model.Category;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

/**
 * @author Clay
 * 
 */
public class BookDAOTest extends AndroidTestCase {

	private BookDAO bookDAO;

	/**
	 * @param name
	 */
	public BookDAOTest() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.test.AndroidTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "test_");
		bookDAO = new BookDAO(context);
		bookDAO.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		bookDAO.close();
		super.tearDown();
	}

	public void testPreConditions() {
		assertNotNull(bookDAO);
	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#insertBook(com.project.bookshelf.model.Book)}
	 * .
	 */
	public void testInsertBook() {
		Book goodBook = new Book();
		goodBook.setAuthor("author");
		goodBook.setFileName("fileName");
		goodBook.setTitle("title");
		goodBook.setBookmark(-1);

		// try insert good book
		long insertedRows = 0;
		try {
			insertedRows = bookDAO.insertBook(goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse("insertBook should return a positive number of inserted rows when successful", 
				insertedRows == 0);
	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#updateBook(com.project.bookshelf.model.Book)}
	 * .
	 */
	public void testUpdateBook() {
		Book goodBook = new Book();
		goodBook.setAuthor("author");
		goodBook.setFileName("fileName");
		goodBook.setTitle("title");
		goodBook.setBookmark(-1);

		// try insert good book
		long updatedRows = 0;
		try {
			updatedRows = bookDAO.updateBook(goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse("updateBook should return a positive number of updated rows when successful", 
				updatedRows == 0);
	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#deleteBook(com.project.bookshelf.model.Book)}
	 * .
	 */
	public void testDeleteBook() {
		Book goodBook = new Book();
		goodBook.setAuthor("author");
		goodBook.setFileName("fileName");
		goodBook.setTitle("title");
		goodBook.setBookmark(-1);

		// try delete good book
		long deletedRows = 0;
		try {
			deletedRows = bookDAO.deleteBook(goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse("deleteBook should return a positve number of deleted rows when successul", 
				deletedRows == 0);
	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#addBookToCategory(com.project.bookshelf.model.Book, com.project.bookshelf.model.Category)}
	 * .
	 */
	public void testAddBookToCategory() {
		Book goodBook = new Book();
		goodBook.setAuthor("author");
		goodBook.setFileName("fileName");
		goodBook.setTitle("title");
		goodBook.setBookmark(-1);

		Category goodCategory = new Category();
		goodCategory.setName("name");

		long id = -1;
		try {
			id = bookDAO.addBookToCategory(goodBook, goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse("addBookToCategory should return the id of the mapping if successful", 
				id == -1);
	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#getBooksByAuthor(java.lang.String)}
	 * .
	 */
	public void testGetBooksByAuthor() {
		String author = "author";

		List<Book> books = null;
		try {
			books = bookDAO.getBooksByAuthor(author);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getBooksByAuthor should return a list of Books when successful", books);
	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#getBooksByTitle(java.lang.String)}
	 * .
	 */
	public void testGetBooksByTitle() {
		String title = "title";

		List<Book> books = null;
		try {
			books = bookDAO.getBooksByTitle(title);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getBooksByTitle should return a list of Books when successful", books);
	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#getBooksByCategory(com.project.bookshelf.model.Category)}
	 * .
	 */
	public void testGetBooksByCategory() {
		Category goodCategory = new Category();
		goodCategory.setName("name");

		List<Book> books = null;
		try {
			books = bookDAO.getBooksByCategory(goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getBooksByCategory should return a list of Books when successful", books);
	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#getAllBooks()}.
	 */
	public void testGetAllBooks() {
		List<Book> books = null;
		try {
			books = bookDAO.getAllBooks();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getAllBooks should return a list of Books when successful", books);
	}

}
