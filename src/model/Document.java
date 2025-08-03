package model;

import java.util.Date;

public class Document {

    private String reference, PaymentMethod;
    private Date date;
    private boolean IsFinalized;
    private String type;
    private int person;

    public Document(String reference, Date date, String PaymentMethod, boolean isFinalized, String type, int person) {
        this.reference = reference;
        this.date = date;
        this.PaymentMethod = PaymentMethod;
        this.IsFinalized = isFinalized;
        this.type = type;
        this.person = person;
    }

    public String getReference() {
        return reference;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public Date getDate() {
        return date;
    }

    public boolean isFinalized() {
        return IsFinalized;
    }

    public String getType() {
        return type;
    }

    public int getPerson() {
        return person;
    }
}
