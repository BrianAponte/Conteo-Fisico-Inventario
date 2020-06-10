class HashNode<K extends Comparable<K>,V extends Comparable<V>> implements Comparable<HashNode<K,V>>{
    K Key;
    V Value;

    //hNode1.key.compareTo(hNode2.key)
    public int compareTo(HashNode<K, V> hNode) {
        if(this.Key.compareTo(hNode.Key) == 0){
            // 0 ==, -1 a<b, 1 a.ct(b) a>b
        }
        return 0;
    }

    public HashNode(final K key, final V value){
      this.Key = key;
      this.Value = value;
    }
  }

public class HashMap<K extends Comparable<K>,V extends Comparable<V>>{
  public D_ArrayImp<HashNode<K, V>> HashTable;
  int size, len;
  
  public HashMap(final int size){
    HashTable = new D_ArrayImp<>(size);
    this.size = size;
    len = 0; //num of data
  }
  
  public void add(final K key, final V value){
  
    final HashNode<K,V> n = new HashNode<>(key, value);
    final HashNode<K, V> first = HashTable.get(Hash(key));
    //si ese slot de la hashtable está vacío
    if(first == null	){
      HashTable.update(Hash(key), n);
    } else{ //si no está vacío
      //si tiene un solo elemento
      if(first.last == null){
        first.next = n;
        first.next.prev = first;
        first.last = n;
      } else{ //si tiene más de un elemento
        first.last.next = n;
        first.last.next.prev = first.last;
        first.last = n;
      }
    }
    
  }
  
  public V find(final K key){
    HashNode<K, V> Hnode = HashTable.get(Hash(key));
    while(Hnode != null){
     if(Hnode.Key == key){
        return Hnode.Value;
      }
      Hnode = Hnode.next; 
    }
  }
  
  public void remove(final K key){
    HashNode<K, V> Hnode = HashTable.get(Hash(key));
    while(Hnode != null){
     if(Hnode.Key == key){
        Hnode.prev.next = Hnode.next;
       	Hnode.next.prev = Hnode.prev;
       	Hnode = null;
       	break;
      }
      Hnode = Hnode.next; 
    }
    
  }
  
  public int hashInt(K key){
    return 0;
  }
  
  public String hashString(K key){
    return "";
  }
  
}