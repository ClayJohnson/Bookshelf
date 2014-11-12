/**
 * 
 */
package com.project.bookshelf.test;

import com.project.bookshelf.model.Category;

import junit.framework.TestCase;

/**
 * @author Clay
 *
 */
public class CategoryTest extends TestCase {

	/**
	 * @param name
	 */
	public CategoryTest(String name) {
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
	 * Test method for {@link com.project.bookshelf.model.Category#Category()}.
	 */
	public void testCategoryShouldConstructWhenEmpty() {
		Category category = new Category();
		
		assertNotNull("an empty Category should be able to be constructed", category);
	}

	/**
	 * Test method for {@link com.project.bookshelf.model.Category#Category(long, java.lang.String)}.
	 */
	public void testCategoryShouldConstructWithId() {
		long id = 1;
		String name = "name";
		
		Category category = new Category(id,name);
		assertNotNull("a Category should be able to be constructed with an id", category);
	}

	/**
	 * Test method for {@link com.project.bookshelf.model.Category#Category(java.lang.String)}.
	 */
	public void testCategoryShouldConstructWithoutId() {
		String name = "name";
		
		Category category = new Category(name);
		assertNotNull("a Category should be able to be constructed without an id", category);
	}

}
