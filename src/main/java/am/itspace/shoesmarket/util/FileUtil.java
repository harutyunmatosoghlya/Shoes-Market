package am.itspace.shoesmarket.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Component
public class FileUtil {
    @Value("${user.image.uploadPath}")
    private String uploadPath;

    public String fileName(MultipartFile multipartFile) {
        String fileName;
        if (!multipartFile.isEmpty()) {
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            File destinationFile = new File(uploadDir, fileName);
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("upload file error", e);
            }
            return fileName;
        }
        return null;
    }
}