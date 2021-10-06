package util;

import model.*;

import java.util.*;

public class BlogHandler {

    public void createNewBlog (User user, Blog blog) {
        List<Blog> userBlog = user.getBlogList();

        if ((!userBlog.contains(blog)) && (!user.getPermission().equals(Privilege.LURKER))) {
            userBlog.add(blog);
            user.setBlogList(userBlog);
        } else {
            System.out.println("This blog already exists!");
        }
    }

    public void createNewPost (User user, Blog blog, Content content) {
        List<Content> userContent = blog.getPostList();

        for (Blog b : user.getBlogList()) {
            if (b.equals(blog)) {
                if (!userContent.contains(content))
                userContent.add(content);
                blog.setPostList(userContent);
            }
        }
    }

    public void createNewComment (Content content, User user, Comment comment) {
        List<Comment> userComment = content.getCommentList();

        if (!user.getPermission().equals(Privilege.LURKER)) {
            userComment.add(comment);
            content.setCommentList(userComment);
        }
    }
}
