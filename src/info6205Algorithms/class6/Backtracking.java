package info6205Algorithms.class6;

public class Backtracking {
    public static void main(String[] args){
        System.out.println("---binarySequence---");
        binarySequence(3);
        System.out.println("---ternarySequence---");
        ternarySequence(2);
        System.out.println("---marySequence---");
        marySequence(3,2);
        System.out.println("---combinations---");
        combinations("ABC",2);
        System.out.println("---subsets---");
        generateSubSets("ABC");

        System.out.println("---subsets sum k---");
        int[] arr = {1,5,3,2,7,8,2,1};
        generateSubSetsSumK(arr,8);

        System.out.println("---permutations---");
        permutations("ABC",2);
    }

    public static void binarySequence(int n){
        if(n<=0)
            return;
        int [] arr = new int[n];
        int current = 0;
        binarySequence(arr,current);
    }

    private static void binarySequence(int[] result, int current){
        if(current == result.length){
            printArray(result);
            return;
        }
        for(int i = 0; i < 2; i++){
            result[current] = i;
            binarySequence(result,current+1);
        }
    }

    public static void ternarySequence(int n){
        if(n<=0)
            return;
        int [] arr = new int[n];
        int current = 0;
        ternarySequence(arr,current);
    }

    private static void ternarySequence(int[] result, int current){
        if(current == result.length){
            printArray(result);
            return;
        }
        for(int i = 0; i < 3; i++){
            result[current] = i;
            ternarySequence(result,current+1);
        }
    }

    //m^n lines
    public static void marySequence(int m, int n){
        if(n<=0)
            return;
        int [] arr = new int[n];
        int current = 0;
        marySequence(arr,current,m);
    }

    private static void marySequence(int[] result, int current, int m){
        if(current == result.length){
            printArray(result);
            return;
        }
        for(int i = 0; i < m; i++){
            result[current] = i;
            marySequence(result,current+1, m);
        }
    }

    private static void printArray(int[] result){
        for(int i = 0; i<result.length;i++)
            System.out.print(result[i]+"   ");
        System.out.println();
    }

    public static void combinations(String str, int n){
        if(str.length()==0 || n<=0){
            return;
        }
        int[] result = new int[n];
        int current = 0;
        combinations(str,result,current,n);
    }

    private static void combinations(String str, int[] result, int current, int n){
        if(current == n){
            printCombinations(str,result);
            return;
        }
        for(int i = 0; i < str.length(); i++){
            result[current] = i;
            combinations(str,result,current+1,n);
        }
    }

    private static void printCombinations(String str, int[] result){
        for (int i =0; i< result.length; i++){
            System.out.print(str.charAt(result[i])+"  ");
        }
        System.out.println();
    }

    public static void generateSubSets(String str){
        if(str.length()==0)
            return;
        int[] result = new int[str.length()];
        int current = 0;
        generateSubSets(result,current,str);
    }

    private static void generateSubSets(int[] result, int current, String str){
        if(current == result.length){
            printSubSets(result,str);
            return;
        }
        for(int i = 0; i<2;i++){
            result[current] = i;
            generateSubSets(result,current+1,str);
        }
    }

    private static void printSubSets(int[] result, String str){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i = 0; i< result.length; i++){
            if(result[i]==1){
                sb.append(str.charAt(i)+",");
            }
        }
        sb.append("}");
        System.out.println(sb);
    }

    public static void generateSubSetsSumK(int[] arr, int k){
        if(arr.length==0 || k <= 0){
            return;
        }

        int[] result = new int[arr.length];
        int current = 0;
        generateSubSetsSumK(arr,result,current,k);
    }
    private static void generateSubSetsSumK(int[] arr, int[] result, int current, int k){
        if(current == result.length){
            printSubSetsSumK(result,arr,k);
            return;
        }
        for(int i = 0; i<2;i++){
            result[current] = i;
            generateSubSetsSumK(arr,result,current+1,k);
        }

    }

    private static void printSubSetsSumK(int[] result, int[] arr, int k){
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i = 0; i<result.length;i++){
            if(result[i]==1) {
                sb.append(arr[i] + "  ");
                sum += arr[i];
            }
        }
        sb.append("}");
        if(sum==k)
            System.out.println(sb);
    }

    public static void permutations(String str, int n){
        if(str.length() == 0 || n<= 0)
            return;
        int[] result = new int[n];
        permutations(result,0,n,str);
    }

    private static void permutations(int[] result, int current,int n,String str){
        if(current == n){
            printCombinations(str,result);
            return;
        }
        for (int i = 0; i<str.length(); i++){
            if(isValid(result,current,i)) {
                result[current] = i;
                permutations(result, current + 1, n, str);
            }
        }
    }

    private static boolean isValid(int[] result, int current, int num){
        for(int i = 0; i < current; i++){
            if(result[i] == num){
                return false;
            }
        }
        return true;
    }
}
