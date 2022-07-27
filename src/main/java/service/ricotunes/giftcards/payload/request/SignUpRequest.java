package service.ricotunes.giftcards.payload.request;


import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {

//	@NotBlank
	@Size(min = 3, max = 15)
	private String username;

    @NotBlank
    @Column(name = "firstname")
    @Size(max = 15)
    private String firstname;

    @NotBlank
    @Column(name = "lastname")
    @Size(max = 15)
    private String lastname;

    @NotBlank
    @Column(name = "phone")
    @Size(max = 15)
    private String phone;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

	@NotBlank
	@Size(min = 6, max = 20)
	private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
