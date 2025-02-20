package the.best.thebestproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import the.best.thebestproject.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
