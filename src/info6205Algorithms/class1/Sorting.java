package info6205Algorithms.class1;

public class Sorting {
    /**Regular: O(1)<O(log2n)<O(n)<O(nlog2n)<O(n^2)<O(n^3)<...<O(2^n)<O(n!)**/
    public static void main(String[] args){
        int[] arr = {6,5,3,1,8,7,3,2,4};
//        BubbleSort(arr);
//        SelectionSort(arr);
//        InsertionSort(arr);
//        MergeSort(arr);
        quickSort(arr,0,arr.length-1);
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    static void Swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**Bubble Sort| Runtime: O(n^2) average and worst case. Memory: O(1)**/
    //start at the beginning of the array and swap the first two elements if the first is greater than the second.
    //go to next pair, continuously making sweeps of the array until it is sorted.
    //the smaller items slowly"bubble" up to the beginning of the list.
    static void BubbleSort(int[] arr){
        for(int i = 0; i< arr.length-1;i++){
            int numberOfSwaps = 0;
            for(int j = 0; j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    Swap(arr, j, j+1);
                    numberOfSwaps++;
                }
            }
            if(numberOfSwaps == 0){
                break;
            }
        }
    }

    /**Selection Sort| Runtime: O(n^2) average and worst case. Memory: O(1)**/
    //find the smallest element using a linear scan and move it to the front(swapping it with the front element)
    //find the second smallest and move it, again doing a linear scan. continue doing this until all the elements are in place
    static void SelectionSort(int[] arr){
        int minIndex;
        for(int i = 0; i<arr.length-1; i++){
            minIndex = i;
            for(int j = i+1; j<arr.length;j++){
                if(arr[minIndex]>arr[j]){
                    minIndex = j;
                }
            }
            if(minIndex>i){
                Swap(arr,i,minIndex);
            }
        }
    }

    /**Insertion Sort| Runtime: O(n^2) average and worst case.**/
    //At the beginning, sorted part contains first element of the array and unsorted one contains the rest.
    //At every step, take first element in the unsorted part and inserts it to the right place of the sorted one.
    static void InsertionSort(int[] arr){
        int inner, temp;
        for(int outer = 1; outer<arr.length;outer++){
            temp = arr[outer];
            inner = outer;
            while (inner>0 && arr[inner-1]>temp){
                arr[inner] = arr[inner-1];
                inner--;
            }
            arr[inner]=temp;
        }
    }

    /**Merge Sort| Runtime: O(nlog(n)) average and worst case.**/
    //Divide the array in half, sort each of those halves, then merge them back together. break -> merge
    //Operation: copy all the elements from the target array segment into a helper array.
    //iterate through helper, track of where the start of the left and right halves, cope the smaller element from each half into the array
    static void MergeSort(int[] arr){
        int[] helper = new int[arr.length];
        MergeSort(arr, helper, 0, arr.length-1);
    }

    static void MergeSort(int[] arr, int[] helper, int low, int high){
        if(low < high) {
            int middle = (low + high) / 2;
            MergeSort(arr,helper,low,middle);
            MergeSort(arr,helper,middle+1,high);
            Merge(arr, helper, low, middle, high);
        }
    }

    static void Merge(int[] arr, int[] helper, int low, int middle, int high){
        for(int i = low; i <= high; i++){
            helper[i] = arr[i];
        }
        int left = low;
        int right = middle +1;
        int current = low;
        while (left<=middle&&right<=high){
            if(helper[left] <= helper[right]){
                arr[current] = helper[left];
                left++;
            }
            else{
                arr[current] = helper[right];
                right++;
            }
            current++;
        }

        //copy the rest of the left side of the array into the target array
        int remainder = middle - left;
        for(int i= 0;i<=remainder;i++){
            arr[current+i] = helper[left+i];
        }
    }

    /**Quick Sort| Runtime: O(nlog(n)) average, O(n^2) worst case.**/
    //pick a random element and partition the array, such that all numbers that are less than the partitioning element
    // come before all elements that are greater than it
    //is the partitioned element is not guaranteed to be the median(or anywhere near the median), runtime could be worst O(n^2)
    static void quickSort(int[] arr, int left, int right){
        int position = partition(arr, left, right);
        if(left<position){
            quickSort(arr,left,position);
        }
        if(position+1<right){
            quickSort(arr,position+1,right);
        }
    }
    static int partition(int[] arr, int left, int right){
        int pivot = arr[(right+left)/2];
        while (left<right){
            while (arr[left]<pivot){
                left++;
            }
            while (arr[right]>pivot){
                right--;
            }
            if (left<right){
                Swap(arr,left,right);
                left++;
                right--;
            }
        }
        return left;
    }

    /**Count sort| Runtime:O(n)**/


    /**Radix Sort(based on Count Sort)| Runtime: O(kn)**/
}
