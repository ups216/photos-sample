package com.example.photos;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class PhotoService {
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

    public Collection<Photo> get() {
        System.out.println("获取所有图片");
        return db.values();
    }

    public Photo get(String id) {
        System.out.println("根据id获取图片");
        Photo photo = db.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "没找到这个图片哎");
        }
        return photo;
    }

    public boolean remove(String id) {
        System.out.println("删除图片");
        Photo photo = db.remove(id);
        if (photo == null) {
            return false;
        }
        return photo != null;
    }

    public Photo create(String id, String originalFilename, String contentType, byte[] bytes) {
        System.out.println("创建图片");
        Photo photo = new Photo(id, originalFilename);
        photo.setContentType(contentType);
        photo.setFileContent(bytes);
        db.put(id, photo);
        return photo;
    }
}
