package dev.springfirst.productservicedec24;

import dev.springfirst.productservicedec24.models.Product;
import dev.springfirst.productservicedec24.projections.ProductWithIdAndPriceProjection;
import dev.springfirst.productservicedec24.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceDec24ApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testProductRepository() {
        List<ProductWithIdAndPriceProjection> products=productRepository.getProductTitlesAndPriceAndGivenCategoryName("mobiles");
        for(ProductWithIdAndPriceProjection product:products){
            System.out.println("Id is "+product.getId()+" and price is "+product.getPrice());
        }
        System.out.println();

        //System.out.println(products);



    }

}
