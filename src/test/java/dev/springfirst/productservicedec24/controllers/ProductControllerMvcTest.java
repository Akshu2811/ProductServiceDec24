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

    @Test // Indicates that this method is a test case to be executed by the testing framework.
    public void test_GetAllProducts_RunSuccessfully() throws Exception {
        // Simulates a GET request to the /products endpoint and expects a successful response (HTTP 200).
        mockMvc.perform(get("/products")).andExpect(status().isOk());

        // Arrange phase: Setting up the test data.
        Long id = 2L; // Product ID
        Product product = new Product(); // Creating a new Product instance.
        product.setId(id); // Setting the ID of the product.
        product.setTitle("Iphone 12"); // Setting the title of the product.
        List<Product> productList = new ArrayList<>(); // Creating a list to hold products.
        productList.add(product); // Adding the created product to the list.
        // Mocking the productService to return the productList when getAllProducts is called.
        when(productService.getAllProducts()).thenReturn(productList);

        // Preparing the expected JSON response.
        String expectedResponse = objectMapper.writeValueAsString(productList);

        // Act phase: Performing the GET request again and verifying the response.
        mockMvc.perform(get("/products"))
               .andExpect(status().isOk()) // Expecting a successful response.
               .andExpect(content().string(expectedResponse)); // Expecting the response content to match the expected JSON.
    }
}
