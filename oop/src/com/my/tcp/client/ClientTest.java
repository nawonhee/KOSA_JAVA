package com.my.tcp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTest {
	public static void main(String[] args) {
		Socket s = null;
		String serverIP = "127.0.0.1";//"192.168.260.260.1";//"127.0.0.1"; //인터넷이 연결되어 있지 않아도 사용한 가능한 ip (localhost)
		int serverPort = 5432;
		OutputStream oos = null;
		DataOutputStream dos = null;
		Scanner sc = new Scanner(System.in);
		
		InputStream is = null;
		DataInputStream dis = null;
		
		try {
			s = new Socket(serverIP, serverPort);
			System.out.println("서버와 연결 성공");
			oos = s.getOutputStream();
			//oos.write(65);
			dos = new DataOutputStream(oos);
			//dos.writeUTF("안농 나는 원희!");
			
			String sendMsg;
			
			is = s.getInputStream();
			dis = new DataInputStream(is);
			String receiveMsg;
			do {
				System.out.print("서버로 보낼 메시지(종료하려면 quit을 입력하세요.): ");
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				receiveMsg = dis.readUTF();
				System.out.println("내가 보낸 메시지: "+receiveMsg);
			}while(!sendMsg.equals("quit"));
		}catch(EOFException e) {
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println(serverIP+"서버 아이피는 존재하지 않습니다. 서버IP를 확인하세요");
		} catch (ConnectException e) {
			System.out.println("서버가 실행되지 않았습니다. 서버 실행하세요");
		} catch (SocketException e) { //연결은 됐는데 소켓이 망가진 경우
			System.out.println("서버가 강제종료되었습니다. 서버 확인하세요");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("서버와의 연결이 종료되었습니다");
			if(s!=null) {
				try {
					s.close(); //소켓이 닫히면서 관련된 stream도 닫힘
				} catch (IOException e) {
				}
			}
		}
	}
}
