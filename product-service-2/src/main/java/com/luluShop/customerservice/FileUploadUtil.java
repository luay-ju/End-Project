package com.luluShop.customerservice;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {


    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException{

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw new IOException("Could not save image file " + fileName + ioe);
        }
    }

   /*

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        try {
            Path uploadPath = Paths.get(uploadDir);
            System.out.println("Absolute Path: " + uploadPath.toAbsolutePath().toString());

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Füge Debugging-Logs hinzu, um den Pfad und Dateinamen zu überprüfen
            System.out.println("Upload Directory: " + uploadPath);
            System.out.println("File Name: " + fileName);

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ioe) {
            throw new IOException("Could not save image file " + fileName, ioe);
        }
    }

  */

}
