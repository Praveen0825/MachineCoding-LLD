package service;

import entity.*;
import enums.*;
import exceptions.*;

import java.util.*;

public class BugBountyService {
    private static BugBountyService instance;
    private final Map<String, BugReport> bugs = new HashMap<>();
    private final UserService userService = UserService.getInstance();

    private static final Map<Status, Set<Status>> transitions = Map.of(
            Status.OPEN, Set.of(Status.REPORT_REVIEW),
            Status.REPORT_REVIEW, Set.of(Status.REJECTED, Status.ACKNOWLEDGED),
            Status.REJECTED, Set.of(Status.CLOSED),
            Status.ACKNOWLEDGED, Set.of(Status.BOUNTY_REVIEW),
            Status.BOUNTY_REVIEW, Set.of(Status.BOUNTY_PAID),
            Status.BOUNTY_PAID, Set.of(Status.CLOSED)
    );

    private BugBountyService() {}

    public static BugBountyService getInstance() {
        if (instance == null) instance = new BugBountyService();
        return instance;
    }

    public void reportBug(String title, String desc, Severity severity, String reporterEmail) {
        if (bugs.containsKey(title)) throw new RuntimeException("Bug with same title exists");
        BugReport bug = new BugReport(title, desc, severity, reporterEmail);
        bugs.put(title, bug);
        System.out.println("Bug reported: " + title);
    }

    public void assignBug(String title, String userName) {
        BugReport bug = getBug(title);
        User assignee = userService.getUserByName(userName);
        bug.setAssignedUser(assignee);
        System.out.println("Assigned " + title + " to " + userName);
    }

    public void updateStatus(String title, Status newStatus) {
        BugReport bug = getBug(title);
        User currentUser = userService.getLoggedInUser();
        if (!currentUser.equals(bug.getAssignedUser()))
            throw new AuthorizationException("Only assigned user can update status");
        if (!transitions.getOrDefault(bug.getStatus(), Set.of()).contains(newStatus))
            throw new InvalidStatusTransitionException("Invalid status transition");
        bug.updateStatus(newStatus);
        System.out.println("Status updated: " + title + " -> " + newStatus);
    }

    public void updateBug(String title, Integer bountyAmount) {
        BugReport bug = getBug(title);
        bug.setBountyAmount(bountyAmount);
        System.out.println("Bounty updated for: " + title);
    }

    public void addComment(String title, String comment) {
        getBug(title).addComment(comment);
        System.out.println("Comment added to: " + title);
    }

    public void deleteBug(String title) {
        User currentUser = userService.getLoggedInUser();
        if (!userService.isAdmin(currentUser))
            throw new AuthorizationException("Only admins can delete bug reports");
        bugs.remove(title);
        System.out.println("Bug deleted: " + title);
    }

    public void listAllBugs() {
        bugs.values().forEach(System.out::println);
    }

    public void listAssignedBugs() {
        User current = userService.getLoggedInUser();
        bugs.values().stream()
                .filter(b -> current.equals(b.getAssignedUser()))
                .forEach(System.out::println);
    }

    public void listCompletedAssignedBugs() {
        User current = userService.getLoggedInUser();
        bugs.values().stream()
                .filter(b -> current.equals(b.getAssignedUser()) && b.isCompleted())
                .forEach(System.out::println);
    }

    public void listIncompleteAssignedBugs() {
        User current = userService.getLoggedInUser();
        bugs.values().stream()
                .filter(b -> current.equals(b.getAssignedUser()) && !b.isCompleted())
                .forEach(System.out::println);
    }

    public void viewBugDetails(String title) {
        getBug(title).printDetails();
    }

    private BugReport getBug(String title) {
        if (!bugs.containsKey(title)) throw new RuntimeException("Bug not found: " + title);
        return bugs.get(title);
    }
}
