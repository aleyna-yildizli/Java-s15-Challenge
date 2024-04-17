package com.workintech.people;

import com.workintech.enums.PersonRoles;

import java.util.Objects;

public abstract class Person {
    private String userName;
    private PersonRoles personRoles;

    public Person(String userName, PersonRoles personRoles) {
        this.userName = userName;
        this.personRoles = personRoles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PersonRoles getPersonRoles() {
        return personRoles;
    }

    public void setPersonRoles(PersonRoles personRoles) {
        this.personRoles = personRoles;
    }

    public PersonRoles whoYouAre() {
        return getPersonRoles();
    }

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", personRoles=" + personRoles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(userName, person.userName) && personRoles == person.personRoles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, personRoles);
    }
}
