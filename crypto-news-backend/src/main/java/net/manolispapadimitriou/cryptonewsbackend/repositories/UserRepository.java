package net.manolispapadimitriou.cryptonewsbackend.repositories;

import net.manolispapadimitriou.cryptonewsbackend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
    public User findByEmail(String email);
}
