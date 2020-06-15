package com.inventory;

class HashNode<K extends Comparable<K>,V extends Comparable<V>> implements Comparable<HashNode<K,V>>{
    private final K Key;
    private final V Value;

    // hNode1.key.compareTo(hNode2.key)
    public int compareTo(final HashNode<K, V> hNode) {
      if (this.Key.compareTo(hNode.Key) == 0) {
        // 0 ==, -1 a<b, 1 a.ct(b) a>b
        return 0;
      }
      if (this.Key.compareTo(hNode.Key) == -1) {
        return -1;
      }
      if (this.Key.compareTo(hNode.Key) == 1) {
        return 1;
      }
      return 0;
    }

    public HashNode(final K key, final V value) {
      this.Key = key;
      this.Value = value;
    }

    public K getKey(){return Key;}
    public V getValue(){return Value;}
  }

  public class HashMap<K extends Comparable<K>, V extends Comparable<V>> {

    protected D_ArrayImp<linkedListImp<HashNode<K, V>>> HashTable;
    protected int capacity, len;

    public HashMap(int size) {
      HashTable = new D_ArrayImp<>(size);
      this.capacity = size;
      len = 0; // num of data
      for(int i = 0;i<size;i++){
        HashTable.add(new linkedListImp<HashNode<K,V>>());
      }
    }

/// Methods
    /**
     * Returns the Table actual capacity.
     * @param None
     * @return Capacity.
     */
    public int getCapacity()
    {return this.capacity;}

    /**
     * Returns the number of elements.
     * @param None
     * @return Number of elements.
     */
    public int getLen()
    {return this.len;}

    /**
     * Adds an element into the HashTable. 
     * @param key
     * @param value
     * @return void
     *
     */
    public void add(final K key, final V value) {

      /*
       *Se hace la segunda verificación porque en caso de que la tabla se haya llenado
       *perfectamente, el mismo d_array ya haría un r_Up, por lo que se haría 2 veces
      */
      if(this.capacity == this.len && this.len != HashTable.getSize()){
        HashTable.rUp();
        this.capacity = HashTable.getSize();
      }

      //m es el hashnode que se piensa añadir
      HashNode<K, V> m = new HashNode<>(key, value);
      /*
       *n es el Node de tipo Hashnode(hace uso de m)
       *que se puede añadir a la linked list,
       *ya que la ll recibe Node, no HNode
      */
      Node<HashNode<K,V>> n = new Node<>(m);
      /*
       *Acceso a la HashTable en donde indica la función hash
      */
      linkedListImp<HashNode<K,V>> lista = this.HashTable.get(hash(key));
      
      // si ese slot de la hashtable está vacío
      if (lista.isEmpty()) {
        //entra a la HashTable en la posición que le indica el hash
        //al entrar encuenta una linked list, y accede al head
        //al cual le pasa un Node de tipo HashNode de tipo K,V
        lista.head = n;
      } else { // si no está vacío
        lista.pushLast(m);
      }
      this.len++;
    }
    
    
    /**
     * Looks for an element, if found returns its value if not, returns null.
     * @param key
     * @return Value
     * 
     */
    public V get(final K key) {

      /*
       *Acceso a la HashTable en donde indica la función hash
      */
      linkedListImp<HashNode<K,V>> lista = this.HashTable.get(hash(key));
      Node<HashNode<K,V>> searcher = lista.head;

      while (searcher != null) {
        if (searcher.key.getKey() == key) {
          return searcher.key.getValue();
        }
        searcher = searcher.next;
      }
      return null;
    }

    /**
     * Looks for an element, if found returns true, otherwise returns false.
     * @param key
     * @return Boolean
     * 
     */
    public boolean find(final K key) {

      /*
       *Acceso a la HashTable en donde indica la función hash
      */
      final linkedListImp<HashNode<K,V>> lista = HashTable.get(hash(key));
      Node<HashNode<K,V>> searcher = lista.head;

      while (searcher != null) {
        if (searcher.key.getKey() == key) {
          return true;
        }
        searcher = searcher.next;
      }
      return false;
    }

    /**
     * Removes an element from the list.
     * @param key
     * @return void
     */
    public void remove(final K key) {
      /*
       *Acceso a la HashTable en donde indica la función hash
      */
      final linkedListImp<HashNode<K,V>> lista = HashTable.get(hash(key));
      Node<HashNode<K,V>> searcher = lista.head;

      while (searcher != null) {
        if (searcher.key.getKey() == key) {
          lista.delete(searcher.key);
        }
        searcher = searcher.next;
      }

    }

    /**
     * Type Getter 4 K, if int returns 0, if String returns 1
     * Protected cuz' no other class needs access to it.
     * @param key
     * @return 0,1
     */
    protected byte getType(K key){
      try{
        Integer.parseInt(key.toString());
        return 0;
      }catch(Exception e){
        return 1;
      }
    }

    //hashea integers o String verificando el datatype
    public int hash(final K key) {
      if(getType(key) == 0){
        //HashInt
        return ((25 * Integer.parseInt(key.toString()) + 102) % 552493) % this.capacity;
      } 
      //HashString
      
      return 0;
    }

 /// 

 public static void main(String []args){
   System.out.println("Hola");
   HashMap<Integer, String> myHash = new HashMap<>(10);
   myHash.add(10, "Carlos");
   System.out.println(myHash.find(10));
 }
}