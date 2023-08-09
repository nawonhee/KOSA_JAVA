class Sound extends Thread{
	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.print(Thread.currentThread().getName());
			System.out.println(" sound"+i);
		}
	}
}


class Caption implements Runnable{
	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.print(Thread.currentThread().getName());
			System.out.println(" caption"+i);
		}
		
	}
}

public class ThreadTest {

	public static void main(String[] args) {
		//Thread ct = Tread.currentThread();
		//ct.getNmae;
		System.out.println(Thread.currentThread().getName());
		
		Sound s = new Sound();
		//s.run(); //일반호출
		s.start();
		
		/* 재사용성 높은 스레드일 경우 클래스이름을 만든다
		Caption c = new Caption();
		//c.start();
		Thread t = new Thread(c);
		t.start();
		*/
		
		
		/*
		 * 재사용성 없는 스레드인 경우 클래스이름을 만들지 않는다. 익명클래스로 객체 생성한다
		 */
		/*
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<100;i++) {
					System.out.print(Thread.currentThread().getName());
					System.out.println(" caption"+i);
				}
				
			}
		});
		t.start();
		*/
		
		
		/*
		 * 람다식
		 */
		Thread t = new Thread(()->{
			for(int i=0;i<100;i++) {
				System.out.print(Thread.currentThread().getName());
				System.out.println(" caption"+i);
			}
		});
		t.start();
		
		for(int i=0;i<100;i++) {
			System.out.println(" 동영상"+i);
		}
		System.out.println("THE END");
	}

}
