package kshopov.web.eprescription.services;

import kshopov.web.eprescription.model.IdentifierType;
import kshopov.web.eprescription.repositories.IdentifierRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdentifierServiceImpl implements IdentifierService {

    private final IdentifierRepository identifierRepository;

    public IdentifierServiceImpl(IdentifierRepository identifierRepository) {
        this.identifierRepository = identifierRepository;
    }

    @Override
    public IdentifierType insertIdentifier(IdentifierType identifier) {
        return identifierRepository.save(identifier);
    }

    @Override
    public List<IdentifierType> getAll() {
        List<IdentifierType> identifierTypes = new ArrayList<>();
        Iterable<IdentifierType> identifiers = identifierRepository.findAll();
        for (IdentifierType identifier : identifiers) {
            identifierTypes.add(identifier);
        }

        return identifierTypes;
    }

    @Override
    public Iterable<IdentifierType> saveAll(Iterable<IdentifierType> identifiers) {
        return identifierRepository.saveAll(identifiers);
    }

    @Override
    public long count() {
        return identifierRepository.count();
    }
}
