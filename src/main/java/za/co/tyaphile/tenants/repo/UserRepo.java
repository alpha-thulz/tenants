package za.co.tyaphile.tenants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.tyaphile.tenants.model.User;

public interface UserRepo extends JpaRepository<User, String> {

}