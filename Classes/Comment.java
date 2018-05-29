
package lt.Blogsite.Classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Comment {
    
    private Date dateTime;
    private String login;
    private ArrayList<String> content;
    private int size;

    public static final String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    
    public Comment() {
        dateTime = new Date();
        login = "";
        content = new ArrayList<String>();
    }
    
    public Comment(Comment comment) {
        dateTime = new Date(comment.getDateTime().getTime());
        login = comment.login;
        content = (ArrayList<String>)comment.getContent().clone();
        size = size = content.size();
        ////userName = User.getLogin(login);
        
    }
    public String getUserName(){
        return login;
    }
   /// public void String setUserName(){
   /// }
    
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
    public String getDateTimeAsString() {
        SimpleDateFormat sdfDate = new SimpleDateFormat(DATETIME_FORMAT);
        return sdfDate.format(dateTime);
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }    

    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(ArrayList<String> content) {
        this.content = (ArrayList<String>)content.clone();
    }
    
    public String getContentAsString() {
        StringBuilder sb = new StringBuilder();
        int size = content.size();
        for (int i = 0; i < size; i++)
        {
            sb.append(content.get(i));
            sb.append("\n");
            sb.append("By " + login);
            
            if ((i + 1) < size) {
                sb.append("\n");
           
            }
        }
        
        
        
        return sb.toString();
    }
    

}
