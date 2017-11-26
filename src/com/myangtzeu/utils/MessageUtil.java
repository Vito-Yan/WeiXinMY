package com.myangtzeu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.myangtzeu.pojo.News;
import com.myangtzeu.pojo.NewsMessage;
import com.myangtzeu.pojo.TextMessage;
import com.myangtzeu.servlet.LoginServlet;
import com.thoughtworks.xstream.XStream;


public class MessageUtil {
	
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_EVENTKEY = "EventKey";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE = "scancode_push";
	
	//xml转为map集合
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String,String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	//将文本对象转为xml
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);		
	}
	
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);;
		text.setToUserName(fromUserName);;
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	}
	
	//主菜单
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("按菜单提示进行操作：\n\n");
		sb.append("1、全部成绩\n");
		sb.append("2、绩点\n");
		sb.append("回复M调出此菜单");
		return sb.toString();
	}
	
	public static String firstMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("您的成绩如下：\n\n\n\n");
		sb.append("回复M调出此菜单");
		return sb.toString();
	}
	
	public static String secondMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("您的绩点如下：\n");
		sb.append("回复M调出此菜单");
		return sb.toString();
	}
	
	//图文消息转为xml
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);		
	}
	
	//全部成绩事件
	public static String initCj(String toUserName,String fromUserName,String account,String password){
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		
		News news = new News();
		news.setTitle("您的全部成绩如下：");
		
		SpiderUtil su = new SpiderUtil();
		List<String> list = new ArrayList<String>();
		su.spiderCj(account, password, list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if(i%2 == 0){
				sb.append(":");
			}else{
				sb.append("\n");
			}									
		}
		news.setDescription(sb.toString()+"\n\n"+"回复【全部成绩】查所有成绩\n"+"回复【绩点】查看绩点\n"+"回复【重置】重新绑定学号");	
//		news.setPicUrl("http://ap18458589.iok.la/WeiXinMY/WebRoot/image/IMG_6497.PNG");
		news.setUrl("www.yuol.online:8080");
		newsList.add(news);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		
		message = newsMessageToXml(newsMessage);
		return message;
	}
	
	//绩点事件
	public static String initJd(String toUserName,String fromUserName){
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		
		News news = new News();
		news.setTitle("您的必修课绩点：");
		news.setDescription("（逃。。。。。");
//		news.setPicUrl("http://ap18458589.iok.la/WeiXinMY/WebRoot/image/IMG_6497.PNG");
		news.setUrl("www.yuol.online:8080");
		newsList.add(news);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		
		message = newsMessageToXml(newsMessage);
		return message;
	}
	
	//重置事件
	public static String initCz(String toUserName,String fromUserName){
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		
		News news = new News();
		news.setTitle("重置学号成功！");
		news.setDescription("点击绑定学号");
//		news.setPicUrl("http://ap18458589.iok.la/WeiXinMY/WebRoot/image/IMG_6497.PNG");
		news.setUrl("http://ap18458589.iok.la/WeiXinMY/index.jsp");
		newsList.add(news);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		
		message = newsMessageToXml(newsMessage);
		return message;
	}
}
