import format.Date;

public class Invoice {

    private int invoiceNo;
    private Date billingDate;
    private String item;
    private int quantity;
    private String cashierName;
    private String customerName;

    public Invoice(int invoiceNo, Date billingDate, String item, int quantity, String cashierName, String customerName)
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

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
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
        return "Invoice{" +
                "invoiceNo=" + invoiceNo +
                ", billingDate=" + billingDate +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                ", cashierName='" + cashierName + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }




}
