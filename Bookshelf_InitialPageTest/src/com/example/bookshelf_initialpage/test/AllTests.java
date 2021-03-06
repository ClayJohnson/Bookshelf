package com.example.bookshelf_initialpage.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(BookDAOTest.class);
		suite.addTestSuite(BookTest.class);
		suite.addTestSuite(CategoryDAOTest.class);
		suite.addTestSuite(CategoryTest.class);
		//$JUnit-END$
		return suite;
	}

}
