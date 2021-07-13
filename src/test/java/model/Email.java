package model;

import java.util.Objects;

public class Email {

    private String emailAddressee;
    private String emailSubject;
    private String emailBody;

    public Email(String emailAddressee, String emailSubject, String emailBody) {
        this.emailAddressee = emailAddressee;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
    }

    public String getEmailAddressee() {
        return emailAddressee;
    }

    public void setEmailAddressee(String emailAddressee) {
        this.emailAddressee = emailAddressee;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailAddressee='" + emailAddressee + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                ", emailBody='" + emailBody + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(getEmailAddressee(), email.getEmailAddressee()) &&
                Objects.equals(getEmailSubject(), email.getEmailSubject()) &&
                Objects.equals(getEmailBody(), email.getEmailBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmailAddressee(), getEmailSubject(), getEmailBody());
    }
}
