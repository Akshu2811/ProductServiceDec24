package dev.springfirst.productservicedec24.services;

import dev.springfirst.productservicedec24.dtos.CreateProductRequestDto;
import dev.springfirst.productservicedec24.dtos.FakeStoreProductDto;
import dev.springfirst.productservicedec24.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoryProductService implements ProductService {

    private RestTemplate restTemplate;//using this you will be able to call third party apis

    private RedisTemplate redisTemplate;
    public FakeStoryProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
            /*
            Call the external fakestore API
            'https://fakestoreapi.com/products'
             */
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products1 = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product p = fakeStoreProductDto.toProduct();
            products1.add(p);
        }

        return products1;
    }

    @Override
    public Product getSingleProduct(long id) {
        /*
        Call the external fakestory product API
        'https://fakestoreapi.com/products/1'


         */
        //FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        Product cachedProduct=(Product)redisTemplate.opsForHash().get("Products","Products_"+id);
        if(cachedProduct!=null){
            /*
            Cache HIT
             */
            return cachedProduct;
        }

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if(fakeStoreProductDtoResponseEntity.getStatusCode() != HttpStatusCode.valueOf(200)){
            //handle this exception
        }
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

        //fakeStoreProductDtoResponseEntity.getHeaders();
        if(fakeStoreProductDto == null){
            return null;
        }
        Product response = fakeStoreProductDto.toProduct();
        redisTemplate.opsForHash().put("Products","Products_"+id,response);
        return response;
    }
    /*
    Call the external fakestory product API
    "https://fakestoreapi.com/products"

     */
    @Override
    public Product CreateProduct(String title,
                                 String description,
                                 double price,
                                 String imageUrl,
                                 String category) {
       /* FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setDescription(description);

        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class);
    */
        return null;
    }

    @Override
    public Page<Product> getPagenatedProducts(int pageNo, int pageSize) {
        return null;
    }
}
