package za.co.tyaphile.tenants.dao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantDao {
    @NotBlank(message = "Please enter tenant's first (and second if available) name")
    private String name;
    @NotBlank(message = "Please enter a valid tenant Surname")
    private String surname;
    @Email
    private String email;
    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}",
            message = "Please enter a valid phone number consisting of 10 numbers e.g: 0831234567")
    @NotBlank
    private String phone;
    @NotNull(message = "Please enter amount the tenant will have to pay monthly")
    private double rentalFee;
    @NotNull(message = "Please enter the date when the rent is due")
    private LocalDate rentalDate;
}
