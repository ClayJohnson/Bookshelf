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
		bookDAO = null;
		super.tearDown();
	}

	public void testPreConditions() {
		assertNotNull(bookDAO);
	}

	public void testInsertUpdateDeleteBook() {
		Book goodBook = new Book("fileName", "title", "author");
		
		// try to insert a good book
		long insertedBookId = -1;
		try {
			insertedBookId = bookDAO.insertBook(goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse("insertBook should not return -1 after inserting a good book", 
				insertedBookId == -1);		
		List<Book> books = bookDAO.getAllBooks();
		assertTrue("The database should contain one book after the first insert", books.size() == 1);
		assertEquals("The fileName of the book in the database should match the inserted book", 
				books.get(0).getFileName(), goodBook.getFileName());
		assertEquals("The title of the book in the database should match the inserted book", 
				books.get(0).getTitle(), goodBook.getTitle());
		assertEquals("The author of the book in the database should match the inserted book", 
				books.get(0).getAuthor(), goodBook.getAuthor());
		assertTrue("The bookmark of the book in the database should match the inserted book", 
				books.get(0).getBookmark() == goodBook.getBookmark());
			
		// try to update the book
		goodBook = books.get(0);
		goodBook.setFileName("newFileName");
		goodBook.setTitle("newTitle");
		goodBook.setAuthor("newAuthor");
		goodBook.setBookmark(5);
		long updatedRows = 0;
		try {
			updatedRows = bookDAO.updateBook(goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertTrue("updateBook should return 1 after updating 1 row", 
				updatedRows == 1);
		books = bookDAO.getAllBooks();
		assertTrue("The database should contain one book after the first update", 
				books.size() == 1);
		assertEquals("The fileName of the book in the database should match the updated book", 
				books.get(0).getFileName(), goodBook.getFileName());
		assertEquals("The title of the book in the database should match the updated book", 
				books.get(0).getTitle(), goodBook.getTitle());
		assertEquals("The author of the book in the database should match the updated book", 
				books.get(0).getAuthor(), goodBook.getAuthor());
		assertTrue("The bookmarked page of the book in the database should match the updated book", 
				books.get(0).getBookmark() == goodBook.getBookmark());
		goodBook = books.get(0);
		
		// try to delete the book		
		long deletedRows = 0;
		try {
			deletedRows = bookDAO.deleteBook(goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertTrue("deleteBook should return 1 after deleting 1 book",
				deletedRows == 1);
		books = bookDAO.getAllBooks();
		assertTrue("The database should contain no books after deleting the only book", 
				books.size() == 0);
	}


	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#addBookToCategory(com.project.bookshelf.model.Book, com.project.bookshelf.model.Category)}
	 * .
	 */
	public void testAddBookToCategory() {
		Book goodBook = new Book("fileName", "title", "author");
		Category goodCategory = new Category("name");

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
		
		fail("NYI");
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
		
		fail("NYI");
	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#getBooksByCategory(com.project.bookshelf.model.Category)}
	 * .
	 */
	public void testGetBooksByCategory() {
		Category goodCategory = new Category("name");

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
