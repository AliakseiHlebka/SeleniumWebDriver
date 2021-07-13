package service;

import model.Email;

public class EmailCreator {

    public static final String EMAIL_ADDRESSEE = "testdata.email.addressee";
    public static final String EMAIL_SUBJECT = "testdata.email.subject";
    public static final String EMAIL_BODY = "testdata.email.body";

    public static Email withAllFieldsFilled() {
        return new Email(TestDataReader.getTestData(EMAIL_ADDRESSEE),
                TestDataReader.getTestData(EMAIL_SUBJECT),
                TestDataReader.getTestData(EMAIL_BODY));
    }

    public static Email withEmptyAddressee() {
        return new Email("", TestDataReader.getTestData(EMAIL_SUBJECT),
                TestDataReader.getTestData(EMAIL_BODY));
    }

    public static Email withEmptySubject() {
        return new Email(TestDataReader.getTestData(EMAIL_ADDRESSEE), "",
                TestDataReader.getTestData(EMAIL_BODY));
    }

    public static Email withEmptyBody() {
        return new Email(TestDataReader.getTestData(EMAIL_ADDRESSEE),
                TestDataReader.getTestData(EMAIL_SUBJECT), "");
    }
}
