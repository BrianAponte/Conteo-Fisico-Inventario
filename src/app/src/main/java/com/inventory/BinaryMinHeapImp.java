package com.inventory;

/**
 * Array based binary min heap
 * @param <T> the type of elements in the array
 *@author slondonoq
 */

class BinaryMinHeapImp <T extends Comparable<T>>{
    private D_ArrayImp<T> HeapArr;

    public BinaryMinHeapImp(){
        HeapArr = new D_ArrayImp<>();
    }

    public void insert(T data) {
        HeapArr.add(data);
        siftUp(HeapArr.getLen()-1);
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

        if(index1 == index2 || index1>= HeapArr.getLen() || index2>=HeapArr.getLen()) {
            return;
        }

        T temp = HeapArr.get(index1);
        HeapArr.update(index1, HeapArr.get(index2));
        HeapArr.update(index2, temp);
    }

    private void siftUp(int indexFrom) {
        while (indexFrom>0) {
            //Checking if the swap is needed
            if(HeapArr.get(indexFrom).compareTo(HeapArr.get(parent(indexFrom)))>0) {
                //if it's not, we stop
                return;
            }
            //If this point is reached, it means the swap is needed
            swap(indexFrom, parent(indexFrom));
            indexFrom = parent(indexFrom);
        }
    }

    public T getMin() throws Exception{
        if(HeapArr.getLen()==0) {
            throw new Exception("Empty Heap");
        }

        //If this point is reached, the Heap is not empty
        return HeapArr.get(0);
    }

    public T ExtractMin() throws Exception{
        if (HeapArr.getLen()==0) {
            throw new Exception("Empty Heap");
        }

        //If this point is reached, the Heap is not empty
        swap(0, HeapArr.getLen()-1);
        T min = HeapArr.pop();
        siftDown(0);
        return min;
    }

    private void siftDown(int index) {
        while(leftChild(index)<HeapArr.getLen()){
            //If we know there is at least a left child, we set indexOfSwap to its index
            int indexOfSwap = leftChild(index);

            //There's a possibility the chosen index is not the one that must be chosen
            //to perform the swap, it could happen if there is a right child
            if(rightChild(index)<HeapArr.getLen()) {
                //If there is a right chid we want to see if it is the minimum between the children
                if(HeapArr.get(leftChild(index)).compareTo(HeapArr.get(rightChild(index)))>0) {
                    //If that's the case, indexOfSwap needs to be changed
                    indexOfSwap = rightChild(index);
                }
            }

            //All that is left is to check if the swap is needed
            if(HeapArr.get(index).compareTo(HeapArr.get(indexOfSwap))<0) {
                //If that's not the case, the heap property is not violated
                //and thus, we end the function
                return;
            }

            //If this point is reached, the swap is needed
            swap(index, indexOfSwap);
            index = indexOfSwap;
        }

    }

    public void delete(T dataToDelete){
        //Checking if the data is in the heap by trying to get its index (we will need it)
        int dataIndex = HeapArr.getIndexOf(dataToDelete);

        //Arraylists indexOf method returns -1 if the object is not in the list, so we have to check
        if(dataIndex==-1) {
            //if the data is not in the heap, continuing makes no sense
            return;
        }

        //As there's no way of knowing how to do the update strategy with  generic types, we'll do 
        //the following: First, checking if the data to delete is the only object in the heap
        if(HeapArr.getLen()==1) {
            //if it happens to be the case, an ExtractMin will do the job
            try{
                ExtractMin();
                return;
            }
            catch(Exception e){}
        }
        //If it's not, we'll swap the data to delete with the rightmost child
        swap(dataIndex, HeapArr.getLen()-1);
        //Then popping it from heapArr 
        try {HeapArr.pop();}
        catch(Exception e) {}
        //All that's left to do decide if a sift operation is needed
        if(dataToDelete.compareTo(HeapArr.get(dataIndex)) >0) {
            siftUp(dataIndex);
        }
        else if(dataToDelete.compareTo(HeapArr.get(dataIndex)) <0) {
            siftDown(dataIndex);
        }
    }

    public void update(T dataToUpdate, T newData) {
        int indexOfData = HeapArr.getIndexOf(dataToUpdate);
        //Similarly as in the delete method, we'll check if the data is in the heap
        if(indexOfData==-1) {
            //If it's not we just stop
            return;
        }

        //If it is, we first perform an update operation
        HeapArr.update(indexOfData, newData);

        //Next, we have to check wether we need any sift operations or none are needed
        //Starting by checking if it is the root
        if(indexOfData==0) {
            //If it is, we call a siftDown
            siftDown(0);
            return;
        }

        //If it's not, we need to compare the new data with it's parent and possibly a child
        if(HeapArr.get(parent(indexOfData)).compareTo(HeapArr.get(indexOfData))>0) {
            //If the parent is greater, we need to perform a siftUp
            siftUp(indexOfData);
            return;
        }

        //if this point is reached the only cases left to consider are either having or not having
        //any children and siftDown does that job for us, it checks if there is a child and if 
        //any swap is needed, so just by calling it we would be done
        siftDown(indexOfData);
    }
}