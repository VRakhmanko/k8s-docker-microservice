package com.epam.vrakhmanko.post.mapper;

import com.epam.vrakhmanko.post.dto.PostDto;
import com.epam.vrakhmanko.post.repository.domain.Post;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface PostMapper {
    PostDto postToPostDto(Post user);

    Post postDtoToPost(PostDto userDto);
}
