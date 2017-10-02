package info6205Algorithms.assignments;

import java.util.Arrays;

public class searchingAssignmet {
    public static void main(String[] args){
        int[] arr1 = {1,2,3,4,5};
        int peak1 = FindPeak(arr1);
        if(peak1!=-1) {
            System.out.println("The peak in arr1 {1,2,3,4,5} is " + arr1[peak1]);
        }else{
            System.out.println("No peak in arr1 {1,2,3,4,5}");
        }

        int[] arr2 = {5,4,3,2,1};
        int peak2 = FindPeak(arr2);
        if(peak2!=-1) {
            System.out.println("The peak in arr2 {5,4,3,2,1} is " + arr2[peak2]);
        }else{
            System.out.println("No peak in arr2 {5,4,3,2,1}");
        }

        int[] arr3 = {2,4,5,7,9,5,3};
        int peak3 = FindPeak(arr3);
        if (peak3!=-1) {
            System.out.println("The peak in arr3 {2,4,5,7,9,5,3} is " + arr3[peak3]);
        }else{
            System.out.println("No peak in arr3 {2,4,5,7,9,5,3}");
        }


        int[] arr = {3,5,7,-2,1,-6,4,-8};
        System.out.print("Find pair elements sum closest to zero in arr {3,5,7,-2,1,-6,4,-8}->");
        FindSumClosestToZero(arr);
    }

    //1. Find peak in an array

    static int FindPeak(int[] arr){
        return FindPeak(arr,0,arr.length-1);
    }

    static int FindPeak(int[] arr, int low, int high){
        if(low == high)
            return low;
        int mid = (low + high)/2;
        if(arr[mid]>arr[mid+1])
            return FindPeak(arr,low,mid);
        return FindPeak(arr,mid+1,high);
    }

    //2. Find Pair where sum is closest to 0

    static void FindSumClosestToZero(int[] arr){
        if(arr.length<2){
            System.out.println("The length of the array must larger than 1, or we can't find a pair elements");
            return;
        }
        Arrays.sort(arr);
        int start = 0;
        int end = arr.length-1;

        int finalLeft = 0;
        int finalRight = arr.length - 1;
        int sum;
        int result = arr[start]+arr[end];

        while(start<end){
            sum = arr[start]+arr[end];
            if(Math.abs(sum)<Math.abs(result)){
                finalLeft = start;
                finalRight = end;
                result = sum;
            }
            if(sum == 0){
                System.out.println("Pair found: "+arr[start]+","+arr[end]+", sum="+sum);
                return;
            }else if(sum<0){
                start++;
            }else
                end--;
        }

        System.out.print("Pair found: "+arr[finalLeft]+","+arr[finalRight]+", sum="+result);
    }
}
