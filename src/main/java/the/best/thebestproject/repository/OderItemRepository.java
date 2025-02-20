package the.best.thebestproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import the.best.thebestproject.model.OderItem;

@Repository
public interface OderItemRepository extends JpaRepository<OderItem, String> {
}
