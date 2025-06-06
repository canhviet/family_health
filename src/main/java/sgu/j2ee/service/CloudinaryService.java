package sgu.j2ee.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public Map uploadFile(MultipartFile file) throws IOException {
        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), Map.of());
            return result;
        } catch (Exception e) {
            throw new IOException("Don't upload file: " + e.getMessage());
        }
    }
}