package com.example.passwordencoding.config;

import com.example.passwordencoding.model.ExcelFinancial;
import org.springframework.batch.item.ItemProcessor;

public class ExcelItemProcessor implements ItemProcessor<ExcelFinancial,ExcelFinancial> {

    @Override
    public ExcelFinancial process(ExcelFinancial excelFinancial) throws Exception {
        return excelFinancial;
    }
}
