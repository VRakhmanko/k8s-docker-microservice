package com.epam.vrakhmanko.post.dto;

public record AuthorPostDto(
        Integer id,
        String username,
        Integer amountOfPosts) {
}
