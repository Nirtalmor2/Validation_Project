package workflows;


import extensions.UIActions;
import io.qameta.allure.Step;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import utilities.CommonOps;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


public class WebFlows extends CommonOps {


    // ==============================   משיכת הערכים מ XML =======================

//    @Step
//    private static void getNamesFromXML(List<String> names) {
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder;
//        Document doc;
//
//        try {
//            dBuilder = dbFactory.newDocumentBuilder();
//            doc = dBuilder.parse(new File("./Configuration/DataConfig.xml"));
//            doc.getDocumentElement().normalize();
//
//
//            names.add(getData("VAL1"));
//            names.add(getData("VAL2"));
//            names.add(getData("VAL3"));
//            names.add(getData("VAL4"));
//            names.add(getData("VAL5"));
//            names.add(getData("VAL6"));
//            names.add(getData("VAL7"));
//            names.add(getData("VAL8"));
//            names.add(getData("VAL9"));
//            names.add(getData("VAL10"));
//
//            // Add more names as needed
//
//        } catch (Exception e) {
//            System.out.println("Exception in reading XML file: " + e);
//        }
//    }

    @Step
    private static void getNamesFromXML(List<String> names) {
        try {
            File xmlFile = new File("./Configuration/DataConfig.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Using try-with-resources to ensure resources are closed properly
            try (FileInputStream fis = new FileInputStream(xmlFile)) {
                Document doc = dBuilder.parse(fis);
                doc.getDocumentElement().normalize();

                String[] keys = {"VAL1", "VAL2", "VAL3", "VAL4", "VAL5", "VAL6", "VAL7", "VAL8", "VAL9", "VAL10"};
                for (String key : keys) {
                    names.add(getData(doc, key));
                }
                // Add more names as needed
            }
        } catch (Exception e) {
            // Provide more specific error messages or log the stack trace for debugging
            System.out.println("Exception in reading XML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getData(Document doc, String key) {
        NodeList nodeList = doc.getElementsByTagName(key);
        if (nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            return element.getTextContent().trim();
        }
        return ""; // or handle missing data appropriately
    }









    //=============================================================================
    public static void validation(String sheetName, String fileName, WebElement mailField, WebElement sendButton, WebElement mailErrorMessage) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        int rowNum = 0;
        List<String> validationErrors = new ArrayList<>();

        try {

            // Create headers for columns A and B
            Row headerRow = sheet.createRow(rowNum++);
            Cell headerCellA = headerRow.createCell(0);
            headerCellA.setCellValue("Input"); // Header for column A
            Cell headerCellB = headerRow.createCell(1);
            headerCellB.setCellValue("Result"); // Header for column B

            // Get the list of names from XML
            List<String> names = new ArrayList<>();
            getNamesFromXML(names);
            for (String name : names) {
                UIActions.clear(mailField);
                UIActions.updateText(mailField, name);
                UIActions.click(sendButton);
                String errorMessage = UIActions.getText(mailErrorMessage);

                // Determine if the validation passed or failed
                String result = errorMessage.isEmpty() ? "Pass" : "Fail";

                if (!errorMessage.isEmpty()) {
                    validationErrors.add(name + ": " + errorMessage);
                }

                Row row = sheet.createRow(rowNum++);
                Cell cellName = row.createCell(0);
                cellName.setCellValue(name);
                Cell cellResult = row.createCell(1);
                cellResult.setCellValue(result);
                sheet.autoSizeColumn(0);
                sheet.setColumnWidth(0, 15 * 256);
            }

            // Write the workbook content to a file
            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
            }

            // Output validation errors
            if (!validationErrors.isEmpty()) {
                System.out.println("Validation Errors:");
                for (String error : validationErrors) {
                    System.out.println(error);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error writing to Excel file", e);
        } finally {
            // Close the workbook
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




















