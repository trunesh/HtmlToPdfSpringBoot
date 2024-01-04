package com.example.FullStackCourse.service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import utility.ImageUtils;

@Service
public class PdfGenerativeService {
	@Autowired
SpringTemplateEngine springTemplateEngine;
	
	public ByteArrayOutputStream createPdf(String title) {
		return HtmlToPDFService.createPdfText(rendererHtmlForPdf(title),true);
	}
	
	private String rendererHtmlForPdf(String title) {
		Context context=new Context();
		
		context.setVariable("title", title);
		context.setVariable("image1", ImageUtils.convertImageToBase64("pdf/images/image1.jpg"));
		context.setVariable("barcode1",BarCodeService.getBarCodeAsBase64("Invoice-12345678"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		context.setVariable("today", dateFormat.format(new Date()));
		return springTemplateEngine.process("pdf/confirmation.html",context);
	}
}
