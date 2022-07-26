package com.example.pruebaapigooglebooks.INTEGRACION.controllers;



import com.example.pruebaapigooglebooks.INTEGRACION.models.TestBook;
import com.example.pruebaapigooglebooks.INTEGRACION.models.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    private final RestTemplate restTemplate;
    public static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    public ApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/test")
    public List<TestBook> getApi(){
        //String uri= "https://www.googleapis.com/books/v1/volumes?q=subject:terror";
        //String uri="https://www.googleapis.com/books/v1/volumes?q=subject:fiction";
      //  String uri="https://www.googleapis.com/books/v1/volumes?q=comic";

        String uri="https://www.googleapis.com/books/v1/volumes?q=poetry";
        List<TestBook> LibrosParaBiblioteca= getBooks(uri);
        logger.info("Total libros guardados "+LibrosParaBiblioteca.size());


        return  LibrosParaBiblioteca;
    }


    //este metodo sirve para poder recoger de 40 en 40 los libros de la api , ya que google no permite más
    private List<TestBook> getBooks(String uri ){
        List<TestBook> totalLibros =new ArrayList<>();
        String url2=uri;
        TestData testData =restTemplate.getForObject(url2, TestData.class);
        int maxCount = Math.toIntExact(testData.getTotalItems());

        for(int i =0;i<maxCount;i=i+40){
            int maxResult=40;

            try {

                if (i + 40 > maxCount) {
                    maxResult = maxCount - i;

                    url2 = uri + "&maxResults=" + maxResult + "&startIndex=" + i + "";
                    testData = restTemplate.getForObject(url2, TestData.class);
                    añadeLibros(testData,totalLibros);

                } else {
                    url2 = uri + "&maxResults=" + maxResult + "&startIndex=" + i + "";
                    testData = restTemplate.getForObject(url2, TestData.class);
                    añadeLibros(testData,totalLibros);

                }

           }catch (NullPointerException e ){

                logger.error("la API no permite recoger más libros o no hay coincidencias  "+e.getMessage());
                logger.info("estos son los libros que hemos podido recoger de la API : "+totalLibros.size());
                return  totalLibros;

            }


        }
        logger.info("total items recogidos de la API " +totalLibros.size());
        return totalLibros;
    }




    private void añadeLibros(TestData testData, List<TestBook> totalLibros){
        for (TestBook item : testData.getItems()
        ) {
            totalLibros.add(item);
            logger.info("añadiendo libros ....total " + totalLibros.size());
        }




    }
}
