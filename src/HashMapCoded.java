
import com.sun.source.tree.ReturnTree;

import java.util.*;

class StringIdx <K, P>{
    K key;
    ArrayList<P> positions;

    StringIdx(K k){
        key = k;
        positions = new ArrayList<P>(1);
    }
}

public class HashMapCoded<K, P> {

    public static final Object EMPTY = new EmptySlot();

    static class EmptySlot {
    }

    public static final Object DELETED = new DeletedElement();

    static class DeletedElement {
    }

    public static final double DEFAULT_LOAD = 0.5;
    public static final int DEFAULT_CAPACITY = 8;
    double targetSize;

    Object[] table;
    int size = 0, numKeys = 0, d = 0, minCap, upperLimit;

    //Constructors

    public HashMapCoded(int capacity, double load) {
        if (load >= 2.0)
            throw new IllegalArgumentException("Load debe ser menor a 1");
        this.targetSize = load;
        int tableSize = (int) Math.pow(2, Math.ceil(Math.log(capacity/load)/Math.log(2)));
        table = new Object[tableSize];
        Arrays.fill(table, EMPTY);
        minCap = capacity;
        upperLimit = (int) (table.length*(targetSize+1)/2);
    }

    public HashMapCoded(){
        this(DEFAULT_CAPACITY, DEFAULT_LOAD);
    }

    public HashMapCoded(int capacity){
        this(capacity, DEFAULT_LOAD);
    }

    public int getNumKeys(){
        return numKeys;
    }

    public int getSize(){
        return size;
    }

    boolean inUse(int slot){
        return table[slot] != EMPTY && table[slot] != DELETED;
    }

    //Primary Hash Function
    static double A = (Math.sqrt(5.0)-1)/2;
    protected int hash(int hashCode) {
        double frac = (hashCode * A) - (int) (hashCode * A);
        int hashValue = (int) (table.length * frac);
        if (hashValue < 0)
            hashValue += table.length;
        return hashValue;
    }

    protected int stepHash(int hashCode){
        int s = (hashCode % (table.length/2 - 1));
        if (s < 0)
            s += (table.length/2 - 1);
        return 2*s + 1;
    }

    final int NONE = -1;
    @SuppressWarnings("unchecked")
    protected int locate (K key) {
        int hashCode = key.hashCode();
        int index = hash(hashCode);
        int step = stepHash(hashCode);
        int insertPosition = NONE;
        while (table[index] != EMPTY){
            if(table[index] != DELETED){
                if(((StringIdx<K, P>) table[index]).key.equals(key))
                    return index;
            }
            else if (insertPosition == NONE){
                insertPosition = index;
            }
            index = (index + step) % table.length;
        }
        if (insertPosition == NONE)
            insertPosition = index;
        return insertPosition;
    }

    public boolean containsKey(K key){
        return  inUse(locate(key));
    }

    @SuppressWarnings("unchecked")
    public Iterator<P> get(K key) {
        int slot = locate(key);
        if(inUse(slot))
            return ( (StringIdx<K, P>) table[slot]).positions.iterator();
        return null;
    }

    @SuppressWarnings("unchecked")
    public void put(K key, P position) {
        size++;
        int slot = locate(key);
        if (!inUse(slot)){
            numKeys++;
            table[slot] = new StringIdx<K, P>(key);
        }
        ((StringIdx<K, P>) table[slot]).positions.add(position);
        growTable();
    }

    boolean deleteElementsFromSlot(int slot){
        if(inUse(slot)){
            table[slot] = DELETED;
            d++;
            size--;
            return true;
        } else
            return false;
    }

    public boolean removeBucket (K key){
        return deleteElementsFromSlot(locate(key));
    }

    void growTable(){
        if (numKeys > upperLimit)
            resizeTable(Math.max(numKeys, minCap));
    }

    @SuppressWarnings("unchecked")
    void resizeTable(int targetCap){
        HashMapCoded<K,P> newTable = new HashMapCoded<>(targetCap, targetSize);
        for (int slot = 0; slot < table.length; slot++)
            if (inUse(slot)) {
                StringIdx<K, P> kp = (StringIdx<K, P>) table[slot];
                int newSlot = newTable.locate((K) kp.key);
                newTable.table[newSlot] = kp;
            }
        this.table = newTable.table;
        d = 0;
        upperLimit = (table.length*(1+targetCap)/2);
    }
}