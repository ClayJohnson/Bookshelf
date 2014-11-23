/**
 * 
 */
package com.example.bookshelf_initialpage.test;

import com.example.bookshelf_initialpage.database.Book;

import junit.framework.TestCase;

/**
 * @author Clay
 *
 */
public class BookTest extends TestCase {

	/**
	 * @param name
	 */
	public BookTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.project.bookshelf.model.Book#Book(long, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	public void testBookShouldConstructWithId() {
		long id = 1;
		String fileName = "file";
		String title = "title";
		String author = "author";
		
		Book book = new Book(id,fileName,title,author);
		assertNotNull("a Book should be able to be constructed with an id");
		assertTrue("A book constructed with id should set it properly",book.getId() == id);
		assertEquals("A book constructed with fileName should set it properly", book.getFileName(), fileName);
		assertEquals("A book constructed with title should set it properly", book.getTitle(), title);
		assertEquals("A book constructed with author should set it properly", book.getAuthor(),author);
		assertTrue("A book should be constructed with the default bookmark value of -1", book.getBookmark() == -1);
	}

	/**
	 * Test method for {@link com.project.bookshelf.model.Book#Book(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	public void testBookShouldConstructWithoutId() {
		String fileName = "file";
		String title = "title";
		String author = "author";
		
		Book book = new Book(fileName,title,author);
		assertNotNull("a Book should be able to be constructed without an id");
		assertEquals("A book constructed with fileName should set it properly", book.getFileName(), fileName);
		assertEquals("A book constructed with title should set it properly", book.getTitle(), title);
		assertEquals("A book constructed with author should set it properly", book.getAuthor(),author);
		assertTrue("A book should be constructed with the default bookmark value of -1", book.getBookmark() == -1);
	}

}
