package the.best.thebestproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import the.best.thebestproject.model.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, String> {
}
