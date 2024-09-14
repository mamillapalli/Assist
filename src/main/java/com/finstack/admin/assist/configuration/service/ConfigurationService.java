package com.finstack.admin.assist.configuration.service;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
@Service
public class ConfigurationService {
	public String getLogoUrl(String title) {
        try {
            // Load XML configuration
            ClassPathResource resource = new ClassPathResource("configuration.xml");
            InputStream inputStream = resource.getInputStream();

            // Parse XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

         // Extract URL based on title
            NodeList pageInfos = document.getElementsByTagName("PageInfo");
            for (int i = 0; i < pageInfos.getLength(); i++) {
                String xmlTitle = pageInfos.item(i).getChildNodes().item(3).getTextContent(); // Assuming title is at index 3
                if (title.equalsIgnoreCase(xmlTitle)) {
                    return pageInfos.item(i).getChildNodes().item(1).getTextContent(); // Assuming image is at index 1
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Log the error and return a default value or an error message
            return "Error retrieving logo URL";
        }
        // Return a default value if no match is found
        return "Logo not found";
    }
}
