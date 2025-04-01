package dev.springfirst.productservicedec24.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Category extends BaseModel{


    private String title;

    // duplicate relation already mentioned in product
    @OneToMany(mappedBy = "category",cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<Product> products; //electronics
    // Category : Product  1:m

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
