package com.BillingApp.Services;

import com.BillingApp.Exceptions.CouldNotWriteUsersException;
import com.BillingApp.Exceptions.EmailAlreadyExistsException;
import com.BillingApp.Model.Admin;
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

public class AdminService {
    private static List<Admin> admins ;
    private static  final Path ADMIN_PATH= FileService.getPathToFile("config","admins.json");

    public static void loadAdmins() throws IOException{
        if(!Files.exists(ADMIN_PATH)){
            FileUtils.copyURLToFile(AdminService.class.getClassLoader().getResource("admins.json"),ADMIN_PATH.toFile());
        }
        ObjectMapper objectMapper= new ObjectMapper();
        admins= objectMapper.readValue(ADMIN_PATH.toFile(), new TypeReference<List<Admin>>() {
        });

    }

    public static void addAdmin(String email,String password,String city, String cinemaName) throws EmailAlreadyExistsException{
        ClientService.checkClientDoesNotAlreadyExist(email);
        checkAdminDoesNotAlreadyExist(email);
        admins.add(new Admin(city,cinemaName,email,encodePassword(email,password)));
        persistAdmins();
    }


    public static void checkAdminDoesNotAlreadyExist(String email) throws EmailAlreadyExistsException {
        for(Admin admin:admins){
            if(email.equals(admin.getEmail()))
                throw new EmailAlreadyExistsException(email);
        }
    }


    private static void persistAdmins(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(ADMIN_PATH.toFile(), admins);
        }catch (IOException exception){
            throw new CouldNotWriteUsersException();
        }
    }

    private static String encodePassword(String salt,String password) {
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
}
