package model;

import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Person(String firstName, String lastName, String email, String phone) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        String v = Objects.requireNonNull(firstName, "firstName").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("firstName empty");
        if (v.length() > 100) throw new IllegalArgumentException("firstName too long");
        this.firstName = v;
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        String v = Objects.requireNonNull(lastName, "lastName").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("lastName empty");
        if (v.length() > 100) throw new IllegalArgumentException("lastName too long");
        this.lastName = v;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        String v = Objects.requireNonNull(email, "email").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("email empty");
        if (!v.matches("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) throw new IllegalArgumentException("invalid email");
        this.email = v;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) {
        String v = null;
        if (phone != null && !phone.isBlank()) {
            v = phone.trim();
            if (!v.matches("^[+0-9\\- ]{6,20}$")) throw new IllegalArgumentException("invalid phone");
        }
        this.phone = v;
    }
}