package com.example.passwordencoding.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "excel_financial")
public class ExcelFinancial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long excelId;

    @Column(name = "segment")
    private String Segment;


    private String Country;


    private String Product;


    private String  DiscountBand;

    private String UnitsSold;


    private String ManufacturingPrice;

    private String GrossSales;


    private String Discounts;


    private String  Sales;


    @Column(name = "cogs")
    private String  COGS;

    public void setCOGS(String COGS) {
        this.COGS = COGS;
    }

    public String getCOGS() {
        return COGS;
    }

    private String Profit;


    private String Date;


    private String MonthNumber;


    private String MonthName;


    private String Year;



}
