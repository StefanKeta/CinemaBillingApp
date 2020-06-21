package com.BillingApp.Services;

import com.BillingApp.Exceptions.EmailAlreadyExistsException;
import com.BillingApp.Model.Admin;
import com.BillingApp.Model.Client;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class ClientServiceTest extends ApplicationTest {

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
        ClientService.loadClients();
        assertTrue(Files.exists(ClientService.CLIENTS_PATH));
    }

    @Test
    public void testClientLoad() throws IOException {
        ClientService.loadClients();
        assertNotNull(ClientService.getClientList());
        //assertEquals(0,ClientService.getClientList().size());
    }

    /*@Test
    public void testAddClient() throws IOException, EmailAlreadyExistsException {
        ClientService.loadClients();
        ClientService.addClient("client1@mail.com","password123",20,"Client1");
        assertNotNull(ClientService.getClientList());
        assertEquals(1,ClientService.getClientList().size());
    }

    @Test
    public void testTwoClients() throws IOException, EmailAlreadyExistsException {
        ClientService.loadClients();
        ClientService.addClient("client2@mail.com","password123",21,"Client2");
        ClientService.addClient("client3@mail.com","password123",23,"Client3");
        assertNotNull(ClientService.getClientList());
        assertEquals(2,ClientService.getClientList().size());
    }*/

    @Test(expected = EmailAlreadyExistsException.class)
    public void testExist() throws IOException, EmailAlreadyExistsException {
        ClientService.loadClients();
        ClientService.addClient("client2@mail.com","password123",21,"Client2");
        List<Client> users = new ObjectMapper().readValue(ClientService.CLIENTS_PATH.toFile(), new TypeReference<List<Client>>() {
        });
        assertNotNull(users);
        assertEquals(1,users.size());
    }

    /*@Test
    public void testAddTwoClientsArePersisted() throws IOException, EmailAlreadyExistsException {
        FileUtils.cleanDirectory(FileService.getApplicationHomePath().toFile());
        ClientService.loadClients();
        ClientService.addClient("client4@mail.com","password123",22,"Client4");
        ClientService.addClient("client5@mail.com","password123",22,"Client5");
        List<Client> users = new ObjectMapper().readValue(ClientService.CLIENTS_PATH.toFile(), new TypeReference<List<Client>>() {
        });
        assertNotNull(users);
        assertEquals(2, users.size());
    }*/

    @Test
    public void testPasswordEncoding() {
        assertNotEquals("password123", ClientService.encodePassword("client5@mail.com", "password123"));
    }

}