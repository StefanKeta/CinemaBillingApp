package com.BillingApp.Services;

import com.BillingApp.Exceptions.CouldNotWriteUsersException;
import com.BillingApp.Exceptions.EmailAlreadyExistsException;
import com.BillingApp.Model.Client;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class ClientService {
    private static List<Client> clients;
    static final Path CLIENTS_PATH =FileService.getPathToFile("config","clients.json");

    public static void loadClients() throws IOException{
        if(!Files.exists(CLIENTS_PATH)){
            FileUtils.copyURLToFile(ClientService.class.getClassLoader().getResource("clients.json"),CLIENTS_PATH.toFile());
        }
        ObjectMapper objectMapper=new ObjectMapper();
       clients= objectMapper.readValue(CLIENTS_PATH.toFile(), new TypeReference<List<Client>>() {
       });

    }
    public static void addClient(String email, String password,int age,String name) throws EmailAlreadyExistsException {
        checkClientDoesNotAlreadyExist(email);
        AdminService.checkAdminDoesNotAlreadyExist(email);
        clients.add(new Client(name,age,email,encodePassword(email,password)));
        persistClients();
    }

    public static void checkClientDoesNotAlreadyExist(String email) throws EmailAlreadyExistsException {
        if(clients!=null)
            for(Client client:clients){
            if(client.getEmail().equals(email))
                throw new EmailAlreadyExistsException(email);

        }
    }

    private static void persistClients(){
        try {
            ObjectMapper objectMapper= new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(CLIENTS_PATH.toFile(),clients);
        }catch (IOException e){
            throw new CouldNotWriteUsersException();
        }
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
    public static List<Client> getClientList() {
        return clients;
    }
}
