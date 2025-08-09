package model;

import java.util.Date;
import java.util.Objects;

public class Document {
    private int id;
    private String reference;
    private Date date;
    private String type;
    private int person;              // client id
    private String paymentMethod;    // e.g. "cash"
    private boolean finalized;

    public Document() {}

    public Document(int id,
                    String reference,
                    Date date,
                    String type,
                    int person,
                    String paymentMethod,
                    boolean finalized) {
        setId(id);
        setReference(reference);
        setDate(date);
        setType(type);
        setPerson(person);
        setPaymentMethod(paymentMethod);
        setFinalized(finalized);
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("id < 0");
        this.id = id;
    }

    public String getReference() { return reference; }
    public void setReference(String reference) {
        String v = Objects.requireNonNull(reference, "reference").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("reference empty");
        if (v.length() > 200) throw new IllegalArgumentException("reference too long");
        this.reference = v;
    }

    public Date getDate() { return date == null ? null : new Date(date.getTime()); }
    public void setDate(Date date) {
        Date v = Objects.requireNonNull(date, "date");
        this.date = new Date(v.getTime());
    }

    public String getType() { return type; }
    public void setType(String type) {
        String v = Objects.requireNonNull(type, "type").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("type empty");
        if (v.length() > 100) throw new IllegalArgumentException("type too long");
        this.type = v;
    }

    public int getPerson() { return person; }
    public void setPerson(int person) {
        if (person <= 0) throw new IllegalArgumentException("person id invalid");
        this.person = person;
    }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) {
        String v = Objects.requireNonNull(paymentMethod, "paymentMethod").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("paymentMethod empty");
        if (v.length() > 50) throw new IllegalArgumentException("paymentMethod too long");
        this.paymentMethod = v;
    }

    public boolean isFinalized() { return finalized; }
    public void setFinalized(boolean finalized) { this.finalized = finalized; }
}