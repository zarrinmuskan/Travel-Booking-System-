public class User {
    private String email;
    private String password;
    private String oauthToken;

    // Getters and setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getOauthToken() { return oauthToken; }
    public void setOauthToken(String oauthToken) { this.oauthToken = oauthToken; }
}