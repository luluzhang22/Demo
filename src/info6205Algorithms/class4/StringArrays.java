package info6205Algorithms.class4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class StringArrays {
    public static void main(String[] args){
        int[] arr = {1,1,2,2,2,1,2,1};
        System.out.println(majorityCandidate(arr));

        int[] arr1 = {-1,-3,0,1,3,-7,-1,0};
        dutchFlag(arr1);

        kadane(new int[] {-5,-2,5,7,1,-7,-9,5,3,1});
        moveZerosToEnd(new int[] {2,0,-1,7,0,2,0,2,0});
        replaceWithNextGreatest(new int[] {17,5,3,2,6,1,3,2});
        System.out.println("Anagram:"+isAnagram("ABc dd eds","sdB c edAd"));
        System.out.println("Unique:"+isUnique("abcdSaD"));
        System.out.println("Palindrome:"+canBePalindrome("Tac  o cat"));
        System.out.println("Compress:"+compress("aabccaa"));
        System.out.println("Rotation:"+isRotation("waterbottle","erbottlewat"));
        System.out.println(replaceSpace("mr john smith"));
    }

    /**find element occurs more than 50%**/
    //??if have two majority candidates??
    static int majorityCandidate(int[] arr){
        int candidate = findCandidate(arr);
        int count = 0;
        for(int i = 0; i<arr.length;i++){
            if(arr[i]==candidate)
                count++;
        }
        if(count>=arr.length/2)
            return candidate;
        return Integer.MIN_VALUE;
    }

    static int findCandidate(int[] arr){
        int majority = arr[0];
        int count = 1;
        for(int i=1;i<arr.length;i++){
            if(majority == arr[i])
                count++;
            else
                count--;
            if(count == 0){
                majority = arr[i];
                count = 1;
            }
        }
        return majority;
    }

    static void swap(int[] arr, int start, int end){
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

    static void display(int[] arr){
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i]+"  ");
        }
        System.out.println();
    }

    /**don't need sort, if negative ->left, if positive ->right**/
    static void dutchFlag(int[] arr){
        int back = 0;
        int mid = 0;
        int front = arr.length-1;
        while(mid<=front){
            if(arr[mid]<0){
                swap(arr,back,mid);
                back++;
                mid++;
            }else if (arr[mid] == 0){
                mid++;
            }else{
                swap(arr,mid,front);
                front--;
            }
        }
        display(arr);
    }


    /**find max increasing sub sequence (?substring) sum**/
    static  void kadane(int[] arr){
        int maxSoFar = 0;
        int sum = 0;

        for(int i =0;i<arr.length;i++){
            sum = sum +arr[i];
            if(maxSoFar<sum)
                maxSoFar = sum;
            if(sum<0){
                sum = 0;
            }
        }
        System.out.println("kadane:"+maxSoFar);
    }

    static void moveZerosToEnd(int[] arr){
        int start = 0;
        int end = arr.length-1;
        while (start<=end){
            if(arr[start] == 0){
                swap(arr,start,end);
                end--;
            }else
                start++;
        }
        display(arr);
    }

    static void replaceWithNextGreatest(int[] arr){
        int maxSoFar = arr[arr.length-1];
        arr[arr.length-1] = Integer.MIN_VALUE;
        for(int i =arr.length-2;i>=0;i--){
            int temp = arr[i];
            arr[i] = maxSoFar;
            if(temp > maxSoFar){
                maxSoFar = temp;
            }
        }
        display(arr);
    }

    static boolean isAnagram(String s1, String s2){
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        if(arr1.length!=arr2.length)
            return false;

        HashMap<Character,Integer> dict = new HashMap<>();
        for(int i = 0;i<arr1.length;i++){
            if(dict.containsKey(arr1[i]))
                dict.put(arr1[i],dict.get(arr1[i])+1);
            else
                dict.put(arr1[i],1);
        }
        for(int i =0; i<arr2.length;i++){
            if(!dict.containsKey(arr2[i])){
                return false;
            }
            dict.put(arr2[i],dict.get(arr2[i])-1);
            if (dict.get(arr2[i])<0)
                return false;
        }
        return true;

    }

    /**implement an algorithm to determine if a string has all unique characters**/
    static boolean isUnique(String s){
        char[] arr = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for(int i = 0; i<arr.length;i++){
            if(set.contains(arr[i])){
                return false;
            }
            set.add(arr[i]);
        }
        return true;
    }

    /**check if a string is a permutation of a palindrome. like "Tact Coa"**/
    static boolean canBePalindrome(String s){
        char[] arr = s.toLowerCase().replaceAll(" ","").toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<arr.length;i++){
            if(!map.containsKey(arr[i]))
                map.put(arr[i],1);
            else
                map.put(arr[i],map.get(arr[i])+1);
        }
        boolean oddFound = false;
        Iterator i = map.keySet().iterator();
        while (i.hasNext()){
            if(map.get(i.next())%2!=0){
                if(oddFound)
                    return false;
                oddFound = true;
            }
        }
        return true;
    }

    /**compress string using the counts of repeated characters. eg:"aabcccccaaa"->"a2b1c5a3"
     * if compressed string > original string, return original string**/
    static String compress(String s){
        if(s == null || s.length()<2){
            return s;
        }
        char[] arr = s.toCharArray();
        char current = arr[0];
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for(int i =1;i<arr.length;i++){
            if(arr[i] == current)
                count++;
            else{
                sb.append(current+""+count);//notice add "", otherwise will be number
                current = arr[i];
                count = 1;
            }
        }
        sb.append(current+""+count);

        return sb.length()<s.length()?sb.toString():s;
    }

    /**check if s2 is a rotation of s1 using only one call to isSubstring**/
    static boolean isRotation(String str1,String str2){
        if(str1.length()!=str2.length())
            return false;
        str2 = str2+str2;
        if(str2.contains(str1)){
            return  true;
        }
        return false;
    }

    static String replaceSpace(String s){
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<arr.length;i++){
            if(arr[i]==' ')
                sb.append("%20");
            else
                sb.append(arr[i]+"");
        }
        return sb.toString();
    }
}
