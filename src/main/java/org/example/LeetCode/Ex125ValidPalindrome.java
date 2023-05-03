package org.example.LeetCode;

public class Ex125ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("0P"));
    }

    public static boolean isPalindrome(String s) {
        String res = "";
        String res2 = "";
        s = s.toLowerCase();
        for(int i=0; i< s.length(); i++){
            if(Character.isLetter(s.charAt(i))|| Character.isDigit(s.charAt(i))){
                res = res + s.charAt(i);
                res2 = s.charAt(i) + res2;
            }
        }
        System.out.println(res);
        System.out.println(res2);
        return res.equals(res2);
    }
}
