package com.xgileit.data;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;


public class ProductProblemDomain implements Serializable,Payment  {


    protected String productNo, name, status, description, brand, colour;

    NumberFormat format = NumberFormat.getCurrencyInstance();
    int qty = 2;
    double price = 250;

    public ProductProblemDomain() {
        productNo = name = description = status = brand = colour = "";
    }

    public ProductProblemDomain(String productNo, String name, String description, String status, String brand, String colour) {
        setProductNo(productNo);
        setName(name);
        setDescription(description);
        setStatus(status);
        setBrand(brand);
        setColour(colour);
    }

    /**
     * @return
     */
    @Override
    public double calcPayment() {
        return qty * price;
    }
    //Data Access Methods
    public static void initialise() throws DataStorageException {
        ProductDataAccess.initialise();
    }

    public static void terminate() throws DataStorageException {
        ProductDataAccess.terminate();
    }

    public static ProductProblemDomain findProductRecord(String aStud) throws NotFoundException {
        return ProductDataAccess.findProductRecord(aStud);
    }

    public static ArrayList<ProductProblemDomain> getAll() {
        return ProductDataAccess.getAll();
    }

    public static ArrayList<ProductProblemDomain> getAll(String aColour) {
        return ProductDataAccess.getAll(aColour);
    }

    // Abstract method (does not have a body)
    // public abstract double productCost();

    //getters
    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        if (productNo.length() <= 9) {
            this.productNo = productNo;

        } else {
            throw new IllegalArgumentException("Invalid Product No, only up to 9 digits");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        {
            this.name = name;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() <= 1000) {
            this.description = description;

        } else {
            throw new IllegalArgumentException("only 1000 characters for description no");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        {
            this.status = status;
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        {
            this.brand = brand;
        }
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        {
            this.colour = colour;
        }
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addNewProduct() throws DuplicateException {
        ProductDataAccess.addNewProduct(this);
    }

    public void updateDescription(String newDescription) throws NotFoundException {
        ProductDataAccess.updateDescription(this, newDescription);
    }

    public void deleteColour(String aColour) throws NotFoundException {
        ProductDataAccess.deleteColour(this, aColour);
    }

    @Override
    public String toString() {

        return productNo + "\t " + name + "\t " + description + "\t " + status + "\t " + brand + "\t " + colour + "\t " + calcPayment();
    }



}
