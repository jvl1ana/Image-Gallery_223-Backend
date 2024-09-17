package com.example.demo.domain.imagePost;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ImagePostServiceImpl implements ImagePostService {
    @Autowired
    private ImagePostRepository repository;

    @Override
    public ImagePost save(ImagePost newImagePost) throws DataIntegrityViolationException {
        if (repository.existsByDescription(newImagePost.getDescription()) && repository.existsByUrl(newImagePost.getUrl())) {
            throw new DataIntegrityViolationException("An Image-Post with this description and image already exists!");
        }
        return repository.save(newImagePost);
    }

    @Override
    public void deleteById(UUID id) throws NoSuchElementException, AccessDeniedException {
        ImagePost imagePost = repository.findById(id).orElseThrow(() -> new NotFoundException("Image-Post with id " + id + " couldn't be found"));
        User currentUser = getCurrentUser();

        if (!canEditOrDelete(currentUser, imagePost)) {
            throw new AccessDeniedException("You do not have permission to delete this post.");
        }
        repository.deleteById(id);
    }

    @Override
    public ImagePost updateById(UUID id, ImagePost updatedImagePost) throws NoSuchElementException, DataIntegrityViolationException, AccessDeniedException {
        ImagePost existingPost = repository.findById(id).orElseThrow(() -> new NotFoundException("Image-Post with id " + id + " couldn't be found"));
        User currentUser = getCurrentUser();
        if (repository.existsByDescription(updatedImagePost.getDescription()) && repository.existsByUrl(updatedImagePost.getUrl())) {
            throw new DataIntegrityViolationException("An Image-Post with this description and image already exists!");
        }

        if (!canEditOrDelete(currentUser, existingPost)) {
            throw new AccessDeniedException("You do not have permission to update this post.");
        }

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

    public List<ImagePost> findPostByUserId(UUID userId) {
        return repository.findAllByAuthorId(userId);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) authentication.getPrincipal()).user();
        }
        throw new IllegalStateException("No user is currently authenticated");
    }

    private boolean canEditOrDelete(User user, ImagePost imagePost) {
        if (user.getId().equals(imagePost.getAuthor().getId())) {
            return true;
        }
        return user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));
    }
}
