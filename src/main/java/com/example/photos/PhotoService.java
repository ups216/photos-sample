package com.example.photos;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository){
        this.photoRepository = photoRepository;
    }

    public Iterable<Photo> get() {
        System.out.println("获取所有图片");
        return photoRepository.findAll();
    }

    public Photo get(Integer id) {
        System.out.println("根据id获取图片");
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "没找到这个图片"));
        return photo;
    }

    public boolean remove(Integer id) {
        System.out.println("删除图片");
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "没找到这个图片"));
        return photo != null;
    }

    public Photo create(String originalFilename, String contentType, byte[] bytes) {
        System.out.println("创建图片");
        Photo photo = new Photo();
        photo.setFileName(originalFilename);
        photo.setContentType(contentType);
        photo.setFileContent(bytes);
        photoRepository.save(photo);
        return photo;
    }
}
