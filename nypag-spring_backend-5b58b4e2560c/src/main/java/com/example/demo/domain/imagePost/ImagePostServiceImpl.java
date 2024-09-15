package com.example.demo.domain.imagePost;

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

    @Override
    public ImagePost save(ImagePost newImagePost) throws  DataIntegrityViolationException{
        if (repository.existsByDescription(newImagePost.getDescription()) && repository.existsByUrl(newImagePost.getUrl())) {
            throw new DataIntegrityViolationException("An Image-Post with this description and image already exists!");
        }
        return repository.save(newImagePost);
    }

    @Override
    public void deleteById(UUID id) throws NoSuchElementException {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Image-Post with id " + id + " couldn't be found");
        }
        repository.deleteById(id);

    }

    @Override
    public ImagePost updateById(UUID id, ImagePost imagePost) throws NoSuchElementException {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Image-Post with id " + id + " couldn't be found");
        } else if (repository.existsByDescription(imagePost.getDescription()) && repository.existsByUrl(imagePost.getUrl())) {
            throw new DataIntegrityViolationException("An Image-Post with this description and image already exists!");
        }
        imagePost.setId(id);
        return repository.save(imagePost);
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
        return false;
    }

    public List<ImagePost> findPostByUserId(UUID userId){
        return repository.findAllByAuthorId(userId);
    }
}
