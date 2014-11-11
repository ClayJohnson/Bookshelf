package com.project.bookshelf.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BookDAOTest.class, BookshelfDBDAOTest.class, BookTest.class,
		CategoryDAOTest.class, CategoryTest.class, MySQLiteHelperTest.class })
public class AllTests {

}
