package entities;


import format.*;
import  java.io.Serializable;

public class Customer implements Serializable {

    private String cusID;
    private String cusName;
    private Date dob;
    private Address address;
    private String telephone;
    private String email;
    private Date dateOfMembership;
    private Date dateOfMembershipExp;


    public Customer(String cusID, String cusName, Date dob, Address address, String telephone, String email, Date dateOfMembership, Date getDateOfMembershipExp) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.dob = dob;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.dateOfMembership = dateOfMembership;
        this.dateOfMembershipExp = getDateOfMembershipExp;
    }

    public String getCusID() {
        return cusID;
    }
    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getCusName() {
        return cusName;
    }
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfMembership() {
        return dateOfMembership;
    }
    public void setDateOfMembership(Date dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public Date getDateOfMembershipExp() {
        return dateOfMembershipExp;
    }
    public void setGetDateOfMembershipExp(Date getDateOfMembershipExp) {
        this.dateOfMembershipExp = getDateOfMembershipExp;
    }





}
