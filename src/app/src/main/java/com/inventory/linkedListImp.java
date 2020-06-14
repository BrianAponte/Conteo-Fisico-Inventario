package com.inventory;

import java.util.Random;

class Node<E> {

    E key;
    Node<E> next;
    //constructor del Nodo
    public Node(final E key) {
        this.key = key;
        this.next = null;
    }
}
/**
 *
 * @param <E>
 */
public class linkedListImp<E> implements Comparable<linkedListImp<E>>{

    Node<E> head, tail;
    int len;


    public int compareTo(linkedListImp<E> ll){
        return 0;
    }

	//Constructor de linkedList
    public linkedListImp() {
        len = 0;
        head = null;
        tail = null;
    }
	//creación del objeto Nodo
    
    

    /*
     *
     * isEmpty() - O(1) len() - O(1) pushFirst(E key) - O(1) pushLast(E key) - O(1)
     * popFirst() - O(1) popLast() - O(n) changeFirst(E key) - O(1) changeLast(E
     * key) - O(1) delete(E key) - O(n) change(E oldKey, E newKey) - O(n)
     * rangeSearch(int n) - O(n) visit() - O(n) sort() - O(n^2) sorted() - O(n^2)
     * inList(E key) - O(n) setEmpty() - O(1)
     */

    // O(1)
    /**
     * Retorna true si la lista está vacía false, si no.
     */
    public boolean isEmpty() {
        return len == 0;
    }

    // O(1)
    /**
     * Retorna la cantidad de elementos en la lista.
     */
    public int len() {
        return len;
    }

    // O(1)
    /**
     * Añade un elemento al inicio de la lista.
     * 
     * @Param key
     */
    public void pushFirst(final E key) {
        final Node<E> n = new Node<>(key);
        n.next = null;
        if (head == null) {
            this.head = n;
            this.tail = n;
        } else {
            n.next = head;
            head = n;
        }
        len++;
    }

    // O(1)
    /**
     * Añade un elemento al final de la lista.
     * 
     * @Param key
     */
    public void pushLast(final E key) {

        final Node<E> n = new Node<>(key);
        if (tail == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        len++;
    }

    // O(1)
    /**
     * Elimina el primer elemento de la lista y lo retorna.
     */
    public E popFirst() {

        if (head == null) {
            return null;
        }

        final Node<E> t = head;
        head = head.next;
        len--;
        return t.key;
    }

    // O(n)
    /**
     * Elimina el último elemento de la lista y lo retorna.
     */
    public E popLast() {
        if (tail != null) {

            if (head.next == null) {
                final Node<E> d = head;
                this.head = null;
                this.tail = null;
                len--;
                return d.key;
            }

            Node<E> previous = null;
            Node<E> last = head;
            while (last.next != null) {
                previous = last;
                last = last.next;
            }
            previous.next = null;
            tail = previous;
            len--;
            return last.key;
        }
        return null;
    }

    // O(1)
    /**
     * Cambia el primer elemento de la lista por key.
     * 
     * @Param key
     */
    public void changeFirst(final E key) {
        head.key = key;
    }

    // O(1)
    /**
     * Cambia el último elemento de la lista por key.
     * 
     * @Param key
     */
    public void changeLast(final E key) {
        tail.key = key;
    }

    // O(n)
    /**
     * Elimina de la lista el elemento key.
     * Si lo encuentra retorna el elemento, 
     * si no, retorna null.
     * 
     * @Param key
     * 
     */
    public E delete(final E key) {
        Node<E> previous = null;
        Node<E> current = head;
        while (current.key != key && current.next != null) {
            previous = current;
            current = current.next;
        }
        if (current.key == key) {
            if (head.next == null) { // lista con un elemento
                head = null;
                tail = null;
            } else if (current.next == null) { // último elemento
                previous.next = null;
                tail = previous;
            } else if (previous == null) { // primer elemento de la lista
                head = head.next;
            } else {
                previous.next = current.next;
                current.next = null;
            }
            len--;
            return current.key;
        }
        return null;
    }

    // O(n)
    /**
     * cambia el elemento oldKey por newKey,
     * si no existe retorna null.
     * 
     * @param oldKey
     * @param newKey
     * @return oldKey, null.
     */
    public E change(final E oldKey, final E newKey) {
        Node<E> n = head;
        while (n != null) {
            if (n.key == oldKey) {
                n.key = newKey;
                return null;
            }
            n = n.next;
        }

        return null;
    }

    // O(n)
    /**
     * Imprime los primeros n elementos de la lista.
     * 
     * @Param n
     */
    public void rangeSearch(int n) {

        if (n > this.len) {
            n = len;
        }
        Node<E> p = head;
        for (int i = 0; i < n; i++) {
            System.out.print(p.key + " ");
            p = p.next;
        }

    }

    // O(n)
    /**
     * Imprime toda la lista.
     */
    public void visit() {
        Node<E> n = head;
        while (n != null) {
            System.out.print(n.key + " ");
            n = n.next;
        }
        System.out.println("");
    }

    // retorna el Nodo de la mitad
    // O(n)
    public Node<E> getSubLMiddle(final Node<E> h) {
        if (h == null) {
            return null;
        }

        Node<E> slow = h;
        Node<E> fast = h;
        while (slow.next != null && fast.next != null) {
            if (fast.next.next == null) {
                return slow;
            }

            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Ordenamiento
    // O(n), revisa si la lista es numérica para que el ordenamiento tenga sentido
    public boolean isNumerical() {

        if (this.head == null) {
            return false;
        }

        final boolean isnum = true;
        Node<E> current = head;
        final E currentKey = current.key;

        while (current.next != null) {
            try {
                final double num = Double.parseDouble(currentKey.toString());
            } catch (final Exception e) {
                return false;
            }

            current = current.next;
        }

        return isnum;
    }

    // O(1)
    public boolean keysInequality(final Node<E> a, final Node<E> b) {
        boolean inequality = false;
        try {
            final double num1 = Double.parseDouble(a.key.toString());
            final double num2 = Double.parseDouble(b.key.toString());

            if (num1 <= num2) {
                inequality = true;
            }
        } catch (final Exception e) {
        }

        return inequality;
    }

    // O(n)
    public Node<E> mergeSorted(final Node<E> a, final Node<E> b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        Node<E> result = null;

        if (keysInequality(a, b)) {
            result = a;
            result.next = mergeSorted(a.next, b);
        }

        else {
            result = b;
            result.next = mergeSorted(a, b.next);
        }

        return result;
    }

    // O(n^2)
    public Node<E> sortSubL(final Node<E> h) {

        if (h == null) {
            return h;
        }

        if (h.next == null) {
            return h;
        }

        final Node<E> mid = getSubLMiddle(h);
        final Node<E> nextOfMid = mid.next;
        mid.next = null;
        final Node<E> rightMid = sortSubL(nextOfMid);
        final Node<E> leftMid = sortSubL(h);

        final Node<E> sorted = mergeSorted(leftMid, rightMid);

        return sorted;
    }

    // O(nlogn)
    /**
     * Ordena la lista.
     */
    public void sort() {
        if (!isNumerical()) {
            return;
        }

        final Node<E> sorted = sortSubL(head);
        head = sorted;

        if (sorted == null) {
            tail = sorted;
        } else {
            Node<E> current = sorted;
            while (current.next != null) {
                current = current.next;
            }
            tail = current;
        }
    }

	//O(nlogn)
  	/**
  	 * Retorna una copia ordenada de la lista, no modifica la original.
  	 */
  	/*private LinkedListImp<E> sorted(){
      LinkedListImp<E> copy = new LinkedListImp();
      Node current = this.head;
      while (current !=null) {
          copy.pushLast(current.key);
          current = current.next;
      }
      copy.sort();
      return copy;
    }
    */
    //O(n)
  	/**
  	 * Busca el elemento key en la lista
  	 * @Param key
  	 * @Return keyInList
  	 */
    private boolean inList(E key) {
        Node n = head;
        while (n != null) {
            if (n.key == key) {
                return true;
            }
            n = n.next;
        }
        return false;
    }

    // O(1)
    /**
     * Deja vacía la lista.
     */
    public void setEmpty() {

        head = null;
        tail = null;
        len = 0;
    }

    public static void main(final String a[]) {
        linkedListImp<Integer> ll;
        ll = new linkedListImp<>();
        /*
         * ll.pushFirst(3); ll.pushFirst(1); ll.pushFirst(2); ll.pushFirst(10);
         * ll.pushLast(5); ll.pushLast(1); System.out.println(ll.len()); //10 2 1 3 5
         * System.out.println(ll.isEmpty()); System.out.println(ll.inList(10));
         * System.out.println(ll.inList(14)); ll.visit(); ll.changeFirst(11);
         * ll.changeLast(3); ll.changeByKey(3, 10); ll.visit();
         * System.out.println(ll.delete(1)); System.out.println(ll.delete(1));
         * ll.visit(); System.out.println(ll.popFirst()); ll.visit();
         * System.out.println(ll.popLast()); ll.visit();
         * System.out.println(ll.popLast()); ll.visit();
         * System.out.println(ll.inList(2)); System.out.println(ll.delete(2));
         * ll.popFirst(); System.out.println(ll.isEmpty()); ll.visit();
         */
        final Random r = new Random();
        for (int i = 0; i < 10000000; i++) {
            ll.pushFirst(r.nextInt(100));
        }
        ll.pushLast(110);
        System.out.println(ll.inList(106));
        System.out.println("done 1");
        //System.out.println(ll.deleteByKey(106));
        System.out.println("done 2");
        //System.out.println(ll.changeByKey(110, 111));
        System.out.println("done 3");
    }
}
