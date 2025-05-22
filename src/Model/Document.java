package Model;

import java.util.Date;

public class Document {

    private String Reference, PaymentMethod;
    private Date CreationDate;
    private boolean IsFinalized;
    private Type Type;
    private Person Person;

    public Document(String Reference, String PaymentMethod, Date CreationDate, Type Type, Person Person) {
        this.Reference = Reference;
        this.PaymentMethod = PaymentMethod;
        this.CreationDate = CreationDate;
        this.Type = Type;
        this.Person = Person;
    }

}
