package com.project.bookshelf.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(BookDAOTest.class);
		suite.addTestSuite(BookshelfDBDAOTest.class);
		suite.addTestSuite(BookTest.class);
		suite.addTestSuite(CategoryDAOTest.class);
		suite.addTestSuite(CategoryTest.class);
		suite.addTestSuite(MySQLiteHelperTest.class);
		//$JUnit-END$
		return suite;
	}

}
