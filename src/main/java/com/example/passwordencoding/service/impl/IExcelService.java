package com.example.passwordencoding.service.impl;

import com.example.passwordencoding.model.ExcelFinancial;
import com.example.passwordencoding.repository.ExcelRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IExcelService implements ItemWriter<ExcelFinancial> {

    @Autowired
    private ExcelRepository excelRepository;

    @Override
    public void write(List<? extends ExcelFinancial> list) throws Exception {
         for( ExcelFinancial excel:list){
             excelRepository.save(excel);
         }

    }
}
