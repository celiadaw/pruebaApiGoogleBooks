package com.example.pruebaapigooglebooks.INTEGRACION.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestVolumeInfo {

    private String title;
    private String [] authors;
    private String publisher;
    private String publishedDate;
    private  List<TestIndustryIdentifiers> industryIdentifiers;
  // private String ISBN_10=industryIdentifiers.get(0);



}
