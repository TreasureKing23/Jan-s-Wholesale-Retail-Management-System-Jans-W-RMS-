package Domain;


import java.io.Serializable;

public class Invoice implements Serializable {

    private int invoiceNo;
    private String billingDate;
    private String item;
    private int quantity;
    private String cashierName;
    private String customerName;

    public Invoice()
    {
        this.invoiceNo = 0;
        this.billingDate = "";
        this.item = "";
        this.quantity = 0;
        this.cashierName = "";
        this.customerName = "";
    }

    public Invoice(int invoiceNo, String billingDate, String item, int quantity, String cashierName, String customerName)
    {
        this.invoiceNo = invoiceNo;
        this.billingDate = billingDate;
        this.item = item;
        this.quantity = quantity;
        this.cashierName = cashierName;
        this.customerName = customerName;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "entities.Invoice{" +
                "invoiceNo=" + invoiceNo +
                ", billingDate=" + billingDate +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                ", cashierName='" + cashierName + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }




}
