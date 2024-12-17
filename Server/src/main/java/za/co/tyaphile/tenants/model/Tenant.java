package za.co.tyaphile.tenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
    private String phone;
    private double rentalFee;
    private LocalDate rentalDate;
    @CreationTimestamp
    private LocalDate createdAt;

    @JsonIgnore
    @ManyToOne
    private Room room;

    public Tenant(String name, String surname, String email, String phone, double rentalFee, LocalDate rentalDate, Room room) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.rentalFee = rentalFee;
        this.rentalDate = rentalDate;
        this.room = room;
    }
}
