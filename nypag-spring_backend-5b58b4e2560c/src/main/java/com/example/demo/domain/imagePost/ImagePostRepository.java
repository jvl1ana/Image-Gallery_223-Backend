package com.example.demo.domain.imagePost;

import com.example.demo.core.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImagePostRepository extends AbstractRepository<ImagePost> {
    boolean existsByDescription(String description);
    boolean existsByUrl(String url);

    List<ImagePost> findAllByAuthorId(UUID authorId);

}
