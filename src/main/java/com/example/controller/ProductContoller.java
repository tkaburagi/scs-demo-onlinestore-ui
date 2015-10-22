package com.example.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.entity.Product;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ProductContoller {
	
	
	@RequestMapping("/products")
	String listPrds(Model model) throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://demo-onlinestore.cfapps.io/products";
		String result = restTemplate.getForObject(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		Product[] prd = mapper.readValue(result, Product[].class);
		System.out.println(result);
		model.addAttribute("prds", prd);
		return "onlinestore/index";
	}

}
