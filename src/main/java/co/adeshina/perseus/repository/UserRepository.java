package co.adeshina.perseus.repository;

import co.adeshina.perseus.model.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
}
