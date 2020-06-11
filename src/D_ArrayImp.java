import java.util.Random;
/**
 * Resizable array with built-in sorting method
 * @param <T> the type of the elements in the array
 */
public class D_ArrayImp<T extends Comparable<T>> {

    T[] array;
    private int len; //amount of elements in the array
    private int capacity; //array current capacity

    /**
  	 * Initializes an array of size 1
  	 */
    public D_ArrayImp() {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) new Comparable[1];
        array = a;
        this.capacity = 1;
        this.len = 0;
    }

    /**
  	 * Initializes an array of a specified size
     * @param initialCap new array's initial capacity
  	 */
    public D_ArrayImp(int initialCap) {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) new Comparable[initialCap];
        array = a;
        this.capacity = initialCap;
        this.len = 0;
    }

    /**
     * Doubles the array capacity if needed
     */
    public void rUp() {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) new Comparable[capacity * 2];
        T[] new_Arr = a;
        for (int i = 0; i < capacity; i++) {
            new_Arr[i] = array[i];
        }
        array = new_Arr;
        new_Arr = null;
        capacity *= 2;
    }


    /**
     * Decreases the array's capacity by half
     */
    public void rDown() {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) new Comparable[(int) capacity / 2];
        T[] new_Arr = a;
        for (int i = 0; i < (capacity / 4); i++) {
            new_Arr[i] = array[i];
        }
        array = new_Arr;
        new_Arr = null;
        capacity /= 2;
    }

    /**
     * If less than 1/4 of the array's capacity it's being used rDown is called
     */
    public void verRDown() {
        if (this.len == (this.capacity / 4)) {
            rDown();
        }
    }

    // almacenamiento
    /**
     * Adds element to the end of the array
     * 
     * @param elem element to add to the array
     */
    public void add(final T elem) {
        if (this.len == this.capacity) {
            rUp();
        }
        array[len] = elem;
        this.len++;
    }

    /**
     * Deletes element at a specified index and moves back all the elements after
     * it, the array's capacity can be reduced if needed.
     * 
     * @param index index of the element to be removed
     */
    public void delete(final int index) {
        if (index < this.capacity && array[index] != null) {
            for (int i = index; i < this.capacity - 1; i++) {
                array[i] = array[i + 1];
            }
            len--;
            verRDown();
        }

    }

    // eliminación del último elemento
    /**
     * Removes the last element in the array and returns it
     * 
     * @throws Exception if the array is empty
     * @return last element in the array
     */
    public T pop() throws Exception {
        if (this.len == 0) {
            throw new Exception("Array is empty");
        }
        len--;
        final T lastElem = array[len];
        array[len] = null;
        verRDown();
        return lastElem;
    }

    // actualización
    /**
     * Changes the element at an specified index
     * 
     * @param index    index of the element to change
     * @param new_elem element's new value
     */
    public void update(final int index, final T new_elem) {
        array[index] = new_elem;
    }

    /**
     * Returns element at a specified index
     * 
     * @param index index of the element to return
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T get(final int index) throws IndexOutOfBoundsException {
        if (index >= capacity || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + len);
        }

        return array[index];
    }

    // consulta total
    /**
     * Prints all the elements in the array.
     */
    public void visit() {
        for (int i = 0; i < this.len; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("Espacios vacios: " + (this.capacity - this.len) + "\n");
    }

    /**
     * Prints all elements included in a specified index range
     * 
     * @param i index range start
     * @param j index range end
     */
    public void rangeSearch(final int i, final int j) {
        if (i <= j) {
            for (int elem = i; elem <= j; elem++) {
                System.out.println(array[elem] + " ");
            }
        }

    }

    // ordenamiento -RandomizedQuickSort- O(NLogN)
    private static Random random = new Random();

    /**
     * Takes as pivot the element at the position l and performs swaps to guarantee
     * that at the end of the call, all elements from l to m1 (pivot's final
     * position) are less than pivot, all elements from index m2 to r are greater
     * than pivot and all the elements between m1 and m2 are equal to pivot
     * 
     * @param a list in wich to perform the swaps
     * @param l index range start
     * @param r index range end
     * @return {m1, m2}
     */

    private int[] partition3(final T[] a, final int l, final int r) {
        final T x = a[l];
        int m1 = l;
        int m2 = l + 1;

        for (int i = l + 1; i <= r; i++) {
            if (a[i].compareTo(x) == -1) {
                if (m1 == m2 - 1) {
                    final T temp = a[i];
                    a[i] = a[m2];
                    a[m2] = temp;
                } else {
                    T temp = a[i];
                    a[i] = a[m1 + 1];
                    a[m1 + 1] = temp;
                    temp = a[i];
                    a[i] = a[m2];
                    a[m2] = temp;
                }
                m1++;
                m2++;
            }

            else if (a[i].compareTo(x) == 0) {
                m2++;
                if (m2 != i + 1) {
                    final T temp = a[i];
                    a[i] = a[m2 - 1];
                    a[m2 - 1] = temp;
                }
            }
        }
        if (a[m1].compareTo(x) != 0) {
            a[l] = a[m1];
            a[m1] = x;
        }

        final int[] m = { m1, m2 };
        return m;
    }

    /**
     * Sorts a given list from index l to index r by calling partition3 recursively
     * and changing the first element of the range by a random pivot
     * 
     * @param a list to sort
     * @param l sorting range start
     * @param r sorting range end
     */
    private void randomizedQuickSort(final T[] a, final int l, final int r) {
        if (l >= r) {
            return;
        }
        final int k = random.nextInt(r - l + 1) + l;
        final T t = a[l];
        a[l] = a[k];
        a[k] = t;
        final int[] m = partition3(a, l, r);
        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1], r);
    }

    /**
     * Sorts the array
     */
    public void sort() {
        randomizedQuickSort(array, 0, len - 1);
    }

    /**
     * Returns a sorted copy of the original array (doesn't modify the original
     * array)
     * 
     * @return sorted copy of the original array
     */
    public D_ArrayImp<T> sorted() {
        final D_ArrayImp<T> copy = new D_ArrayImp<>(len);
        for (int i = 0; i < this.len; i++) {
            copy.add(array[i]);
        }
        copy.sort();
        return copy;
    }

    /**
     * Remove's all the elements from this array. The array will be empty after this
     * call
     */
    public void setEmpty() {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) new Comparable[1];
        array = a;
        len = 0;
        capacity = 1;
    }

    /**
     * Checks if the array contains a specified element
     * 
     * @param elem element to look for in the array
     * @return true if the array contains the specified element
     */
    public boolean inList(final T elem) {
        for (int i = 0; i < len; i++) {
            if (array[i].compareTo(elem) == 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Searchs for a specified element in the array and returns it's index. If the
     * array doesn't contain such element, -1 is returned
     * 
     * @param element element to look for in the array
     * @return index of specified element
     */
    public int getIndexOf(final T element) {
        for(int i = 0; i<len; i++){
            if(array[i].compareTo(element)==0){
                return i;
            }
        }
        return -1;
    }

    //r # elementos in list
  	/**
  	 * Returns the amount of elements in the array
     * @return array's length
  	 */
    public int getLen() {return this.len;}

    //r tamaño de list
  	/**
  	 * Returns the array capacity
     *@return array's current maximum capcity 
  	 */
    public int getSize() {return this.capacity;}

  	/**
  	 * Checks if the array is empty
     * @return true if the array is empty 
  	 */
    public boolean isEmpty() {return len == 0;}

    public static void main(String args[]){
        D_ArrayImp<Integer> my_darray = new D_ArrayImp<>(10);
        my_darray.add(10);

        System.out.println(my_darray.get(9));
    }
}