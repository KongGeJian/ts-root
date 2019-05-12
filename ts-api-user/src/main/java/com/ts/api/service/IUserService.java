package com.ts.api.service;

import com.ts.api.po.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserService {

    @PostMapping("/getUser")
    UserDTO getUser(@RequestParam("name") String name);

    @PostMapping("/getUserInfo")
    String getUserInfo();
}
