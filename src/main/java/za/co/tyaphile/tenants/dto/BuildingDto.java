package za.co.tyaphile.tenants.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDto {
    @NotBlank(message = "Building name cannot be empty")
    private String name;
    @NotBlank(message = "Building address cannot be empty")
    private String address;
}
