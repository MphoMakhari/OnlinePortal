package com.xgileit.application;

import com.xgileit.data.ProductProblemDomain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ViewProductRecords extends JFrame {
    JTextArea taView = new JTextArea();

    public ViewProductRecords(ArrayList<ProductProblemDomain> arProduct) {
        //make textarea uneditable
        taView.setEditable(false);
        Container scrn = getContentPane();
        scrn.setLayout(new BorderLayout());
        scrn.add(taView, BorderLayout.CENTER);
        //call method to display data
        displayAll(arProduct);


    }

    public void displayAll(ArrayList<ProductProblemDomain> arProduct) {
        String aSubj = JOptionPane.showInputDialog("Enter a Colour:");
        if (aSubj.isEmpty()) {
            arProduct = ProductProblemDomain.getAll();

        } else {
            arProduct = ProductProblemDomain.getAll(aSubj);


        }
        taView.append("No of Loaded data: " + arProduct.size() + "\n");
        taView.append("Product No\t" + "Name\t" + "Description\t" + "Status\t" + "Brand\t" + "Colour\t" + "\tProd Cost" + "\n");
        for (int x = 0; x < arProduct.size(); x++) {
            taView.append(arProduct.get(x).toString() + "\n");


        }
    }
}
