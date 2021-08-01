package cucumber.runner;

import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.TableTransformer;

import model.Email;
import model.EmailResource;

public class EmailTableTransformer implements TableTransformer<EmailResource> {

    @Override
    public EmailResource transform(DataTable dataTable) throws Throwable {
        EmailResource emails = new EmailResource();

        dataTable.cells()
            .stream()
            .skip(1)
            .map(fields -> new Email(fields.get(0), fields.get(1), fields.get(2)))
            .forEach(emails::addEmail);
        return emails;
    }
}
