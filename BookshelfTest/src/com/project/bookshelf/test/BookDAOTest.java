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
public class BookDAOTest extends AndroidTestCase {

	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;

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
				getContext(), "BookDAOtest_");
		bookDAO = new BookDAO(context);
		bookDAO.open();
		categoryDAO = new CategoryDAO(context);
		categoryDAO.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		// wipe the database clean, probably a better way to do this
		List<Book> books = bookDAO.getAllBooks();
		for (Book book : books) {
			bookDAO.deleteBook(book);
		}
		List<Category> categories = categoryDAO.getAllCategories();
		for (Category category : categories) {
			categoryDAO.deleteCategory(category);
		}
		bookDAO.close();
		bookDAO = null;
		categoryDAO.close();
		categoryDAO = null;
	}

	public void testPreConditions() {
		assertNotNull(bookDAO);
	}

	public void testInsertUpdateDeleteBookWithoutId() {
		Book goodBook = new Book("fileName", "title", "author");

		// try to insert a good book
		long insertedBookId = -1;
		try {
			insertedBookId = bookDAO.insertBook(goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse(
				"insertBook should not return -1 after inserting a good book",
				insertedBookId == -1);
		List<Book> books = bookDAO.getAllBooks();
		assertTrue(
				"The database should contain one book after the first insert",
				books.size() == 1);
		assertEquals(
				"The fileName of the book in the database should match the inserted book",
				books.get(0).getFileName(), goodBook.getFileName());
		assertEquals(
				"The title of the book in the database should match the inserted book",
				books.get(0).getTitle(), goodBook.getTitle());
		assertEquals(
				"The author of the book in the database should match the inserted book",
				books.get(0).getAuthor(), goodBook.getAuthor());
		assertTrue(
				"The bookmark of the book in the database should match the inserted book",
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
		assertTrue(
				"The database should contain one book after the first update",
				books.size() == 1);
		assertEquals(
				"The fileName of the book in the database should match the updated book",
				books.get(0).getFileName(), goodBook.getFileName());
		assertEquals(
				"The title of the book in the database should match the updated book",
				books.get(0).getTitle(), goodBook.getTitle());
		assertEquals(
				"The author of the book in the database should match the updated book",
				books.get(0).getAuthor(), goodBook.getAuthor());
		assertTrue(
				"The bookmarked page of the book in the database should match the updated book",
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
		assertTrue(
				"The database should contain no books after deleting the only book",
				books.size() == 0);
	}
	
	public void testInsertUpdateDeleteBookWithId() {
		long id = 5;
		Book goodBook = new Book(id, "fileName", "title", "author");

		// try to insert a good book
		long insertedBookId = -1;
		try {
			insertedBookId = bookDAO.insertBook(goodBook);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse(
				"insertBook should not return -1 after inserting a good book",
				insertedBookId == -1);
		assertTrue("insertBook should return the correct id", id == insertedBookId);
		List<Book> books = bookDAO.getAllBooks();
		assertTrue(
				"The database should contain one book after the first insert",
				books.size() == 1);
		assertTrue("The id of the book in the database should match the inserted book",
				books.get(0).getId() == goodBook.getId());
		assertEquals(
				"The fileName of the book in the database should match the inserted book",
				books.get(0).getFileName(), goodBook.getFileName());
		assertEquals(
				"The title of the book in the database should match the inserted book",
				books.get(0).getTitle(), goodBook.getTitle());
		assertEquals(
				"The author of the book in the database should match the inserted book",
				books.get(0).getAuthor(), goodBook.getAuthor());
		assertTrue(
				"The bookmark of the book in the database should match the inserted book",
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
		assertTrue(
				"The database should contain one book after the first update",
				books.size() == 1);
		assertTrue("The id of the book in the database should match the inserted book",
				books.get(0).getId() == goodBook.getId());
		assertEquals(
				"The fileName of the book in the database should match the updated book",
				books.get(0).getFileName(), goodBook.getFileName());
		assertEquals(
				"The title of the book in the database should match the updated book",
				books.get(0).getTitle(), goodBook.getTitle());
		assertEquals(
				"The author of the book in the database should match the updated book",
				books.get(0).getAuthor(), goodBook.getAuthor());
		assertTrue(
				"The bookmarked page of the book in the database should match the updated book",
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
		assertTrue(
				"The database should contain no books after deleting the only book",
				books.size() == 0);
	}
	
	

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#addBookToCategory(com.project.bookshelf.model.Book, com.project.bookshelf.model.Category)}
	 * .
	 */
	public void testAddBookToCategoryGetBooksByCategoryWithId() {
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
			id = bookDAO.addBookToCategory(goodBook, goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse(
				"addBookToCategory should return the id of the mapping if successful",
				id == -1);

		// get the book back for the category
		List<Book> books = null;
		try {
			books = bookDAO.getBooksByCategory(goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getBooksByCategory should return a list of books", books);
		assertTrue("getBooksByCategory should return the right number of books, actual numer:" + books.size(), books.size() == 1);
		assertEquals("getBooksByCategory should return the right books",
				goodBook, books.get(0));

	}
	
	public void testAddBookToCategoryGetBooksByCategoryWithoutId() {
		Book goodBook = new Book("fileName", "title", "author");
		Category goodCategory = new Category("name");
		
		// add book and category to database
		try {
			bookDAO.insertBook(goodBook);
			categoryDAO.insertCategory(goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}	
		
		// retrieve the book and category with assigned ids
		List<Book> books = bookDAO.getAllBooks();
		List<Category> categories = categoryDAO.getAllCategories();
		assertTrue("There should be 1 book in the db after 1 insert", books.size() == 1);
		assertTrue("There should be 1 category in the db after 1 insert", categories.size() == 1);
		goodBook = books.get(0);
		goodCategory = categories.get(0);	
		
		// add the mapping between book and category
		long id = -1;
		try {
			id = bookDAO.addBookToCategory(goodBook, goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertFalse(
				"addBookToCategory should return the id of the mapping if successful",
				id == -1);

		// get the book back for the category
		books = null;
		try {
			books = bookDAO.getBooksByCategory(goodCategory);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getBooksByCategory should return a list of books", books);
		assertTrue("getBooksByCategory should return the right number of books, actual numer:" + books.size(), books.size() == 1);
		assertEquals("getBooksByCategory should return the right books",
				goodBook, books.get(0));

	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#getBooksByAuthor(java.lang.String)}
	 * .
	 */
	public void testGetBooksByAuthor() {
		String authorSame = "authorSame";
		String authorDifferent = "authorDifferent";

		Book book1 = new Book("fileName1", "title1", authorDifferent);
		Book book2 = new Book("fileName2", "title2", authorSame);
		Book book3 = new Book("fileName3", "title3", authorSame);

		try {
			bookDAO.insertBook(book1);
			bookDAO.insertBook(book2);
			bookDAO.insertBook(book3);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		List<Book> books = null;
		try {
			books = bookDAO.getBooksByAuthor(authorSame);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getBooksByAuthor should return a list of Books", books);
		assertTrue(
				"getBooksByAuthor should return the correct number of books",
				books.size() == 2);
		assertEquals(
				"getBooksByAuthor should return books with the same author",
				books.get(0).getAuthor(), books.get(1).getAuthor());
		assertEquals(
				"getBooksByAuthor should return books by the correct author",
				books.get(0).getAuthor(), authorSame);
	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#getBooksByTitle(java.lang.String)}
	 * .
	 */
	public void testGetBooksByTitle() {
		String titleSame = "titleSame";
		String titleDifferent = "titleDifferent";

		Book book1 = new Book("fileName1", titleDifferent, "author1");
		Book book2 = new Book("fileName2", titleSame, "author2");
		Book book3 = new Book("fileName3", titleSame, "author3");

		try {
			bookDAO.insertBook(book1);
			bookDAO.insertBook(book2);
			bookDAO.insertBook(book3);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		List<Book> books = null;
		try {
			books = bookDAO.getBooksByTitle(titleSame);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getBooksByTitle should return a list of Books", books);
		assertTrue("getBooksByTitle should return the correct number of books",
				books.size() == 2);
		assertEquals("getBooksByTitle should return books with the same title",
				books.get(0).getTitle(), books.get(1).getTitle());
		assertEquals(
				"getBooksByTitle should return books with the correct title",
				books.get(0).getTitle(), titleSame);
	}

	/**
	 * Test method for
	 * {@link com.project.bookshelf.database.BookDAO#getAllBooks()}.
	 */
	public void testGetAllBooks() {
		Book book1 = new Book("fileName1", "title1", "author1");
		Book book2 = new Book("fileName2", "title2", "author2");
		Book book3 = new Book("fileName3", "title3", "author3");

		try {
			bookDAO.insertBook(book1);
			bookDAO.insertBook(book2);
			bookDAO.insertBook(book3);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		List<Book> books = null;
		try {
			books = bookDAO.getAllBooks();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull("getAllBooks should return a list of Books", books);
		assertTrue("getAllBooks should return the correct number of Books",
				books.size() == 3);
	}

}
