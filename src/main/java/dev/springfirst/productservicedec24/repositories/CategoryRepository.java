package dev.springfirst.productservicedec24.repositories;

import dev.springfirst.productservicedec24.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Select * from Category where title like "apparel"
    // JPA methods => Declared queries
    Category findByTitle(String title);
}
