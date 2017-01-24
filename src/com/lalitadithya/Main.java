package com.lalitadithya;


import com.lalitadithya.KDTree.KDTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static com.lalitadithya.util.Util.getPointObject;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        KDTree<String> tree = new KDTree<>(2);
        while (true) {
            System.out.println("1-> Insert a node");
            System.out.println("2-> Delete a node");
            System.out.println("3-> Search for a node");
            System.out.println("4-> Get all points in a region");
            System.out.println("5-> View the tree");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(br.readLine());
            double x, y;
            switch (choice) {
                case 1:
                    System.out.println("Enter the value of x");
                    x = Double.parseDouble(br.readLine());
                    System.out.println("Enter the value of y");
                    y = Double.parseDouble(br.readLine());
                    System.out.println("Enter the string to insert");
                    String obj = br.readLine();
                    tree.insertNode(obj, getPointObject(new double[]{x, y}));
                    break;
                case 2:
                    System.out.println("Enter the value of x");
                    x = Double.parseDouble(br.readLine());
                    System.out.println("Enter the value of y");
                    y = Double.parseDouble(br.readLine());
                    tree.delete(getPointObject(new double[]{x, y}));
                    break;
                case 3:
                    System.out.println("Enter the value of x");
                    x = Double.parseDouble(br.readLine());
                    System.out.println("Enter the value of y");
                    y = Double.parseDouble(br.readLine());
                    System.out.println("Result = " + tree.searchSingle(getPointObject(new double[]{x, y})));
                    break;
                case 4:
                    System.out.println("Enter the value of x (NE)");
                    x = Double.parseDouble(br.readLine());
                    System.out.println("Enter the value of y (NE)");
                    y = Double.parseDouble(br.readLine());
                    PointIn2DimensionalSpace NE = getPointObject(new double[]{x, y});
                    System.out.println("Enter the value of x (SW)");
                    x = Double.parseDouble(br.readLine());
                    System.out.println("Enter the value of y (SW)");
                    y = Double.parseDouble(br.readLine());
                    PointIn2DimensionalSpace SW = getPointObject(new double[]{x, y});
                    Rectangle rectangle = new Rectangle(NE, SW);
                    System.out.println("Result = " + Arrays.toString(tree.searchRange(rectangle).toArray()));
                    break;
                case 5:
                    tree.visualize();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}
