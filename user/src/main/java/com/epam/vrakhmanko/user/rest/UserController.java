package com.epam.vrakhmanko.user.rest;


import com.epam.vrakhmanko.user.dto.UserDto;
import com.epam.vrakhmanko.user.mapper.PostMapper;
import com.epam.vrakhmanko.user.repository.UserRepository;
import com.epam.vrakhmanko.user.repository.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

import java.util.Optional;

import static com.epam.vrakhmanko.user.rest.Constants.USER_DOES_NOT_EXIST;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final PostMapper mapper;

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto) {
        userRepository.save(mapper.userDtoToUser(userDto));
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(404).body(USER_DOES_NOT_EXIST);
        } else {
            return ResponseEntity.ok(user.get());
        }
    }

    @PutMapping("/{id}/amountOfPosts")
    public ResponseEntity<?> addPost(@PathVariable Integer id) {
        return updatePosts(id, 1);
    }

    @DeleteMapping("/{id}/amountOfPosts")
    public ResponseEntity<?> deletePost(@PathVariable Integer id) {
        return updatePosts(id, -1);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (userRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(404).body(USER_DOES_NOT_EXIST);
        } else {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody UserDto updatedUserDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(404).body(USER_DOES_NOT_EXIST);
        } else {
            updatedUserDto.setId(id);
            return ResponseEntity.ok(userRepository.save(mapper.userDtoToUser(updatedUserDto)));
        }
    }

    private ResponseEntity<?> updatePosts(Integer id, Integer itr) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(404).body(USER_DOES_NOT_EXIST);
        } else {
            int amountOfPosts = user.get().getAmountOfPosts() + itr;
            user.get().setAmountOfPosts(Math.max(amountOfPosts, 0));
            userRepository.save(user.get());
            return ResponseEntity.ok(user.get());
        }
    }
}
