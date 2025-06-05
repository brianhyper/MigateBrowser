package com.migate.util;

import javafx.print.PrinterJob;
import javafx.scene.web.WebView;

public class PDFExportUtil {
    public static void exportToPDF(WebView webView, String filePath) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean proceed = job.showPrintDialog(null);  // ata hii pia but still not working
            if (proceed) {
                boolean success = job.printPage(webView);
                if (success) {
                    job.endJob();
                }
            }
        }
    }
}
