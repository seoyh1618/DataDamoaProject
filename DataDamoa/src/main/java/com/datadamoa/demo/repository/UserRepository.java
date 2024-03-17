package com.datadamoa.demo.repository;
import com.datadamoa.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,UUID>{

}
