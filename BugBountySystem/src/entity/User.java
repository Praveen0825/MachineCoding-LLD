package entity;

import enums.RoleType;

public class User {
    private String name;
    private String email;
    private RoleType role;

    public User(String name, String email, RoleType role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public RoleType getRoleType() { return role; }

    public void setEmail(String email) { this.email = email; }

    public void setRoleType(RoleType role) { this.role = role; }

    @Override
    public String toString() {
        return name + " (" + role + ")";
    }
}
