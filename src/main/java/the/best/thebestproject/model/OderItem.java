package the.best.thebestproject.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "oder_items")
public class OderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    Orders orders;


}
