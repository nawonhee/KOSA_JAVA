import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;

class Horse extends Canvas implements Runnable{
	private String name;
	private int x;
	private int y;
	Horse(String name){
		this.name = name;
		x = 50;
		y = 10;
	}
	@Override
	public void paint(Graphics g) {
		g.drawString(name, x, y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			System.out.println(name);
			this.setX(this.getX()+10);
			this.repaint();
			long millis = (long) (Math.random()*500+1); //0.0<= <1.0
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
public class HorseRace {
	private JFrame f; //프레임
	private JButton btReady, btStart; //버튼
	private Horse[] horses;
	public HorseRace(){
		f = new JFrame("달려"); //액자 객체생성
		f.setLayout(new GridLayout(4,1)); //4행1열
		Container c = f.getContentPane(); //프레임뒷판
		horses = new Horse[3]; //말 3마리 배열
		for(int i=0; i<horses.length; i++) {
			horses[i]= new Horse((i+1)+"번 말");
			c.add(horses[i]); //추가된 Horse의 paint()자동호출됨
			
		}
		Panel p = new Panel();
		btReady = new JButton("준비");
		btStart = new JButton("달려");
		p.add(btReady);p.add(btStart);
		c.add(p);
		
		btReady.addActionListener((e)->{
			for(Horse h : horses) {
				h.setX(0); //뒤로 위치시킴
				h.repaint(); //Horse객체의 drawing이 clear되고 paint()호출됨
			}
		});
		btStart.addActionListener((e)->{
			for(Horse h : horses) {
//				for(int i=0;i<20;i++) {
//					h.setX(h.getX()+10);
//					h.repaint();
//				}
				Thread t = new Thread(h);
				t.start();
			}
		});
		f.setSize(500, 200);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new HorseRace();
	}
}