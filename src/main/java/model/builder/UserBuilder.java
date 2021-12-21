package model.builder;

import enumuration.Gender;
import model.member.User;
import model.ticket.Ticket;

/**
 * @author Negin Mousavi
 */
public class UserBuilder {
    private final User user = new User();

    public static UserBuilder getBuilder() {
        return new UserBuilder();
    }

    public User build() {
        return user;
    }

    public UserBuilder withNationalCode(String nationalCode) {
        user.setNationalCode(nationalCode);
        return this;
    }

    public UserBuilder withPhoneNumber(String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
        return this;
    }

    public UserBuilder withGivenName(String givenName) {
        user.setGivenName(givenName);
        return this;
    }

    public UserBuilder withSurName(String surName) {
        user.setSurName(surName);
        return this;
    }

    public UserBuilder withGender(Gender gender) {
        user.setGender(gender);
        return this;
    }

    public UserBuilder withAge(int age) {
        user.setAge(age);
        return this;
    }

    public UserBuilder withTicket(Ticket ticket) {
        user.setTicket(ticket);
        return this;
    }
}
