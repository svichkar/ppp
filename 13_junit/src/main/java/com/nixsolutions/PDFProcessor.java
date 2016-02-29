package com.nixsolutions;

import java.io.File;
import java.util.Map;

public class PDFProcessor {
    private PDFTool pdfTool;

    public PDFProcessor(PDFTool pdfTool) {
        this.pdfTool = pdfTool;
    }

    public void fulfillPDFTemplate(File template, File result, Map<String, String> data) {
        Map<String, String> forms = pdfTool.extractForms(template);
        for (String key : forms.keySet()) {
            if (data.containsKey(key)) {
                forms.put(key, data.get(key));
            }
        }
        pdfTool.copyFile(template, result);
        pdfTool.replaceForms(result, forms);
    }
}
