import java.util.Random;

/**
 *
 * @param <E>
 */
public class linkedListImp<E> {

    Node head, tail;
    int len;


	//Constructor de linkedList
    public linkedListImp() {
        len = 0;
        head = null;
        tail = null;
    }
	//creación del objeto Nodo
    public class Node {

        E key;
        Node next;
		//constructor del Nodo
        public Node(E key) {
            this.key = key;
            this.next = null;
        }
    }

  	/*
           *
      isEmpty() - O(1)
      len() - O(1)
      pushFirst(E key) - O(1)
      pushLast(E key) - O(1)
      popFirst() - O(1)
      popLast() - O(n)
      changeFirst(E key) - O(1)
      changeLast(E key) - O(1)
      delete(E key) - O(n)
      change(E oldKey, E newKey) - O(n)
      rangeSearch(int n) - O(n)
      visit() - O(n)
      sort() - O(n^2)
      sorted() - O(n^2)
      inList(E key) - O(n)
      setEmpty() - O(1)
  	 */

    //O(1)
  	/**
  	 * Retorna true si la lista está vacía
  	 * false, si no.
  	 */
    private boolean isEmpty() {
        return len == 0;
    }

    //O(1)
  	/**
  	 * Retorna la cantidad de elementos en la lista.
  	 */
    private int len() {
        return len;
    }

    //O(1)
  	/**
  	 * Añade un elemento al inicio de la lista.
  	 * @Param key
  	 */
    private void pushFirst(E key) {
        Node n = new Node(key);
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

    //O(1)
  	/**
  	 * Añade un elemento al final de la lista.
  	 * @Param key
  	 */
    private void pushLast(E key) {

        Node n = new Node(key);
        if (tail == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        len++;
    }

    //O(1)
  	/**
  	 * Elimina el primer elemento de la lista y lo retorna.
  	 */
    private E popFirst() {

        if (head == null) {
            return null;
        }

        Node t = head;
        head = head.next;
        len--;
        return t.key;
    }

    //O(n)
  	/**
  	 * Elimina el último elemento de la lista y lo retorna.
  	 */
    private E popLast() {
        if (tail != null) {

            if (head.next == null) {
                Node d = head;
                this.head = null;
                this.tail = null;
                len--;
                return d.key;
            }

            Node previous = null;
            Node last = head;
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

    //O(1)
  	/**
  	 * Cambia el primer elemento de la lista por key.
  	 * @Param key
  	 */
    public void changeFirst(E key) {
        head.key = key;
    }

    //O(1)
  	/**
  	 * Cambia el último elemento de la lista por key.
  	 * @Param key
  	 */
    public void changeLast(E key) {
        tail.key = key;
    }

    //O(n)
  	/**
  	 * Elimina de la lista el elemento key.
  	 * @Param key
  	 */
    private E delete(E key) {
        Node previous = null;
        Node current = head;
        while (current.key != key && current.next != null) {
            previous = current;
            current = current.next;
        }
        if (current.key == key) {
            if (head.next == null) { //lista con un elemento
                head = null;
                tail = null;
            } else if (current.next == null) { //último elemento
                previous.next = null;
                tail = previous;
            } else if (previous == null) { //primer elemento de la lista
                head = head.next;
            } else {
                previous.next = current.next;
            }
            len--;
            return current.key;
        }
        Node k = new Node((E) "No Such Element in list");
        return k.key;
    }

    //O(n)
  	/**
  	 * cambia el elemento oldKey por newKey.
  	 * @Param oldKey
  	 * @Param newKey
  	 */
    private E change(E oldKey, E newKey) {
        Node n = head;
        while (n != null) {
            if (n.key == oldKey) {
                n.key = newKey;
                return null;
            }
            n = n.next;
        }
        Node k = new Node((E) "No Such Element in list");
        return k.key;
    }

 	//O(n)
  	/**
  	 * Imprime los primeros n elementos de la lista.
  	 * @Param n
  	 */
  	private void rangeSearch(int n) {

        if (n > this.len) {
            n = len;
        }
        Node p = head;
        for (int i = 0; i < n; i++) {
            System.out.print(p.key + " ");
            p = p.next;
        }

    }

    //O(n)
  	/**
  	 * Imprime toda la lista.
  	 */
    private void visit() {
        Node n = head;
        while (n != null) {
            System.out.print(n.key + " ");
            n = n.next;
        }
        System.out.println("");
    }

  	//retorna el Nodo de la mitad
  	//O(n)
  	public Node getSubLMiddle(Node h){
        if (h == null) {return null;}

        Node slow = h;
        Node fast = h;
        while (slow.next != null && fast.next!= null) {
            if(fast.next.next == null) {return slow;}

            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

  	//Ordenamiento
  	//O(n), revisa si la lista es numérica para que el ordenamiento tenga sentido
  	private boolean isNumerical() {
        if (head == null) {return false;}

        boolean isnum = true;
        Node current = head;
        E currentKey = current.key;

        while(current.next!=null) {
            try {
                double num = Double.parseDouble(currentKey.toString());
            }
            catch (Exception e) {return false;}

            current = current.next;
        }

        return isnum;
    }

  	//O(1)
  	private boolean keysInequality(Node a, Node b) {
        boolean inequality = false;
        try {
            double num1 = Double.parseDouble(a.key.toString());
            double num2 = Double.parseDouble(b.key.toString());

            if(num1<=num2) {
                inequality = true;
            }
        }
        catch (Exception e) {}

        return inequality;
        }

  	//O(n)
  	private Node mergeSorted(Node a, Node b){
        if(a==null) {return b;}
        if(b==null) {return a;}

        Node result = null;

        if(keysInequality(a, b)) {
            result = a;
            result.next = mergeSorted(a.next, b);
        }

        else {
            result = b;
            result.next = mergeSorted(a, b.next);
        }

        return result;
    }

  	//O(n^2)
  	private Node sortSubL(Node h) {

        if (h == null) {return h;}

        if (h.next == null) {return h;}

        Node mid = getSubLMiddle(h);
        Node nextOfMid = mid.next;
        mid.next = null;
        Node rightMid = sortSubL(nextOfMid);
        Node leftMid = sortSubL(h);

        Node sorted = mergeSorted(leftMid, rightMid);

        return sorted;
    }
  	//O(nlogn)
  	/**
  	 * Ordena la lista.
  	 */
  	private void sort() {
        if(!isNumerical()) {return;}

        Node sorted = sortSubL(head);
        head = sorted;

        if(sorted == null) {
            tail = sorted;
        } else {
            Node current = sorted;
            while(current.next!= null) {
                current = current.next;
            }
            tail = current;
        }
    }

	//O(nlogn)
  	/**
  	 * Retorna una copia ordenada de la lista, no modifica la original.
  	 */
  	private LinkedListImp<E> sorted(){
      LinkedListImp<E> copy = new LinkedListImp();
      Node current = this.head;
      while (current !=null) {
          copy.pushLast(current.key);
          current = current.next;
      }
      copy.sort();
      return copy;
    }

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

    //O(1)
    /**
     * Deja vacía la lista.
     */
    public void setEmpty() {

        head = null;
        tail = null;
        len = 0;
    }

    public static void main(String a[]) {
        linkedListImp<Integer> ll;
        ll = new linkedListImp<>();
        /*
        ll.pushFirst(3);
        ll.pushFirst(1);
        ll.pushFirst(2);
        ll.pushFirst(10);
        ll.pushLast(5);
        ll.pushLast(1);
        System.out.println(ll.len());
        //10 2 1 3 5
        System.out.println(ll.isEmpty());
        System.out.println(ll.inList(10));
        System.out.println(ll.inList(14));
        ll.visit();
        ll.changeFirst(11);
        ll.changeLast(3);
        ll.changeByKey(3, 10);
        ll.visit();
        System.out.println(ll.delete(1));
        System.out.println(ll.delete(1));
        ll.visit();
        System.out.println(ll.popFirst());
        ll.visit();
        System.out.println(ll.popLast());
        ll.visit();
        System.out.println(ll.popLast());
        ll.visit();
        System.out.println(ll.inList(2));
        System.out.println(ll.delete(2));
        ll.popFirst();
        System.out.println(ll.isEmpty());
        ll.visit();
         */
        Random r = new Random();
        for (int i = 0; i < 10000000; i++) {
            ll.pushFirst(r.nextInt(100));
        }
        ll.pushLast(110);
        System.out.println(ll.inList(106));
        System.out.println("done 1");
        System.out.println(ll.deleteByKey(106));
        System.out.println("done 2");
        System.out.println(ll.changeByKey(110, 111));
        System.out.println("done 3");
    }
}
