package uz.developer.darstutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developer.darstutorial.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
