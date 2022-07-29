package service.ricotunes.giftcards.payload.response;

public class JwtAuthenticationResponse<T> {
    private String accessToken;
    private String message = "Login Successful";
//    private Long id;
//    private String firstname;
//    private String lastname;
//    private String phone;
//    private String email;

    public JwtAuthenticationResponse() {
    }

        public JwtAuthenticationResponse(String accessToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
//        this.id = id;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.phone = phone;
//        this.email = email;
    }

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

}
