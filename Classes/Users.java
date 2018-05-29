
package lt.Blogsite.Classes;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

public class Users {
    
    private ArrayList<User> data;
    
    public static final String USERS_FILENAME = "users.txt";
    
    public Users() {
        data = new ArrayList<User>();
        load();
    }
    
    public int size() {
        return data.size();
    }
    
    public User getUser(int index) {
        if ((index < 0) || (index > (data.size() - 1))) {
            return null;
        }
        return data.get(index);
    }
    
    public void addUser(User user) {
        data.add(user);
    }
    
    private void load() {
        FileHelper fh = new FileHelper();
        UserDataFile udf = new UserDataFile(fh.getUsersPathFileName(USERS_FILENAME));
        data = udf.getUsers();
    }

    public void writeToFile() {
        try {
            StringBuilder sb = new StringBuilder();
            int size = data.size();
            for (int i = 0; i < size; i++) {
                sb.append(data.get(i).getDataToFileAsString());

                if ((i + 1) < size) {
                    sb.append("\n");
                    sb.append("\n");
                }
            }
            FileHelper fh = new FileHelper();
            String fileName = fh.getUsersPathFileName(USERS_FILENAME);
            Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "UTF-8"));
            try {
                out.write(sb.toString());
            } finally {
                out.close();
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
}
