package br.com.amaro.service;

import br.com.amaro.dto.CategoriesEnum;
import br.com.amaro.dto.ProductDTO;
import br.com.amaro.dto.ProductsDTO;
import br.com.amaro.util.AmaroUtils;
import com.google.gson.Gson;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


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
        AmaroUtils.validate(productDTO);
        productDTO.getProducts().forEach(product -> AmaroUtils.apply(product));


        long productId = 8360;

        ProductDTO product  =  productDTO.getProducts().stream().filter( p -> p.getId() == productId).findAny().orElse(null);

        if(product != null)
        {
            System.out.println(product.getTags());
            productDTO.getProducts().remove(product);
        }

        //find the array for id position
        //take te id in an  DTO
        //calculate the euclidian distance between principal search e another search

        ProductsDTO euclideanProducts = euclideanDistance(product.getTagsVector(),productDTO);



        List<ProductDTO> similarity = euclideanProducts.getProducts().stream().sorted(Comparator.comparing(ProductDTO::getSimilarity).reversed()).collect(Collectors.toList());
        ProductsDTO similarProducts = new ProductsDTO();
        similarProducts.setProducts(similarity.stream().limit(3).collect(Collectors.toList()));


        System.out.println(similarProducts.getProducts());




    }



    private static ProductsDTO euclideanDistance(List<Integer> distance1, ProductsDTO productsDTO)
    {

        ProductsDTO productsSimilarity  = new ProductsDTO();

        for(ProductDTO products : productsDTO.getProducts())
        {
            double similarity = 0.0;

            for (int i =0; i < products.getTagsVector().size(); i++)
            {
                similarity = similarity+Math.pow((distance1.get(i) - products.getTagsVector().get(i)),2.0);
            }


            products.setSimilarity(new BigDecimal(Math.sqrt(similarity)).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
            productsSimilarity.getProducts().add(products);
        }


        return productsSimilarity;


    }
}
