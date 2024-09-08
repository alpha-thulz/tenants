package za.co.tyaphile.tenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate rentalDate;
    private LocalDate joinDate;

    @JsonIgnore
    @ManyToOne
    private Room room;

    public Tenant(String name, String surname, String email, String phone, double rentalFee, LocalDate rentalDate,
                  LocalDate joinDate, Room room) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.rentalFee = rentalFee;
        this.rentalDate = rentalDate;
        this.joinDate = joinDate;
        this.room = room;
    }
}
