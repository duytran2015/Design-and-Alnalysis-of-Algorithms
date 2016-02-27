/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.Random;
//import java.util.Scanner;
/**
 *
 * @author duytran
 */
public class Sort {
    private static Random random = new Random();
    
    public static void main(String[] args) {
        int n, loop;
        long startTime, endTime;
        long exc1a, exc2a, exc3a, exc4a, exc5a,
             exc1b, exc2b, exc3b, exc4b, exc5b;
        
        for (int i = 1; i < 10; i++){
            exc1a = 0; exc1b = 0; exc2a = 0; exc2b = 0; exc3a = 0; exc3b = 0;
            exc4a = 0; exc4b = 0; exc5a = 0; exc5b = 0;
            n = (int)Math.pow(2, i);
            System.out.println("\nNumber of elements: "+n+" / Power of: "+i);
            
            //Generate m random arrays
            for (int m = 0; m < 1000; m++){
                int[] a = genArray(n);
                int[] b = new int[n];
                //System.out.print ("Unsorted List is:\n");
                //printList(a);
                
                //Extra loop to find ave. exectime
                if(i > 0 && i < 8){
                    loop = 1000;
                }
                else{
                    loop = 1;
                }
           
                //System.out.println("Loop: "+loop);
                //System.out.println("1a. Insertion Sort Random Array.");
                startTime = System.currentTimeMillis();
                for(int j = 0; j < loop; j++){
                    System.arraycopy(a, 0, b, 0, n);
                    insertionSort(b,0,n);
                }
                endTime = System.currentTimeMillis();
                exc1a += (endTime-startTime);
                //System.out.print (" Sorted List is:\n");
                //printList(b);
                //System.out.println (" Execution time: " + (endTime-startTime));
            
                //System.out.println("1b. Insertion Sort Sorted Array.");
                startTime = System.currentTimeMillis();
            
                for(int j = 0; j < loop; j++){
                    insertionSort(b,0,n-1);
                }
                endTime = System.currentTimeMillis();
                exc1b += (endTime-startTime);
                //System.out.print (" Sorted List is:\n");
                //printList(b);
                //System.out.println (" Execution time: " + (endTime-startTime));
            
            
                //System.out.println("2a. Merge Sort Random Array.");
                startTime = System.currentTimeMillis();
                for(int j = 0; j < loop; j++){
                    System.arraycopy(a, 0, b, 0, n);
                    mergeSort(b,0,n-1);
                }
                endTime = System.currentTimeMillis();
                exc2a += (endTime-startTime);
                //System.out.print ("Sorted List is:\n");
                //printList(b);
                //System.out.println ("Execution time: " + (endTime-startTime));
            
                //System.out.println("2b. Merge Sort Sorted Array.");
                startTime = System.currentTimeMillis();
                for(int j = 0; j < loop; j++){
                    mergeSort(b,0,n-1);
                }
                endTime = System.currentTimeMillis();
                exc2b += (endTime-startTime);
                //System.out.print (" Sorted List is:\n");
                //printList(b);
                //System.out.println (" Execution time: " + (endTime-startTime));
            
            
                //System.out.println("3a. Quick Sort Random Array.");
                startTime = System.currentTimeMillis();
                for(int j = 0; j < loop; j++){
                    System.arraycopy(a, 0, b, 0, n);
                    quickSort(b,0,n-1);
                }
                endTime = System.currentTimeMillis();
                exc3a += (endTime-startTime);
                //System.out.print (" Sorted List is:\n");
                //printList(b);
                //System.out.println (" Execution time: " + (endTime-startTime));
            
                if (i < 14){
                    //System.out.println("3b. Quick Sort Sorted Array.");
                    startTime = System.currentTimeMillis();
                    for(int j = 0; j < loop; j++){
                        quickSort(b,0,n-1);
                    }
                    endTime = System.currentTimeMillis();
                    exc3b += (endTime-startTime);
                    //System.out.print (" Sorted List is:\n");
                    //printList(b);
                    //System.out.println (" Execution time: " + (endTime-startTime));
                }
            
            
                //System.out.println("4a. Quick Sort 2 Random Array.");
                startTime = System.currentTimeMillis();
                for(int j = 0; j < loop; j++){
                    System.arraycopy(a, 0, b, 0, n);
                    quickSort2(b,0,n-1);
                }
                endTime = System.currentTimeMillis();
                exc4a += (endTime-startTime);
                //System.out.print (" Sorted List is:\n");
                //printList(b);
                //System.out.println (" Execution time: " + (endTime-startTime));
            
                if (i < 14){
                    //System.out.println("4b. Quick Sort 2 Sorted Array.");
                    startTime = System.currentTimeMillis();
                    for(int j = 0; j < loop; j++){
                        quickSort2(b,0,n-1);
                    }
                    endTime = System.currentTimeMillis();
                    exc4b += (endTime-startTime);
                    //System.out.print (" Sorted List is:\n");
                    //printList(b);
                    //System.out.println (" Execution time: " + (endTime-startTime));
                }
            
            
                //System.out.println("5a. Quick Sort 3 Random Array.");
                startTime = System.currentTimeMillis();
                for(int j = 0; j < loop; j++){
                    System.arraycopy(a, 0, b, 0, n);
                    quickSort3(b,0,n-1);
                }
                endTime = System.currentTimeMillis();
                exc5a += (endTime-startTime);
                //System.out.print (" Sorted List is:\n");
                //printList(b);
                //System.out.println (" Execution time: " + (endTime-startTime));
            
                if (i < 14){
                    //System.out.println("5b. Quick Sort 3 Sorted Array.");
                    startTime = System.currentTimeMillis();
                    for(int j = 0; j < loop; j++){
                        quickSort3(b,0,n-1);
                    }
                    endTime = System.currentTimeMillis();
                    exc5b += (endTime-startTime);
                    //System.out.print (" Sorted List is:\n");
                    //printList(b);
                    //System.out.println (" Execution time: " + (endTime-startTime));
                }
            
                //System.out.println("\n");
            }
            
            System.out.println ("Insertion sort random array exc. time: " + exc1a);
            System.out.println ("Insertion sort sorted array exc. time: " + exc1b);
            System.out.println ("Merge sort random array exc. time: " + exc2a);
            System.out.println ("Merge sort sorted array exc. time: " + exc2b);
            System.out.println ("Quick sort random array exc. time: " + exc3a);
            System.out.println ("Quick sort sorted array exc. time: " + exc3b);
            System.out.println ("Quick sort 2 random array exc. time: " + exc4a);
            System.out.println ("Quick sort 2 sorted array exc. time: " + exc4b);
            System.out.println ("Quick sort 3 random array exc. time: " + exc5a);
            System.out.println ("Quick sort 3 sorted array exc. time: " + exc5b);
            
        }
    }                    
    
     
    static int[] genArray(int n){
        //Random random = new Random();
	int[] a = new int[n];
	for (int i = 0; i < n; ++i) {
	    a[i] = random.nextInt(1000);
	}
        return a;
    }

    static int[] insertionSort(int[] a, int start, int end){
	for (int i = start; i < end; i++) {
            for(int j = i ; j > 0 ; j--){
		if(a[j] < a[j-1]){
                    swap(a,j,j-1);
		}
                //else break;
            }
	}
	return a;
    }

    static void printList(int [] a){
        for(int i=0; i < a.length; i++){
            System.out.print(" " + a[i]);
        }
        System.out.println("");
    }
    
    static void mergeSort(int[] a, int first, int last){
	if(last - first == 0){
            //do nothing
        }
	else if (last - first == 1) {
            if(a[first] > a[last]){
		int temp = a[first];
		a[first] = a[last];
		a[last] = temp;
            }
	}
	else{
            int mid = (first + last) / 2;
            mergeSort(a, first, mid);
            mergeSort(a, mid + 1, last);
            merge(a, first, mid, last);
	}
    }

    static void merge(int[] a, int first, int mid, int last){
        int[] temp = new int[last - first + 1];
	int i = first; int j = mid + 1;
	for(int k = first; k <= last; k++){
            if(i > mid || j > last){
		if(i > mid && j <= last){
                    temp[k - first] = a[j];
                    j++;
		}
		else if(i <= mid && j > last){
                    temp[k - first] = a[i];
                    i++;
		}
		else{
                    break;
		}
            }
            else{
		if(a[i] < a[j]){
                    temp[k - first] = a[i];
                    i++;
		}
		else{
                    temp[k - first] = a[j];
                    j++;
		}
            }
	}
		
	for(int count = 0; count < temp.length; count++){
            a[first + count] = temp[count];
	}
    }
    
    static void quickSort(int a[], int start, int end) {
        if (start < end) {
            int index = partition(a, start, end);
            quickSort(a, start, index - 1);
            quickSort(a, index + 1, end);
	}
    }
    
    static void quickSort2(int a[], int start, int end) {
        if (end - start + 1 <= 16){
            insertionSort(a,start,end);
        }
        else{
            if (start < end) {
                int index = partition(a, start, end);
                quickSort2(a, start, index - 1);
                quickSort2(a, index + 1, end);
            }
        }
    }
    
    static void quickSort3(int a[], int start, int end) {
        if ((end - start + 1) > 16){
            //Random random = new Random();
            int temp = start + random.nextInt(end-start+1)%(end-start+1);
            swap (a,start,temp);
            if (start < end) {
                int index = partition(a, start, end);
                quickSort3(a, start, index - 1);
                quickSort3(a, index + 1, end);
            }
        }
        else{
            if (start < end) {
                int index = partition(a, start, end);
                quickSort3(a, start, index - 1);
                quickSort3(a, index + 1, end);
            }
        }
    }
    
    static int partition(int a[], int start, int end) {
	int pivot = a[end];
	int index = start;
	for (int i = start; i < end; i++) {
            if (a[i] <= pivot) {
		swap(a, i, index);
		index++;
            }
	}
	swap(a, index, end);
	return index;
    }

    static void swap(int a[], int x, int y) {
	int temp = a[x];
	a[x] = a[y];
	a[y] = temp;
    }
}
