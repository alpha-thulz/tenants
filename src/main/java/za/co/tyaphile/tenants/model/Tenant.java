package za.co.tyaphile.tenants.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private double rentalFee;

    @OneToOne(mappedBy = "tenant")
    private Room room;


    public Tenant(String name, String surname, String email, String phone, double rentalFee) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.rentalFee = rentalFee;
    }
}
