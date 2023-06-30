import java.util.*;
// Albert Szarek
// ISTE 222
// Prof. Golen
// Due April 3, 2022

/*
Need 100 datasets/arrays
each dataset needs 1000 elements (Random)
Ranges between 1 and 10000
Loop through every data set and see if each item is produced as a searchinput
*/

public class ClassicSearchesA3 {
    ArrayList<ArrayList<Integer> > aList = new ArrayList<ArrayList<Integer> >();
    ArrayList<ArrayList<Integer> > aList2 = new ArrayList<ArrayList<Integer> >();
    // new ArrayList<ArrayList<Integer> > (n);
    int ops = 0;

    Random random = new Random();

    public ClassicSearchesA3() {

    }

    int linearSearchOrdered(int[] arr, int key) {
        int n = arr.length;
        ops = 0;

        for(int i = 0; i < n; i++) {
            ops++;
            if(arr[i] == key)
                return i;
            else if(arr[i] > key) {
                return -1;
            }
        }

        return -1;
    }

    void checkFinalArrays() {
        for(int i = 0; i < aList.size(); i++) {
            int[] tmp = new int[aList.get(i).size()];
            for(int j =0; j < aList.get(i).size(); j++) {
                tmp[j] = aList.get(i).get(j);
            }
            Arrays.sort(tmp);
            System.out.println("Results for array " + i + ": ");
            for(int x: tmp) {
                System.out.println(x);
            }
            System.out.println();
        }

    }

    void addDataSets() {
        ArrayList<Integer> tmp =
                new ArrayList<Integer>();
        for(int i =0;  i < 100; i++) {
            tmp.clear();
            for(int j =0;  j < 1000; j++) {
                tmp.add(getRandomNumber(1, 10000));
            }
            aList.add(tmp);
        }

    }

    boolean checkArraysForValue(int key, int arrayCounter) {
        int[] tmp = new int[aList.get(arrayCounter).size()];
        for(int j =0; j < aList.get(arrayCounter).size(); j++) {
            tmp[j] = aList.get(arrayCounter).get(j);
        }

        return binarySearch(tmp, key) != -1;
    }

    void addDataSetsUnique() {
        ArrayList<Integer> tmp =
                new ArrayList<Integer>();
        int iTmp = 0;
        int arrayCounter = 0;
        for(int i =0;  i < 100; i++) {
            tmp.clear();
            for(int j =0;  j < 1000; j++) {
                iTmp = getRandomNumber(1, 10000);
                while(checkArraysForValue(iTmp, arrayCounter)) {
                    iTmp = getRandomNumber(1, 10000);
                }
                tmp.add(iTmp);
            }
            aList2.add(tmp);
        }

    }

    int getRandomNumber(int min, int max){
        //return random.nextInt(max) + min;
        return random.nextInt((max - min) + 1) + min;
    }

    int binarySearch(int arr[], int key) {
        int start = 0;
        int end = arr.length - 1;
        ops = 0;

        while(start <= end) {
            ops++;
            int mid = (start + end) / 2;
            if(arr[mid] == key)
                return mid;
            else if(arr[mid] < key)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return -1;
    }

    int interpolationSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        ops = 0;

        while(low <= high && key >= arr[low] && key <= arr[high]) {
            ops++;
            int index = low + (((key - arr[low]) * (high - low)) /
                    (arr[high] - arr[low]));

            if(key == arr[index])
                return index;

            if(key < arr[index])
                high = index - 1;
            else
                low = index + 1;
        }

        return -1;
    }

    void searchResult(String type, int key, int index) {
        if(index != -1)
            System.out.println(type + ": Found " + key + " at index " + index +
                    " in " + ops + " operations");
        else
            System.out.println(type + ": Did not find " + key + " in " + ops +
                    " operations");
    }

    void printArray(int arr[]) {
        int n = arr.length;

        for(int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    public static void main(String[] args) {
        ClassicSearchesA3 a3 = new ClassicSearchesA3();

        ArrayList<Integer> searchOps = new ArrayList<Integer>();

        /*int index;
        int key;
        int[] nums = {15, 98, 7, 22, 9, 61, 57};

        Arrays.sort(nums);
        printArray(nums);
        key = 78;
        searchResult("Linear", key, linearSearchOrdered(nums, key));
        searchResult("Binary", key, binarySearch(nums, key));
        searchResult("Interpolation", key, interpolationSearch(nums, key));*/

        a3.addDataSets();
        a3.addDataSetsUnique();
        System.out.println("We are now calling this function");
        //a3.checkFinalArrays();

        // Successful Searches
        // Linear Search Ordered
        for(int i = 0; i < a3.aList.size(); i++) {
            int[] tmp = new int[a3.aList.get(i).size()];
            for(int j =0; j < a3.aList.get(i).size(); j++) {
                tmp[j] = a3.aList.get(i).get(j);
            }
            Arrays.sort(tmp);
            for(int j = 0; j < a3.aList.get(i).size(); j++) {
                a3.linearSearchOrdered(tmp, a3.aList.get(i).get(j));
                searchOps.add(a3.ops);
            }
        }

        int searchAverage = 0;
        for(int i = 0; i < searchOps.size(); i++) {
            searchAverage += searchOps.get(i);
        }

        System.out.println("linearSearchOrdered average: " + searchAverage / searchOps.size());

        // Binary Search
        searchOps.clear();
        for(int i = 0; i < a3.aList.size(); i++) {
            int[] tmp = new int[a3.aList.get(i).size()];
            for(int j =0; j < a3.aList.get(i).size(); j++) {
                tmp[j] = a3.aList.get(i).get(j);
            }
            Arrays.sort(tmp);
            for(int j = 0; j < a3.aList.get(i).size(); j++) {
                a3.binarySearch(tmp, a3.aList.get(i).get(j));
                searchOps.add(a3.ops);
            }
        }

        searchAverage = 0;
        for(int i = 0; i < searchOps.size(); i++) {
            searchAverage += searchOps.get(i);
        }

        System.out.println("binarySearch average: " + searchAverage / searchOps.size());

        // Interpolation Search
        searchOps.clear();
        for(int i = 0; i < a3.aList.size(); i++) {
            int[] tmp = new int[a3.aList.get(i).size()];
            for(int j =0; j < a3.aList.get(i).size(); j++) {
                tmp[j] = a3.aList.get(i).get(j);
            }
            Arrays.sort(tmp);
            for(int j = 0; j < a3.aList.get(i).size(); j++) {
                a3.interpolationSearch(tmp, a3.aList.get(i).get(j));
                searchOps.add(a3.ops);
            }
        }

        searchAverage = 0;
        for(int i = 0; i < searchOps.size(); i++) {
            searchAverage += searchOps.get(i);
        }

        System.out.println("interpolationSearch average: " + searchAverage / searchOps.size());

        // Unsuccessful Searches
        // Linear Search Ordered
        searchOps.clear();
        for(int i = 0; i < a3.aList.size(); i++) {
            int[] tmp = new int[a3.aList.get(i).size()];
            for(int j =0; j < a3.aList.get(i).size(); j++) {
                tmp[j] = a3.aList.get(i).get(j);
            }
            int[] tmp2 = new int[a3.aList2.get(i).size()];
            for(int j =0; j < a3.aList2.get(i).size(); j++) {
                tmp2[j] = a3.aList2.get(i).get(j);
            }
            Arrays.sort(tmp);
            for(int j = 0; j < a3.aList2.get(i).size(); j++) {
                a3.linearSearchOrdered(tmp, a3.aList2.get(i).get(j));
                searchOps.add(a3.ops);
            }
        }

        searchAverage = 0;
        for(int i = 0; i < searchOps.size(); i++) {
            searchAverage += searchOps.get(i);
        }

        System.out.println("linearSearchOrdered average (FAIL): " + searchAverage / searchOps.size());

        // Binary Search
        searchOps.clear();
        for(int i = 0; i < a3.aList.size(); i++) {
            int[] tmp = new int[a3.aList.get(i).size()];
            for(int j =0; j < a3.aList.get(i).size(); j++) {
                tmp[j] = a3.aList.get(i).get(j);
            }
            int[] tmp2 = new int[a3.aList2.get(i).size()];
            for(int j =0; j < a3.aList2.get(i).size(); j++) {
                tmp2[j] = a3.aList2.get(i).get(j);
            }
            Arrays.sort(tmp);
            for(int j = 0; j < a3.aList2.get(i).size(); j++) {
                a3.binarySearch(tmp, a3.aList2.get(i).get(j));
                searchOps.add(a3.ops);
            }
        }

        searchAverage = 0;
        for(int i = 0; i < searchOps.size(); i++) {
            searchAverage += searchOps.get(i);
        }

        System.out.println("binarySearch average (FAIL): " + searchAverage / searchOps.size());

        // Interpolation Search
        searchOps.clear();
        for(int i = 0; i < a3.aList.size(); i++) {
            int[] tmp = new int[a3.aList.get(i).size()];
            for(int j =0; j < a3.aList.get(i).size(); j++) {
                tmp[j] = a3.aList.get(i).get(j);
            }
            int[] tmp2 = new int[a3.aList2.get(i).size()];
            for(int j =0; j < a3.aList2.get(i).size(); j++) {
                tmp2[j] = a3.aList2.get(i).get(j);
            }
            Arrays.sort(tmp);
            for(int j = 0; j < a3.aList2.get(i).size(); j++) {
                a3.interpolationSearch(tmp, a3.aList2.get(i).get(j));
                searchOps.add(a3.ops);
            }
        }

        searchAverage = 0;
        for(int i = 0; i < searchOps.size(); i++) {
            searchAverage += searchOps.get(i);
        }

        System.out.println("interpolationSearch average (FAIL): " + searchAverage / searchOps.size());

        //System.out.println(a3.aList.get(0).get(0));
        //System.out.println(a3.aList.get(1).get(0));

        /*ArrayList<Integer> tmp =
        new ArrayList<Integer>();
        ArrayList<Integer> tmp2 =
        new ArrayList<Integer>();

        for(int i = 0; i < 10; i++) {
            tmp.add(getRandomNumber(1, 10000));
        }

        for(int i = 0; i < 10; i++) {
            tmp2.add(getRandomNumber(1, 10000));
        }

        System.out.println("Print 1: ");

        for(int i = 0; i < 10; i++) {
            System.out.println(tmp.get(i));
        }

        System.out.println("Print 2: ");

        for(int i = 0; i < 10; i++) {
            System.out.println(tmp2.get(i));
        }*/

        /*System.out.println("Print 1: ");

        for(int i = 0; i < a3.aList.get(0).size(); i++) {
            System.out.println(a3.aList.get(0).get(i));
        }

        System.out.println("Print 2: ");

        for(int i = 0; i < a3.aList.get(0).size(); i++) {
            System.out.println(a3.aList2.get(0).get(i));
        }*/
    }



}