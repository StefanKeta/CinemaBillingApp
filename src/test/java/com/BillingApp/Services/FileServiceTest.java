package com.BillingApp.Services;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class FileServiceTest extends ApplicationTest {

    @BeforeClass
    public static void setUpClass(){
        FileService.APP_FOLDER=".test-BillingApp";
        FileService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileService.getApplicationHomePath().toFile());
    }

    @Test
    public void testIfPathExists(){
        assertTrue(Files.exists(FileService.getApplicationHomePath()));
    }

}