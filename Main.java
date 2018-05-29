
package lt.Blogsite;

import lt.Blogsite.Classes.Blogs;
import lt.Blogsite.GUI.MainFrame;
import lt.Blogsite.Classes.Users;

public class Main {
    
    public static void main(String[] args) {
        final Users users = new Users();
        final Blogs blogs = new Blogs();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(users, blogs).setVisible(true);
            }
        });

//        try {
//            String currentDir = new java.io.File(".").getCanonicalPath();
//            // System.out.println("Current dir:" + currentDir);
//            new BlogDataFile(currentDir + "\\Data\\Blogs\\2017-04-25 (1).txt");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        // new Blogs();
        
//        Date date = new Date();
//        System.out.println(date);
        //date.

//        try {
//            String current = new java.io.File( "." ).getCanonicalPath();
//            System.out.println("Current dir:" + current);
//
//            String fileFullPath = current + "\\Data\\Blogs\\2017-04-25 (1).txt";
//            File fileDir = new File(fileFullPath);

    }
    
}
