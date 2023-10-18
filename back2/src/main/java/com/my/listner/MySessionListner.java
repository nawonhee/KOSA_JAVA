package com.my.listner;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


@WebListener
public class MySessionListner implements HttpSessionAttributeListener {
	private int loginedCnt;
	
    public MySessionListner() {
        System.out.println("MySessionListner객체 생성됨");
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
         System.out.println("MySessionListner.attributeAdded()메서드 호출됨");
         if(se.getName().equals("loginedId")) {
        	 loginedCnt++;
        	 System.out.println("로그인되었습니다: 로그인된 사용자수 : "+loginedCnt);
         }
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
//    	System.out.println("MySessionListner.attributeRemoved()메서드 호출됨");
    	if(se.getName().equals("loginedId")) {
       	 loginedCnt--;
       	 System.out.println("로그아웃되었습니다: 로그인된 사용자수 : "+loginedCnt);
        }
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
