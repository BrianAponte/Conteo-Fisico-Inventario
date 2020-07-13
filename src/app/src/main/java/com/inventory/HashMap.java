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

  /* CONSTRUCTOR */
  public HashNode(final K key, final V value) {
    this.Key = key;
    this.Value = value;
  }

  /* METHODS */
  public K getKey(){return Key;}
  public V getValue(){return Value;}

}

public class HashMap<K extends Comparable<K>, V extends Comparable<V>> {

  protected D_ArrayImp<linkedListImp<HashNode<K, V>>> HashTable;
  protected int len;

  /* CONSTRUCTOR */
  public HashMap(int size) {
    HashTable = new D_ArrayImp<>(size);
    len = 0; // Amount of data
    for(int i = 0;i<size;i++){
      HashTable.add(new linkedListImp<HashNode<K,V>>());
    }
  }

  /* METHODS */

  /**
   * Returns the Table actual capacity.
   * @return capacity.
   */
  public int getCapacity()
  {return this.HashTable.getSize();}

  /**
   * Returns the number of elements.
   * @return Number of elements.
   */
  public int getLen()
  {return this.len;}

  /**
   * Increases size of the table.
   * @return void
   *
   */
  protected void increaseSize(){
    D_ArrayImp<linkedListImp<HashNode<K,V>>> oldTable = HashTable;
    HashTable = new D_ArrayImp<>(HashTable.getSize()*2);

    for(int i = 0; i < HashTable.getSize(); i++){
      HashTable.add(new linkedListImp<HashNode<K,V>>());
    }

    for (int i = 0; i<oldTable.getSize(); i++){
      D_ArrayImp<HashNode<K,V>> hashArray = oldTable.get(i).getAsArray();
      for (int j=0;j<hashArray.getLen();j++){
        HashTable.get(hash(hashArray.get(j).getKey())).pushLast(hashArray.get(j));
      }
    }
  }

  /**
   * Adds an element into the map
   * @param key
   * @param value
   * @return void
   */
  public void add(final K key, final V value) {

    if(this.HashTable.getSize() == this.len){
      increaseSize();
    }

    //m es el hashnode que se piensa añadir
    HashNode<K, V> m = new HashNode<>(key, value);

    //Acceso a la HashTable en donde indica la función hash
    linkedListImp<HashNode<K,V>> lista = this.HashTable.get(hash(key));

    lista.pushLast(m);
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
      if (searcher.key.getKey().compareTo(key)==0) {
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
      if (searcher.key.getKey().compareTo(key)==0) {
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
   * Type Getter for key, if int returns 0, if String returns 1
   * Protected because no other class needs access to it.
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
      return (int)((Long.parseLong(key.toString())*Long.parseLong("2654435761") % Math.pow(2,32)% this.HashTable.getSize()));

    }
    //HashString

    return 0;
  }

/*   public void iter() {
    for(int i=0;i<HashTable.getLen();i++) {
      linkedListImp<HashNode<K,V>> current = HashTable.get(i);
      if(!current.isEmpty()) {
        current.visit();
      }
      else {
        System.out.println("Empty: "+i);
      }
    }
  } */
  ///
}