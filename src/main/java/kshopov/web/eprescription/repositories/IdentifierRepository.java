package kshopov.web.eprescription.repositories;

import kshopov.web.eprescription.model.Identifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentifierRepository extends JpaRepository<Identifier, Long> {
}
