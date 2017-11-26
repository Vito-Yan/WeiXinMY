package com.myangtzeu.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.myangtzeu.pojo.CJ;

public class SpiderUtil {

	public  List<String> spiderCj(String account, String password, List<String> list){
		
		//System.setProperty("phantomjs.binary.path", "D:\\长大在线\\我的长大\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		//dCaps.setCapability("takesScreenshot", false);
		WebDriver driver = new PhantomJSDriver(dCaps);
		try {
			driver.get("http://jwc2.yangtzeu.edu.cn:8080/login.aspx");
			driver.findElement(By.name("txtUid")).sendKeys("201402632");
			driver.findElement(By.name("txtPwd")).sendKeys("211599100yxz");
			//Actions action = new Actions(driver);
			//action.contextClick(driver.findElement(By.id("btLogin")));
			driver.findElement(By.id("btLogin")).click();
			Set<Cookie> cookies = driver.manage().getCookies();
			driver.get("http://jwc2.yangtzeu.edu.cn:8080/cjcx.aspx");
			String js = "document.getElementById('btAllcj').removeAttribute('onclick');document.getElementById('btAllcj').setAttribute('type', 'submit');";
			((JavascriptExecutor) driver).executeScript(js);
			driver.findElement(By.name("btAllcj")).click();
			String html = driver.getPageSource();
			Document doc = Jsoup.parse(html);
			Elements trs = doc.select("table").select("tr");
//			CJ cj = new CJ();
//			List<String> list = new ArrayList<String>();
			for(int i = 0;i<trs.size();i++){
				if(i>1){
					Elements tds = trs.get(i).select("td");
					for(int j = 0;j<tds.size();j++){
						if(j==0){
							String course = tds.get(j).text();
//							cj.setCourse(course);
							list.add(course);
						}else if(j==1){
							String score = tds.get(j).text();
//							cj.setScore(score);
							list.add(score);
						}						
					}
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(driver != null){
				driver.quit();
			}			
		}
		return list;
	}
}
