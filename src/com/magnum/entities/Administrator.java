package com.magnum.entities;


public class Administrator extends User {
    private String firstName;
    private String lastName;

    public Administrator() {
    }

    public Administrator(
            String username,
            String email,
            String password,
            String avatar,
            String status,
            String firstName,
            String lastName) {
        super(username, email, password, avatar, status);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Administrator(
            int id,
            String username,
            String email,
            String password,
            String avatar,
            String status,
            String firstName,
            String lastName) {
        super(id, username, email, password, avatar, status);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Administrator{"
                + "ID="
                + this.getID()
                + ", avatar="
                + this.getAvatar()
                + ", email='"
                + this.getEmail()
                + '\''
                + ", username='"
                + this.getUsername()
                + '\''
                + ", password='"
                + this.getPassword()
                + '\''
                + ", status="
                + this.getStatus()
                + ", firstName='"
                + firstName
                + '\''
                + ", lastName='"
                + lastName
                + '\''
                + '}';
    }

  
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
