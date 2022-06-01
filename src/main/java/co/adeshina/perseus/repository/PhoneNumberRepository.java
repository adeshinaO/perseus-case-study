package co.adeshina.perseus.repository;

import co.adeshina.perseus.model.entity.PhoneNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Long> {
}
