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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    String id;

    String name;

    String description;

    String price;

    String image;

    int stock;

    @ManyToOne
    @JoinColumn(name = "categories_id", referencedColumnName = "id")
    Categories categories;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    Set<CartItem> cartItems;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    Set<OderItem> oderItems;


}
