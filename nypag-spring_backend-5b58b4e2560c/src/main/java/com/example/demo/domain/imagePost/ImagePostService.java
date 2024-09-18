package com.example.demo.domain.imagePost;

import com.example.demo.core.generic.AbstractService;

import java.util.List;
import java.util.UUID;

public interface ImagePostService extends AbstractService<ImagePost> {

    List<ImagePost> findPostByUserId(UUID userId);

}
