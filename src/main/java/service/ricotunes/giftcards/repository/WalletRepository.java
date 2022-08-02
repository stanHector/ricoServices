package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

//    Wallet findByUserId(long userId);
}
