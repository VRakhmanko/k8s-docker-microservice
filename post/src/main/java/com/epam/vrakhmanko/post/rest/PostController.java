package com.epam.vrakhmanko.post.rest;


import com.epam.vrakhmanko.post.dto.AuthorPostDto;
import com.epam.vrakhmanko.post.dto.PostDto;
import com.epam.vrakhmanko.post.mapper.PostMapper;
import com.epam.vrakhmanko.post.repository.PostRepository;
import com.epam.vrakhmanko.post.repository.domain.Post;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

import static com.epam.vrakhmanko.post.rest.Constants.POST_DOES_NOT_EXIST;


@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostRepository postRepository;
    private final PostMapper mapper;
    private final WebClient userClient;

    @PostMapping
    public ResponseEntity<PostDto> add(@Valid @RequestBody PostDto postDto) {
        userClient
                .put()
                .uri(postDto.getAuthorId() + "/amountOfPosts")
                .retrieve()
                .bodyToMono(AuthorPostDto.class)
                .share()
                .block();
                postRepository.save(mapper.postDtoToPost(postDto));
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return ResponseEntity.status(404).body(POST_DOES_NOT_EXIST);
        } else {
            return ResponseEntity.ok(mapper.postToPostDto(post.get()));
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return ResponseEntity.status(404).body(POST_DOES_NOT_EXIST);
        } else {
            userClient
                    .delete()
                    .uri(post.get().getAuthorId() + "/amountOfPosts")
                    .retrieve()
                    .bodyToMono(AuthorPostDto.class)
                    .share()
                    .block();
            postRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody PostDto updatedPostDto) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return ResponseEntity.status(404).body(POST_DOES_NOT_EXIST);
        } else {
            updatedPostDto.setId(id);
            return ResponseEntity.ok(postRepository.save(mapper.postDtoToPost(updatedPostDto)));
        }
    }
}
