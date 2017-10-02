package info6205Algorithms.assignments;

public class assignment1 {
    public static void main(String[] args) {
        int[] arr = {6,5,3,1,8,7,2,4};
        int thirdLargest = FindThirdLargest(arr, 0, arr.length - 1);
        System.out.println("The 3rd largest number in {6,5,3,1,8,7,2,4} is:"+thirdLargest);
    }

    static int FindThirdLargest(int[] arr, int i, int j){
        int thirdLarPos = arr.length - 3;
        int pos = Partition(arr, i, j);
        if(pos == thirdLarPos){
            return arr[thirdLarPos];
        }
        else if(pos > thirdLarPos){
            return FindThirdLargest(arr, i, pos - 1);
        }
        else{
            return FindThirdLargest(arr, pos +1, j);
        }
    }

    static int Partition(int[] arr, int i, int j){
        int pivot = arr[j];
        int wall = i - 1;
        for(int k = i; k < j; k++){
            if(arr[k] <= pivot){
                wall ++;
                Swap(arr, k, wall);
            }
        }
        Swap(arr , j,wall+1);
        return wall + 1;
    }

    static void Swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
