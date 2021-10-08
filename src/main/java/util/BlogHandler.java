package util;

import model.*;

import java.util.*;

public class BlogHandler {

    public void createNewBlog (User user, Blog blog) {
        List<Blog> userBlog = user.getBlogList();

        if ((!userBlog.contains(blog)) && (!user.getPrivilege().equals(Privilege.LURKER))) {
            userBlog.add(blog);
            user.setBlogList(userBlog);
        } else {
            System.out.println("This blog already exists!");
        }
    }

    public void modifyContent (User user, Content oldVersion, Content newVersion) {
        List<Blog> userBlog = user.getBlogList();
         for (Blog b : userBlog) {
             if ((!user.getPrivilege().equals(Privilege.LURKER)) && (b.getPostList().contains(oldVersion))) {
                 oldVersion = newVersion;
            }
        }
    }

    public void removeBlog (User user, Blog blog) {
        List<Blog> userBlog = user.getBlogList();

        if (!user.getPrivilege().equals(Privilege.LURKER)) {
            for (Blog b : userBlog) {
                if (b.equals(blog)) {
                    userBlog.remove(b);
                    user.setBlogList(userBlog);
                }
            }
        } else {
            System.out.println("Could not find the searched blog!");
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

        if (!user.getPrivilege().equals(Privilege.LURKER)) {
            userComment.add(comment);
            content.setCommentList(userComment);
        }
    }
}
