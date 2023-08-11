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

class ClientThread extends Thread {
	private Socket s;
	InputStream is = null;
	DataInputStream dis = null;
	
	ClientThread(Socket s) throws IOException{
		this.s = s;
		is = s.getInputStream();
		dis = new DataInputStream(is);
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				String receiveMsg;
				receiveMsg = dis.readUTF();
				//System.out.println("서버가 되돌려준 메시지:" + receiveMsg);
				System.out.println(receiveMsg);
			}
		}catch(SocketException e) {
		}catch(EOFException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class ClientMultiThread {

	public static void main(String[] args) {
		Socket s = null;
		String serverIP = "127.0.0.1";//"192.168.260.260.1";//"127.0.0.1"; //인터넷이 연결되어 있지 않아도 사용한 가능한 ip (localhost)
		int serverPort = 5432;
		OutputStream oos = null;
		DataOutputStream dos = null;

		
		Scanner sc = new Scanner(System.in);
		
		try {
			s = new Socket(serverIP, serverPort);
			System.out.println("서버와 연결 성공");
			
			new ClientThread(s).start(); //새 스레드 시작
			
			oos = s.getOutputStream();
			dos = new DataOutputStream(oos);
			
			String sendMsg;
			do {
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				
			}while(!sendMsg.equals("quit"));
		}catch (UnknownHostException e) {
			//e.printStackTrace();
			System.out.println(serverIP +"서버는 존재하지 않습니다. 서버IP를 확인하세요 ");
		} catch(ConnectException e) { //자식예외
			System.out.println("서버가 실행되지 않았습니다. 서버실행을 확인하세요");
		} catch(SocketException e) { //부모예외
			System.out.println("서버가 강제종료되었습니다. 서버확인하세요");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//dos.close(); -> oos.close() -> s.close();
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
