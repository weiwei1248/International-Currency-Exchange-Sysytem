package ca.uwindsor.ices.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import ca.uwindsor.ices.jpa.User;

@Repository
public interface UserRepository extends BaseRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    
    User findOne(int usid);
    
}
