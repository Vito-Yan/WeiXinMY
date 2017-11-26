package com.myangtzeu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myangtzeu.utils.CheckUtil;
import com.myangtzeu.utils.MessageUtil;

public class WeiXinServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp"); 
		String nonce = request.getParameter("nonce"); 
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter(); 
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Map<String,String> map = MessageUtil.xmlToMap(request);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String message = null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				if("1".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());					
				}else if("2".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.secondMenu());					
				}else if("M".equals(content) || "m".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}
//				TextMessage text = new TextMessage();
//				text.setFromUserName(toUserName);
//				text.setToUserName(fromUserName);
//				text.setMsgType("text");
//				text.setCreateTime(new Date().getTime());
//				text.setContent("您发送的消息是："+content);
//				message = MessageUtil.textMessageToXml(text);
			}else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
				String eventType = map.get("Event");
				String eventKey = map.get("EventKey");
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
					if(eventKey.equals("11")){
						HttpSession session = request.getSession();
						String account = (String) session.getAttribute("account");
						String password = (String) session.getAttribute("password");
						message = MessageUtil.initCj(toUserName, fromUserName, account, password);
						System.out.println(account);
					}else if(eventKey.equals("12")){
						message = MessageUtil.initJd(toUserName, fromUserName);
					}else if(eventKey.equals("22")){
						response.sendRedirect("InvalidateServlet");
			            message = MessageUtil.initCz(toUserName, fromUserName); 						
					}
				}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
					String url = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, url);
				}else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
					String key = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, key);
				}
			}else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				String label = map.get("Label");
				message = MessageUtil.initText(toUserName, fromUserName, label);
			}
			out.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
