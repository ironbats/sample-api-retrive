package br.com.amaro.service;

import br.com.amaro.dto.ProductsDTO;
import br.com.amaro.util.AmaroUtils;
import org.springframework.stereotype.Service;


@Service
public class AmaroService {




    public ProductsDTO retriveProducts()
    {       
       ProductsDTO productsDTO = AmaroUtils.create();
       AmaroUtils.validate(productsDTO);
       productsDTO.getProducts().forEach(product -> AmaroUtils.apply(product));

        return productsDTO;
    }

}
