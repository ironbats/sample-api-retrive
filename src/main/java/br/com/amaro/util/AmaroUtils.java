package br.com.amaro.util;

import br.com.amaro.builder.ProductChargeVO;
import br.com.amaro.builder.ProductSearchVO;
import br.com.amaro.builder.ProductsVO;
import br.com.amaro.dto.CategoriesEnum;
import br.com.amaro.dto.ProductDTO;
import br.com.amaro.dto.ProductsDTO;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AmaroUtils {

    private static long limitSize = 20;

    public static ProductsDTO create(){

        ProductsDTO productDTO =  null;

        try
        {
            Reader reader = new FileReader(new File(ClassLoader.getSystemResource("produtos.json").getFile()));
            Gson gson = new Gson();
            productDTO  =  gson.fromJson(reader,ProductsDTO.class);
        }catch (IOException cause)
        {
            log.warn("Ocurred some error with your products , check your  application ");
        }

        return productDTO;
    }


    public static void apply(ProductDTO tags)
    {
        tags.getTags().stream().forEach(code ->
        {
           matchWithCategories(code,tags);
        });
    }


    private static void matchWithCategories(String code,ProductDTO tags)
    {
        boolean isOK = CategoriesEnum.matchWithCategories(code);
        tags.getTagsVector().add ( isOK ? 1 : 0);
    }

    public static void validate(ProductsDTO productDTO)
    {
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
                    tags.getTags().add(CategoriesEnum.ramdomCategoris().name().toUpperCase());
                }

            }
        });

    }

    public static ProductsDTO euclideanDistance(List<Integer> index, ProductsDTO productsDTO)
    {
        ProductsDTO productsSimilarity  = new ProductsDTO();

        for(ProductDTO products : productsDTO.getProducts())
        {
            double similarity = 0.0;

            for (int i =0; i < products.getTagsVector().size(); i++)
            {
                similarity = similarity+Math.pow((index.get(i) - products.getTagsVector().get(i)),2.0);
            }
            products.setSimilarity(new BigDecimal(Math.sqrt(similarity)).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
            productsSimilarity.getProducts().add(products);
        }
        return productsSimilarity;

    }

    public static ProductsVO builderProductsVO(ProductsDTO productsDTO )
    {
        List<Object> productChargeVOS = new ArrayList<>();

        for(ProductDTO productDTO : productsDTO.getProducts())
        {
            productChargeVOS.add(ProductChargeVO.builder()
                    .id(productDTO.getId()).name(productDTO.getName())
                    .tags(productDTO.getTags()).
                            tagsVector(productDTO.getTagsVector()).build());
        }

        ProductsVO productsVO = new ProductsVO();
        productsVO.setProducts(productChargeVOS);

        return productsVO;
    }

    public static ProductsVO builderSpecificVO(ProductsDTO productsDTO )
    {
        List<Object> specificProducts = new ArrayList<>();

        for(ProductDTO productDTO : productsDTO.getProducts())
        {
            specificProducts.add(ProductSearchVO.builder().id(productDTO.getId()).name(productDTO.getName()).similarity(productDTO.getSimilarity()).build());
        }

        ProductsVO productsVO = new ProductsVO();
        productsVO.setProducts(specificProducts);

        return productsVO;
    }

}
