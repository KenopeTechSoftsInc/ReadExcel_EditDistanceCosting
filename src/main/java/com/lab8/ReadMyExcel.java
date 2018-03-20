/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab8;

/**
 *
 * @author opeol
 * @author Opeyemi
 */
/*MAIN CLASS
*Program Title: A program that implement Dynamic Programming
*AUTHOR: OLATUNJI, OPEYEMI MSc IT
*INSTRUCTOR: Prof Olac Fuentes
*TA: Zakia Kadri, Adul Kader

*LAST DATE OF MODIFICATION: April 28 2017, 11.15pm
 */



import static com.lab8.EditDistanceWithCost.editDistanceCostArray;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// reading the excel file
public class ReadMyExcel {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //readMyExcel xread2 = new ReadMyExcel();
        double[][] thisMatrixCost = (getValue());
        for (int k = 0; k < thisMatrixCost.length; k++) {
            for (int j = 0; j < thisMatrixCost.length; j++) {
                System.out.println(thisMatrixCost[k][j] + "  ");
            }

        }

        String[] thisSequenceArray = proteinSequenceArray("proteinSequence.txt");

        for (int i = 0; i < thisSequenceArray.length; i++) {
            // System.out.println(thisSequenceArray[i] + " ");
        }

        char[] charOrder = {'C', 'S', 'T', 'P', 'A', 'G', 'N', 'D', 'E', 'Q', 'H', 'R', 'K', 'M', 'I', 'L', 'V', 'F', 'Y', 'W'};

        int j = 0;
        int i = 0;

        do {
            for (; i < thisSequenceArray.length; i++) {

                double myEditDist2 = editDistanceCostArray(thisSequenceArray[j], thisSequenceArray[i], -3, -3, thisMatrixCost, charOrder);

                System.out.println(myEditDist2 + " ");
                System.out.println();

            }
            j++;
        } while (j < thisSequenceArray.length);

       
        thisSequenceArray = proteinSequenceArray("proteinSequence.txt");
        System.out.println();
        System.out.println("Searching for user prompted sequence");
        System.out.println();

        Scanner askUser = new Scanner(System.in);
        System.out.println("Please input the sequence you want to search");
        String sequenceUserDesired = askUser.nextLine();
        System.out.println(thisSequenceArray.length);
        
        for (int k=0; k < thisSequenceArray.length; k++) {
             System.out.println(" OKAY ");
            double myEditDist3 = editDistanceCostArray(sequenceUserDesired, thisSequenceArray[k], -3, -3, thisMatrixCost, charOrder);

            System.out.println(myEditDist3 + " fetched");
            System.out.println();

        }

    }

    public static double[][] getValue() throws FileNotFoundException, IOException {
        XSSFRow row;
        XSSFCell cell;
        FileInputStream inputStream = new FileInputStream("blosum62.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        // get sheet number
        //int sheetCn = workbook.getNumberOfSheets();
        // get 0th sheet data
        XSSFSheet sheet = workbook.getSheetAt(0);

        // get number of rows from sheet
        int rows = sheet.getLastRowNum() + 1;

        int cols = sheet.getRow(0).getLastCellNum();
        //String[][] excelData = new String[rows][cols];
        double[][] costMatrix = new double[rows][cols];

        //double[][] nums = null;
        //Integer[][] arr = null;
        try {

            // System.out.println("Populating array commenced");
            //System.out.println("row number is: " + rows + " and cols number is: " + cols);
            for (int i = 1; i < rows; i++) {
                System.out.println("this is I: " + i);
                row = sheet.getRow(i);

                for (int j = 1; j < cols; j++) {

                    cell = row.getCell(j);

                    //System.out.println("Populating cell "+ j+" "+cell);
                    double value = Double.parseDouble(cellToString(cell));
                    costMatrix[i][j] = value;
                    System.out.println(costMatrix[i][j]);
                }

                //System.out.println("I am here " + Arrays.deepToString((String[][])(excelData)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return costMatrix;
    }

    public static String[] proteinSequenceArray(String filename) throws FileNotFoundException {
        //String filename = null;
        File file = new File(filename);
        Scanner sequenceFile = new Scanner(new File(filename));
        String sequence = "";

        List<String> sequenceTmpArray = new ArrayList<String>();

        // while loop
        while (sequenceFile.hasNextLine()) {
            // find next line
            sequence = sequenceFile.nextLine();
            sequenceTmpArray.add(sequence);
        }
        sequenceFile.close();

        String[] sequenceArray = sequenceTmpArray.toArray(new String[0]);

        //for (String s : tempsArray) {
        // System.out.println(s);
        //}
        return sequenceArray;

    }

    // method to covert the cell values to string   
    private static String cellToString(XSSFCell cell) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        int myType = cell.getCellType();
        String result;

        // formula cannot be evaluated so throw an exception
        if (myType == XSSFCell.CELL_TYPE_FORMULA) {
            throw new RuntimeException("Cannit process Formula. Please change field to result of formula");
        } //if blanks 
        else if (myType == XSSFCell.CELL_TYPE_BLANK) {
            result = "";
        } else {
            result = String.valueOf(cell);
        }

        return result;

    }

}




