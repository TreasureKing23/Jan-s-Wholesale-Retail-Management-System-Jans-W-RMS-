package entities;


import format.*;
import  java.io.Serializable;

public class Customer implements Serializable {

    private String cusID;
    private String cusName;
    private String dob;
    private Address address;
    private String telephone;
    private String email;
    private String dateOfMembership;
    private String dateOfMembershipExp;


    public Customer() {
        this.cusID = "";
        this.cusName = "";
        this.dob = "";
        this.address = new Address();
        this.telephone = "";
        this.email = "";
        this.dateOfMembership = "";
        this.dateOfMembershipExp = "";

    }

    public Customer(String cusID, String cusName, String dob, Address address, String telephone, String email, String dateOfMembership, String getDateOfMembershipExp) {
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

    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
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

    public String getDateOfMembership() {
        return dateOfMembership;
    }
    public void setDateOfMembership(String dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public String getDateOfMembershipExp() {
        return dateOfMembershipExp;
    }
    public void setGetDateOfMembershipExp(String getDateOfMembershipExp) {
        this.dateOfMembershipExp = getDateOfMembershipExp;
    }

    @Override
    public String toString() {
        return "Customer{" + "cusID=" + cusID + ", cusName=" + cusName + ", dob=" + dob + ", address=" + address + ", telephone=" + telephone + ", email=" + email + ", dateOfMembership=" + dateOfMembership + ", dateOfMembershipExp=" + dateOfMembershipExp + '}';
    }




}
