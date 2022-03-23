/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.farmingdale.m04hashing;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author gerstl
 * @param <K> Key type
 * @param <V> Value type
 */
// Note that by definition, all K should have a hashCode() method and an 
// equals() method. Thus we don't need, e., extends Hashable (there is no 
// such interface)
public class HashTable<K extends Comparable<K>, V> {

    static int DEFAULT_SIZE = 1_001;

    class Node<K, V> {

        K key;
        V value;
        Node<K, V> next;
    }
    ArrayList<Node<K, V>> internalTable;
    int tableSize; // table array capacity)
    int count;

    

    public HashTable(int size) {
        tableSize = size;
        count = 0;
        internalTable= new ArrayList<Node<K,V>>(tableSize);

    } // int CTOR

    public HashTable() {
        tableSize = DEFAULT_SIZE;
        count = 0;
        internalTable = new ArrayList<Node<K,V>>(DEFAULT_SIZE);
        
        
        
    } // default ctor

    public void clear() {
    }

    public int size() {
        return 0;
    }

    // iterator is private since Node<> is private. Used by sameAs()
    // and toString
    private Iterator<Node<K, V>> iterator() {
        var iterRv = new Iterator<Node<K, V>>() {
            Node<K,V> current = null;
            int pos = 0;
            @Override
            public boolean hasNext() {

              boolean hasNext = false;
              if(current != null && pos <= tableSize){
                  pos++;
                  hasNext = true;
              }
              return hasNext;

            } // hasNext()

            @Override
            public Node<K, V> next() {
                return null;
            } // next()

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            } // remove()
        }; // anon inner class iterator
        return iterRv;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Node<K,V>> iter = iterator();
        sb.append("[");
        
        while(iter.hasNext()){
            sb.append(iter.next());
            if(iter.hasNext()){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public V get(K key) {
        
        int HC = key.hashCode();
        int index = HC % tableSize;
        
        if(index < 0)
            index *= -1;
        
        Node<K,V> getNode = internalTable.get(index);
        
        Node<K,V> previousNode = null;
        while(previousNode != null){
            if(previousNode.key.equals(key) && HC == previousNode.hashCode())
                break;
            
            previousNode = getNode;
            getNode = getNode.next;
        }
        
        count--;
        
        if(previousNode != null){
            previousNode.next = getNode.next;
        } else {
            internalTable.set(index, getNode.next);
        }
        
        
        
        return getNode.value;
    }

    public V put(K key, V value) {
        
          
        int HC = key.hashCode();
        int index = HC % tableSize;
        
        Node<K,V> head = internalTable.get(index); 
        
        
        
        
        
        
        
        return null;
    }

    public V remove(K key) {
        return null;
    }


    public Boolean sameAs(java.util.HashMap<K, V> other) {
        return false;
    }

}
