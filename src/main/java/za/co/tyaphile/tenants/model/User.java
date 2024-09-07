package za.co.tyaphile.tenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users_tbl")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false)
    private String username;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Pattern(regexp = "^[+]?(\\d{1,2})?[\\s.-]?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
    private String phone;

    @OneToMany(mappedBy ="user", orphanRemoval = true)
    private List<Building> building;

    public User(String username, String password, String firstName, String lastName, String email, String phone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public User(String id, String username, String password, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}