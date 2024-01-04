package com.example.FullStackCourse.Controller;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.FullStackCourse.service.PdfGenerativeService;

@Controller
@RequestMapping("/p")
public class PdfController {

	@Autowired
	PdfGenerativeService pdfGenerativeService;
	
	@GetMapping
	public ResponseEntity<Resource> createPdf(@RequestParam(required=false,defaultValue="Default title") String title){
		ByteArrayOutputStream pdf = pdfGenerativeService.createPdf(title);
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"test.pdf\"")
				.body(new ByteArrayResource(pdf.toByteArray()));
		
	}
}
