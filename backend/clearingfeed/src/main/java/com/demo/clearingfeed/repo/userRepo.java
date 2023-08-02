package com.demo.clearingfeed.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.demo.clearingfeed.entity.UserLogin;

@EnableJpaRepositories
@Repository
public interface userRepo extends JpaRepository<UserLogin,Integer> {
	Optional<UserLogin> findOneByEmailAndPassword(String email, String password);
    UserLogin findByEmail(String email);
}
