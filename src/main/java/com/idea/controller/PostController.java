package com.idea.controller;

import com.idea.payload.PostDto;
import com.idea.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    //http://localhost:8082/api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto savedDto = postService.createPost((postDto));
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
    //http://localhost:8082/api/1
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    //get all data
    //http://localhost:8082/api
    @GetMapping
    public List<PostDto> getAllPost(){
        List<PostDto> postdtos = postService.getAllPost();
        return postdtos;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable long id,@RequestBody PostDto postDto){
        PostDto dto = postService.updatePostById(id,postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
