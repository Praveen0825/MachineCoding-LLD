import entity.*;
import enums.*;
import service.*;

public class Main {
    public static void main(String[] args) {
        UserService users = UserService.getInstance();
        BugBountyService bugs = BugBountyService.getInstance();

        users.preloadUsers(
                new User("user1", "user1@email.com", RoleType.ADMIN),
                new User("user2", "user2@email.com", RoleType.AGENT)
        );

        users.login("user1");
        bugs.reportBug("Bug Title 1", "Bug Desc 1", Severity.P0, "reporter1@email.com");
        bugs.reportBug("Bug Title 2", "Bug Desc 2", Severity.P1, "reporter2@email.com");
        bugs.assignBug("Bug Title 1", "user1");
        bugs.assignBug("Bug Title 2", "user2");
        bugs.updateStatus("Bug Title 1", Status.REPORT_REVIEW);
        bugs.updateStatus("Bug Title 1", Status.ACKNOWLEDGED);
        bugs.updateStatus("Bug Title 1", Status.BOUNTY_REVIEW);
        bugs.updateBug("Bug Title 1", 1000);
        bugs.addComment("Bug Title 1", "This is a comment.");
        bugs.deleteBug("Bug Title 2");
        bugs.listAllBugs();
        bugs.listAssignedBugs();
        bugs.listCompletedAssignedBugs();
        bugs.listIncompleteAssignedBugs();
        bugs.viewBugDetails("Bug Title 1");
        users.logout();

        users.login("user2");
        bugs.updateStatus("Bug Title 2", Status.REPORT_REVIEW);
        bugs.updateStatus("Bug Title 2", Status.REJECTED);
        bugs.updateStatus("Bug Title 2", Status.CLOSED);

    }
}