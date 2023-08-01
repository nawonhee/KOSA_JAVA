public class Datatype{
    public static void main(String[] args){
        byte b = 127;
        int i = b;  //자동형변환
        long l = 1234567890L;

        float f = 12.3F;
        double d = 12.3;

        f = i;  //자동형변환
        f = l;  //자동형변환

        b = (byte)d;  //강제형변환
        System.out.println(b);  //12

        char c;
        c = '가'; //'A';
        i = c; //자동형변환 65
        System.out.println(i);

        i++; //44033 66
        c = (char)i; //강제형변환 각 B
        System.out.println(c);
    }
}