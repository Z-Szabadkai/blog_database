package util;

import config.GetScanner;
import model.*;

import java.util.*;

public class BlogHandler {

    List<Blog> blogs = new ArrayList<>();

    public void createNewBlog () {
        Blog blog = new Blog();
        BlogTemplate blogTemplate = new BlogTemplate();

        System.out.println("What is the title of the blog?");
        String title = GetScanner.newScanner();
        blog.setBlogName(title);

        System.out.println("Choose a template");
        byte[] background = new byte[0];
        blogTemplate.setTemplateBackground(background);
        templateStyle ts = templateStyle.valueOf(GetScanner.newScanner());
        blogTemplate.setStyle(ts);
        templateColor tc = templateColor.valueOf(GetScanner.newScanner());
        blogTemplate.setColor(tc);

        blog.setTemplate(blogTemplate);

        blogs.add(blog);
    }

    public Post createNewPost (String title) {
        Post post = new Post();
        post.setTitle(title);

        System.out.println("What's on your mind?");
        String content = GetScanner.newScanner();
        post.setContent(content);

        System.out.println("Please add some tags!");
        post.setTags(addTags());

        System.out.println("Would you like others to comment on it? Y/N");
        String comment = GetScanner.newScanner();
        if (comment.equalsIgnoreCase("y")) {
            post.setCanComment(true);
        }
        return post;
    }

    public String[] addTags() {
        String[] tags = new String[5];
        for (int t = 0; t < tags.length-1; t++) {
            String tag = GetScanner.newScanner();
            tags[t] = tag;
        }
        return tags;
    }

    public String createComment(Post post, User user, String comment) {
        Map<String, String> commentList = new HashMap();
        if ((post.isCanComment()) && (!user.getPermission().equals(Privilege.LURKER)))
            comment = GetScanner.newScanner();
            commentList.put(user.getUserName(), comment);
        return null;
    }
}
