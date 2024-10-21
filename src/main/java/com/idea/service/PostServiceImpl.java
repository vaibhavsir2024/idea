package com.idea.service;

import com.idea.entity.Post;
import com.idea.exception.ResourceNotFoundException;
import com.idea.payload.PostDto;
import com.idea.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class PostServiceImpl implements PostService{
    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo){
        this.postRepo = postRepo;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        post.setDescription(postDto.getDescription());
        Post post = mapToEntity(postDto);
        Post savedPost = postRepo.save(post);
        PostDto dto = mapToDto(savedPost);
//        PostDto dto = new PostDto();
//        dto.setId(savedPost.getId());
//        dto.setTitle(savedPost.getTitle());
//        dto.setContent(savedPost.getContent());
//        dto.setDescription(savedPost.getDescription());

        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(id)
        );
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        return dto;
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = postRepo.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return postDtos;
    }



    Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }

    PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }

    @Override
    public void deletePostById(long id) {
        postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        postRepo.deleteById(id);
    }

    @Override
    public PostDto updatePostById(long id, PostDto postDto) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        Post updateContent = mapToEntity(postDto);
        updateContent.setId(post.getId());
        Post updatePostInfo = postRepo.save(updateContent);
        return mapToDto(updatePostInfo);
    }
}
