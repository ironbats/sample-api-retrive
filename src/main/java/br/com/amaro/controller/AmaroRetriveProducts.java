package br.com.amaro.controller;

import br.com.amaro.builder.ProductsVO;
import br.com.amaro.service.AmaroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


@RequestMapping("/retive-products")
@Controller
public class AmaroRetriveProducts {

    @Autowired
    private AmaroService amaroService;


    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getProductsAmaro()  throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        ProductsVO productsDTO = amaroService.retriveProducts();
        return  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productsDTO);
    }


    @GetMapping(value = "/{id}",produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String searchSimilarProduct(@PathVariable long id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ProductsVO productsDTO = amaroService.searchEuclidianSimilarityProducts(id);
        return  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productsDTO);
    }


}
