package com.uk.POST_CRUD.service;

import com.uk.POST_CRUD.model.Post;
import com.uk.POST_CRUD.repository.PostRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PostService.class);
    @Autowired
    private PostRepository repository;


    public Post createPost(Post post) {
        if (post != null) {
            return repository.save(post);
        } else {
            return null;
        }
    }

    public void deletePost(Long id) {
        if (findPostById(id) != null) {
            repository.deleteById(id);
        }else{
            log.info("Post with id " + id + " not found");
        }
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public List<Post> getPostById(Long id) {
        Post findPost = findPostById(id);
        if(findPost != null) {
            findPost.setWatches(findPost.getWatches() + 1);
            repository.save(findPost);
            return List.of(findPost);
        }else{
            return null;
        }
    }

    public Post updatePost(Long id, Post updatedPost) {
        Post existingPost = findPostById(id);
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setDescription(updatedPost.getDescription());
        return repository.save(existingPost);
    }

        public Post findPostById(Long id) {
        return repository.findById(id).orElse(null);
    }


}
