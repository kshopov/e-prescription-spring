package kshopov.web.eprescription.services;

import kshopov.web.eprescription.model.IdentifierType;

public interface IdentifierService {

    public IdentifierType insertIdentifier(IdentifierType identifier);

    public Iterable<IdentifierType> saveAll(Iterable<IdentifierType> identifiers);

    public Iterable<IdentifierType> getAll();

    public long count();

}
