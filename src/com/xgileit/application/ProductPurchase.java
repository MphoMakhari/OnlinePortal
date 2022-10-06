package com.xgileit.application;

import com.xgileit.data.DuplicateException;
import com.xgileit.data.ProductProblemDomain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ProductPurchase extends JFrame {

    JTextField txtNm = new JTextField(25);
    JTextField txtDescription = new JTextField(25);
    JTextField txtProductNo = new JTextField(25);
    JComboBox<String> cmbBrand = new JComboBox<>();
    JComboBox<String> cmbItemCategory = new JComboBox<>();
    JRadioButton rbTrue = new JRadioButton("True (Available)");
    JRadioButton rbFalse = new JRadioButton("False (UnAvailable)");
    JLabel lblProductNo = new JLabel("Product No:");
    JLabel lblName = new JLabel("Name:");
    JLabel lblDescription = new JLabel("Description:");
    JLabel lblStatus = new JLabel("Status:");
    JLabel lblBrand = new JLabel("Brand Name:");
    JLabel lblCategory = new JLabel("Category:");
    JButton btnSelect = new JButton("Select Brand");
    JButton btnAdd = new JButton("Enroll Student");
    JButton btnClear = new JButton("Clear");
    JButton btnClose = new JButton("Close");
    private final ArrayList<ProductProblemDomain> arProduct;

    public ProductPurchase(ArrayList<ProductProblemDomain> arProduct) {
        this.arProduct = arProduct;
        JPanel pnl = new JPanel();
        pnl.setLayout(null);
        pnl.add(txtNm);
        pnl.add(txtProductNo);
        pnl.add(txtDescription);

        pnl.add(cmbBrand);
        pnl.add(cmbItemCategory);
        pnl.add(rbTrue);
        pnl.add(rbFalse);

        pnl.add(lblProductNo);
        pnl.add(lblName);
        pnl.add(lblDescription);
        pnl.add(lblStatus);
        pnl.add(lblBrand);
        pnl.add(lblCategory);
        pnl.add(btnAdd);
        pnl.add(btnClear);
        pnl.add(btnClose);
        pnl.add(btnSelect);


        lblProductNo.setBounds(10, 20, 140, 20);
        txtProductNo.setBounds(100, 20, 120, 20);
        lblName.setBounds(10, 50, 140, 20);
        txtNm.setBounds(100, 50, 120, 20);
        lblDescription.setBounds(10, 80, 140, 20);
        txtDescription.setBounds(100, 80, 120, 20);
        lblStatus.setBounds(10, 110, 140, 20);
        rbTrue.setBounds(100, 110, 100, 20);
        rbFalse.setBounds(210, 110, 100, 20);
        lblBrand.setBounds(10, 140, 140, 20);
        cmbBrand.setBounds(100, 140, 120, 20);
        btnSelect.setBounds(220, 140, 120, 20);
        lblCategory.setBounds(10, 170, 140, 20);
        cmbItemCategory.setBounds(100, 170, 120, 20);
        btnAdd.setBounds(10, 210, 120, 20);
        btnClear.setBounds(150, 210, 120, 20);
        btnClose.setBounds(280, 210, 120, 20);

        setContentPane(pnl);

        btnAdd.addActionListener(new btnAddEvent());
        btnClear.addActionListener(new btnClearEvent());
        btnClose.addActionListener(new btnCloseEvent());
        btnSelect.addActionListener(new btnSelectEvent());

        getCourses();


    }//constructor

    //get inputs
    public ProductProblemDomain getProducts() {
        String prodNo, name, description, status, brand, category;
        ProductProblemDomain objProduct = null;

        try {

            brand = cmbBrand.getSelectedItem().toString();
            category = cmbItemCategory.getSelectedItem().toString();
            prodNo = txtProductNo.getText();
            name = txtNm.getText();
            description = txtDescription.getText();
            if (rbTrue.isSelected()) {
                status = rbTrue.getText();
            } else {
                status = rbFalse.getText();
            }
            objProduct = new ProductProblemDomain(prodNo, name, description, status, brand, category) {

            };
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return objProduct;
    }

    public void getCourses() {
        String rLine;
        String[] arsp;
        try {
            Scanner scRd = new Scanner(new FileReader("brand.txt"));
            while (scRd.hasNextLine()) {
                rLine = scRd.nextLine();
                arsp = rLine.split(",");
                cmbBrand.addItem(arsp[0]);
            }
            scRd.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading brand file: " + ex.getMessage());
        }
    }

    private class btnAddEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                ProductProblemDomain objProduct = getProducts();

                objProduct.addNewProduct();
            } catch (DuplicateException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private class btnClearEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtNm.setText("");
            txtProductNo.setText("");
            txtProductNo.requestFocus();
            txtNm.setText("");
            txtDescription.setText("");
            cmbBrand.setSelectedIndex(0);
            cmbItemCategory.removeAllItems();
            rbTrue.setSelected(false);
            rbFalse.setSelected(false);

        }
    }

    private class btnSelectEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {


            String rLine;
            String[] arsp;

            try {
                Scanner scRd = new Scanner(new FileReader("category.txt"));
                while (scRd.hasNextLine()) {
                    rLine = scRd.nextLine();
                    arsp = rLine.split(",");
                    if (cmbBrand.getSelectedIndex() == 0) {
                        if ("Clothes".equals(arsp[0])) {
                            cmbItemCategory.addItem(arsp[1]);
                        }
                    } else if (cmbBrand.getSelectedIndex() == 1) {
                        if ("Toys".equals(arsp[0])) {
                            cmbItemCategory.addItem(arsp[1]);
                        }
                    } else if (cmbBrand.getSelectedIndex() == 2) {
                        if ("Electronics".equals(arsp[0])) {
                            cmbItemCategory.addItem(arsp[1]);
                        }
                    }
                }
                scRd.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error reading category: " + ex.getMessage());
            }

        }

    }

    private class btnCloseEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            dispose();


        }

    }


}
