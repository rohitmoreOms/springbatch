package com.example.passwordencoding.repository;

import com.example.passwordencoding.model.ExcelFinancial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelRepository extends JpaRepository<ExcelFinancial,Long> {
}
