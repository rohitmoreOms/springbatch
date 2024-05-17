package com.example.passwordencoding.service;

import java.io.ByteArrayInputStream;

public interface IPdfService {
    ByteArrayInputStream generatePdfFile(String contentGenerate);
}
