package com.myangtzeu.test;

import java.util.ArrayList;
import java.util.List;

import com.myangtzeu.menu.Menu;
import com.myangtzeu.pojo.AccessToken;
import com.myangtzeu.utils.SpiderUtil;
import com.myangtzeu.utils.WeiXinUtil;

import net.sf.json.JSONObject;

public class WeiXinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeiXinUtil.getAccessToken();
			System.out.println("票据："+token.getToken());
			System.out.println("有效时间："+token.getExpiresIn());

			String menu = JSONObject.fromObject(WeiXinUtil.initMenu()).toString();
			System.out.println(menu);
			int result = WeiXinUtil.createMenu(token.getToken(), menu);
			if(result==0){
				System.out.println("创建菜单成功");
			}else{
				System.out.println("错误码："+result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
