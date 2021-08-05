package model;

import java.util.ArrayList;
import java.util.List;

public class EmailResource {

    private List<Email> emails = new ArrayList<>();

    public List<Email> getEmails() {
        return emails;
    }

    public void addEmail(Email email) {
        emails.add(email);
    }
}
