
package lt.Blogsite.Classes;

public class User {
    
    private String login;
    private String password;
    private String email;
    
    public static final String SECTION_STARTS_WITH = "{";
    public static final String SECTION_ENDS_WITH = "}";
    public static final String USER_DATA_STARTS = "{user-comment-starts}";
    
    public static final String USER_LOGIN = "{user-login}";
    public static final String USER_PASSWORD = "{user-password}";
    public static final String USER_EMAIL = "{user-Email}";
    public static final String USER_DATA_ENDS = "{user-comment-ends}";
    
    public User() {
        login = "";
        password = "";
        email = "";
    }
    
    public User(User user) {
        login = user.login;
        password = user.password;
        email = user.email;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }    
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns text to fill in configuration file. 
     *
     * @return      Returns text to fill in configuration file
     */
    public String getDataToFileAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append(USER_DATA_STARTS);
        sb.append("\n");
        sb.append(USER_LOGIN);
        sb.append("\n");
        sb.append(login);
        sb.append("\n");
        sb.append(USER_PASSWORD);
        sb.append("\n");
        sb.append(password);
        sb.append("\n");
        sb.append(USER_EMAIL);
        sb.append("\n");
        sb.append(email);
        sb.append("\n");
        sb.append(USER_DATA_ENDS);
        return sb.toString();
    }

    
}
