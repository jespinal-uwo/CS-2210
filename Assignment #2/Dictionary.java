public class Dictionary implements DictionaryADT {

    private RecordNode recordHashTable[]; // Hash Table
    private int sizeHashTable; // Size of hash table
    private int count; // number of Record Objects

    public Dictionary(int size) {

        this.sizeHashTable = size;

        // Initializes Hash Table to the specified size
        this.recordHashTable = new RecordNode[size];

        // Intializes all elements of the hash table to null
        for (int i = 0; i < size; i++) {
            recordHashTable[i] = null;
        }
    }

    public int put(Record rec) throws DuplicatedKeyException {

        // Determines on what index of the hash table to put the Record object
        int pos = hashFunction(rec.getKey());

        // Creates a node that stores a record object
        RecordNode NodeRecord = new RecordNode(rec);
        RecordNode current = new RecordNode();

        // Checks to see if a collision was found
        // Uses Separate Chaining to Resolve Collisions
        if (recordHashTable[pos] != null) {
            // Current Node points to the first node in the linked list
            current = recordHashTable[pos];

            // Scans through the linked list until it reaches the end or if the key found is
            // the same key
            // as the record that's being put in the linked list (Duplicated keys)
            while (current.getNext() != null && !current.getElement().getKey().equals(rec.getKey())) {
                current = current.getNext();
            }

            // Not a duplicated key was found therefore it is safe to add the Node
            // containing the new Record object
            // at the end of the linked list
            if (current.getNext() == null) {
                current.setNext(NodeRecord);
                this.count++; // Increases the number of Record objects found
            }

            // If the node in the linked list contains the same key as the record object we
            // are trying to put
            // in the linked list, we have found duplicated keys and an exception is thrown.
            if (current.getElement().getKey().equals(rec.getKey())) {
                throw new DuplicatedKeyException("Duplicated Key!");
            }

            return 1;
        }

        // if a collision wasn't found, first entry of hash table stores a node
        // containing a Record object
        else {
            this.count++; // Increases the number of Record objects found
            recordHashTable[pos] = NodeRecord;
            return 0;
        }

    }

    public void remove(String key) throws InexistentKeyException {
        // Determines on what index of the hash table the Record object was put
        int pos = hashFunction(key);

        // Record object should be found in this position in the hash table, if the hash
        // table at the position is null
        // it means the Record Object was never there to begin with
        if (recordHashTable[pos] == null) {
            throw new InexistentKeyException("Inexistent Key!");
        }

        else {

            // Before and current pointers to traverse through the linked list
            RecordNode before = new RecordNode();
            RecordNode current = new RecordNode();

            before = null;
            current = recordHashTable[pos];

            // Traverses through the linked list, stops if the key matches or until it
            // reaches the end of the linked list
            while (!current.getElement().getKey().equals(key) && current != null) {
                before = current;
                current = current.getNext();
            }

            // if it reaches the end of the linked list, this means the Record object was
            // never there to begin with,
            // thus, it throws an exception
            if (current == null) {
                throw new InexistentKeyException("Inexistent Key!");
            }

            // Finds matching keys
            if (current.getElement().getKey().equals(key)) {

                this.count--; // Decreases the number of Record Objects

                // If the Record Object to be removed was found in the first node of the linked
                // list, the front
                // of the linked list points to the 2nd node of the linked list, removing the
                // first node from
                // the linked list
                if (before == null)
                    recordHashTable[pos] = (current.getNext());
                else {
                    // Removes Record Object from the linked list
                    before.setNext(current.getNext());
                }
            }

        }
    }

    // Returns Record Object given a key
    public Record get(String key) {

        // Finds entry in the hashTable were Record Object should be found
        int pos = hashFunction(key);

        RecordNode p = new RecordNode();

        p = recordHashTable[pos];

        // Traverses through the linked list
        while (p != null && !p.getElement().getKey().equals(key)) {
            p = p.getNext();
        }

        // Returns null if the Record object wasn't found
        if (p == null)
            return null;
        else
            return p.getElement(); // Returns Record Object

    }

    // Returns the number of Record Objects in the hash table
    public int numRecords() {
        return this.count;
    }

    // Converts String into an integer to be used as a compression map
    private int polynomialHashCode(String keyString, int X, int size) {

        int lengthOfString = keyString.length();

        int conversion = lengthOfString - 1;

        // Converts first character of the String into an integer
        int val = (int) keyString.charAt((conversion - (lengthOfString - 1)));

        // Polynomial Hash Fuction, it uses Horner's rule to perform modular arithmetic
        // on each term of the polynomial to avoid integer overflow

        // Performs mod based on the size of the Hash Table to distribute Record Objects
        // uniformly
        // across the entire Hash Table
        for (int i = lengthOfString - 2; i >= 0; i--) {
            val = (val * X + ((int) keyString.charAt(conversion - i))) % size;
        }

        return val;
    }

    // Hash Functions that uses the polynomial hash code
    private int hashFunction(String key) {

        int position = polynomialHashCode(key, 33, this.sizeHashTable);

        return position;
    }
}
