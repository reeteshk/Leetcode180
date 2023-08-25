/*

 146. LRU Cache
Medium
17.8K
788
Companies
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

 

Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
 

Constraints:

1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.

 */

class LRUCache {

    ArrayList<Pair>al;
    HashMap<Integer,Integer>hm;
    int size;
    int capacity;
    public LRUCache(int capacity) {
        al=new ArrayList<>();
        hm=new HashMap<>();
        this.capacity=capacity;
        size=0;
    }
    
    public int get(int key) {
        if(hm.containsKey(key))
        {
            for(int i=0;i<al.size();i++)
            {
                Pair p=al.get(i);
                if(p.key==key)
                {
                    al.remove(i);
                    al.add(p);
                }
            }
            return hm.get(key);

        }
        else
        {
            return -1;
        }
        
    }
    
  public void put(int key, int value) {
    if (hm.containsKey(key)) {
        // If the key already exists, update its value and move it to the end of the list
        for (int i = 0; i < al.size(); i++) {
            Pair p = al.get(i);
            if (p.key == key) {
                al.remove(i);
                break;
            }
        }
        al.add(new Pair(key, value));
        hm.put(key, value);
    } else {
        if (size < capacity) {
            // If the cache is not full, simply add the new key-value pair
            al.add(new Pair(key, value));
            hm.put(key, value);
            size++;
        } else {
            // If the cache is full, remove the least recently used item (the first item in the list)
            Pair p = al.remove(0);
            hm.remove(p.key);
            al.add(new Pair(key, value));
            hm.put(key, value);
        }
    }
}
}
class Pair{
    int key;
    int value;
    Pair(int key,int value)
    {
        this.key=key;
        this.value=value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */



 /*
  Method 2
  Using LinkedList
  */

  class LRUCache {
    // Create doubly linkedlist
    private Node head = new Node(0, 0);     // head node
    private Node tail = new Node(0, 0);     // tail node
    // create map to store the key and its node
    private Map<Integer, Node> map = new HashMap<>();
    private int capacity;   // globally store the capacity
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.next = head;
    }

    public int get(int key) {
        // if key is not present, return -1
        if (!map.containsKey(key)) {
            return -1;
        }

        // otherwise, fetch the node mapped to the key
        Node node = map.get(key);
        // we will set the node as the most recently used
        // by placing it right next to head
        remove(node);       // remove from wherever it is (also from map)
        insert(node);       // put it right after head  (also add it to map)
        return node.value;  // return value for the key
    }

    public void put(int key, int value) {
        // if map contains the key, we will remove the node from the map
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        // if map size has reached max capacity, we will remove/evict the
        // LEAST RECENTLY USED node that will be right before the tail of linkedlist
        if (map.size() == capacity) {
            remove(tail.prev);
        }
        // we will insert the new node at head's next and put it in the map
        insert(new Node(key, value));
    }
    
    // This method removes the node from its current position 
    // as well as it removes the mapping from the hashmap
    private void remove(Node node) {
        // remove from map
        map.remove(node.key);       
        // delete the node
        node.prev.next = node.next; 
        node.next.prev = node.prev;
    }

    // This method adds the node right at the next of the head
    // as well as it puts a new mapping into the hashmap
    private void insert(Node node) {
        // add to hashmap
        map.put(node.key, node);    
        // Place at head's next
        Node headNext = head.next;
        headNext.prev = node;
        node.next = headNext;
        head.next = node;
        headNext.prev = node;
        node.prev = head;
    }
    
    // The node class
    private class Node {
        Node next, prev;
        int key, value;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

