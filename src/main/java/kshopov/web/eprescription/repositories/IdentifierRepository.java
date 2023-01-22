package kshopov.web.eprescription.repositories;

import kshopov.web.eprescription.model.Identifier;
import org.springframework.data.repository.CrudRepository;

public interface IdentifierRepository extends CrudRepository<Identifier, Long> {
}
