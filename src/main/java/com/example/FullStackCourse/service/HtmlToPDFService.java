package com.example.FullStackCourse.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.BaseFont;

public class HtmlToPDFService {

    public static ByteArrayOutputStream createPdfText(String html, boolean secured) {
        System.out.println("HTML content before rendering to PDF:\n" + html);

        ByteArrayOutputStream fs = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        
        URL fontResourceURL = HtmlToPDFService.class.getResource("/fonts/LiberationSans-Regular.ttf");
        URL fontResourceURL2 = HtmlToPDFService.class.getResource("/fonts/LiberationSans-Bold.ttf");
        
        try {
        	renderer.getFontResolver().addFont(fontResourceURL.getPath(), BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
        	renderer.getFontResolver().addFont(fontResourceURL2.getPath(),BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
        }catch(IOException e) {
        	throw new RuntimeException(e);
        } catch (com.lowagie.text.DocumentException e) {
			// TODO Auto-generated catch block
        	throw new RuntimeException(e);
		}
        
        
        try {
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(fs);
            
            PdfReader reader = new PdfReader(fs.toByteArray());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfStamper stamper =new PdfStamper(reader,out);
            
            if(secured==true) {
            	stamper.setEncryption(null, null, PdfWriter.ALLOW_PRINTING,PdfWriter.STANDARD_ENCRYPTION_128);
            }else {
            	stamper.setEncryption(null, null, PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_MODIFY_CONTENTS , PdfWriter.STANDARD_ENCRYPTION_128);
            }
            
            
        } catch (DocumentException e) {
            System.err.println("DocumentException occurred while creating PDF: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception occurred while creating PDF: " + e.getMessage());
            e.printStackTrace();
        }

        return fs;
    }
}
