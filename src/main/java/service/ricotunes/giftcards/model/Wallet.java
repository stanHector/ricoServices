package service.ricotunes.giftcards.model;


import javax.persistence.*;

@Entity
@Table(name = "wallet")
public class Wallet extends DateAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double currentBalance;

    @JoinColumn(name = "user_id")
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;


    public Wallet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
