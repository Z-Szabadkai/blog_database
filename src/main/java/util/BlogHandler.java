package util;

import model.*;

import java.io.FileInputStream;
import java.util.*;

public class BlogHandler {

    List<User> users = new ArrayList<>();
    List<Blog> blogs = new ArrayList<>();

    public void createNewUser() {
        User user = new User();

        System.out.println("What is your username?");
        String userName;
        do {
            System.out.println("That name is already taken, choose another one please!");
            userName = getScanner().next();
        }
        while (!users.contains(userName));
        user.setUserName(userName);

        user.setPermission(Privilege.USER);

        System.out.println("Please type in your e-mail address!");
        String email = getScanner().next();
        user.setE_mail(email);

        System.out.println("Please add your password! (Hint: don't let anybody see");
        String pw = getScanner().next();
        user.setPassword(pw);

        Date date = new Date();
        user.setRegistration_date(date);

        users.add(user);
        System.out.println("Profile successfully created!");
    }

    public void createNewBlog () {
        Blog blog = new Blog();
        BlogTemplate blogTemplate = new BlogTemplate();

        System.out.println("What is the title of the blog?");
        String title = getScanner().next();
        blog.setBlogName(title);

        System.out.println("Choose a template");
        String name = getScanner().next();
        blogTemplate.setTemplateName(name);
        byte[] background = new byte[0];
        blogTemplate.setTemplateBackground(background);
        templateStyle ts = templateStyle.valueOf(getScanner().next());
        blogTemplate.setStyle(ts);
        templateColor tc = templateColor.valueOf(getScanner().next());
        blogTemplate.setColor(tc);

        blog.setTemplate(blogTemplate);

        blogs.add(blog);
    }

    public Post createNewPost (String title) {
        Post post = new Post();
        post.setTitle(title);

        System.out.println("What's on your mind?");
        String content = getScanner().next();
        post.setContent(content);

        System.out.println("Please add some tags!");
        post.setTags(addTags());

        System.out.println("Would you like others to comment on it? Y/N");
        String comment = getScanner().next();
        if (comment.equalsIgnoreCase("y")) {
            post.setCanComment(true);
        }
        return post;
    }

    public String[] addTags() {
        String[] tags = new String[5];
        for (int t = 0; t < tags.length-1; t++) {
            String tag = getScanner().next();
            tags[t] = tag;
        }
        return tags;
    }

//    public void addPostToBlog(User user, Blog blog) {
//        List<Blog> postList = new ArrayList<>();
//        if (!user.getPermission().equals(Privilege.LURKER)) {
//            Post posts = createNewPost()
//            postList.add(post);
//        }
//    }

    public String createComment(Post post, User user, String comment) {
        Map<String, String> commentList = new HashMap();
        if ((post.isCanComment()) && (!user.getPermission().equals(Privilege.LURKER)))
            comment = getScanner().next();
            commentList.put(user.getUserName(), comment);
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    private Scanner getScanner() {
        return new Scanner(System.in);
    }
}
