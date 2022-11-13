package Domain;

public class Products {

    private String prodCode;
    private String prodName;
    private String prodShortDesc;
    private String prodLongDesc;
    private int prodStock;
    private double unitPrice;

    public Products()
    {
        this.prodCode = "";
        this.prodName = "";
        this.prodShortDesc = "";
        this.prodLongDesc = "";
        this.prodStock = 0;
        this.unitPrice = 0.0;
    }
    public Products(String prodCode, String prodName, String prodShortDesc, String prodLongDesc, int prodStock, double unitPrice) {
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodShortDesc = prodShortDesc;
        this.prodLongDesc = prodLongDesc;
        this.prodStock = prodStock;
        this.unitPrice = unitPrice;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdShortDesc() {
        return prodShortDesc;
    }

    public void setProdShortDesc(String prodShortDesc) {
        this.prodShortDesc = prodShortDesc;
    }

    public String getProdLongDesc() {
        return prodLongDesc;
    }

    public void setProdLongDesc(String prodLongDesc) {
        this.prodLongDesc = prodLongDesc;
    }

    public int getProdStock() {
        return prodStock;
    }

    public void setProdStock(int prodStock) {
        this.prodStock = prodStock;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "entities.Products{" +
                "prodCode='" + prodCode + '\'' +
                ", prodName='" + prodName + '\'' +
                ", prodShortDesc='" + prodShortDesc + '\'' +
                ", prodLongDesc='" + prodLongDesc + '\'' +
                ", prodStock=" + prodStock +
                ", unitPrice=" + unitPrice +
                '}';
    }



}
