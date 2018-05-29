
package lt.Blogsite.Classes;

import java.io.IOException;

public class FileHelper {

    private static final String BLOGS_DIR = "\\Data\\Blogs";
    private static final String DIRECTORY_FILENAME_SEPARATOR = "\\";
    
    private static final String IMAGES_DIR = "\\Data\\Images";
    private static final String USERS_DIR = "\\Data";
    
    public String getBlogsPath() {
        try {
            return new java.io.File(".").getCanonicalPath() + BLOGS_DIR;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    public String getBlogsPathFileName(String fileName) {
        return getBlogsPath() + DIRECTORY_FILENAME_SEPARATOR + fileName;
    }
    
    public String getImagesPath() {
        try {
            return new java.io.File(".").getCanonicalPath() + IMAGES_DIR;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    public String getImagesPathFileName(String fileName) {
        return getImagesPath() + DIRECTORY_FILENAME_SEPARATOR + fileName;
    }
    
    public String getUsersPath() {
        try {
            return new java.io.File(".").getCanonicalPath() + USERS_DIR;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    public String getUsersPathFileName(String fileName) {
        return getUsersPath() + DIRECTORY_FILENAME_SEPARATOR + fileName;
    }
    
}
