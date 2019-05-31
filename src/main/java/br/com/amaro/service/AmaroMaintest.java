package br.com.amaro.service;

import br.com.amaro.dto.CategoriesEnum;
import br.com.amaro.dto.ProductsDTO;
import com.google.gson.Gson;

import java.io.*;
import java.util.stream.Stream;


public class AmaroMaintest {

    public static void main(String[] args)  throws  IOException{


        Reader reader = new FileReader(new File(ClassLoader.getSystemResource("produtos.json").getFile()));
        long limitSize = 20;

        Gson gson = new Gson();
        ProductsDTO productDTO =  gson.fromJson(reader,ProductsDTO.class);

        productDTO.getProducts().stream().forEach( tags -> {
            long value = tags.getTags().stream().count();
            if(value > limitSize)
            {
                long count = value - limitSize;
                for(long i=0; i < count; i++)
                {
                    tags.getTags().remove(tags.getTags().get((int) i));

                }
            }

            else if(value < limitSize)
            {
                long count = limitSize - value;
                for(long i=0; i< count; i++)
                {
                    tags.getTags().add(CategoriesEnum.ramdomCategoris().name());
                }

            }
        });

    }
}
