package br.com.amaro.service;

import br.com.amaro.dto.ProductDTO;
import br.com.amaro.dto.ProductsDTO;
import br.com.amaro.util.AmaroUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AmaroService {


    public ProductsDTO retriveProducts()
    {       
       ProductsDTO productsDTO = AmaroUtils.create();
       AmaroUtils.validate(productsDTO);
       productsDTO.getProducts().forEach(product -> AmaroUtils.apply(product));

        return productsDTO;
    }


    //TODO refactor
    public ProductsDTO searchEuclidianSimilarityProducts(long id)
    {
        ProductsDTO productsDTO = AmaroUtils.create();
        AmaroUtils.validate(productsDTO);
        productsDTO.getProducts().forEach(product -> AmaroUtils.apply(product));

        ProductDTO principalProduct =  productsDTO.getProducts().stream().filter( p -> p.getId() == id).findAny().orElse(null);
        if(principalProduct != null)
        {
            ///exclude to match
            productsDTO.getProducts().remove(principalProduct);
            ProductsDTO euclideanProducts = AmaroUtils.euclideanDistance(principalProduct.getTagsVector(),productsDTO);

            List<ProductDTO> similarity = euclideanProducts.getProducts().stream().sorted(Comparator.comparing(ProductDTO::getSimilarity).reversed()).collect(Collectors.toList());
            ProductsDTO similarProducts = new ProductsDTO();
            similarProducts.setProducts(similarity.stream().limit(3).collect(Collectors.toList()));
            return similarProducts;

        }

        return null;
    }

}
