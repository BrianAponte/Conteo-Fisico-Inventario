package com.inventory;

import java.util.Arrays;

/**
 *.
 * @param <E>
 */
public class dArrayImp<E> {

    private E array[];
    private int len; //elementos inlist
    private int capacity; //espacio

    public dArrayImp() {
        array = (E[]) new Object[1];
        this.capacity = 1;
        this.len = 0;
    }

  	/*
      rUp()
      rDown()
      add(E elem)
      delete(int index)
      pop()
      update(int index, E new_elem)
      get(int index)
      visit()
      rangeSearch(int i, int j)
      sortSubArr(int start, int end)
      sort()
      setEmpty()
      inList(E elem)
      getIndexOf(E elem)
      getLen()
      getSize()
      isEmpty()
  	 */

  	/**
  	 * Aumenta el tamaño del array al doble.
  	 */
    public void rUp() {
        E new_Arr[];
        new_Arr = (E[]) new Object[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            new_Arr[i] = array[i];
        }
        array = new_Arr;
        new_Arr = null;
        capacity *= 2;
    }

    public void rDown() {
        E[] new_Arr = (E[]) new Object[capacity / 2];
        for (int i = 0; i < (capacity / 4); i++) {
            new_Arr[i] = array[i];
        }
        array = new_Arr;
        new_Arr = null;
        capacity /= 2;
    }

  	/**
  	 * Si la cantidad de elementos es 1/4 del espacio total del array, disminuye el tamaño del array a la mitad.
  	 */
    public void verRDown() {
        if (this.len == (this.capacity / 4)) {
            rDown();
        }
    }

    //almacenamiento
  	/**
  	 * Añade elem al final del arreglo
  	 * @Param elem
  	 */
    public void add(E elem) {
        if (this.len == this.capacity) {
            rUp();
        }
        array[len] = elem;
        this.len++;
    }

  	/**
  	 * Elimina el elemento en la posición index
  	 * y mueve una posición hacia atrás todos
  	 * los elementos después de index, si es necesario
  	 * reduce el tamaño del arreglo.
  	 * @Param index
  	 */
    public void delete(int index) {
        if (index < this.capacity && array[index] != null) {
            for (int i = index; i < this.capacity - 1; i++) {
                array[i] = array[i + 1];
            }
            len--;
            verRDown();
        }

    }

    //eliminación del último elemento
  	/**
  	 * Elimina el último elemento del arreglo.
  	 */
    public void pop() {
        if (this.len > 0) {
            len--;
            array[len] = null;
            verRDown();
        }
    }

    //actualización
  	/**
  	 * Cambia el elemento en la posición index por new_Elem
  	 * @Param index
  	 * @Param new_Elem
  	 */
    public void update(int index, E new_elem) {
        array[index] = new_elem;
    }

  	/**
  	 * Retorna el elemento en la posición index
  	 * @Param index
  	 */
    public E get(int index) {return array[index];}

    //consulta total
  	/**
  	 * Imprime todo el arreglo.
  	 */
    public void visit() {
        for (int i = 0; i<this.len;i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("Espacios vacios: " + (this.capacity-this.len) + "\n");
    }

    /**
     * Busqueda parcial de datos.
     * @Param i: Índice inicial de la búsqueda.
     * @Param j: Índice final de la búsqueda.
     */
    public void rangeSearch(int i, int j) {
        if (i <= j) {
            for (E elem : Arrays.copyOfRange(array, i, j + 1)) {
                System.out.println(elem + " ");
            }
        }

    }

    //ordenamiento -QuickSort- O(N^2) en el peor de los casos
  	/* ya que comparadores en java sólo funcionan con valores numéricos, ordenamiento
  	 servirá sólo para listas numéricas*/
  	private boolean isNumerical() {
      try {
        double num = Double.parseDouble(array[0].toString());
      }
      catch (Exception e) {return false;}
      return true;
    }

  	public void sortSubArr(int start, int end) {
      if(len <= 1 || len<start) {return;}

   	 	if (start >= end) {return;}

      if(!isNumerical()) {return;}

			int i = start;
			int j = end;
    	int middle = start + (end - start) / 2;
			double pivot = Double.parseDouble(array[middle].toString());
			while (i<=j) {
    		while(Double.parseDouble(array[i].toString())<pivot) {i++;}

      	while(Double.parseDouble(array[j].toString())>pivot) {j--;}

      	if(i<=j) {
        	E tempi = array[i];
        	array[i] = array[j];
        	array[j] = tempi;
        	i++;
        	j--;
      	}
			}
			if(j>start) {sortSubArr(start, j);}

			if (i<end) {sortSubArr(i, end);}
  	}

  	/**
  	 * Ordena el arreglo
  	 */
    public void sort() {
			sortSubArr(0, len-1);
    }

  	/**
  	 * Retorna una copia ordenada del arreglo, no modifica el original.
  	 */
  	//public dArrayImp<E> sorted(){

    //}

  	/**
  	 * re-inicia el arreglo dejándolo vacío.
  	 */
    public void setEmpty() {
        array = (E[]) new Object[1];
        len = 0;
        capacity = 1;
    }

  	/**
  	 * Busca elem en la lista
  	 * @Param elem
  	 * @Return boolean
  	 */
    public boolean inList(E elem) {
        for (E e : array) {
            if (e == elem) {
                return true;
            }
        }
        return false;
    }

    //retorna -1 si no está en la lista
  	/**
  	 * Busca un elemento y retorna su posición en el arreglo, si no lo encuentra retorna -1
  	 */
    public int getIndexOf(E elem){
        for(int i = 0; i<capacity; i++){
            if(array[i] == elem){
                return i;
            }
        }
        return -1;
    }

    //r # elementos in list
  	/**
  	 * retorna la cantidad de elementos en el arreglo
  	 */
    public int getLen() {return this.len;}

    //r tamaño de list
  	/**
  	 * Retorna la capacidad máxima (incluyendo espacios null) del arreglo
  	 */
    public int getSize() {return this.capacity;}

  	/**
  	 * Retorna si el arreglo está vacío.
  	 */
    public boolean isEmpty() {return len == 0;}


    public static void main(String s[]){
        dArrayImp<Integer> dlist;
        dlist = new dArrayImp<>();
    }
}
