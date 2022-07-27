package service.ricotunes.giftcards.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Integer> {

}