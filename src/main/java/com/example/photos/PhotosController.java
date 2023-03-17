package com.example.photos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotosController {

    private final Map<String, Photo> db = new HashMap<>(){{
        put("1", new Photo("1", "hello.jpg"));
        put("2", new Photo("2", "world.jpg"));
        put("3", new Photo("3", "java.jpg"));
        put("4", new Photo("4", "c++.jpg"));
        put("5", new Photo("5", "python.jpg"));
        put("6", new Photo("6", "c#.jpg"));
        put("7", new Photo("7", "red.png"));
        put("8", new Photo("8", "black.png"));
        put("9", new Photo("9", "blue.png"));
        put("10", new Photo("10", "green.png"));
    }};

    @GetMapping("/")
    public String HelloWorld(){
        System.out.println("Hello World!");
        return "Hello World!";
    }

    @GetMapping("/photos")
    public Collection<Photo> get(){
        System.out.println("获取所有图片");
        return db.values();
    }

    //根据id获取图片
    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id) {
        System.out.println("根据id获取图片");
        Photo photo = db.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "没找到这个图片哎");
        }
        return db.get(id);
    }

    //根据id删除图片
    @DeleteMapping("/photos/{id}")
    public String delete(@PathVariable String id) {
        System.out.println("删除id为 "+id+"的图片");
        if (db.remove(id) == null ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "没找到这个图片");
        }
        db.remove(id);
        return "success";
    }

    //创建新图片
    @PostMapping("/photos")
    public String post(@RequestPart("data") MultipartFile file) throws IOException {
        System.out.println("上传图片");
        Photo photo = new Photo(UUID.randomUUID().toString(), file.getOriginalFilename());
        photo.setContentType(file.getContentType());
        photo.setFileContent(file.getBytes());
        db.put(photo.getId(), photo);
        return "success";
    }

}
