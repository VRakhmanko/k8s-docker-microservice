package com.epam.vrakhmanko.post.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.epam.vrakhmanko.post.repository.domain.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
}
