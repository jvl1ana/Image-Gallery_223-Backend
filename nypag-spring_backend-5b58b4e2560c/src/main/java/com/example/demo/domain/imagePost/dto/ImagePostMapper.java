package com.example.demo.domain.imagePost.dto;

import com.example.demo.core.generic.AbstractMapper;
import com.example.demo.domain.imagePost.ImagePost;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImagePostMapper extends AbstractMapper<ImagePost, ImagePostDTO> {
    ImagePost fromNewImagePostDTO(NewImagePostDTO dto);

}
