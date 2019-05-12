/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.ts.api.fallback;

import com.ts.api.feign.IUserServiceFeigin;
import com.ts.api.po.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements IUserServiceFeigin {


	@Override
	public UserDTO getUser(String name) {
		return null;
	}


	@Override
	public String getUserInfo() {
		return "服务器忙,请稍后重试!";
	}

}
