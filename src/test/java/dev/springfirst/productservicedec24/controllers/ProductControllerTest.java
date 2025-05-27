package dev.springfirst.productservicedec24.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import dev.springfirst.productservicedec24.Exceptions.ProductNotFoundException;
import dev.springfirst.productservicedec24.dtos.CreateProductRequestDto;
import dev.springfirst.productservicedec24.models.Product;
import dev.springfirst.productservicedec24.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;




@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockBean
    ProductService productService;

    /**
     * Test case for verifying successful retrieval of product details when a valid product ID is provided.
     */
    @Test
    public void testGetAllProductDetailsWithPositiveId_returnsProductSuccessfully() throws ProductNotFoundException {

        //Arrange
        Long id=2L;

        Product product=new Product();
        product.setId(id);
        product.setTitle("Iphone 12");

        when(productService.getSingleProduct(id)).thenReturn(product);

        //Act
        ResponseEntity<Product> response= productController.getSingleProduct(id);




        //Assert
        /*
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(),product.getId());
        assertEquals(response.getBody().getTitle(),product.getTitle());
        */
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());


    }

    /**
     * Test case for verifying successful creation of a product when valid product data is provided.
     */
    @Test
    public void testCreateProduct_returnsCreatedProductSuccessfully() {
        // Arrange
        CreateProductRequestDto requestDto = new CreateProductRequestDto();
        requestDto.setTitle("Test Product");
        requestDto.setDescription("Test Description");
        requestDto.setPrice(99.99);
        requestDto.setImage("test-image.jpg");
        requestDto.setCategory("Test Category");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle(requestDto.getTitle());
        expectedProduct.setDescription(requestDto.getDescription());
        expectedProduct.setPrice(requestDto.getPrice());
        expectedProduct.setImageUrl(requestDto.getImage());

        when(productService.CreateProduct(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getPrice(),
                requestDto.getImage(),
                requestDto.getCategory()
        )).thenReturn(expectedProduct);

        // Act
        Product actualProduct = productController.createProduct(requestDto);

        // Assert
        assertNotNull(actualProduct);
        assertEquals(expectedProduct.getId(), actualProduct.getId());
        assertEquals(expectedProduct.getTitle(), actualProduct.getTitle());
        assertEquals(expectedProduct.getDescription(), actualProduct.getDescription());
        assertEquals(expectedProduct.getPrice(), actualProduct.getPrice());
        assertEquals(expectedProduct.getImageUrl(), actualProduct.getImageUrl());
    }
}
