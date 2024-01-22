package composite;

import java.util.ArrayList;
import java.util.List;

public class DistributionList extends Contact {
    private final List<Contact> contacts = new ArrayList<>();

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public void remove(Contact contact) {
        contacts.remove(contact);
    }

    public void sendMail(String msg) {
        for (Contact contact : contacts) {
            contact.sendMail(msg);
        }
    }
}
