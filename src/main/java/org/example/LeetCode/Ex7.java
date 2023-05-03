package org.example.LeetCode;

public class Ex7 {
    public static void main(String[] args) {
        System.out.println(reverse(2147483647));
    }

    public static int reverse(int x) {
        String valx = String.valueOf(x);
        String d = "";
        String c = "";
        for(int i = 0; i < valx.length(); i++){
            if(Character.isDigit(valx.charAt(i))){
                d= d+ valx.charAt(i);
            }else{
                c = c + valx.charAt(i);
            }
        }
        StringBuilder res = new StringBuilder(d);
        c = c+res.reverse();
        d = String.valueOf(res);
        if(Long.parseLong(d) < 2147483647){
            return Integer.parseInt(c);
        }else{
            return 0;
        }
    }
}
