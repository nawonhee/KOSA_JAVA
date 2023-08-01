import java.util.Scanner;

public class Loop{
    public static void main(String[] args){
        /*
        System.out.println("JAVA");
        System.out.println("JAVA");
        System.out.println("JAVA");
        System.out.println("JAVA");
        System.out.println("JAVA");
        */

    	/*
        int i = 0;
        while(i<5){
            System.out.println("JAVA" + i);
            i++;
        }  
        */
    	
    	int i;
    	for(i=0;i<5;i++) {
    		System.out.println("JAVA"+i);
    	}

        int sum = 0;
        /*
        sum+=1;  //sum=sum+1
        sum+=2;  //sum=sum+2
        sum+=3;  //sum=sum+3
        sum+=4;  
        sum+=5;  
        sum+=6;  
        sum+=7;  
        sum+=8;  
        sum+=9;  
        sum+=10;  
        */

        i=1;
        while(i<=10){
            sum+=i;
            i++;
        }
        System.out.println("1~10합: " + sum);

        /*
        i=1;
        sum=0;
        //1~100합
        while(i<=100){
            sum+=i;
            i++;
        }
        */
        
        sum=0;
        for(i=1;i<=100;i++) {
        	sum+=i;
        }
        System.out.println("1~100합: "+sum);
        

        i=1;
        int oddSum=0;
        int evenSum=0;
        //1~100의 홀수합, 짝수합
        while(i<=100){
            if(i%2!=0)
                oddSum+=i;
            else
                evenSum+=i;

            i++;
        }
        System.out.println("홀수합: "+oddSum);
        System.out.println("짝수합: "+evenSum);
        
        //값치환
        int a, b, temp;
        a=10;
        b=20;
        
        temp=a;
        a=b;
        b=temp;
        
        System.out.println(a+" "+b);
        
        char ch='A';
//        System.out.println(ch);
//        ch++; //ch=ch+1;
//        
//        System.out.println(ch);
//        ch++;
        
        /*
        while(ch<='Z') {
        	System.out.print(ch);
        	System.out.print(',');
        	//ch++;
        	ch = (char)(ch+1);
        }
        */
        
        for(ch='A';ch<='Z';ch++) {
        	System.out.print(ch);
        	System.out.print(',');
        }
        
        //피보나치 수열
        System.out.println("피보나치 수열");
        
        int bbNum = 1; //전전수
        int bNum = 0; //전수
//        int cNum = bbNum+bNum; //현재수1
//        System.out.println(cNum);
//        //전수를 전전수에 대입
//        //현재수를 전수에 대입
//        
//        bbNum = bNum; //전수를 전전수에 대입 :0
//        bNum = cNum; //현재수를 전수에 대입 전수 :1
//        cNum = bbNum+bNum; //현재수 1
//        
//        cNum = bbNum+bNum; //현재수 1
//        System.out.println(cNum); 
//        bbNum = bNum; //전수를 전전수에 대입 :1
//        bNum = cNum; //현재수를 전수에 대입 전수 :1
//        cNum = bbNum+bNum; //현재수 2
//        
//        cNum = bbNum+bNum; //현재수 2
//        System.out.println(cNum);
//        bbNum = bNum; //전수를 전전수에 대입 :2
//        bNum = cNum; //현재수를 전수에 대입 전수 :1
//        cNum = bbNum+bNum; //현재수 2
        
        /*
        int cNum;
        i=0;
        while(i<20) {
        	cNum=bbNum+bNum;
        	System.out.print("[i="+i+"]"+cNum+",");
        	bbNum=bNum;
        	bNum=cNum;
        	i++;
        }
        */
        
        int cNum;
        for(i=0;i<20;i++) {
        	cNum=bbNum+bNum;
        	System.out.print("[i="+i+"]"+cNum+",");
        	bbNum=bNum;
        	bNum=cNum;
        }
        
        System.out.println("1~10까지 출력하시오");
        
        i=0;
        /*
        while(i<10) {
        	i++;
        	System.out.println(i);
        }
        */
        
        
        
        for(i=0;i<10;) {
        	i++;
        	System.out.println(i);
        }
        System.out.println();
        
        Scanner sc=new Scanner(System.in);
        
        /*
        System.out.print("메시지를 입력하세요. 작업을 중단하려면 exit를 입력하세요:");
        String line = sc.nextLine(); 
        if(!line.equals("exit")) {  //문자열 비교할 때에는 euquals로 비교해야 주소가 아닌 내용을 비교할 수 있다
        	System.out.println("입력한 메시지:"+line);
        	System.out.print("메시지를 입력하세요. 작업을 중단하려면 exit을 입력하세요:");
        	line = sc.nextLine();
        	System.out.println("입력한 메시지:"+line);
        	System.out.print("메시지를 입력하세요.작업을 중단하려면 exit를 입력하세요:");
        	line = sc.nextLine();
        }
        */
        
        String line;
        do {
        	System.out.print("메시지를 입력하세요. 작업을 중단하려면 exit를 입력하세요:");
        	line = sc.nextLine();
        	System.out.println("입력한 메시지:"+line);
        }while(!line.equals("exit"));
    }
}