import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
	
	public static void select() {
		//1. jdbc드라이버설치
		//2. 드라이버 클래스들 jvm에 로드
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("JDBC드라이버 로드성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
				
		//3. DB와 연결
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //아이피,포트번호,서비스아이디
		String user = "hr";
		String password = "hr";
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB와 연결 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		/*
		//4. sql문 db에 송신하기
		Statement stmt = null;
				
		//5. sql문 결과 수신하기
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			int dId=60; //부서번호
			String fn = "D";
			String selectSQL = "SELECT employee_id, first_name, hire_date, salary\r\n"
								+ "FROM employees\r\n"
								+ "WHERE department_id="+dId
								+"AND SUBSTR(first_name, 1, 1) = '"+fn+"'";
			rs = stmt.executeQuery(selectSQL); //송신, rs: 결과집합수신
			while(rs.next()) {
				int eId = rs.getInt("employee_id");
				String eName = rs.getString(2); //칼럼인덱스1부터 시작함 //rs.getString("first_name");
				Date eHdt = rs.getDate(3);
				int eSal = rs.getInt("salary");
				System.out.println(eId+":"+eName+":"+eHdt+":"+eSal);
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//연결 꼭 끊어야 메모리 누수 안 생김
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		*/
				
		//4. sql문 db에 송신하기 --> 얘를 더 권장
		PreparedStatement pstmt = null;
		//5. sql문 결과 수신하기
		ResultSet rs = null;
					
		String selectSQL = "SELECT employee_id, first_name, hire_date, salary\r\n" 
				+"FROM employees\r\n"
				+"WHERE department_id=?\r\n"
				+"AND SUBSTR(first_name,1,1)=?";
		try {
			pstmt = conn.prepareStatement(selectSQL); //일단 송신, 오라클 엔진에게 미리 보내서 기억시킴
			pstmt.setInt(1, 60); //첫번째 물음표값에 세팅
			pstmt.setString(2, "D"); //두번째 물음표값에 세팅
			rs = pstmt.executeQuery(); //바인드변수(믈음표)만 보냄
						
			while(rs.next()) {
				int eId = rs.getInt("employee_id");
				String eName = rs.getString(2); //칼럼인덱스1부터 시작함 //rs.getString("first_name");
				Date eHdt = rs.getDate(3);
				int eSal = rs.getInt("salary");
				System.out.println(eId+":"+eName+":"+eHdt+":"+eSal);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//연결 꼭 끊어야 메모리 누수 안 생김
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
					
	}

	public static void insertTest() {
		//2.jdbc드라이버 로드
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로드 성공");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		//3. db와 연결
		Connection conn = null;
		String url="jdbc:oracle:thin:@192.168.1.84:1521:xe";
		String user="hr";
		String password = "hr";
		try {
			conn = DriverManager.getConnection(url, user,password);
			//conn.setAutoCommit(false); 자동종료되지 않도록 
			System.out.println("DB와 연결 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//4. sql구문 송신
		PreparedStatement pstmt = null;
		String insertSQL = "INSERT INTO customer(id, pwd, name) VALUES (?,?,?)";
		try {
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, "id12");
			pstmt.setString(2, "p84");
			pstmt.setString(3, "나wh");
			int rowcnt = pstmt.executeUpdate(); //이 구문이 삽입하는 행수 반환하는 함수
			System.out.println(rowcnt+"건 추가 성공");
			//conn.commit(); 성공하면 종료
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//conn.rollback(); 오류나면 되돌리기
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	public static void main(String[] args) {
		//selectTest();
		insertTest();
	}

}
