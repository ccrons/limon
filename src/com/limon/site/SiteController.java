package com.limon.site;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.limon.base.controller.BaseController;

@Controller

public class SiteController extends BaseController{
	
	@RequestMapping("/site")
    public void site(HttpServletRequest request, HttpServletResponse response){
		try {
			String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
			response.sendRedirect(allPath+"site/index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}
