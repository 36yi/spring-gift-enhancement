package gift.Entity;


import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 15)
    private String name;

    @Column(nullable = false, length = 255)
    private String image_url;

}