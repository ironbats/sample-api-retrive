package br.com.amaro;

import br.com.amaro.builder.ProductChargeVO;
import br.com.amaro.builder.ProductSearchVO;
import br.com.amaro.builder.ProductsVO;
import br.com.amaro.service.AmaroService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AmaroServiceTest {


    private AmaroService amaroService = new AmaroService();


    @Test
    public void testRetriverProducts() {

        ProductsVO productsVO =  amaroService.retriveProducts();

        Assert.assertNotNull(((ProductChargeVO)productsVO.getProducts().get(0)).getTagsVector());
        Assert.assertNotNull(((ProductChargeVO)productsVO.getProducts().get(0)).getTags());
    }


    @Test
    public void testSearchEuclidianSimilarityProducts()
    {
        long id = 8371;
        long limitSize = 3;

        ProductsVO productsVO  = amaroService.searchEuclidianSimilarityProducts(id);
        Assert.assertEquals(limitSize,productsVO.getProducts().size());
        Assert.assertNotNull(((ProductSearchVO)productsVO.getProducts().get(0)).getSimilarity());

    }


}
