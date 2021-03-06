package org.easymis.easysaas.gateway.controller;

import org.easymis.easysaas.common.result.RestResult;
import org.easymis.easysaas.common.utils.MD5Util;
import org.easymis.easysaas.gateway.entitys.mybatis.dto.Member;
import org.easymis.easysaas.gateway.entitys.vo.AuthRequest;
import org.easymis.easysaas.gateway.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;


@Api(description = "系统登录")
@Validated
@RestController
@Slf4j
public class LoginController {



	@Autowired
	private MemberService userRepository;
    //
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RestResult login(AuthRequest ar) {
		Member userDetails=userRepository.findByMobile(ar.getUsername());
		if(userDetails!=null) {
			System.out.println(MD5Util.md5(ar.getPassword()));
			if (MD5Util.md5(ar.getPassword()).equals(userDetails.getPassword())) {
				//return RestResult.buildSuccess(new AuthResponse(jwtUtil.generateToken(userDetails),ar.getUsername()));
			} else {
				return RestResult.buildFail("密码错误");
			}
		}		
		return RestResult.buildFail();
	}
/*	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login( @RequestBody  AuthRequest ar) {
		return userRepository.findByUsername(ar.getUsername()).map((userDetails) -> {
			if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails),ar.getUsername()));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}*/
}
