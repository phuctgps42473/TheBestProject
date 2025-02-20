package the.best.thebestproject.model;



import jakarta.persistence.*;
import lombok.*;
import the.best.thebestproject.enums.AddressType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "address")
public class Address {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false, updatable = false)
    private Users users;

    @Column(nullable = false, columnDefinition = "nvarchar(63)")
    private String street;

    @Column(nullable = false, columnDefinition = "nvarchar(31)")
    private String ward;

    @Column(nullable = false, columnDefinition = "nvarchar(31)")
    private String district;

    @Column(nullable = false, columnDefinition = "nvarchar(31)")
    private String city;

    @Column(columnDefinition = "nvarchar(255)")
    private String note;

    @Column(nullable = false)
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private AddressType type = AddressType.HOME;

}
