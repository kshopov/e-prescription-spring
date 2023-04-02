package kshopov.web.eprescription.repositories;

import kshopov.web.eprescription.model.IdentifierType;
import org.springframework.data.repository.CrudRepository;

public interface IdentifierRepository extends CrudRepository<IdentifierType, Long> {
}
