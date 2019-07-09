package com.trilogyed.bookservice;

import com.trilogyed.bookservice.controller.BookControllerTests;
import com.trilogyed.bookservice.dao.BookDaoTests;
import com.trilogyed.bookservice.service.BookServiceTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        BookControllerTests.class,
        BookDaoTests.class,
        BookServiceTests.class
})

public class BookServiceTestSuite {
}
