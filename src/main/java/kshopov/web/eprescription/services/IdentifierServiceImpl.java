package kshopov.web.eprescription.services;

import kshopov.web.eprescription.model.Identifier;
import kshopov.web.eprescription.repositories.IdentifierRepository;
import org.springframework.stereotype.Service;

@Service
public class IdentifierServiceImpl implements IdentifierService {

    private final IdentifierRepository identifierRepository;

    public IdentifierServiceImpl(IdentifierRepository identifierRepository) {
        this.identifierRepository = identifierRepository;
    }

    @Override
    public Identifier insertIdentifier(Identifier identifier) {
        return identifierRepository.save(identifier);
    }

    @Override
    public Iterable<Identifier> getAll() {
        return identifierRepository.findAll();
    }

    @Override
    public Iterable<Identifier> saveAll(Iterable<Identifier> identifiers) {
        return identifierRepository.saveAll(identifiers);
    }

    @Override
    public long count() {
        return identifierRepository.count();
    }
}
