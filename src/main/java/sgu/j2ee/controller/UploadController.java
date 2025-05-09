package sgu.j2ee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import sgu.j2ee.service.CloudinaryService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    

    @PostMapping
    public ResponseEntity<Map> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Map result = cloudinaryService.uploadFile(file);
        return ResponseEntity.ok(result);
    }
}
