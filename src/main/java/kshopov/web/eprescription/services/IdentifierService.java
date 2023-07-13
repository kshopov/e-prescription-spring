package kshopov.web.eprescription.services;

import kshopov.web.eprescription.model.IdentifierType;

import java.util.List;

public interface IdentifierService {

    public IdentifierType insertIdentifier(IdentifierType identifier);

    public Iterable<IdentifierType> saveAll(Iterable<IdentifierType> identifiers);

    public List<IdentifierType> getAll();

    public long count();

}
