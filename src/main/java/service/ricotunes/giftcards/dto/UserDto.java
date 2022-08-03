package service.ricotunes.giftcards.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {

    private long id;

    @NotBlank
    private String fullname;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotBlank
    private String password;


    public UserDto() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
