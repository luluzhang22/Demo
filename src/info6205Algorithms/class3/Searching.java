package info6205Algorithms.class3;

import java.util.Arrays;
import java.util.Map;

public class Searching {

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6};
        boolean found = BinSearchRecursive(arr,1);
        System.out.println(found);
        found = BinarySearch(arr,2);
        System.out.println(found);

        Rotate(arr,2);
        Rotate(new int[] {1,2,3},3);

        System.out.println(GetOccurances(new int[] {1,2,3,4,5,5,5,5,5,5},5));

        System.out.println(FindFirstOccurance(new int[] {1,2,2,2,2,2,4,5,6},2));
        System.out.println(FindLastOccurance(new int[] {1,1,2},1));

        System.out.println(FindFirstBiggerThanK(new int[] {10},8));
        System.out.println(Ceiling(new int[] {10,15},9));
        System.out.println(Floor(new int[] {1,5,7},9));
        System.out.println(FindPeak(new int[] {5,4,3,2,1}));

        SortArrayWaveForm(new int[] {-2,1,3});
        SortArrayWaveFormLinear(new int[] {0,2,2});

        FindSumEqualToX(new int[] {3,2,3,-2},0);
        FindSumClosestToX(new int[] {3,5,7,-2,1,-6,4,-8},0);
        FindPairSumEqualRest(new int[] {3,5,7,-2,1,-6,4,-8});
    }

    static void display(int[] arr){
        for (int i = 0;i<arr.length;i++){
            System.out.print(arr[i]+"   ");
        }
        System.out.println();
    }

    //iterator
    static boolean BinarySearch(int[] arr, int x){
        int low = 0;
        int high = arr.length-1;

        while (low<=high){
            /**int mid = (low + high)/2;  may overflow**/
            int mid = low + (high - low)/2;
            if(arr[mid] == x){
                System.out.println("Found");
                return true;
            }
            else if(arr[mid] < x){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return false;
    }

    //recursive
    static boolean BinSearchRecursive(int[] arr,int x){
        return BinSearchRecursive(arr,x,0,arr.length-1);
    }

    static boolean BinSearchRecursive(int[] arr,int x,int low,int high){
        if(low>high)
            return false;

        int mid=(low+high)/2;
        if(arr[mid]==x){
            return true;
        }else if (arr[mid]<x){
            return BinSearchRecursive(arr,x,mid+1,high);
        }else
            return BinSearchRecursive(arr, x,low,mid-1);
    }

    static void Swap(int[] arr,int start,int end){
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end]=temp;
    }

    static void Reverse(int[] arr){
        int start=0;int end=arr.length-1;
        while(start<=end){
            Swap(arr,start,end);
            start++;
            end--;
        }
    }

    static void Reverse(int[] arr,int start,int end){

        while(start<=end){
            Swap(arr,start,end);
            start++;
            end--;
        }
    }

    /**1,2,3,4,5,6->6,5,4,3,2,1->5,6,1,2,3,4**/
    static void Rotate(int[] arr,int x){
        x = x%arr.length;
        if(x>0){
            Reverse(arr);
            Reverse(arr,0,x-1);
            Reverse(arr,x,arr.length-1);
        }
        display(arr);
    }

    /**find the count of x in arr**/
    static  int GetOccurances(int[] arr,int x){
        return GetOccurances(arr,x,0,arr.length-1);
    }
    static int GetOccurances(int[] arr,int x, int start, int end){
        Arrays.sort(arr);
        if(arr[start]>x){
            return 0;
        }
        if(arr[end]<x){
            return 0;
        }
        if (arr[start]==x&&arr[end]==x){
            return end-start+1;
        }
        int mid = (start+end)/2;
        if(arr[mid]==x){
            return (1+
                    GetOccurances(arr,x,start,mid-1)+
                    GetOccurances(arr,x,mid+1,end));
        }else if (arr[mid]<x){
            return GetOccurances(arr,x,mid+1,end);
        }else {
            return GetOccurances(arr,x,start,mid-1);
        }
    }


    static int FindFirstOccurance(int[] arr,int x){
        return FindFirstOccurance(arr,x,0,arr.length-1);
    }
    static int FindFirstOccurance(int[] arr,int x,int start,int end){
        if(arr[start]>x){
            return -1;
        }
        if(arr[end]<x)
            return -1;

        if(arr[start]==x)
            return start;
        int mid = start/2+end/2;

        if(arr[mid] >= x)
            return FindFirstOccurance(arr,x,start,mid);
        else
            return FindFirstOccurance(arr,x,mid+1,end);
    }


    static int FindLastOccurance(int[] arr,int x){
        return FindLastOccurance(arr,x,0,arr.length-1);
    }
    static int FindLastOccurance(int[] arr,int x,int start,int end){
        if(arr[start]>x){
            return -1;
        }
        if(arr[end]<x)
            return -1;

        if(arr[end]==x)
            return end;
        int mid = start/2+end/2;

        if(arr[mid] < x)
            return FindLastOccurance(arr,x,mid+1,end);
        else
            return FindLastOccurance(arr,x,start,mid);
    }

    static int FindFirstBiggerThanK(int[] arr, int k){
        int result = -1;
        int start = 0;int end = arr.length-1;
        if(arr[end]<k)
            return -1;

        while (start<=end){
            /**better: int mid = start+(end-start)/2;**/
            int mid = (start+end)/2;
            /**wrong: int mid = start/2+end/2;**/
            if(arr[mid]>k){
                result = mid;
                end = mid-1;
            }
            else
                start = mid+1;
        }
        return result;
    }

    /**if find k return k, else find first bigger than k**/
    static int Ceiling(int[] arr,int k){
        if(arr[arr.length-1]<k)
            return -1;
        int result = -1;
        int start = 0;int end = arr.length-1;
        while(start<=end){
            int mid = start+(end-start)/2;
            if(arr[mid]==k){
                return mid;
            }else if (arr[mid]>k){
                result = mid;
                end = mid-1;
            }else
                start = mid+1;
        }
        return result;
    }


    static int Floor(int[] arr,int k){
        if(arr[0]>k)
            return -1;
        int result = -1;
        int start = 0;int end = arr.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(arr[mid]==k){
                return mid;
            }else if (arr[mid]<k){
                result = mid;
                start = mid+1;
            }else
                end = mid-1;
        }
        return result;
    }


    static int FindPeak(int[] arr){
        return FindPeak(arr,0,arr.length-1);
    }

    static int FindPeak(int[] arr,int low,int high){
        if(low==high)
            return low;
        if(low>high)
            return -1;

        int mid=(low+high)/2;

        if(arr[mid] > arr[mid+1])
            return FindPeak(arr,low,mid);
        else
            return FindPeak(arr,mid+1,high);

    }

    /**sort, then swap within 2 elements**/
    static void SortArrayWaveForm(int[] arr){
        Arrays.sort(arr);
        for(int i =0;i<arr.length-1;i=i+2){
            Swap(arr,i,i+1);
        }
        display(arr);
    }

    /**step by 2, current element should bigger then back and front**/
    static void SortArrayWaveFormLinear(int[] arr){
        for(int i = 0;i<arr.length;i=i+2){
            if(i>0&&arr[i-1]>arr[i]){
                Swap(arr,i-1,i);
            }
            if(i<arr.length-1&&arr[i]<arr[i+1])
                Swap(arr,i,i+1);
        }
        display(arr);
    }

    static void FindSumEqualToX(int[] arr,int x){
        Arrays.sort(arr);
        int start = 0;
        int end = arr.length-1;

        while(start<end){
            if(arr[start]+arr[end]==x){
                System.out.println("Found:start->"+arr[start]+" end->"+arr[end]);
                return;
            }else if (arr[start]+arr[end]<x)
                start++;
            else
                end--;
        }
        System.out.println("doesn't find sum equal to "+x);
    }

    static void FindSumClosestToX(int[] arr,int x){
        Arrays.sort(arr);
        int start = 0;
        int end = arr.length-1;

        int finalLeft = 0;
        int finalRight = arr.length - 1;
        int sum;
        int result = arr[start] + arr[end];

        while(start<end){
            sum = arr[start] + arr[end];
            if(Math.abs(sum-x)<Math.abs(result-x)){
                finalLeft = start;
                result = sum;
                finalRight = end;
            }
            if(sum == x){
                System.out.println("start = "+arr[start]+" end ="+arr[end]);
                return;
            }else if(arr[start]+arr[end]<x){
                start++;
            }else
                end--;
        }
        System.out.println("start = "+arr[finalLeft]+" end = "+arr[finalRight]+" sum = "+result);
    }

    /**1.sort 2.sum 3.find pair sum equal sum/2**/
    static void FindPairSumEqualRest(int[] arr){
        Arrays.sort(arr);
        int sum =0;
        for(int i =0;i<arr.length;i++){
            sum+=arr[i];
        }
        sum = sum/2;
        int start = 0;
        int end =arr.length-1;

        while(start<end){
            if(arr[start]+arr[end]==sum){
                System.out.println("found: start->"+arr[start]+" end->"+arr[end]);
                return;
            } else if (arr[start] + arr[end]<sum) {
                start++;
            }else
                end--;
        }
    }

}
