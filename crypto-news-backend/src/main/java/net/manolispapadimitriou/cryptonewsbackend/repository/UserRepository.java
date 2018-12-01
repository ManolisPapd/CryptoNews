package net.manolispapadimitriou.cryptonewsbackend.repository;

import net.manolispapadimitriou.cryptonewsbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
    public User findByUsernameOrEmail(String username,String email);
    public User findByEmail(String email);
}
