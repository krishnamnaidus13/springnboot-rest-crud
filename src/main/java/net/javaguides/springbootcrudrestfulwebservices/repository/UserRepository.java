package net.javaguides.springbootcrudrestfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springbootcrudrestfulwebservices.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
