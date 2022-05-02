package com.showupdate.merchants;

public class customerz {
    String  bill, name, referenceNumber;

    public customerz() {
    }

    public customerz(String bill, String name, String referenceNumber) {
        this.referenceNumber = referenceNumber;
        this.name = name;
        this.bill = bill;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
