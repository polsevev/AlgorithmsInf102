import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        ArrayList<Integer> randomInts = randomInts();
        ArrayList<Integer> randomIntsCopy = new ArrayList<>(randomInts);
        ArrayList<Integer> randomIntsCopy1 = new ArrayList<>(randomInts);
        ArrayList<Integer> randomNumbersSorted = mergeSort(randomInts);
        ArrayList<Integer> randomNumbers2 = insertSort(randomIntsCopy);
        quickSort(randomIntsCopy1, 0, randomIntsCopy.size()-1);
        System.out.println(randomIntsCopy1);
        System.out.println(randomNumbersSorted.equals(randomNumbers2) && randomIntsCopy1.equals(randomNumbersSorted));
        System.out.println(randomNumbersSorted);
        System.out.println(randomNumbers2);
        Integer locationOfNumber = findBinarySearch(randomNumbersSorted, 55, randomNumbersSorted.size()-1, 0);
        System.out.println(locationOfNumber);
        System.out.println(randomNumbersSorted.get(locationOfNumber));
    }
    public static ArrayList<Integer> randomInts(){
        ArrayList<Integer> randomInts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            randomInts.add(new Random().nextInt(100) +1);
        }
        return randomInts;
    }

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> a){
        int n = a.size();
        if(n == 1)
            return a;

        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> last = new ArrayList<>();

        for (int i = 0; i < n/2; i++) {
            first.add(a.get(0));
            a.remove(0);
            last.add(a.get(a.size()-1));
            a.remove(a.size()-1);
        }
        if(a.size() > 0)
            first.addAll(a);

        first = mergeSort(first);
        last = mergeSort(last);
        return merge(first, last);
    }

    public static ArrayList<Integer> merge(ArrayList<Integer> first, ArrayList<Integer> last){
        ArrayList<Integer> c = new ArrayList<>();

        while(!first.isEmpty() && !last.isEmpty()){
            if(first.get(0) > last.get(0)){
                c.add(last.get(0));
                last.remove(0);
            }else{
                c.add(first.get(0));
                first.remove(0);
            }
        }

        while(!first.isEmpty()){
            c.add(first.get(0));
            first.remove(0);
        }
        while(!last.isEmpty()){
            c.add(last.get(0));
            last.remove(0);
        }
        return c;
    }

    static Integer findBinarySearch(ArrayList<Integer> a, int toFind, int upper, int lower){
        int n = lower + ((upper - lower)/2);
        if(toFind == a.get(n)){
            return n;
        }
        else if(n <= lower){
            return null;
        }
        else if(toFind < a.get(n)){
            return findBinarySearch(a, toFind, n, lower);
        }else{
            return findBinarySearch(a, toFind, upper, n);
        }
    }
    static ArrayList<Integer> insertSort(ArrayList<Integer> a){
        Integer n = a.size();
        for (int i = 1; i < n; i++) {
            int currentNumber = a.get(i);
            int counter = i - 1;
            while(counter >= 0 && a.get(counter) > currentNumber){
                a.set(counter + 1, a.get(counter));
                counter -= 1;
            }
            a.set(counter+1, currentNumber);
        }
        return a;
    }

    static void quickSort(ArrayList<Integer> a, int low, int high){
        if(low >= high){
            return;
        }
        int p = a.get(new Random().nextInt(high));
        int index = partition(a, low, high, p);
        quickSort(a, low, index-1);
        quickSort(a, index, high);
    }

    static Integer partition(ArrayList<Integer> a, int low, int high, int p){
        while(low <= high){
            while(a.get(low) < p){
                low++;
            }
            while(a.get(high) > p){
                high--;
            }
            if (low <= high) {
                int temp = a.get(high);
                a.set(high, a.get(low));
                a.set(low, temp);
                low++;
                high--;
            }
        }
        return low;
    }

}
