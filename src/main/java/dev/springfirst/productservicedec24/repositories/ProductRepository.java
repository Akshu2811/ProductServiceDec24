package dev.springfirst.productservicedec24.repositories;

import dev.springfirst.productservicedec24.models.Category;
import dev.springfirst.productservicedec24.models.Product;
import dev.springfirst.productservicedec24.projections.ProductWithIdAndPriceProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll();

    @Override
    Page<Product> findAll(Pageable pageable);//pageno,pagesize
    //from which limit and which offset you need to fetch
    //page number always starts from zero
    //pageno=20 pagesize=25

    Product save(Product p);

    @Override
    Optional<Product> findById(Long id);

    // finding products by category object
     List<Product> findByCategory(Category category);

     // finding products by passing category title
    List<Product> findAllByCategory_Title(String title);

    //finding all products by passing category id
    List<Product> findAllByCategory_Id(Long id);

    /*
    We don't have complete control over the query that JPA will execute for us?
    I am interested only in certain columns I will provide the certain query
    HQL - Similar to SQL with a pinch of OOP
     */
    /*
    Providing the JPA to query can be done in two ways:
    1. HQL
    2. Native SQL
     */
    @Query("select p.id,p.price from Product p where p.category.title= :categoryName")
    List<ProductWithIdAndPriceProjection> getProductTitlesAndPriceAndGivenCategoryName(String categoryName);

}
