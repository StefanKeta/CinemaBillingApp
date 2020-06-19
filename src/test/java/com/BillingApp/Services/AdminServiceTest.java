package com.BillingApp.Services;

import com.BillingApp.Exceptions.EmailAlreadyExistsException;
import com.BillingApp.Model.Admin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class AdminServiceTest extends ApplicationTest {

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
    public void testIfFileDoesNotExist() throws IOException {
        AdminService.loadAdmins();
        assertTrue(Files.exists(AdminService.ADMIN_PATH));
    }

    @Test
    public void testAdminLoad() throws IOException {
        AdminService.loadAdmins();
        assertNotNull(AdminService.getAdminList());
        assertEquals(0,AdminService.getAdminList().size());
    }

    @Test
    public void testAddAdmin() throws IOException, EmailAlreadyExistsException {
        FileUtils.cleanDirectory(FileService.getApplicationHomePath().toFile());
        AdminService.loadAdmins();
        AdminService.addAdmin("test2@mail.com","password123","CITY","CINEMA");
        assertNotNull(AdminService.getAdminList());
        assertEquals(1,AdminService.getAdminList().size());
    }

    @Test
    public void testTwoAdmins() throws IOException, EmailAlreadyExistsException {
        FileUtils.cleanDirectory(FileService.getApplicationHomePath().toFile());
        AdminService.loadAdmins();
        AdminService.addAdmin("test3@mail.com","password123","CITY","CINEMA2");
        AdminService.addAdmin("test1@mail.com","password123","CITY","CINEMA2");
        assertNotNull(AdminService.getAdminList());
        assertEquals(2,AdminService.getAdminList().size());
    }

    @Test(expected = EmailAlreadyExistsException.class)
    public void testExist() throws IOException, EmailAlreadyExistsException {
        FileUtils.cleanDirectory(FileService.getApplicationHomePath().toFile());
        AdminService.loadAdmins();
        AdminService.addAdmin("test3@mail.com","password123","CITY","CINEMA2");
        List<Admin> users = new ObjectMapper().readValue(AdminService.ADMIN_PATH.toFile(), new TypeReference<List<Admin>>() {
        });
        assertNotNull(users);
        assertEquals(1,users.size());
    }

    @Test
    public void testAddTwoAdminsArePersisted() throws IOException, EmailAlreadyExistsException {
        AdminService.loadAdmins();
        AdminService.addAdmin("test3@mail.com","password123","CITY","CINEMA2");
        AdminService.addAdmin("test1@mail.com","password123","CITY","CINEMA2");
        List<Admin> users = new ObjectMapper().readValue(AdminService.ADMIN_PATH.toFile(), new TypeReference<List<Admin>>() {
        });
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testPasswordEncoding() {
        assertNotEquals("password123", AdminService.encodePassword("test3@mail.com", "password123"));
    }
}