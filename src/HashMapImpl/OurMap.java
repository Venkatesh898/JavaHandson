package HashMapImpl;

import java.util.ArrayList;
import java.util.List;

public class OurMap<K, V> {
    List<MapNode<K, V>> bucket;
    int capacity; // bucket size
    int size;  // no of entries in map
    private static final int INITIAL_CAPACITY = 5;

    public OurMap() {
        bucket = new ArrayList<>();
        capacity = INITIAL_CAPACITY;
        for (int i = 0; i < capacity; i++)
            bucket.add(null);
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return hashCode % capacity;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        MapNode<K, V> newEntry = new MapNode<>(key, value);
        head = bucket.get(bucketIndex);
        newEntry.next = head;
        bucket.set(bucketIndex, newEntry);
        double loadfactor=(1.0*size)/capacity;
        System.out.println("inserting key "+key);
        System.out.println("Load Factor:"+loadfactor);
        if(loadfactor>0.7)
        {
            rehash();
        }
    }
    private  void  rehash()
    {
        System.out.println("Rehashing Buckets:");
        List<MapNode<K,V>>temp=bucket;
        bucket=new ArrayList<>();
        capacity*=2;
        for(int i=0;i<capacity;i++)
            bucket.add(null);
        size=0;
        for(int i=0;i<temp.size();i++ )
        {
            MapNode<K,V>head=temp.get(i);
            while(head!=null)
            {
                put(head.key, head.value);
                head=head.next;
            }
        }
    }

    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);
        MapNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    bucket.set(bucketIndex, head.next);
                    break;
                } else
                    prev.next = head.next;
                head.next = null;
                size--;
                break;


            }
            prev = head;
            head = head.next;
        }
    }

    private class MapNode<K, V> {
        K key;
        V value;
        MapNode<K, V> next;

        public MapNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
