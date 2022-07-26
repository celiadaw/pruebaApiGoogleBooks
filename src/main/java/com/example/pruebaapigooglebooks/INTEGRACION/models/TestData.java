package com.example.pruebaapigooglebooks.INTEGRACION.models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestData {

    private String kind;
    private Long totalItems;
    private ArrayList<TestBook> items;

}
