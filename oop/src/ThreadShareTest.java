class Share {
	private int i; //공유객체 사용하는 다른 thread에게 cpu를 뺏기지 마라! -> 동기화 처리
	
	void push() {
		for(int j=0;j<100;j++) { //밖에 있는 이유? 웬만하면 이블록 안은 짧게 쓰는 걸 권장, 그때그때 잠금
			synchronized(this) {
				this.notify(); //이 공유객체를 사용하는 wait된 스레드를 깨운다
				System.out.print("Before push:i="+this.i);
				this.i++;
				System.out.println(", After push:i="+this.i);
			}
		}
	}
	void pop() {
		for(int j=0;j<100;j++) {
			synchronized(this) { //잠금장치
				if(this.i==0) {
					try {
						this.wait(); //이 공유객체를 사용하는 현재 스레드를 일시중지한다.
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print("Before pop: i="+this.i);
				this.i--;
				System.out.println(", After pop: i="+this.i);
			}
		}
	}
}

class Push extends Thread{
	private Share s;
	Push(Share s){
		this.s =s;
	}
	@Override
	public void run() {
		this.s.push();
	}
}

class Pop extends Thread{
	private Share s;
	Pop(Share s){
		this.s = s;
	}
	@Override
	public void run() {
		this.s.pop();
	}
}

public class ThreadShareTest {
	public static void main(String[] args) {
		Share s;
		s = new Share();
		//Push push = new Push();
		//push.s = s; //private이 아니었을 때
		Push push = new Push(s);
		Pop pop = new Pop(s);
		push.start();
		pop.start();
	}
}
