abstract class Shape{ //컴파일시 class Shape extends Object{로 바뀜
	//private double area;
	protected double area;
	double getArea() {
		return area;
	}
	//void makeArea(){}
	abstract void makeArea();
	
	public String toString() {
		return "도형의 면적은 " + area+"입니다";
	}
}

class Triangle extends Shape{
	void makeArea() {
		
	}
}

class Circle extends Shape{
	private int radius;
	
	//Circle(){}
	Circle(int radius){
		this.radius = radius;
	}
	
	int getRadius(){
		return radius;
	}
	
	void makeArea() {
		area = radius*radius*3.14;
	}
	public String toString() {
		return "반지름이 "+radius+"인 원 도형의 면적은 "+area+"입니다";
		//return "반지름이 "+radius+"인 원 "+super.toString();
	}
}

class Rectangle extends Shape{
	private int width;
	private int height;
	
	Rectangle(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	void makeArea() {
		area = width*height;
	}
	public String toString() {
		return "가로"+width+", 세로"+height+"인 원 도형의 면적은 "+area+"입니다";
		//return "가로"+width+", 세로"+height+"인 사각형 "+super.toString();
	}
}

public class ShapeTest { //public class ShapeTest extends Object{}
	public static void main(String[] args) {
		/*
		Circle c = new Circle(5); //반지름이 5인 원객체
		System.out.println(c.getRadius()); //5
		c.makeArea(); //원의 면적을 계산한다
		System.out.println(c.getArea()); //면적값 소수점까지 정확히 출력되도록
		
		Rectangle r = new Rectangle(3, 4); //가로3, 세로4인 사각형객체
		r.makeArea(); //사각형의 면적을 계산한다
		System.out.println(r.getArea()); //면적값 12.0
		*/
		
		Shape[] shapes = new Shape[5];
		shapes[0] = new Circle(5); //업캐스팅
		shapes[1] = new Rectangle(3, 4); //업캐스팅
		System.out.println(shapes[4]); //toString()가 자동 호출 null
		
		//다운캐스팅
		for(int i=0;i<2;i++) {
			shapes[i].makeArea();
			System.out.println(shapes[i].getArea());
			System.out.println(shapes[i].toString());
			
			System.out.println(shapes[i]); //shapes[i].toString()가 자동호출됨
			/*
			//사용자 입장에서 너무 불편함. 결합도가 증가! 
			if(shapes[i] instanceof Circle) {
				Circle c = (Circle)shapes[i];
				c.makeArea();
				System.out.println(shapes[i].getArea());
			}
			else if(shapes[i] instanceof Rectangle) {
				Rectangle r = (Rectangle)shapes[i];
				r.makeArea();
				System.out.println(r.getArea());
			}
			*/
		}
		
		
	}
}
