package dev.springfirst.productservicedec24.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.springfirst.productservicedec24.models.Product;
import dev.springfirst.productservicedec24.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

/* The end goal of this mvc test is can we successfully invoke the endpoints in the ProductController and get back the expected results */

//These are unit tests for endpoints which cannot be tested by ProductControllerTest
@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;
    /**
     * Test that the endpoint exists and runs successfully and
     * returns a status code of 200 OK.
     */

    @Test
    public void test_GetAllProducts_RunSuccessfully() throws Exception {

        mockMvc.perform(get("/products")).andExpect(status().isOk());

        //Arrange
        Long id=2L;

        Product product=new Product();
        product.setId(id);
        product.setTitle("Iphone 12");
        List<Product> productList=new ArrayList<>();
        productList.add(product);
        when(productService.getAllProducts()).thenReturn(productList);


        String expectedResponse= objectMapper.writeValueAsString(productList);

        //Act
        mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(content().string(expectedResponse));

    }


}
