package com.mycompany.proyecto;

/**
 *
 * @author slondonoq
 */
import java.util.ArrayList;

class BinaryMinHeapImp <T extends Comparable<T>>{
    private ArrayList<T> HeapArr;

    public BinaryMinHeapImp(){
        HeapArr = new ArrayList<>();
    }

    public void insert(T data) {
        HeapArr.add(data);
        siftUp(HeapArr.size()-1);
    }

    private int parent(int childIndex) {
        return (int) (childIndex-1)/2;
    }

    private int leftChild(int parentIndex) {
        return 2*parentIndex + 1;
    }

    private int rightChild(int parentIndex) {
        return 2*parentIndex + 2;
    }

    private void swap(int index1, int index2) {

        if(index1 == index2 || index1>= HeapArr.size() || index2>=HeapArr.size()) {
            return;
        }

        T temp = HeapArr.get(index1);
        HeapArr.set(index1, HeapArr.get(index2));
        HeapArr.set(index2, temp);
    }

    private void siftUp(int indexFrom) {
        while (indexFrom>0) {
            //Checking if the swap is needed
            if(HeapArr.get(indexFrom).compareTo(HeapArr.get(parent(indexFrom)))==1) {
                //if it's not, we stop
                return;
            }
            //If this point is reached, it means the swap is needed
            swap(indexFrom, parent(indexFrom));
            indexFrom = parent(indexFrom);
        }
    }

    public T getMin() throws Exception{
        if(HeapArr.size()==0) {
            throw new Exception("Empty Heap");
        }

        else {
            return HeapArr.get(0);
        }
    }

    public T ExtractMin() throws Exception{
        if (HeapArr.size()==0) {
            throw new Exception("Empty Heap");
        }
        
        swap(0, HeapArr.size()-1);
        T min = HeapArr.remove(HeapArr.size()-1);
        siftDown(0);
        return min;
    }

    private void siftDown(int index) {
        while(leftChild(index)<HeapArr.size()) {
            //If we know there is at least a left child, we set indexOfSwap to its index
            int indexOfSwap = leftChild(index);

            //There's a possibility the chosen index is not the one that must be chosen
            //to perform the swap, it could happen if there is a right child
            if(rightChild(index)<HeapArr.size()) {
                //If there is a right chid we want to see if it is the minimum between the children
                if(HeapArr.get(leftChild(index)).compareTo(HeapArr.get(rightChild(index)))==1) {
                    //If that's the case, indexOfSwap needs to be changed
                    indexOfSwap = rightChild(index);
                }
            }

            //All that is left is to check if the swap is needed
            if(HeapArr.get(index).compareTo(HeapArr.get(indexOfSwap))==-1) {
                //If that's not the case, the heap property is not violated
                //and thus, we end the function
                return;
            }

            //If this point is reached, the swap is needed
            swap(index, indexOfSwap);
        }

    }

    public void delete(int dataToDelete){
        //Checking if the data is in the heap by trying to get its index (we will need it)
        int dataIndex;

        try{
            dataIndex = HeapArr.indexOf(dataToDelete);
        }
        catch(Exception e) {
            //If an exception pops out it means the data is not in the heap, so we stop
            return;
        }

        //As there's no way of knowing how to do the update strategy with  generic types, we'll do the following:
        //First, checking if the data to delete is the only object in the heap
        if(HeapArr.size()==1) {
            //if it happens to be the case, an ExtractMin will do the job
            try{
                ExtractMin();
                return;
            }
            catch(Exception e){}
        }
        //If it's not, we'll swap the data to delete with the rightmost child
        swap(dataIndex, HeapArr.size()-1);
        //Then popping it from heapArr 
        HeapArr.remove(HeapArr.size()-1);
        //All that's left to do is to perform a sift down
        siftDown(dataIndex);
    }

    public void update(int index, int newData) {
        
    }

}