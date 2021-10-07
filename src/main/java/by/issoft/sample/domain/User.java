package by.issoft.sample.domain;

import lombok.*;

public class User implements Comparable<User> {
    private String id;
    private final String username;

    private String firstName;
    private String lastName;

    private Age age;

    private UserStatus status;
    private UserRole userRole;

    //constructor
    public User(String username) {
        this.username = username;
    }

    //getters & setters
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Age getAge() {
        return age;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(by.issoft.sample.domain.Age age) {
        this.age = age;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public int compareTo(User other) {
        return this.getUsername().compareTo(other.getUsername());
    }


}

