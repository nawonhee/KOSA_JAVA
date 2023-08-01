import java.util.*;

public class Condition{
    public static void main(String[] args){
        /*
        //int a=4;
        int a = (int)(Math.random()*100); //0<= <100
        System.out.println("a="+a);

        if(a%2==1){
            System.out.println("홀수");
            System.out.println("제곱값은" + (a * a)); 
        }
        
        if(a%2==1){
            System.out.println("홀수");
        }
        else{
            System.out.println("짝수");
        }
        */

         
        //int year = 2023;
        System.out.print("출생년도를 입력하세요:");
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int year = sc.nextInt();

        System.out.println(year + "년도에 해당하는 동물을 출력하시오");
        //년도를 12로 나눈 나머지값이 0이면 원숭이, 1이면 닭, 2이면 개, 3이면 돼지, 
        // 4이면 쥐띠, 5이면 소, 6이면 호랑이, 7이면 토끼, 8이면 용, 9이면 뱀, 10이면 말
        // 11이면 양
        
        int mod = year%12;
        
        String []arr = {"원숭이띠", "닭띠", "개띠", "돼지띠", "쥐띠", "소띠", "호랑이띠", "토끼띠", "용띠", "뱀띠", "말띠", "양띠"};
        System.out.println(arr[mod]+"띠입니다.");
        /*
        if(mod == 0)
            System.out.println("원숭이띠입니다.");
        else if(mod == 1)
            System.out.println("닭띠입니다.");
        else if(mod == 2)
            System.out.println("개띠입니다.");
        else if(mod == 3)
            System.out.println("돼지띠입니다.");
        else if(mod == 4)
            System.out.println("쥐띠입니다.");
        else if(mod == 5)
            System.out.println("소띠입니다.");
        else if(mod == 6)
            System.out.println("호랑이띠입니다.");
        else if(mod == 7)
            System.out.println("토끼띠입니다.");
        else if(mod == 8)
            System.out.println("용입니다.");
        else if(mod == 9)
            System.out.println("뱀띠입니다.");
        else if(mod == 10)
            System.out.println("말띠입니다.");
        else if(mod == 11)
            System.out.println("양띠입니다.");
        */

        int koScore = 70;
        int engScore = 60;
        int mathScore = 84;
        int totalScore = koScore + engScore + mathScore; //총점
        float avg = (float)totalScore/3; //평균   or toalScore/3.0F
        System.out.println(avg);

        //평균값이 90점이상이면 "A등급"을 출력
        // 80점 이상이면 "B등급"을 출력
        // 70점 이상이면 "C등급"을 출력
        // 60점 이상이면 "D등급"을 출력
        // 미만이면 "F등급"을 출력

        /*
        if(avg>=90)
            System.out.println("A등급");
        else if(avg>=80)
            System.out.println("B등급");
        else if(avg>=70)
            System.out.println("C등급");
        else if(avg>=60)
            System.out.println("D등급");
        else
            System.out.println("F등급");
        */

        String []name = {"가위", "바위", "보"};
        System.out.print("가위바위보게임");
        System.out.print("가위-1, 바위-2, 보-3 을 입력하세요");
        sc = new java.util.Scanner(System.in);

        int u = sc.nextInt(); //사용자가 낸 값
        System.out.println("사용자가 낸 값: " +name[u-1]);

        int r = (int)(Math.random()*3+1); //컴퓨터가 낸 값
        System.out.println("컴퓨터가 낸 값: " +name[r-1]);

        if(u==r)
            System.out.println("비겼습니다.");
        else if(u<1 || u>3)
            System.out.println("잘못 입력하셨습니다.");
        else if(u==1 && r==3 || u==2 && r==1 || u==3 && r==2)
            System.out.println("이겼습니다.");
        else
            System.out.println("졌습니다.");
        

        int a = 4;

        switch(a%2){
            case 1:
                System.out.println("홀수");
                break;
            case 0:
                System.out.println("짝수");
        }

        year = 2023;
        mod = year%12;
        
        switch(mod){
            case 0:
                System.out.println("원숭이띠");
                break;
            case 1:
                System.out.println("닭띠");
                break;
            default:
                System.out.println("그외의 동물");
        }
        

        Calendar c;
        c = Calendar.getInstance(); //현재날짜객체
        int month = c.get(Calendar.MONTH); //월 : 1월인 경우 0, 2월인경우 1반환
        System.out.println(month); //7
        if(month<=5){ //상반기
            System.out.println("현재"+(month+1)+"는 상반기입니다");
        }
        else{
            System.out.println("현재"+(month+1)+"는 하반기입니다");
        }

        switch(month) {
            case 0 : 
            case 1 : 
            case 2 : 
            case 3 : 
            case 4 : 
            case 5 :
                System.out.println("현재"+(month+1)+"는 상반기입니다");
            default :  
                System.out.println("현재"+(month+1)+"는 하반기입니다");
        }
        
    }
}