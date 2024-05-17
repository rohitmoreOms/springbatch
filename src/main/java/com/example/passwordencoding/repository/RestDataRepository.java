package com.example.passwordencoding.repository;

import com.example.passwordencoding.model.RestData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestDataRepository extends JpaRepository<RestData,Long> {
}
