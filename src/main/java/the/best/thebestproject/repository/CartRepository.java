package the.best.thebestproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import the.best.thebestproject.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
}
