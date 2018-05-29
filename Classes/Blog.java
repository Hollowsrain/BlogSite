
package lt.Blogsite.Classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Blog {
    
    private String fileName;
    private Date dateTime;
    private int dateTimeShiftMonths;
    private int dateTimeShiftDays;
    private int dateTimeShiftHours;
    private String title;
    private String pictureFileName;
    private ArrayList<String> preContent;
    private ArrayList<String> content;
    private ArrayList<Comment> comments;
    
    public static final String SECTION_STARTS_WITH = "{";
    public static final String SECTION_ENDS_WITH = "}";
    public static final String BLOG_DATETIME = "{blog-datetime}";
    
    public static final String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    // public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_STRING = "0000-00-00 00:00:00";
    
    public static final String BLOG_DATETIME_SHIFT_MONTHS = "{blog-datetime-shift-months}";
    public static final String BLOG_DATETIME_SHIFT_DAYS = "{blog-datetime-shift-days}";
    public static final String BLOG_DATETIME_SHIFT_HOURS = "{blog-datetime-shift-hours}";
    
    public static final String DEFAULT_BLOG_DATETIME_SHIFT_STRING = "0";
    public static final int DEFAULT_BLOG_DATETIME_SHIFT = 0;
    
    public static final String BLOG_TITLE = "{blog-title}";
    public static final String BLOG_PICTURE_FILE_NAME = "{blog-picture-file-name}";
    public static final String BLOG_PRE_CONTENT = "{blog-pre-content}";
    public static final String BLOG_CONTENT = "{blog-content}";
    public static final String BLOG_COMMENT_STARTS = "{blog-comment-starts}";
    public static final String BLOG_COMMENT_DATETIME = "{blog-comment-datetime}";
    public static final String BLOG_COMMENT_LOGIN = "{blog-comment-login}";
    public static final String BLOG_COMMENT_CONTENT = "{blog-comment-content}";
    public static final String BLOG_COMMENT_ENDS = "{blog-comment-ends}";
   
    /**
     * Constructs a new Blog.
     */
    public Blog() {
        fileName = "";
        dateTime = new Date();
        dateTimeShiftMonths = 0;
        dateTimeShiftDays = 0;
        dateTimeShiftHours = 0;
        title = "";
        pictureFileName = "";
        preContent = new ArrayList<String>();
        content = new ArrayList<String>();
        comments = new ArrayList<Comment>();
    }

    /**
     * Constructs a new Blog with the information in another Blog object.
     *
     * @param   blog   another Blog object
     */
    public Blog(Blog blog) {
        fileName = blog.getFileName();
        dateTime = new Date(blog.getDateTime().getTime());
        dateTimeShiftMonths = blog.dateTimeShiftMonths;
        dateTimeShiftDays = blog.dateTimeShiftDays;
        dateTimeShiftHours = blog.dateTimeShiftHours;
        title = blog.title;
        pictureFileName = blog.pictureFileName;
        preContent = (ArrayList<String>)blog.getPreContent().clone();
        content = (ArrayList<String>)blog.getContent().clone();
        comments = (ArrayList<Comment>)blog.getComments().clone();
    }

    /**
     * Returns file name of Blog saved.
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * Sets file name of Blog to save.
     *
     * @param   fileName   file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * Returns Blog's creation date and time.
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Sets Blog's creation date and time.
     *
     * @param   dateTime   creation date and time
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
    /**
     * Returns string how Blog's date and time saved in file.
     */
    public String getDateTimeAsString() {
        SimpleDateFormat sdfDate = new SimpleDateFormat(DATETIME_FORMAT);
        return sdfDate.format(dateTime);
    }

    /**
     * Returns Blog's date and time creation shift in months.
     */
    public int getDateTimeShiftMonths() {
        return dateTimeShiftMonths;
    }

    /**
     * Sets Blog's date and time creation shift in months.
     *
     * @param   dateTimeShiftMonths   date and time shift in months.
     */
    public void setDateTimeShiftMonths(int dateTimeShiftMonths) {
        this.dateTimeShiftMonths = dateTimeShiftMonths;
    }

    public int getDateTimeShiftDays() {
        return dateTimeShiftDays;
    }

    public void setDateTimeShiftDays(int dateTimeShiftDays) {
        this.dateTimeShiftDays = dateTimeShiftDays;
    }
    
    public int getDateTimeShiftHours() {
        return dateTimeShiftHours;
    }

    public void setDateTimeShiftHours(int dateTimeShiftHours) {
        this.dateTimeShiftHours = dateTimeShiftHours;
    }
    
    public Date getShiftedDateTime() {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.add(Calendar.MONTH, dateTimeShiftMonths);
        c.add(Calendar.DATE, dateTimeShiftDays);
        c.add(Calendar.HOUR, dateTimeShiftHours);
        Date d = new Date();
        d.setTime(c.getTime().getTime());
        return d;
    }
    
    public String getShiftedDateTimeAsString() {
        SimpleDateFormat sdfDate = new SimpleDateFormat(DATETIME_FORMAT);
        return sdfDate.format(getShiftedDateTime());
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public ArrayList<String> getPreContent() {
        return preContent;
    }

    public void setPreContent(ArrayList<String> preContent) {
        this.preContent = (ArrayList<String>)preContent.clone();
    }
    
    public String getPreContentAsString() {
        StringBuilder sb = new StringBuilder();
        int size = preContent.size();
        for (int i = 0; i < size; i++)
        {
            sb.append(preContent.get(i));
            if ((i + 1) < size) {
                sb.append("\n");
            }
        }
        return sb.toString();
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
            if ((i + 1) < size) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = (ArrayList<Comment>)comments.clone();
    }

    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new ArrayList<Comment>();
        }
        comments.add(comment);
    }
    
    public void writeToFile() {
        FileHelper fh = new FileHelper();
        File f = new File(fileName);
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fh.getBlogsPathFileName(f.getName())), "UTF-8"));
            try {
                out.write(getDataToFileAsString());
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
    
    public String getDataToFileAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BLOG_DATETIME);
        sb.append("\n");
        sb.append(getDateTimeAsString());
        sb.append("\n");
        sb.append(BLOG_DATETIME_SHIFT_MONTHS);
        sb.append("\n");
        sb.append(dateTimeShiftMonths);
        sb.append("\n");
        sb.append(BLOG_DATETIME_SHIFT_DAYS);
        sb.append("\n");
        sb.append(dateTimeShiftDays);
        sb.append("\n");
        sb.append(BLOG_DATETIME_SHIFT_HOURS);
        sb.append("\n");
        sb.append(dateTimeShiftHours);
        sb.append("\n");
        sb.append(BLOG_TITLE);
        sb.append("\n");
        sb.append(title);
        sb.append("\n");
        sb.append(BLOG_PICTURE_FILE_NAME);
        sb.append("\n");
        sb.append(pictureFileName);
        sb.append("\n");
        sb.append(BLOG_PRE_CONTENT);
        sb.append("\n");
        sb.append(getPreContentAsString());
        sb.append("\n");
        sb.append(BLOG_CONTENT);
        sb.append("\n");
        sb.append(getContentAsString());
        
        sb.append("\n");
        
        int size = comments.size();
        
        
        for (int i = 0; i < size; i++) {
            sb.append(BLOG_COMMENT_STARTS);
            sb.append("\n");
            sb.append(BLOG_COMMENT_DATETIME);
            sb.append("\n");
            sb.append(comments.get(i).getDateTimeAsString());
            sb.append("\n");
            sb.append(BLOG_COMMENT_LOGIN);
            sb.append("\n");
            sb.append(comments.get(i).getLogin());
            sb.append("\n");
            sb.append(BLOG_COMMENT_CONTENT);
            sb.append("\n");
            sb.append(comments.get(i).getContentAsString());
            sb.append("\n");
            sb.append(BLOG_COMMENT_ENDS);
            
            if ((i + 1) < size) {
                sb.append("\n");
            }
        }
        
        
        return sb.toString();
    }

    public void addComment(String commonUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
