package lt.Blogsite.GUI;

import java.util.Date;
import javax.swing.JOptionPane;

import lt.Blogsite.Classes.Blogs;
import lt.Blogsite.Classes.Comment;
import lt.Blogsite.Classes.User;
import lt.Blogsite.Classes.Users;
import lt.Blogsite.Classes.Blog;

public class MainFrame extends javax.swing.JFrame {
    
    private Users users = null;
    private String commonUser = null;
    private boolean administrator = false;

    Blogs blogs = null;
    Blogs searchedBlogs = null;
    
    private Blogs getBlogs() {
        if (searchedBlogs != null) {
            return searchedBlogs;
        }
        return blogs;
    }
    
    public static final int FIRST_BLOG = 0;
    public static final int MAX_BLOGS_PER_PAGE = 2;
    
    LoginPanel lp;
    BlogsPanel bsp;
//    BlogPanel bp;
    AddCommentPanel acp;
    AddBlogPanel abp;
    AddUserPanel aup;
    
    private Object JLabel;
    
    private int firstInList = FIRST_BLOG;

    /**
     * Creates new form PagrindinisLangasJFrame
     */
    public MainFrame(Users users, Blogs blogs) {
        this.users = users;
        // commonUser = "";
        this.blogs = blogs;
        
        initComponents();
        
        lp = new LoginPanel(this);
        getContentPane().add(lp);
    }
    
    public String getCommonUser() {
        return commonUser;
    }
    
    public boolean hasUser() {
        return ((commonUser != null) && (!commonUser.equals("")));
    }
    
    public void addToList(int from) {
        firstInList = from;
        int size = getBlogs().size();
        for (int i = firstInList; (i < size) && ((i - firstInList) < MAX_BLOGS_PER_PAGE); i++) {
            BlogPreviewPanel bpp = new BlogPreviewPanel(this, getBlogs().getBlog(i));
            bsp.addTo(bpp);
        }
    }
  
    public void Login() {
        commonUser = "";
        String login = lp.getLogin();
        String password = lp.getPassword();
        int size = users.size();
        for (int i = 0; i < size; i++) {
            User user = users.getUser(i);
            if ((user.getLogin().equals(login)) && (user.getPassword().equals(password))) {
                commonUser = user.getLogin();
                administrator = (i == 0);
                break;
            }
        }
        getContentPane().removeAll();
        bsp = new BlogsPanel(this);
        addToList(firstInList);
        getContentPane().add(bsp);
        invalidate();
        validate();
        bsp.setButtonsState(firstInList > 0, (firstInList + MAX_BLOGS_PER_PAGE) < getBlogs().size(), administrator, (searchedBlogs != null));
    }
    
    public void finishApplication() {
        System.exit(NORMAL);
    }
    
    public void prevBlogs() {
        bsp.removeFrom();
        firstInList = (firstInList > MAX_BLOGS_PER_PAGE) ? (firstInList - MAX_BLOGS_PER_PAGE) : FIRST_BLOG;
        addToList(firstInList);
        bsp.setButtonsState(firstInList > 0, (firstInList + MAX_BLOGS_PER_PAGE) < getBlogs().size(), administrator, (searchedBlogs != null));
    }
    
    public void nextBlogs() {
        bsp.removeFrom();
        int size = getBlogs().size();
        firstInList = ((firstInList + MAX_BLOGS_PER_PAGE) < size) ? (firstInList + MAX_BLOGS_PER_PAGE) : (size - MAX_BLOGS_PER_PAGE);
        addToList(firstInList);        
        bsp.setButtonsState(firstInList > 0, (firstInList + MAX_BLOGS_PER_PAGE) < size, administrator, (searchedBlogs != null));
    }

    public void showBlog(Blog blog) {
        getContentPane().removeAll();
        BlogPanel bp = new BlogPanel(this, blog);
        getContentPane().add(bp);
        invalidate();
        validate();
    }
    
    public void showBlogs() {
        getContentPane().removeAll();
        bsp = new BlogsPanel(this);
        int size = getBlogs().size();
        addToList(firstInList);
        bsp.setButtonsState(firstInList > 0, (firstInList + MAX_BLOGS_PER_PAGE) < size, administrator, (searchedBlogs != null));
        getContentPane().add(bsp);
        invalidate();
        validate();
    }
    
    public void showAddComment(Blog blog) {
        getContentPane().removeAll();
        acp = new AddCommentPanel(this, blog);
        getContentPane().add(acp);
        invalidate();
        validate();
    }
    
    public void addComment(Blog blog) {
        Comment comment = new Comment();
        comment.setDateTime(new Date());
        comment.setLogin(commonUser);
        comment.setContent(acp.getComment());
        blog.addComment(comment);
        blog.addComment(commonUser);
        blog.writeToFile();
        showBlog(blog);
    }
    
    public void showAddBlog() {
        getContentPane().removeAll();
        abp = new AddBlogPanel(this);
        getContentPane().add(abp);
        invalidate();
        validate();
    }
    
    public void addBlog() {
        Blog blog = abp.getBlog();
        getBlogs().addBlog(blog);
        blog.writeToFile();
        
        getContentPane().removeAll();
        bsp = new BlogsPanel(this);
        addToList(firstInList);
        getContentPane().add(bsp);
        invalidate();
        validate();
        bsp.setButtonsState(firstInList > 0, (firstInList + MAX_BLOGS_PER_PAGE) < getBlogs().size(), administrator, (searchedBlogs != null));
    }

    public void showAddUser() {
        getContentPane().removeAll();
        aup = new AddUserPanel(this);
        getContentPane().add(aup);
        invalidate();
        validate();
    }
    
    public void addUser() {
        User user = aup.getNewUser();
        users.addUser(user);
        users.writeToFile();
        
        getContentPane().removeAll();
        bsp = new BlogsPanel(this);
        addToList(firstInList);
        getContentPane().add(bsp);
        invalidate();
        validate();
        bsp.setButtonsState(firstInList > 0, (firstInList + MAX_BLOGS_PER_PAGE) < getBlogs().size(), administrator, (searchedBlogs != null));
    }
    
    public void searchFor() {
        String str = JOptionPane.showInputDialog("Search for contains text: ");
        System.out.println(str);
        searchedBlogs = new Blogs(blogs, str);

        getContentPane().removeAll();
        bsp = new BlogsPanel(this);
        addToList(firstInList);
        getContentPane().add(bsp);
        invalidate();
        validate();
        bsp.setButtonsState(firstInList > 0, (firstInList + MAX_BLOGS_PER_PAGE) < getBlogs().size(), administrator, (searchedBlogs != null));

    }
    
    public void showAllBlogs() {
        searchedBlogs = null;
        
        getContentPane().removeAll();
        bsp = new BlogsPanel(this);
        addToList(firstInList);
        getContentPane().add(bsp);
        invalidate();
        validate();
        bsp.setButtonsState(firstInList > 0, (firstInList + MAX_BLOGS_PER_PAGE) < getBlogs().size(), administrator, (searchedBlogs != null));

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.GridLayout(0, 1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        final Users users = new Users();
        final Blogs blogs = new Blogs();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(users, blogs).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
