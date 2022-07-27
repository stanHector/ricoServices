package service.ricotunes.giftcards.service;

import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.Visitor;
import service.ricotunes.giftcards.repository.VisitorRepository;

@Service
public class VisitorService {


    private VisitorRepository repository;

    public VisitorService(VisitorRepository repository) {
        this.repository = repository;
    }

    public Visitor saveVisitorInfo(Visitor visitor) {
        return repository.save(visitor);
    }

}
