package org.henrylightfoot.d2c.controller;
import javafx.scene.Scene;
import org.henrylightfoot.d2c.ReportGenerator;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.view.ReportsView;
import org.henrylightfoot.d2c.view.TasksView;


public class ReportsController implements Controller {
    private final Triage triage;
    private final Scene scene;
    private final ReportsView view;

    public ReportsController(Triage triage) {
        this.triage = triage;
        view = new ReportsView();
        setButtons();
        scene = new Scene(view.getView(), 600, 400);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

    }
    //create list of all customers button functionality
    private void custListReport() {
        String uniquePath = triage.getReportGenerator().getUniqueFilename("src/main/resources/reports", "Sales", "pdf");
        triage.getReportGenerator().generatePdfReport("src/main/resources/customer_list_report.jrxml",uniquePath);
        triage.getReportGenerator().openPdf(uniquePath);
    }
    //create list of all products button functionality
    private void prodListReport() {
        String uniquePath = triage.getReportGenerator().getUniqueFilename("src/main/resources/reports", "Products", "pdf");
        triage.getReportGenerator().generatePdfReport("src/main/resources/product_list_report.jrxml",uniquePath);
        triage.getReportGenerator().openPdf(uniquePath);
    }
    //create summary of all tasks on system button functionality
    private void taskListReport() {
        String uniquePath = triage.getReportGenerator().getUniqueFilename("src/main/resources/reports", "Tasks", "pdf");
        triage.getReportGenerator().generatePdfReport("src/main/resources/customer_task_completion.jrxml", uniquePath);
        triage.getReportGenerator().openPdf(uniquePath);
    }
    //button setting
    private void setButtons() {
        view.getBackButton().setOnAction(event -> {triage.showPage("welcome");});
        view.getCustReportButton().setOnAction(event -> {custListReport();});
        view.getProductsReportButton().setOnAction(event -> {prodListReport();});
        view.getCustTaskRateButton().setOnAction(event -> {taskListReport();});
    }

    public Scene getScene() {
        return scene;
    }
}