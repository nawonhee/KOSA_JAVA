import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * GUI프로그램 순서
 * 1. 이벤트 소스(bt)와 이벤트 종류(ActionEvent)를 결정한다
 * 2. 이벤트처리용 클래스(이벤트핸들러)를 작성한다
 * class  MyHandler implements ActionListener{}
 * 3. 이벤트소스와 이벤트핸들러를 연결한다
 *    bt.addActionListener( new MyHandler() );
 */

/*
class MyHandler implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("클릭되었습니다");
	}
	
}
*/

/*
class MyHandler implements ActionListener{
	private JTextField t;
	MyHandler(JTextField t){
		this.t = t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		t.setText("클릭되었습니다");
	}
	
}
*/

public class GUITest {
	private JFrame f;
	private JButton bt;
	private JTextField t;
	
	/*
	class MyHandler implements ActionListener{  //위 outerclass를 innerclass로 바꿔봤음
		@Override
		public void actionPerformed(ActionEvent e) {
			t.setText("클릭되었습니다"); //t를 만들 필요 없이 갖다 쓸 수 있다
		}
	}
	*/
	
	public GUITest() {
		f = new JFrame("프레임"); //창
		bt = new JButton("클릭"); //버튼
		t = new JTextField("입력하세요"); //한줄 입력란
		
		Container c = f.getContentPane(); //프레임뒷판
		c.setLayout(new FlowLayout()); //순서대로 정렬
		c.add(bt); //버튼추가
		c.add(t); //텍스트필드 추가
		
		//bt.addActionListener( new MyHandler() );
		//bt.addActionListener( new MyHandler(t) );
		/* 더 줄인 거
		 bt.addActionListener( new ActionListener() {
		 
			public void actionPerformed(ActionEvent e) {
			t.setText("클릭되었습니다"); //t를 만들 필요 없이 갖다 쓸 수 있다
		} 
		});
		*/
		bt.addActionListener((e)->{ t.setText("클릭되었습니다");});
		
		f.setSize(300, 200); //프레임 크기 결정
		f.setVisible(true); //보여줌
	}
	public static void main(String[] args) {
		new GUITest();
	}

}