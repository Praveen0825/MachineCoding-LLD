package entity;

import enums.*;

import java.time.LocalDateTime;
import java.util.*;

public class BugReport {
    private final String title;
    private String description;
    private Status status;
    private Severity severity;
    private int bountyAmount;
    private final String reporterEmail;
    private User assignedUser;
    private final LocalDateTime createdTime;
    private LocalDateTime closedTime;
    private final List<String> comments = new ArrayList<>();
    private final Map<Status, LocalDateTime> statusTimeline = new HashMap<>();
    private int timeSpent = 0;

    public BugReport(String title, String description, Severity severity, String reporterEmail) {
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = Status.OPEN;
        this.reporterEmail = reporterEmail;
        this.createdTime = LocalDateTime.now();
        updateStatus(Status.OPEN);
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public void setBountyAmount(int bountyAmount) {
        this.bountyAmount = bountyAmount;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public void updateStatus(Status newStatus) {
        this.status = newStatus;
        statusTimeline.put(newStatus, LocalDateTime.now());
        if (newStatus == Status.CLOSED) {
            this.closedTime = LocalDateTime.now();
        }
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public boolean isCompleted() {
        return status == Status.CLOSED;
    }

    public void logTimeSpent(int minutes) {
        timeSpent += minutes;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Status: " + status + ", Assigned: " +
                (assignedUser == null ? "None" : assignedUser.getName());
    }

    public void printDetails() {
        System.out.println("Bug Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Severity: " + severity);
        System.out.println("Reporter Email: " + reporterEmail);
        System.out.println("Assigned To: " + (assignedUser == null ? "None" : assignedUser.getName()));
        System.out.println("Bounty: " + bountyAmount);
        System.out.println("Created At: " + createdTime);
        System.out.println("Status Timeline: " + statusTimeline);
        System.out.println("Comments: " + comments);
        System.out.println("Time Spent: " + timeSpent + " mins");
    }
}
