package gift.controller;

import gift.model.Product;
import gift.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ProductController {
    private final ProductRepository productDao;

    public ProductController(ProductRepository productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));
    }

    @PostMapping("/products")
    public void addProduct(@Valid @RequestBody Product product) {
        if(!product.getName().contains("카카오")){
            product.setMdApproved(true);
        }
        productDao.save(product);
    }

    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productDao.deleteById(id);
    }

    @PatchMapping("/products/{id}")
    public void updateProduct(@Valid @PathVariable Long id, @RequestBody Product product) {
        if(!product.getName().contains("카카오")){
            product.setMdApproved(true);
        }else{
            product.setMdApproved(false);
        }
        productDao.save(product);
    }

}