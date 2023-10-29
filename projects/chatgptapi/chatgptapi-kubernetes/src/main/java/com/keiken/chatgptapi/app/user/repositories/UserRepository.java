package com.keiken.chatgptapi.app.user.repositories;

import com.keiken.chatgptapi.app.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
}

