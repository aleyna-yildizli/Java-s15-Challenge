package com.workintech.people;

import com.workintech.Library;
import com.workintech.enums.PersonRoles;

public class Librarian extends Person {
    private Library library;



    public Librarian(String userName, String password, Library library) {
        super(userName, password, PersonRoles.LIBRARIAN);
        this.library = library;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
