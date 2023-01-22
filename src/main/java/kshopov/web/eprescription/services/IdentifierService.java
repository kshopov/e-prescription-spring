package kshopov.web.eprescription.services;

import kshopov.web.eprescription.model.Identifier;

public interface IdentifierService {

    public Identifier insertIdentifier(Identifier identifier);

    public Iterable<Identifier> saveAll(Iterable<Identifier> identifiers);

    public Iterable<Identifier> getAll();

    public long count();

}
