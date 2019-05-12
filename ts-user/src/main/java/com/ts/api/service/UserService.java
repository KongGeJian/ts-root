package com.ts.api.service;

import com.ts.api.po.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService implements IUserService {

	@Value("${server.port}")
	private String serverPort;

	@PostMapping("/getUser")
	@Override
	public UserDTO getUser(@RequestParam("name") String name) {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(name + "端口号:" + serverPort);
		userDTO.setAge(20);
		return userDTO;
	}

	@RequestMapping("/getUserInfo")
	@Override
	public String getUserInfo() {
		System.out.println(" 我是会员服务,会员服务调用订单服务开始啦！！");
		try {
			// 会员服务接口产生1.5秒的延迟
			Thread.sleep(1500);
		} catch (Exception e) {
		}
		return "订单服务接口调用会员服务接口成功....";
	}

}
