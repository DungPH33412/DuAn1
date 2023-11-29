package com.example.duan1.Model;

public class Detailnvoice {
    private String idKey;
    private String idProduct;
    private String idInvoice;
    private String quantityProduct;

    public Detailnvoice() {
    }

    public Detailnvoice(String idKey, String idProduct, String idInvoice, String quantityProduct) {
        this.idKey = idKey;
        this.idProduct = idProduct;
        this.idInvoice = idInvoice;
        this.quantityProduct = quantityProduct;
    }

    public String getIdKey() {
        return idKey;
    }

    public void setIdKey(String idKey) {
        this.idKey = idKey;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(String idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(String quantityProduct) {
        this.quantityProduct = quantityProduct;
    }
}
