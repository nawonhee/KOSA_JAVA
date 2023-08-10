package com.my.tcp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public static void main(String[] args) {
		int port = 5432;
		ServerSocket ss = null;  //예외처리 해야 해서 지역변수 null값 주기
		Socket s = null;
		InputStream is = null;
		OutputStream os = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		try {
			ss = new ServerSocket(port);
			System.out.println("클라이언트 접속을 기다리기");
			s = ss.accept();
			is = s.getInputStream();
			//int readValue = is.read();
			//System.out.println("클라이언트가 보낸 메시지: "+(char)readValue);
			dis = new DataInputStream(is);
			//String readValue;
			//dis.readUTF(readValue);
			String receiveMsg;
			os = s.getOutputStream();
			dos = new DataOutputStream(os);

			while(!(receiveMsg = dis.readUTF()).equals("quit")) {
				System.out.println("클라이언트가 보낸 메시지: "+receiveMsg);
				dos.writeUTF(receiveMsg);
			}
		} catch(EOFException e) {
			
		} catch(BindException e) {
			System.out.println(port+"포트가 이미 사용중입니다");
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("클라이언트와의 연결이 종료되었습니다"); //강제종료든 정상종료든 출력되도록 finally에 
			if(s!=null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
