package com.epam.vrakhmanko.user.repository;

import com.epam.vrakhmanko.user.repository.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
