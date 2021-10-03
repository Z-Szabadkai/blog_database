package util;

import model.*;

import java.util.*;

public class BlogHandler {

    List<String> users = new ArrayList<>();

    public User createNewUser() {
        User user = new User();

        System.out.println("What is your username?");
        String userName;
        do {
            System.out.println("That name is already taken, choose another one please!");
            userName = getScanner().next();
        }
        while (!users.contains(userName));
        return user;
    }

    public Blog createNewBlog (String userName, byte[] avatar, String blogTitle, BlogTemplate template) {
        return new Blog(userName, avatar, blogTitle, template);
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

    public List<String> getUserData(long id) {

        return null;
    }

    private Scanner getScanner() {
        return new Scanner(System.in);
    }
}
