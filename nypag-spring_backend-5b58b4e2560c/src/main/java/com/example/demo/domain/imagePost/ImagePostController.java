package com.example.demo.domain.imagePost;

import com.example.demo.domain.imagePost.dto.ImagePostDTO;
import com.example.demo.domain.imagePost.dto.ImagePostMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;


import java.awt.*;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/imagePosts")
public class ImagePostController {
    @Autowired
    ImagePostServiceImpl imagePostService;

    @Autowired
    ImagePostService service;

    @Autowired
    ImagePostMapper mapper;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('IMAGE_READ')")
    @Operation(summary = "Get all image-posts of every user", description = "Displays all existing image-posts with their picture, author, description and like count.")
    public ResponseEntity<List<ImagePostDTO>> getAllImagePosts() {
        List<ImagePost> imagePost = imagePostService.findAll();
        return new ResponseEntity<>(mapper.toDTOs(imagePost), HttpStatus.OK);
    }

    @GetMapping("/{imagePostId}")
    @PreAuthorize("hasAuthority('IMAGE_READ')")
    @Operation(summary = "Get image-posts by their id", description = "Displays a detailed view of the image-post by its id.")
    public ResponseEntity<ImagePostDTO> getImagePostById(@PathVariable("imagePostId") UUID imagePostId) throws NotFoundException {
        ImagePost imagePost = imagePostService.findById(imagePostId);
        return new ResponseEntity<>(mapper.toDTO(imagePost), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('IMAGE_READ')")
    @Operation(summary = "Get all image-posts of every user", description = "Displays all existing posts of one user with their picture, author, description and like count.")
    public ResponseEntity<List<ImagePostDTO>> getAllPostsByUser(@PathVariable("userId") UUID userId) {
        List<ImagePost> imagePost = imagePostService.findPostByUserId(userId);
        return new ResponseEntity<>(mapper.toDTOs(imagePost), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('IMAGE_CREATE')")
    @Operation(summary = "Create a new image-post", description = "Adds a new image-Post with its information.")
    public ResponseEntity<ImagePostDTO> addImagePost(@RequestBody ImagePostDTO imagePostDTO) throws DataIntegrityViolationException {
        ImagePost imagePost = imagePostService.save(mapper.fromDTO(imagePostDTO));
        return new ResponseEntity<>(mapper.toDTO(imagePost), HttpStatus.CREATED);
    }

    @PutMapping("/{imagePostId}")
    @PreAuthorize("hasAuthority('IMAGE_UPDATE')")
    @Operation(summary = "Update an image-post", description = "Updates the information of an existing image-post.")
    public ResponseEntity<ImagePostDTO> updateById(@PathVariable("imagePostId") UUID imagePostId, @RequestBody ImagePostDTO imagePostDTO) throws AccessDeniedException {
            ImagePost imagePost = imagePostService.updateById(imagePostId, mapper.fromDTO(imagePostDTO));
            return new ResponseEntity<>(mapper.toDTO(imagePost), HttpStatus.OK);
    }

    @DeleteMapping("/{imagePostId}")
    @PreAuthorize("hasAuthority('IMAGE_DELETE')")
    @Operation(summary = "Delete an image-post", description = "Removes an image-post using its id.")
    public ResponseEntity<String> deleteById(@PathVariable("imagePostId") UUID imagePostId) throws NotFoundException, AccessDeniedException {
            imagePostService.deleteById(imagePostId);
            return new ResponseEntity<>("ImagePost with id " + imagePostId + " has successfully been deleted", HttpStatus.OK);
    }


}
