package org.henrylightfoot.d2c;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.henrylightfoot.d2c.model.DAO;

import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class ReportGenerator {
    private Triage triage;
    public ReportGenerator(Triage triage) {
        this.triage = triage;
    }


    public void generatePdfReport(String jrxmlPath, String pdfOutputPath) {
        try {
            // 1. Compile JRXML file
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);

            // 2. Connect to your PostgreSQL database
            Connection conn = triage.getAccessService().getConn();

            // 3. Fill the report
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ReportTitle", "My Custom Report"); // optional

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

            // 4. Export to PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfOutputPath);

            System.out.println("Report exported to: " + pdfOutputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openPdf(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("Desktop is not supported on this system.");
                }
            } else {
                System.out.println("File does not exist: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUniqueFilename(String directoryPath, String baseName, String extension) {
        int counter = 0;
        String filename = baseName + "." + extension;
        File file = new File(directoryPath, filename);

        while (file.exists()) {
            counter++;
            filename = baseName + "-" + counter + "." + extension;
            file = new File(directoryPath, filename);
        }

        return file.getAbsolutePath();
    }

}

