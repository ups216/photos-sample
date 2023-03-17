package com.example.photos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

@RestController
public class PhotosController {

    private final PhotoService photoService;

    public PhotosController(@Autowired PhotoService photoService){
        this.photoService = photoService;
    }

    @GetMapping("/")
    public String HelloWorld(){
        System.out.println("Hello World!");
        return "Hello World!";
    }

    @GetMapping("/photos")
    public Collection<Photo> get(){
        return photoService.get();
    }

    //根据id获取图片
    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id) {
        return photoService.get(id);
    }

    //根据id删除图片
    @DeleteMapping("/photos/{id}")
    public String delete(@PathVariable String id) {
        photoService.remove(id);
        return "success";
    }

    //创建新图片
    @PostMapping("/photos")
    public String post(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = photoService.create(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType(), file.getBytes());
        return (photo != null) ? photo.getId(): "failed";
    }

}
