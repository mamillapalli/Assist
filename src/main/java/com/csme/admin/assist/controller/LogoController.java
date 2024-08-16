package com.csme.admin.assist.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping
public class LogoController {
	 @GetMapping("/logo")
	    public ResponseEntity<Resource> getLogo() {
	        Resource logo = new ClassPathResource("static/finstack_logo.png");

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_TYPE, "image/png")
	                .body(logo);
}
}