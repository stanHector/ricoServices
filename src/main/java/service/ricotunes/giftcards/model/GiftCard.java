package service.ricotunes.giftcards.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="gift_card")
public class GiftCard extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name= "name")
    private String name;

    @NotBlank
    @Column(name= "type")
    private String type;

    @NotBlank
    @Column(name= "category")
    private String category;

//    @NotBlank
    @Column(name= "amount")
    private double amount;

    //TODO
    //image
    @Column(name= "image",  length = 4096, columnDefinition = "TEXT")
    private String image;

    public GiftCard() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
