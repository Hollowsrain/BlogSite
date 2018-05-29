
package lt.Blogsite.Classes;

import java.util.ArrayList;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;

public class Blogs {
    
    private ArrayList<Blog> data = null;
    
    public Blogs() {
        data = new ArrayList<Blog>();
        load();
    }
    
    public Blogs(Blogs blogs, String searchFor) {
        String searching = searchFor.toLowerCase();
        data = new ArrayList<Blog>();
        int size = blogs.size();
        for (int i = 0; i < size; i++) {
            Blog blog = blogs.getBlog(i);
            String preContent = blog.getPreContentAsString().toLowerCase();
            String content = blog.getContentAsString().toLowerCase();
            if (preContent.contains(searching) || content.contains(searching)) {
                data.add(blog);
            }
        }
    }
    
    public int size() {
        return data.size();
    }
    
    public Blog getBlog(int index) {
        if ((index < 0) || (index > (data.size() - 1))) {
            return null;
        }
        return data.get(index);
    }
    
    private void load() {
        FileHelper fh = new FileHelper();
        File dir = new File(fh.getBlogsPath());
        File[] filesList = dir.listFiles();
        for (File file : filesList) {
            if (file.isFile()) {
                String fileName = fh.getBlogsPathFileName(file.getName());
                BlogDataFile bdf = new BlogDataFile(fileName);
                addBlog(bdf.getBlog());
            }
        }
        sort();
    }
    
    public void addBlog(Blog blog) {
        data.add(blog);
    }
    
    private void reLoad() {
        
    }
    
    private void sort() {
        Collections.sort(data, new BlogComparatorDateTimeDescending());
    }
    
}

class BlogComparator implements Comparator<Blog> {
    
    @Override
    public int compare(Blog o1, Blog o2) {
        return o1.getShiftedDateTime().compareTo(o2.getShiftedDateTime());
    }
    
}

class BlogComparatorDateTimeDescending implements Comparator<Blog> {
    
    @Override
    public int compare(Blog o1, Blog o2) {
        return -(o1.getShiftedDateTime().compareTo(o2.getShiftedDateTime()));
    }
    
}
