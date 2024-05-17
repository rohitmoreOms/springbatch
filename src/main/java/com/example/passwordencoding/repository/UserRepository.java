package com.example.passwordencoding.repository;

import com.example.passwordencoding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    void findByIsDeleted(boolean b);
}
