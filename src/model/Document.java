package model;

import java.util.Date;

public class Document {

    private String reference, PaymentMethod;
    private Date date;
    private boolean IsFinalized;
    private String type;
    private int person;

    public Document(String reference, String PaymentMethod, Date date, String type, int person) {
        this.reference = reference;
        this.PaymentMethod = PaymentMethod;
        this.date = date;
        this.type = type;
        this.person = person;
    }

}
