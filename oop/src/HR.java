import com.my.product.dto.Product;

class Employee{
	String no;
	String name;
	int salary;
	
	void setNo(String no){
		this.no = no;
	}
	void setName(String name){
		this.name = name;
	}
	void setSalary(int salary) {
		this.salary = salary;
	}
	String getNo(){
		return no;
	}
	String getName() {
		return name;
	}
	int getSalary() {
		return salary;
	}
	void print() {
		System.out.println("사번:"+no+", 사원명:"+name+", 기본급:"+salary);
	}
}

public class HR {
	public static void main(String[] args) {
		Employee e1 = new Employee();
		String no = e1.no;
		System.out.println(e1.no);
		
		e1.setNo("2301");
		e1.setName("오문정");
		e1.setSalary(1000);
		System.out.println("사원의 사번-"+e1.getNo()+", 사원명-"+e1.getName()+", 기본급-"+e1.getSalary());
		e1.print(); //사번:사번값, 사원명:사원명값, 기본급:기본급값 출력
		
		Employee eTemp;
		//eTemp = null;
		//eTemp = new Employee();
		eTemp = e1;
		System.out.println(e1==eTemp); //true
		eTemp.setSalary(2000);
		System.out.println(e1.getSalary());
		eTemp = null; //참조고리를 끊겠다는 의미
		System.out.println(eTemp); //null
		System.out.println(e1.getSalary()); //2000
		//System.out.println(eTemp.getSalary()); //NullPointerException 발생 후 프로그램 종료
		System.out.println("THE END");
		
		Employee[] employees; //선언
		//최대20명의 사원이 저장될 수 있는 배열 생성
		employees = new Employee[20];
		employees[0] = e1;
		
		for(int i=2;i<=5;i++) {
			Employee e2 = new Employee();
			e2.setNo("230"+i);
			e2.setName("name"+i);
			e2.setSalary(1000);
			employees[i-1] = e2;
		}
		
		/*
		Employee e2 = new Employee();
		e2.setNo("2302");
		e2.setName("name2");
		e2.setSalary(1000);
		employees[1] = e2;
		
		Employee e3 = new Employee();
		e3.setNo("2303");
		e3.setName("name3");
		e3.setSalary(1000);
		employees[2] = e3;
		
		Employee e4 = new Employee();
		e4.setNo("2304");
		e4.setName("name4");
		e4.setSalary(1000);
		employees[3] = e4;
		
		Employee e5 = new Employee();
		e5.setNo("2305");
		e5.setName("name5");
		e5.setSalary(1000);
		employees[4] = e5;
		*/
		
		for (int i=0;i<5;i++) {
			employees[i].print();
		}
		
		Product[] produrts = new Product[5]; //상품저장소
		int totalCnt; //저장된 상품수
		
	} 
}
