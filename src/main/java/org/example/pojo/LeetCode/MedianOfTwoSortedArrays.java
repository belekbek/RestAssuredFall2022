package org.example.pojo.LeetCode;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int a[] = {1, 2};
        int b[] = {3, 4};
        System.out.println(findMedianSortedArrays(a,b));
        double c= 5/2;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int sum[] = new int[len];
        int a = 0;
        for(int i=0; i<nums1.length; i++){
            sum[a] = nums1[i];
            a++;
        }
        for(int i=0; i<nums2.length; i++){
            sum[a] = nums2[i];
            a++;
        }
        Arrays.sort(sum);
        System.out.println(Arrays.toString(sum));
        if(sum.length % 2 == 0){
            double res = (sum[(sum.length/2)-1] + sum[sum.length/2]);
            return res /2;
        }else {
            return sum[sum.length/2];
        }
    }
}

