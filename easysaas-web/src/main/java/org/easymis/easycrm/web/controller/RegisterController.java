package org.easymis.easycrm.web.controller;

import org.easymis.easycrm.web.service.CrmLeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "登录")
@RequestMapping("register")
@Validated
@Controller
public class RegisterController {
	@Autowired
	private CrmLeadsService leadsService;
	
	@ApiOperation(value = "WebSocket测试首页", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "/index.html", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model) throws Exception {
		System.out.println("WebSocket测试首页!!!");
		model.addAttribute("userId", "test001");
		return "/index";
	}

}
