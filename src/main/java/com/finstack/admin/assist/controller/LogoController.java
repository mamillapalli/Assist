package com.finstack.admin.assist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finstack.admin.assist.configuration.service.ConfigurationService;
@RestController
@RequestMapping("/logo")
public class LogoController {
	
	@Autowired
    private ConfigurationService configurationService;
	
	 @GetMapping("/{title}")
	 public ResponseEntity<String> getLogo(@PathVariable String title) {
	        try {
	            // Fetch the logo URL from the configuration service
	            String logoUrl = configurationService.getLogoUrl(title + " logo"); // Append ' logo' to match the titles in XML
	            System.out.println("Fetched logo URL: " + logoUrl);

	            // Handle cases where the logo URL is not found or invalid
	            if ("Logo not found".equals(logoUrl) || "Error retrieving logo URL".equals(logoUrl) || logoUrl == null) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Logo not found or error retrieving URL");
	            }

	            // Return the logo URL
	            return ResponseEntity.ok().body(logoUrl);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
	        }
	    }
}