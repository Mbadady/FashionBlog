package com.FashionblogMbadady.FashionBlog.service;

import com.FashionblogMbadady.FashionBlog.customException.ResourceNotFoundException;
import com.FashionblogMbadady.FashionBlog.dto.request.CreatePostRequest;
import com.FashionblogMbadady.FashionBlog.dto.request.PostDtoCustom;
import com.FashionblogMbadady.FashionBlog.entity.Post;
import com.FashionblogMbadady.FashionBlog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public CreatePostRequest createPost(CreatePostRequest request) {
//        Optional<Post> post = postRepository.findById(theId);

        Post post = new Post();

        if(request.getEmail().isEmpty()){
            throw new RuntimeException("Email must be inputted");
        }

        post.setCategory(request.getCategory());
        post.setEmail(request.getEmail());
        post.setDescription(request.getDescription());
        post.setComments(request.getComments());

//        Post postToBeCreated = postRepository.save(request);

        postRepository.save(post);

        request.setDescription(post.getDescription());
        request.setEmail(post.getEmail());
        request.setId(post.getId());
        request.setCategory(post.getCategory());
        request.setComments(post.getComments());

        return request;
    }

    @Override
    public CreatePostRequest getAPostById(Long id) {
//        return postRepository.findById(id).orElseThrow(()->new RuntimeException("Post cannot be found"));
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));

        CreatePostRequest postRequest = new CreatePostRequest();

        postRequest.setCategory(post.getCategory());
        postRequest.setId(post.getId());
        postRequest.setDescription(post.getDescription());
        postRequest.setEmail(post.getEmail());
        postRequest.setComments(post.getComments());


     return postRequest;
    }

    @Override
    public PostDtoCustom getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        // 1. create a pageable instance
        // 2. findAll by passing pageable argument
        // 3.

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPosts = posts.getContent();




       List<CreatePostRequest> content = listOfPosts.stream().map(post -> {
           CreatePostRequest createPostRequest = new CreatePostRequest();
            createPostRequest.setId(post.getId());
            createPostRequest.setDescription(post.getDescription());
            createPostRequest.setCategory(post.getCategory());
            createPostRequest.setEmail(post.getEmail());
            createPostRequest.setComments(post.getComments());
            return createPostRequest;
        }).collect(Collectors.toList());

        PostDtoCustom postDtoCustom = new PostDtoCustom();
        postDtoCustom.setContent(content);
        postDtoCustom.setPageNo(posts.getNumber());
        postDtoCustom.setPageSize(posts.getSize());
        postDtoCustom.setTotalPages(posts.getTotalPages());
        postDtoCustom.setTotalElements(posts.getTotalElements());
        postDtoCustom.setLast(posts.isLast());

        return postDtoCustom;
    }

    @Override
    public List<CreatePostRequest> findByCategory(String category) {
        List<Post> posts = postRepository.findByCategory(category);
        if(posts.isEmpty()){
            throw new RuntimeException("Post with Category " + category + " not found");
        }

        CreatePostRequest postRequest = new CreatePostRequest();


        return posts.stream().map(post -> {
            postRequest.setId(post.getId());
            postRequest.setCategory(post.getCategory());
            postRequest.setDescription(post.getDescription());
            postRequest.setEmail(post.getEmail());
            postRequest.setComments(post.getComments());
            return postRequest;
        }).collect(Collectors.toList());

    }

    @Override
    public CreatePostRequest updatePost(CreatePostRequest request, Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new RuntimeException("Post not Found"));

        post.setCategory(request.getCategory());
        post.setDescription(request.getDescription());
        post.setEmail(request.getEmail());
        post.setId(id);

        postRepository.save(post);

        request.setEmail(post.getEmail());
        request.setId(post.getId());
        request.setCategory(post.getCategory());
        request.setDescription(post.getDescription());

        return request;
    }

    @Override
    public String deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post not found"));

        postRepository.delete(post);

        return "Post Deleted Successfully";
    }
}
