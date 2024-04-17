package com.workintech.people;

import com.workintech.Library;
import com.workintech.enums.PersonRoles;

public class Librarian extends Person {
    private Library library;
    private String password;

    public Librarian(String userName, Library library, String password) {
        super(userName, PersonRoles.LIBRARIAN);
        this.library = library;
        this.password = password;
    }


    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
