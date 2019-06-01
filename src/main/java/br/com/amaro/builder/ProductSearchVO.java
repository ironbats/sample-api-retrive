package br.com.amaro.builder;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProductSearchVO {

    private long id;
    private String name;
    private Double similarity;
}
