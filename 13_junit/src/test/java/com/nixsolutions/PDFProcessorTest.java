package com.nixsolutions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PDFProcessorTest {
    @Mock
    private PDFTool pdfTool;
    @InjectMocks
    private PDFProcessor pdfProcessor;
    @Captor
    private ArgumentCaptor<Map<String, String>> captor;

    @Test
    public void shouldPopulateGivenFieldsWhenExists() {
        // given
        Map<String, String> forms = new HashMap<String, String>();
        forms.put("Name", null);
        forms.put("Title", "title");
        given(pdfTool.extractForms(null)).willReturn(forms);
        Map<String, String> data = new HashMap<String, String>();
        data.put("Name", "name");
        // when
        pdfProcessor.fulfillPDFTemplate(null, null, data);
        // then
        verify(pdfTool).replaceForms(any(File.class), captor.capture());
        Map<String, String> populatedForm = captor.getValue();
        //assertThat(populatedForm.keySet(), contains("Name", "Title"));
        assertThat(populatedForm.get("Name"), equalTo("name"));
        assertThat(populatedForm.get("Title"), equalTo("title"));
    }
}
