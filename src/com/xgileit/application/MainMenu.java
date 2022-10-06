package com.xgileit.application;

import com.xgileit.data.DataStorageException;
import com.xgileit.data.NotFoundException;
import com.xgileit.data.ProductProblemDomain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MainMenu extends JFrame {


    JMenuItem miExit, miPurchase, miShow, miSearch, miUpdate, miDelete;
    JMenu mnStud;
    private ArrayList<ProductProblemDomain> arProduct;

    public MainMenu() {

        miPurchase = new JMenuItem("Product Purchasing");
        miShow = new JMenuItem("View Product Records");
        miExit = new JMenuItem("Exit");
        miSearch = new JMenuItem("Search Product Record by Product No");
        miUpdate = new JMenuItem("Update Product Colour");
        miDelete = new JMenuItem("Cancel Product Colour");
//listeners
        miPurchase.addActionListener(new miPurchaseEvent());
        miShow.addActionListener(new miShowEvent());
        miExit.addActionListener(new miExitEvent());
        miSearch.addActionListener(new miSearchEvent());
        //miUpdate.addActionListener(new miUpdateEvent());
        miDelete.addActionListener(new miDeleteEvent());


        //creating menus
        JMenu mnfile = new JMenu("File");
        //Add items in an order of their appearance
        mnfile.add(miSearch);
        // mnfile.add(miUpdate);
        mnfile.add(miDelete);
        mnfile.add(miExit);
        mnStud = new JMenu("Products");
        mnStud.add(miPurchase);
        mnStud.add(miShow);

        //create menu bar and add menus to menu bar
        JMenuBar jmBar = new JMenuBar();
        jmBar.add(mnfile);
        jmBar.add(mnStud);

        //set menu bar
        setJMenuBar(jmBar);
        try {
            ProductProblemDomain.initialise();
        } catch (DataStorageException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public static void main(String[] args) {
        MainMenu fMenu = new MainMenu();
        fMenu.setSize(500, 500);
        fMenu.setTitle("HOME MENU - BY MPHO MAKHARI");
        fMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fMenu.setVisible(true);

    }

    private class miPurchaseEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ProductPurchase frm = new ProductPurchase(arProduct);
            frm.setTitle("PRODUCT PURCHASE FORM");
            frm.setSize(500, 300);
            frm.setResizable(false);
            frm.setVisible(true);
        }


    }

    private class miShowEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ViewProductRecords frm = new ViewProductRecords(arProduct);
            frm.setTitle("VIEW PRODUCT RECORDS - BY MR M MAKHARI");
            frm.setSize(700, 300);
            frm.setResizable(false);
            frm.setVisible(true);
        }
    }

    private class miSearchEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            try {
                String search = JOptionPane.showInputDialog("Enter Product No to Search:");

                ProductProblemDomain objProduct = null;
                objProduct = ProductProblemDomain.findProductRecord(search);
                JOptionPane.showMessageDialog(null, objProduct);

            } catch (NotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }


    }

    //nInner Class
    private class miUpdateEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //update
            try {
                String search = JOptionPane.showInputDialog("Enter Product No to Search:");

                ProductProblemDomain objProduct = null;
                objProduct = ProductProblemDomain.findProductRecord(search);
                String newDescription = JOptionPane.showInputDialog("Enter New Description:");

                objProduct.updateDescription(newDescription);
            } catch (NotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private class miDeleteEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //DELETE
            try {
                String search = JOptionPane.showInputDialog("Enter  Product No to Search:");

                ProductProblemDomain objProduct = null;
                objProduct = ProductProblemDomain.findProductRecord(search);
                String subj = JOptionPane.showInputDialog("Enter  Colour to cancel:");
                objProduct.deleteColour(subj);
            } catch (NotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }


        }
    }

    private class miExitEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                int resp;
                resp = JOptionPane.showConfirmDialog(null, "Do u want to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    ProductProblemDomain.terminate();
                    System.exit(0);
                } else

                    mnStud.requestFocus();
            } catch (DataStorageException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }


}