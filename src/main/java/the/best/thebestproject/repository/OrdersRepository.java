package the.best.thebestproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import the.best.thebestproject.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, String> {
}
