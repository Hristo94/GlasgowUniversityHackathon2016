package com.emotiv.examples.AverageBandPowers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TimerTask;

public class FocusMonitor extends TimerTask {
	String url = "http://foohackathon.pythonanywhere.com/home/post/";
	String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
	String param1;
	Boolean focused = true;
	HttpRequestSender httpRequestSender = new HttpRequestSender();
	int prev = 0;
	URLConnection connection;

	@Override
	public void run() {
		
		//System.out.println(AverageBandPowers.getFocusValue());
		// TODO Auto-generated method stub
	    //System.out.println(AverageBandPowers.getFocusValue());
		if (AverageBandPowers.getFocusValue() >= 1000 && focused == true) {
			System.out.println("Not focused");
			try {
				httpRequestSender.sendGet(url, 0);
				focused = false;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			//System.out.println("RAZSEQN E!");
		else if (AverageBandPowers.getFocusValue() < 1000 && focused == false){
			System.out.println("Focus");
			try {
				httpRequestSender.sendGet(url, 1);
				 focused = true;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			//System.out.println("NE E RAZSEQN!");
		AverageBandPowers.setFocusValue(0);
	}
}
