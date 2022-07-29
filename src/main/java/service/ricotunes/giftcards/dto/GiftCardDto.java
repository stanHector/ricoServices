package service.ricotunes.giftcards.dto;

import javax.validation.constraints.NotBlank;

public class GiftCardDto {
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String category;

    @NotBlank
    private String amount;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
