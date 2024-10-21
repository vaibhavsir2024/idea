package com.idea.service;

import com.idea.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);

    List<PostDto> getAllPost();

    void deletePostById(long id);

    PostDto updatePostById(long id, PostDto postDto);
}
