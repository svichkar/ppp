package com.nixsolutions;

import java.io.File;
import java.util.Map;

public interface PDFTool {
    Map<String, String> extractForms(File template);

    void copyFile(File template, File result);

    void replaceForms(File result, Map<String, String> forms);
}