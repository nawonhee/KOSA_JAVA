//제공자 코드
/**
 * TV용 클래스이다
 * @author 나원희
 * @version 1.0
 */
class TV{
	boolean power; //전원
	int channel;
	int volume;
	
	/**
	 * 전원을 켠다
	 */
	void powerOn(){
		power = true; //현재 사용중인 객체(=this)의 power변수를 찾아라 -> main메서드에서 생성한 객체
	}
	
	/**
	 * 전원상태를 반환한다
	 * @return 전원이 켜진 상태면 true반환, 꺼진 상태면 false를 반환한다 
	 */
	boolean isPower(){
		return power;
	}
	
	/**
	 * 채널을 설정한다
	 * @param i 설정할 채널값
	 */
	void setChannel(int i){
		channel = i;
	}
	
	/**
	 * 현재채널값을 반환한다
	 * @return 현재채널값
	 */
	int getChannel(){
		return channel;
	}
	
	/**
	 * 채널값을 1증가한다
	 */
	void channelUp(){
		channel++;
	}
	
	void volumeUp(){
		volume++;
	}
	
	void volumeDown() {
		if(volume==0) {
			return;
		}else {
			volume--;
		}
	}
	int getVolume() {
		return volume;
	}
}

//사용자코드
public class WatchTV {

	public static void main(String[] args) {
		TV tv;  //참조형지역변수선언
		tv = new TV();
		System.out.println(tv.power); //false
		System.out.println(tv.channel); //0
		System.out.println(tv.volume); //0
		
		tv.powerOn();
		
		TV tv1 = new TV();
		tv1.powerOn();
		
		boolean flag = tv.isPower();
		if(flag) {
			System.out.println("전원이 켜졌습니다");
			tv.setChannel(11);
			int ch = tv.getChannel();
			System.out.println("현재 채널은 "+ch);
			
			tv.channelUp();
			System.out.println("현재 채널은 "+tv.getChannel());
			for(int i=0;i<20;i++) {
				tv.volumeUp();
			}
			
			for(int i=0;i<50;i++) {
				tv.volumeDown();
			}
			
			System.out.println("현재 음량은 "+tv.getVolume());
		}else {
			System.out.println("전원이 꺼졌습니다");
		}
	}

}
