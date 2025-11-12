package Utilities;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class PDFReporter implements IReporter {

    private static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/screenshots/";

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        Document document = new Document(PageSize.A4);
        try {
            //String filePath = outputDirectory + "/TestNG_Detailed_Report.pdf";
        	String reportsDir = System.getProperty("user.dir") + "/test-output/PDFReports/";
        	File reportFolder = new File(reportsDir);

        	// create folder if not exists
        	if (!reportFolder.exists()) {
        	    reportFolder.mkdirs();
        	}

        	// create the report file
//        	String filePath = reportsDir + "TestNG_Report_" + System.currentTimeMillis() + ".pdf";
        	String filePath = reportsDir + "TestNG_Detailed_Signin report.pdf";
        	PdfWriter.getInstance(document, new FileOutputStream(filePath));

        	
        	
        	// ======================
       
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

            // ====== Report Title ======
            Paragraph title = new Paragraph("TestNG Automation Report\n\n", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // ====== Environment Info ======
            document.add(new Paragraph("Environment Details", headerFont));
            document.add(new Paragraph("OS: " + System.getProperty("os.name"), normalFont));
            document.add(new Paragraph("Java Version: " + System.getProperty("java.version"), normalFont));
            document.add(new Paragraph("User: " + System.getProperty("user.name"), normalFont));
            document.add(new Paragraph("Execution Date: " + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date()), normalFont));
            document.add(new Paragraph("\n"));

            // ====== Iterate over suites ======
            int totalPassed = 0, totalFailed = 0, totalSkipped = 0;

            for (ISuite suite : suites) {
                Map<String, ISuiteResult> results = suite.getResults();

                for (ISuiteResult result : results.values()) {
                    ITestContext context = result.getTestContext();

                    totalPassed += context.getPassedTests().size();
                    totalFailed += context.getFailedTests().size();
                    totalSkipped += context.getSkippedTests().size();

                    document.add(new Paragraph("Suite Name: " + context.getName(), headerFont));
                    document.add(new Paragraph("Start Time: " + context.getStartDate(), normalFont));
                    document.add(new Paragraph("End Time: " + context.getEndDate(), normalFont));
                    document.add(new Paragraph("Passed: " + context.getPassedTests().size(), normalFont));
                    document.add(new Paragraph("Failed: " + context.getFailedTests().size(), normalFont));
                    document.add(new Paragraph("Skipped: " + context.getSkippedTests().size(), normalFont));
                    document.add(new Paragraph("\n"));

                    // ====== Table of Tests ======
                    PdfPTable table = new PdfPTable(3);
                    table.setWidthPercentage(100);
                    table.addCell(new PdfPCell(new Phrase("Test Name", headerFont)));
                    table.addCell(new PdfPCell(new Phrase("Status", headerFont)));
                    table.addCell(new PdfPCell(new Phrase("Time (ms)", headerFont)));

                    // Passed
                    for (ITestResult passed : context.getPassedTests().getAllResults()) {
                        table.addCell(passed.getName());
                        table.addCell("PASSED");
                        table.addCell(String.valueOf(passed.getEndMillis() - passed.getStartMillis()));
                    }

                    // Failed
                    for (ITestResult failed : context.getFailedTests().getAllResults()) {
                        table.addCell(failed.getName());
                        table.addCell("FAILED");
                        table.addCell(String.valueOf(failed.getEndMillis() - failed.getStartMillis()));
                    }

                    // Skipped
                    for (ITestResult skipped : context.getSkippedTests().getAllResults()) {
                        table.addCell(skipped.getName());
                        table.addCell("SKIPPED");
                        table.addCell(String.valueOf(skipped.getEndMillis() - skipped.getStartMillis()));
                    }

                    document.add(table);
                    document.add(new Paragraph("\n"));

                    // ====== Screenshots for Failed Tests ======
                    for (ITestResult failed : context.getFailedTests().getAllResults()) {
                        String screenshotFile = SCREENSHOT_PATH + failed.getName() + ".png";
                        File file = new File(screenshotFile);
                        if (file.exists()) {
                            document.add(new Paragraph("Screenshot for Failed Test: " + failed.getName(), normalFont));
                            Image screenshot = Image.getInstance(screenshotFile);
                            screenshot.scaleToFit(500, 300);
                            screenshot.setAlignment(Element.ALIGN_CENTER);
                            document.add(screenshot);
                            document.add(new Paragraph("\n"));
                        }
                    }
                    document.add(new Paragraph("-------------------------------------------------------------\n"));
                }
            }

            // ====== PIE CHART (Pass/Fail/Skip) ======
            document.newPage();
            document.add(new Paragraph("Test Summary Chart", headerFont));
            document.add(new Paragraph("\n"));

            PdfContentByte canvas = writer.getDirectContent();
            drawPieChart(canvas, totalPassed, totalFailed, totalSkipped, 200, 500, 150);

            document.close();
            System.out.println("✅ PDF Report with Pie Chart generated at: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draw Pie Chart using iText graphics
     */
    private void drawPieChart(PdfContentByte canvas, int passed, int failed, int skipped, float x, float y, float radius) {
        int total = passed + failed + skipped;
        if (total == 0) return;

        float startAngle = 0;
        float passAngle = 360f * passed / total;
        float failAngle = 360f * failed / total;
        float skipAngle = 360f * skipped / total;

        // Draw Passed Slice (Green)
        canvas.setColorFill(new BaseColor(34, 139, 34));
        canvas.moveTo(x, y);
        canvas.arc(x - radius, y - radius, x + radius, y + radius, startAngle, passAngle);
        canvas.lineTo(x, y);
        canvas.fill();
        startAngle += passAngle;

        // Draw Failed Slice (Red)
        canvas.setColorFill(new BaseColor(220, 20, 60));
        canvas.moveTo(x, y);
        canvas.arc(x - radius, y - radius, x + radius, y + radius, startAngle, failAngle);
        canvas.lineTo(x, y);
        canvas.fill();
        startAngle += failAngle;

        // Draw Skipped Slice (Yellow)
        canvas.setColorFill(new BaseColor(255, 165, 0));
        canvas.moveTo(x, y);
        canvas.arc(x - radius, y - radius, x + radius, y + radius, startAngle, skipAngle);
        canvas.lineTo(x, y);
        canvas.fill();

        // Legend
        float legendX = x + radius + 30;
        float legendY = y + 50;
        canvas.setColorFill(BaseColor.BLACK);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Legend:"), legendX, legendY, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Passed (" + passed + ")", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.GREEN)), legendX, legendY - 20, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Failed (" + failed + ")", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED)), legendX, legendY - 40, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Skipped (" + skipped + ")", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.ORANGE)), legendX, legendY - 60, 0);
    }
}

