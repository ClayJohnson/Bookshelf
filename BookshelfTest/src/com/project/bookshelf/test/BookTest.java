/**
 * 
 */
package com.project.bookshelf.test;

import com.project.bookshelf.model.Book;

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
	 * Test method for {@link com.project.bookshelf.model.Book#Book()}.
	 */
	public void testBookShouldContructWhenEmpty() {
		Book book = new Book();
		
		assertNotNull("an empty Book should be able to be constructed", book);
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
	}

}
