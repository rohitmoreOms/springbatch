package com.example.passwordencoding.Controller;

import com.example.passwordencoding.service.IPdfService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class PdfController {

    @Autowired
    private IPdfService iPdfService;

    @PostMapping("/generatePdf")
    public ResponseEntity<?>generatePdf(HttpServletResponse response,String contentGenerate) throws IOException {

        ByteArrayInputStream byteArrayInputStream= iPdfService.generatePdfFile(contentGenerate);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Discreption","attachment; filename=Index.html");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
        return new ResponseEntity(byteArrayInputStream, HttpStatus.OK) ;
    }

}
