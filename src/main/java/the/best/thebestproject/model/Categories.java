package the.best.thebestproject.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "categories")
public class Categories {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    String id;
    String name;

    @OneToMany(mappedBy = "categories")
    Set<Product> products;
}
