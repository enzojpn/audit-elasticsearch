package br.gov.sp.audit.auditelasticsearch.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	   @RequestMapping("/error")
	    public String handleError() {
	        return "error"; // Retorna o nome do arquivo HTML (error.html)
	    }
 
	    public String getErrorPath() {
	        return "/error";
	    }
}
