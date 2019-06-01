package br.com.amaro.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "name",
        "tags"
})
@JsonIgnoreProperties(ignoreUnknown =   true)
@Data
public class ProductDTO {

    private long id;
    private String name;
    private List<String> tags;
    private List<Integer> tagsVector = new ArrayList<>();
    private Double similarity;


}
