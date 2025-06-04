package service;

import entity.User;
import enums.RoleType;
import exceptions.AuthenticationException;
import exceptions.AuthorizationException;

import java.util.*;

public class UserService {
    private static UserService instance;
    private final Map<String, User> users = new HashMap<>();
    private User loggedInUser = null;

    private UserService() {}

    public static UserService getInstance() {
        if (instance == null) instance = new UserService();
        return instance;
    }

    public void preloadUsers(User... userArray) {
        for (User user : userArray) {
            users.put(user.getName(), user);
        }
    }

    public void login(String name) {
        if (!users.containsKey(name)) throw new AuthenticationException("User not found");
        loggedInUser = users.get(name);
        System.out.println(name + " logged in.");
    }

    public void logout() {
        if (loggedInUser == null) throw new AuthenticationException("No user logged in");
        System.out.println(loggedInUser.getName() + " logged out.");
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        if (loggedInUser == null) throw new AuthenticationException("No user logged in");
        return loggedInUser;
    }

    public User getUserByName(String name) {
        if (!users.containsKey(name)) throw new AuthorizationException("User not found: " + name);
        return users.get(name);
    }

    public boolean isAdmin(User user) {
        return user.getRoleType() == RoleType.ADMIN;
    }
}
