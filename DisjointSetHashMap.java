package comprehensive;

import java.util.HashMap;

/**
 * A DisjointSetHashMap implementation of the DisjointSet interface.
 * This implementation uses a HashMap for storing element relationships.
 * @author Trent Hansen and Matthew Ashton-Knochel
 * @param <E> The element type stored in the disjoint set.
 */
public class DisjointSetHashMap<E> implements DisjointSet<E> {

    // HashMap for storing the elements and their corresponding parent elements
    private HashMap<E, E> backingMap;

    /**
     * Constructs an empty DisjointSetHashMap.
     */
    public DisjointSetHashMap() {
        backingMap = new HashMap<>();
    }

    /**
     * Creates a new set containing the given element.
     * If the element is already in the disjoint set, this method does nothing.
     *
     * @param element The element to create a set for.
     */
    @Override
    public void makeSet(E element) {
        if (backingMap.containsKey(element)) {
            return;
        }
        // Store the element as its own parent
        backingMap.put(element, element);
    }

    /**
     * Returns the representative element of the set containing the given element.
     *
     * @param element The element to find the representative for.
     * @return The representative element of the set.
     */
    @Override
    public E getRepresentative(E element) {
        // Check if the element exists in the backingMap, return null if not
        if (!backingMap.containsKey(element)) {
            return null;
        }

        // Initialize current and next variables
        E current = element;
        E next = backingMap.get(element);

        // Traverse the path until the representative is found (when current and next are the same)
        while (!next.equals(current)) {
            current = next;
            next = backingMap.get(current);
        }

        // Return the representative element
        return current;
    }

    /**
     * Unions the sets containing the given elements.
     *
     * @param e1 The first element.
     * @param e2 The second element.
     * @return true if the union occurred, false otherwise.
     */
    @Override
    public boolean union(E e1, E e2) {
        // check that both elements exist
        E n1 = backingMap.get(e1);
        E n2 = backingMap.get(e2);
        if (n1 == null || n2 == null) {
            return false;
        }
        // Get the representatives of the two elements
        E rep1 = getRepresentative(n1);
        E rep2 = getRepresentative(n2);

        // Update the parent of rep1 to rep2, effectively joining the sets
        backingMap.put(rep1, rep2);

        //If the elements were from different sets return true
        return !rep1.equals(rep2);
    }
}
