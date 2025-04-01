package dev.springfirst.productservicedec24.services;

import dev.springfirst.productservicedec24.Exceptions.ProductNotFoundException;
import dev.springfirst.productservicedec24.dtos.CreateProductRequestDto;
import dev.springfirst.productservicedec24.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getSingleProduct(long id) throws ProductNotFoundException;

    Product CreateProduct(String title,
                          String description,
                          double price,
                          String imageUrl,
                          String category);

    Page<Product> getPagenatedProducts(int pageNo, int pageSize);

}
