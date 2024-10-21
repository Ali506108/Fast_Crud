package com.uk.POST_CRUD.controller;

import com.uk.POST_CRUD.model.Post;
import com.uk.POST_CRUD.service.PostService;
import jakarta.persistence.PostLoad;
import org.hibernate.jdbc.Expectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping("")
    public ResponseEntity<List<Post>> getAllPosts() {
        try{
            return ResponseEntity.ok().body(service.getAllPosts());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        try{
            Post find_Post = service.findPostById(id);
            if(find_Post != null) {
                find_Post.setWatches(find_Post.getWatches() + 1);
                service.updatePost(id, find_Post);
                return ResponseEntity.ok().body(find_Post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Post> savePhoto(@RequestBody Post post) {
        try{
            service.createPost(post);
            return ResponseEntity.ok().body(post);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        try{
            service.deletePost(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        try{
            service.updatePost(id, post);
            return ResponseEntity.ok().body(post);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

}
