package dev.springfirst.productservicedec24.controllers;

import dev.springfirst.productservicedec24.Exceptions.ProductNotFoundException;
import dev.springfirst.productservicedec24.dtos.CreateProductRequestDto;
import dev.springfirst.productservicedec24.models.Product;
import dev.springfirst.productservicedec24.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {


    public ProductService productService;

   public ProductController(@Qualifier("FakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    // GET /products
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("products/paginated")
    Page<Product> getPaginatedProducts(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
       //should return page of prod or list of prod to frontend
        //explore how to convert page<T> to list<T>
            return productService.getPagenatedProducts(pageNo, pageSize);

    }
    // GET /products/{id}
    @GetMapping("/products/{id}")
    /*public Product getSingleProduct(@PathVariable("id") long id){
        return productService.getSingleProduct(id);
    }*/
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException {

        Product p = productService.getSingleProduct(id);
        ResponseEntity<Product> responseEntity;
        if (p == null) {
            responseEntity = new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(p, HttpStatus.OK);
        }


        return responseEntity;
    }
    /*
    Create a product
    {
    title:
    description:
    price:
    category:
    }

     */
    // POST /products
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){
        return productService.CreateProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getCategory()); 
    }
}
