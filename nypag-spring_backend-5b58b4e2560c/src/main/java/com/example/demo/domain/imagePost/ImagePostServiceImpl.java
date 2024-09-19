package com.example.demo.domain.imagePost;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ImagePostServiceImpl implements ImagePostService {
    @Autowired
    private ImagePostRepository repository;

    @Transactional
    @Override
    public ImagePost save(ImagePost newImagePost) throws DataIntegrityViolationException {
        return repository.save(newImagePost);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) throws NoSuchElementException {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Image-Post with id " + id + " couldn't be found"));
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public ImagePost updateById(UUID id, ImagePost updatedImagePost) throws NoSuchElementException, DataIntegrityViolationException {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Image-Post with id " + id + " couldn't be found"));

        updatedImagePost.setId(id);
        return repository.save(updatedImagePost);
    }

    @Override
    public List<ImagePost> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ImagePost> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ImagePost findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image-Post with id " + id + " couldn't be found"));
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public List<ImagePost> findPostByUserId(UUID userId) {
        return repository.findAllByAuthorId(userId);
    }

}
