
package lt.Blogsite.Classes;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class DataFile {
    
    private String fileName;
    
    public static final String CHARSET_NAME = "UTF-8";
    
    protected ArrayList<String> data = null;
    
    public DataFile(String fileName) {
        this.fileName = fileName;
        data = new ArrayList<String>();
    }
    
    public void load() {
        data.clear();
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(fileName), CHARSET_NAME));

            String str;
            while ((str = in.readLine()) != null) {
                data.add(str);
            }

            in.close();
        } catch (UnsupportedEncodingException e) {
            // System.out.println(e.getMessage());
        } catch (IOException e) {
            // System.out.println(e.getMessage());
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
    }
    
}
