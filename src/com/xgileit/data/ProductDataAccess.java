package com.xgileit.data;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class ProductDataAccess {

    private static final String productFile = "Product.dat";
    private static ArrayList<ProductProblemDomain> arProduct;

    public static void initialise() throws DataStorageException {
        try {
            FileInputStream fis = new FileInputStream(productFile);
            ObjectInputStream objRead = new ObjectInputStream(fis);
            arProduct = (ArrayList<ProductProblemDomain>) objRead.readObject();
            objRead.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No data in the file " + ex.getMessage());

            arProduct = new ArrayList<>();
        } catch (IOException ex) {
            throw new DataStorageException("No data to read " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//init

    public static void terminate() throws DataStorageException {
        try {
            FileOutputStream fos = new FileOutputStream(productFile);
            ObjectOutputStream objWrite = new ObjectOutputStream(fos);

            objWrite.writeObject(arProduct);

            objWrite.close();

        } catch (IOException ex) {
            throw new DataStorageException("Cannot write " + ex.getMessage());
        }
    }//term

    public static void addNewProduct(ProductProblemDomain objProd) throws DuplicateException {
        boolean duplicate = false;
        for (int x = 0; x < arProduct.size(); x++) {
            if ((arProduct.get(x).getProductNo().equals(objProd.getProductNo())) && (arProduct.get(x).getColour().equals(objProd.getColour()))) {
                duplicate = true;

                break;
            }
        }
        if (duplicate) {
            throw new DuplicateException(objProd.getColour() + ": Product already shipped for this colour");
        } else {
            arProduct.add(objProd);
        }
    }

    public static void updateDescription(ProductProblemDomain objProd, String newDescription) throws NotFoundException {

        boolean found = false;
        String aStud = objProd.getProductNo();
        for (int x = 0; x < arProduct.size() && !found; x++) {
            if (aStud.equals(arProduct.get(x).getProductNo())) {
                found = true;
                arProduct.get(x).setDescription(newDescription);

            }
        }
        if (!found) {
            throw new NotFoundException(aStud + " not found");
        }
    }

    public static ProductProblemDomain findProductRecord(String aProduct) throws NotFoundException {
        boolean found = false;
        ProductProblemDomain objProduct = null;
        for (int x = 0; x < arProduct.size() && !found; x++) {
            objProduct = arProduct.get(x);
            if (aProduct.equals(objProduct.getProductNo())) {
                found = true;

            }
        }
        if (found) {
            return objProduct;

        } else {
            throw new NotFoundException(aProduct + " Product not found ");
        }
    }

    public static void deleteColour(ProductProblemDomain objProduct, String aColour) throws NotFoundException {
        boolean found = false;
        String aStud = objProduct.getProductNo();
        for (int x = 0; x < arProduct.size() && !found; x++) {
            if ((arProduct.get(x).getProductNo().equals(objProduct.getProductNo())) && (arProduct.get(x).getColour().equals(aColour))) {
                found = true;
                arProduct.remove(x);
            }
        }
        if (!found) {
            throw new NotFoundException("Product not found " + aStud);
        }

    }

    public static ArrayList<ProductProblemDomain> getAll() {
        return arProduct;
    }

    public static ArrayList<ProductProblemDomain> getAll(String aColour) {
        ArrayList arrData = new ArrayList();
        for (ProductProblemDomain oStud : arProduct) {
            if (oStud.getColour().equals(aColour)) {
                arrData.add(oStud);

            }
        }
        return arrData;
    }


}
