package com.example.passwordencoding.service.impl;


import com.example.passwordencoding.service.IPdfService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService implements IPdfService {

    @Override
    public ByteArrayInputStream generatePdfFile(String contentGenerate) {

//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(htmlContent);
//        renderer.layout();
//        renderer.createPDF(outputStream, false);
//        renderer.finishPDF();
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
//        return inputStream;`

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();

        return null;
    }
}
