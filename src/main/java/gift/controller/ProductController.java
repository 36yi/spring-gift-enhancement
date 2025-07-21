package gift.controller;

import gift.model.Product;
import gift.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return productDao.findAll(pageable);
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