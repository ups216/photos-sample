package com.example.photos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {
    private final PhotoService photoService;

    public DownloadController(@Autowired PhotoService photoService) {
        this.photoService = photoService;
    }

    //根据id下载对应图片
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadById(@PathVariable Integer id) {
        Photo photo = photoService.get(id);
        if (photo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(photo.getContentType()))
                .body(photo.getFileContent());
    }

}
