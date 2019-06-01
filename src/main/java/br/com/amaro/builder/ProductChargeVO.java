package br.com.amaro.builder;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ProductChargeVO {

    private long id;
    private String name;
    private List<String> tags;
    private List<Integer> tagsVector = new ArrayList<>();
}
