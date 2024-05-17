package com.example.passwordencoding.repository;

import com.example.passwordencoding.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    Team findByEmailIgnoreCase(String username);
}
