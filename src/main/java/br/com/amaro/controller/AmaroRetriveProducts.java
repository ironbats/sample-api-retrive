package br.com.amaro.controller;

import br.com.amaro.dto.ProductsDTO;
import br.com.amaro.service.AmaroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/retive-products")
@Controller
public class AmaroRetriveProducts {

    @Autowired
    private AmaroService amaroService;


    @GetMapping
    @ResponseBody
    public ProductsDTO getProductsAmaro()
    {
        return  amaroService.retriveProducts();
    }


    @GetMapping(value = "/findIdProduct/{id}")
    @ResponseBody
    public ProductsDTO searchSimilarProduct(@PathVariable long id)
    {
        return amaroService.searchEuclidianSimilarityProducts(id);
    }


}
