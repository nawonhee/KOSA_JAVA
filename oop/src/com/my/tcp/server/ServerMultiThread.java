package com.my.tcp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class ServerThread extends Thread{
	private Socket s;
	private InputStream is = null;
	private OutputStream os = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	//private ArrayList<ServerThread> list; //구체화된 클래스타입
	private Vector<ServerThread> list;
	private String clientIP;
	
	ServerThread(Socket s, Vector<ServerThread> list) throws IOException{
		this.s = s;
		this.list = list;
		is = s.getInputStream();
		dis = new DataInputStream(is);
		os = s.getOutputStream();
		dos = new DataOutputStream(os);
		InetAddress ip = s.getInetAddress(); //IP정보를 얻을 수 있음
		clientIP = ip.getHostAddress();
		System.out.println(clientIP+"클라이언트가 접속했습니다");
		broadcast(clientIP+"님이 접속했습니다");
	}
	
	@Override
	public void run() { //오버라이딩 규칙 때문에 throws를 못함
		try { //반복문 안에 하면 반복문이 계속 돌아감
			String receiveMsg;
			while(!(receiveMsg=dis.readUTF()).equals("quit")){
				//System.out.println("클라이언트가 보낸 메시지: "+receiveMsg);
				//dos.writeUTF(receiveMsg);
				/*
				for(ServerThread t:list) { //접속한 모든 클라이언트에게 송신
					t.dos.writeUTF(clientIP+">"+receiveMsg);
				}
				*/
				broadcast(clientIP+">"+receiveMsg);
			}
		}catch(EOFException e) {
			
		}
		catch(IOException e) {
			
		}finally {
			list.remove(this);
			System.out.println(clientIP+"님이 나갔습니다");
			//System.out.println("클라이언트와의 연결이 종료되었습니다"); //강제종료든 정상종료든 출력되도록 finally에 
			/*for(ServerThread t:list) {
				try { //얘는 작업 중단하면 안 되니깐 반복문 안에서 잡아준다
					t.dos.writeUTF(clientIP+"님이 나갔습니다"); //각각의 클라이언트에게 전송하는 과정
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			*/
			broadcast(clientIP+"님이 나갔습니다");
			if(s!=null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	private void broadcast(String msg) {
		for(ServerThread t : list) {
			try {
				t.dos.writeUTF(msg);
			}catch(IOException e) {
				
			}
		}
	}
}

public class ServerMultiThread {

	public static void main(String[] args) {
	
		int port = 5432;
		ServerSocket ss = null;  //예외처리 해야 해서 지역변수 null값 주기
		Socket s = null;
		
		//List<ServerThread> list = new List<>(); //일반화된 인터페이스타입
		//-> List<ServerThread> list = new Vector<>();
		//ArrayList<ServerThread> list = new ArrayList<>(); //구체화된 클래스 타입
		Vector<ServerThread> list = new Vector<>();
		
		try {
			ss = new ServerSocket(port);
			System.out.println("클라이언트 접속을 기다리기");
			while(true) {
				s = ss.accept(); //접속되면 소켓 생성
				ServerThread t = new ServerThread(s, list);
				list.add(t);
				t.start(); //새로운 스레드 시작
			}
			
		} catch(EOFException e) {
			
		} catch(BindException e) {
			System.out.println(port+"포트가 이미 사용중입니다");
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
