
package lt.Blogsite.Classes;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class BlogDataFile extends DataFile {
    
    private Blog blog = null;

    int parsingLine = 0;
    int linesToParse = 0;
    
    public BlogDataFile(String fileName) {
        super(fileName);
        blog = new Blog();
        blog.setFileName(fileName);
        load();
    }
    
    public Blog getBlog() {
        return new Blog(blog);
    }

    @Override
    public void load() {
        super.load();
        
        parsingLine = 0;
        linesToParse = data.size();
        while (parsingLine < linesToParse) {
            String str = data.get(parsingLine);
            try {
                parseBlogDateTimeSection(str);
                parseBlogDateTimeShiftMonthsSection(str);
                parseBlogDateTimeShiftDaysSection(str);
                parseBlogDateTimeShiftHoursSection(str);
                parseBlogTitleSection(str);
                parseBlogPictureFileNameSection(str);
                parseBlogPreContentSection(str);
                parseBlogContentSection(str);
                parseCommentSection(str);
            } catch (DataFoundException e) {
                // ...
            }
            parsingLine++;
        }
    }
    
    private void parseBlogDateTimeSection(String line) throws DataFoundException {
        if (line.equals(Blog.BLOG_DATETIME) == true) {
            parsingLine++;
            if (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (isSectionStartEnd(str) == true) {
                    str = Blog.DEFAULT_DATE_TIME_STRING;
                    parsingLine--;
                }
                SimpleDateFormat format = new SimpleDateFormat(Blog.DATETIME_FORMAT); 
                try {
                    blog.setDateTime(format.parse(str));
                } catch (ParseException e) {
                    System.out.println("Unparseable using " + format);
                    blog.setDateTime(new Date());
                }
            }
            throw new DataFoundException(Blog.BLOG_DATETIME);
        }
    }
    
    private boolean isSectionStartEnd(String line) {
        return (line.startsWith(Blog.SECTION_STARTS_WITH)) && (line.endsWith(Blog.SECTION_ENDS_WITH));
    }
    
    private void parseBlogDateTimeShiftMonthsSection(String line) throws DataFoundException {
        if (line.equals(Blog.BLOG_DATETIME_SHIFT_MONTHS)) {
            parsingLine++;
            if (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (isSectionStartEnd(str) == true) {
                    str = Blog.DEFAULT_BLOG_DATETIME_SHIFT_STRING;
                    parsingLine--;
                }
                try {
                    blog.setDateTimeShiftMonths(java.lang.Integer.parseInt(str));
                } catch (NumberFormatException e) {
                    System.out.println("Unparseable using " + str);
                    blog.setDateTimeShiftMonths(Blog.DEFAULT_BLOG_DATETIME_SHIFT);
                }
            }
            throw new DataFoundException(Blog.BLOG_DATETIME_SHIFT_MONTHS);
        }
    }
    
    private void parseBlogDateTimeShiftDaysSection(String line) throws DataFoundException {
        if (line.equals(Blog.BLOG_DATETIME_SHIFT_DAYS)) {
            parsingLine++;
            if (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (isSectionStartEnd(str) == true) {
                    str = Blog.DEFAULT_BLOG_DATETIME_SHIFT_STRING;
                    parsingLine--;
                }
                try {
                    blog.setDateTimeShiftDays(java.lang.Integer.parseInt(str));
                } catch (NumberFormatException e) {
                    System.out.println("Unparseable using " + str);
                    blog.setDateTimeShiftDays(Blog.DEFAULT_BLOG_DATETIME_SHIFT);
                }
            }
            throw new DataFoundException(Blog.BLOG_DATETIME_SHIFT_DAYS);
        }
    }
    
    private void parseBlogDateTimeShiftHoursSection(String line) throws DataFoundException {
        if (line.equals(Blog.BLOG_DATETIME_SHIFT_HOURS)) {
            parsingLine++;
            if (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (isSectionStartEnd(str) == true) {
                    str = Blog.DEFAULT_BLOG_DATETIME_SHIFT_STRING;
                    parsingLine--;
                }
                try {
                    blog.setDateTimeShiftHours(java.lang.Integer.parseInt(str));
                } catch (NumberFormatException e) {
                    System.out.println("Unparseable using " + str);
                    blog.setDateTimeShiftHours(Blog.DEFAULT_BLOG_DATETIME_SHIFT);
                }
            }
            throw new DataFoundException(Blog.BLOG_DATETIME_SHIFT_HOURS);
        }
    }
    
    private void parseBlogTitleSection(String line) throws DataFoundException {
        if (line.equals(Blog.BLOG_TITLE)) {
            parsingLine++;
            if (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (isSectionStartEnd(str) == true) {
                    str = "";
                    parsingLine--;
                }
                blog.setTitle(str);
            }
            throw new DataFoundException(Blog.BLOG_TITLE);
        }
    }
    
    private void parseBlogPictureFileNameSection(String line) throws DataFoundException {
        if (line.equals(Blog.BLOG_PICTURE_FILE_NAME)) {
            parsingLine++;
            if (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (isSectionStartEnd(str) == true) {
                    str = "";
                    parsingLine--;
                }
                blog.setPictureFileName(str);
            }
            throw new DataFoundException(Blog.BLOG_PICTURE_FILE_NAME);
        }
    }
    
    private void parseBlogPreContentSection(String line) throws DataFoundException {
        if (line.equals(Blog.BLOG_PRE_CONTENT)) {
            ArrayList<String> lines = new ArrayList<String>();
            parsingLine++;
            while (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (isSectionStartEnd(str) == true) {
                    parsingLine--;
                    break;
                }
                lines.add(str);
                parsingLine++;
            }
            
            // remove last empty line
            int size = lines.size();
            if ((size > 0) && (lines.get(size - 1).equals(""))) {
                lines.remove(size - 1);
            }

            blog.setPreContent(lines);
            throw new DataFoundException(Blog.BLOG_PRE_CONTENT);
        }
    }
    
    private void parseBlogContentSection(String line) throws DataFoundException {
        if (line.equals(Blog.BLOG_CONTENT)) {
            ArrayList<String> lines = new ArrayList<String>();
            parsingLine++;
            while (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (isSectionStartEnd(str) == true) {
                    parsingLine--;
                    break;
                }
                lines.add(str);
                parsingLine++;
            }
            
            // remove last empty line
            int size = lines.size();
            if ((size > 0) && (lines.get(size - 1).equals(""))) {
                lines.remove(size - 1);
            }

            blog.setContent(lines);
            throw new DataFoundException(Blog.BLOG_CONTENT);
        }
    }
    
    private void parseCommentSection(String line) throws DataFoundException {
        if (line.equals(Blog.BLOG_COMMENT_STARTS)) {
            Comment comment = new Comment();
            try {
                parsingLine++;
                while (parsingLine < linesToParse) {
                    String str = data.get(parsingLine);
                    if (str.equals(Blog.BLOG_COMMENT_ENDS)) {
                        throw new CommentSectionEndsException(Blog.BLOG_COMMENT_ENDS);
                    }
                    try {
                        parseBlogCommentDateTimeSection(str, comment);
                        parseBlogCommentLoginSection(str, comment);
                        parseBlogCommentContentSection(str, comment);
                    } catch (DataFoundException e) {
                        // ...
                    }
                    parsingLine++;
                }
                throw new DataFoundException();
            } catch (CommentSectionEndsException e) {
                throw new DataFoundException();
            } finally {
                blog.addComment(comment);
            }
        }
    }
    
    private void parseBlogCommentDateTimeSection(String line, Comment comment) throws CommentSectionEndsException, DataFoundException {
        if (line.equals(Blog.BLOG_COMMENT_DATETIME)) {
            parsingLine++;
            if (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (str.equals(Blog.BLOG_COMMENT_ENDS)) {
                    throw new CommentSectionEndsException(Blog.BLOG_COMMENT_ENDS);
                }
                if (isSectionStartEnd(str) == true) {
                    str = Blog.DEFAULT_DATE_TIME_STRING;
                    parsingLine--;
                }
                SimpleDateFormat format = new SimpleDateFormat(Blog.DATETIME_FORMAT); 
                Date dateTime;
                try {
                    dateTime = format.parse(str);
                    comment.setDateTime(dateTime);
                } catch (ParseException e) {
                    System.out.println("Unparseable using " + format);
                }
            }
            throw new DataFoundException(Blog.BLOG_COMMENT_DATETIME);
        }
    }
    
    private void parseBlogCommentLoginSection(String line, Comment comment) throws CommentSectionEndsException, DataFoundException {
        if (line.equals(Blog.BLOG_COMMENT_LOGIN)) {
            parsingLine++;
            if (parsingLine < linesToParse) {
                String str = data.get(parsingLine);
                if (str.equals(Blog.BLOG_COMMENT_ENDS)) {
                    throw new CommentSectionEndsException(Blog.BLOG_COMMENT_ENDS);
                }
                if (isSectionStartEnd(str) == true) {
                    str = "";
                    parsingLine--;
                }
                comment.setLogin(str);
            }
            throw new DataFoundException(Blog.BLOG_COMMENT_LOGIN);
        }
    }
    
    private void parseBlogCommentContentSection(String line, Comment comment) throws CommentSectionEndsException, DataFoundException {
        if (line.equals(Blog.BLOG_COMMENT_CONTENT)) {
            ArrayList<String> lines = new ArrayList<String>();
            parsingLine++;
            try {
                while (parsingLine < linesToParse) {
                    String str = data.get(parsingLine);
                    if (str.equals(Blog.BLOG_COMMENT_ENDS)) {
                        throw new CommentSectionEndsException(Blog.BLOG_COMMENT_ENDS);
                    }
                    if (isSectionStartEnd(str) == true) {
                        parsingLine--;
                        break;
                    }
                    lines.add(str);
                    parsingLine++;
                }
                throw new DataFoundException(Blog.BLOG_COMMENT_CONTENT);
            } catch (CommentSectionEndsException e) {
                throw new CommentSectionEndsException(Blog.BLOG_COMMENT_CONTENT);
            } finally {
                // remove last empty line
                int size = lines.size();
                if ((size > 0) && (lines.get(size - 1).equals(""))) {
                    lines.remove(size - 1);
                }

                comment.setContent(lines);
            }
        }
    }
  
}
