package model;

import java.util.Date;

public class Document {

    private String reference, PaymentMethod;
    private Date date;
    private boolean IsFinalized;
    private Type type;
    private Person person;

    public Document(String reference, String PaymentMethod, Date date, Type type, Person person) {
        this.reference = reference;
        this.PaymentMethod = PaymentMethod;
        this.date = date;
        this.type = type;
        this.person = person;
    }

}
