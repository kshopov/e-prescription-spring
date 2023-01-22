package kshopov.web.eprescription.bootstrap;

import kshopov.web.eprescription.model.Identifier;
import kshopov.web.eprescription.services.IdentifierService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    private final IdentifierService identifierService;

    public BootstrapData(IdentifierService identifierService) {
        this.identifierService = identifierService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(identifierService.count() == 0) {
            List<Identifier> identifiersList = Arrays.asList(
                    new Identifier("ЕГН", "EGN", "v1.0.0", null),
                    new Identifier("ЛНЧ", "LNZ", "v1.0.0", null),
                    new Identifier("Социален номер - за чужди граждани", "SSN", "v1.0.0", null),
                    new Identifier("Номер на паспорт", "PASS", "v1.0.0", null),
                    new Identifier("Друг идентификатор", "Other", "v1.0.0", null),
                    new Identifier("Новородено", "NBN", "v1.3.7", null)
            );
            identifierService.saveAll(identifiersList);
        }
    }
}
