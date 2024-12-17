package za.co.tyaphile.tenants.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.tyaphile.tenants.model.role.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDao {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private Role role;
}