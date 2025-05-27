package dev.springfirst.productservicedec24.services;

import dev.springfirst.productservicedec24.Exceptions.ProductNotFoundException;
import dev.springfirst.productservicedec24.models.Category;
import dev.springfirst.productservicedec24.models.Product;
import dev.springfirst.productservicedec24.repositories.CategoryRepository;
import dev.springfirst.productservicedec24.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Primary
@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException{
        // to get single product from the product table
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            //if product with the given id does not exist
            throw new ProductNotFoundException("Product with the given id does not exist");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
        //get all products from product table
    }

    @Override
    public Product CreateProduct(String title,
                                 String description,
                                 double price,
                                 String imageUrl,
                                 String category) {

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        //you need to get the corresponding category object from category table
        Category categoryFromDB=categoryRepository.findByTitle(category);

        if(categoryFromDB==null){
            Category newCategory=new Category();
            newCategory.setTitle(category);
            product.setCategory(newCategory);
        }
        else{
            product.setCategory(categoryFromDB);
        }

        Product createdProduct = productRepository.save(product);

        return createdProduct;

    }

    @Override
    public Page<Product> getPagenatedProducts(int pageNo, int pageSize) {//controller class will give the number and size
        //Pageable was an interface
        //PageRequest is a class
        //Can I pass an object of PageRequest class in the place of
        //Animal<----Dog(Animal animal=new Dog();)
        //Pageable<---PageRequest
        return productRepository.findAll(PageRequest.of(pageNo, pageSize,
                Sort.by("title").descending().and(Sort.by("price").ascending())));
    }
}
