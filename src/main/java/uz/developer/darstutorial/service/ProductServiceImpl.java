package uz.developer.darstutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.darstutorial.exception.ResourceNotFoundException;
import uz.developer.darstutorial.model.Product;
import uz.developer.darstutorial.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> product1 = this.productRepository.findById(product.getId());

        if (product1.isPresent()){
            Product productUpdate = product1.get();
            productUpdate.setId(product.getId());
            productUpdate.setName(product.getName());
            productUpdate.setDescription(product.getDescription());
            productRepository.save(productUpdate);
            return productUpdate;
        }
        else {
            throw new ResourceNotFoundException("Record not found with id: " + product.getId());
        }
    }

    @Override
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(long productId) {
       Optional<Product> optionalProduct = this.productRepository.findById(productId);
       if (optionalProduct.isPresent()){
           return optionalProduct.get();
       }
       else {
           throw new ResourceNotFoundException("Record not found with id: " + productId);
       }
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (optionalProduct.isPresent()){
            this.productRepository.delete(optionalProduct.get());
        }
        else {
            throw new ResourceNotFoundException("Record not found with id: " + id);
        }
    }
}
