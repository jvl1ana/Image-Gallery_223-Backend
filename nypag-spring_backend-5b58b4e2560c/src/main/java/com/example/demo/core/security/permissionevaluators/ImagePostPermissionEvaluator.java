package com.example.demo.core.security.permissionevaluators;

import com.example.demo.domain.imagePost.ImagePost;
import com.example.demo.domain.imagePost.ImagePostRepository;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.UUID;

@Component
public class ImagePostPermissionEvaluator {
    @Autowired
    private ImagePostRepository imagePostRepository;

    public boolean canEditOrDelete(User user, UUID imagePostId) {
        ImagePost imagePost = imagePostRepository.findById(imagePostId)
                .orElseThrow(() -> new NotFoundException("Image-Post with id " + " does not exist."));

        if (user.getId().equals(imagePost.getAuthor().getId())) {
            return true;
        }
        return user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));
    }

}
