package br.com.amaro.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonPropertyOrder({
        "products"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProductsDTO {

    private List<ProductDTO> products;


}
