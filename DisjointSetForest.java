package comprehensive;

import java.util.HashMap;

/**
 * An implementation of the DisjointSet interface using a disjoint set forest
 * It uses path compression and ranks to optimize the operations.
 * @author Trent Hansen and Matthew Ashton-Knochel
 * @param <E> The element type stored in the disjoint set.
 */
public class DisjointSetForest<E> implements DisjointSet<E> {

    // HashMap for storing the elements and their corresponding nodes
    private HashMap<E, Node<E>> backingMap;

    /**
     * default constructor for an empty DisjointSetForest.
     */
    public DisjointSetForest() {
        backingMap = new HashMap<>();
    }

    /**
     * creates a new set containing the given element.
     * If the element is already in the disjoint set, this method does nothing.
     *
     * @param element the element to create a set for.
     */
    @Override
    public void makeSet(E element) {
        if (backingMap.containsKey(element)) {
            return;
        }

        backingMap.put(element, new Node<E>(element));
    }

    /**
     * @param element The element to find the representative for.
     * @return The representative element of the set.
     */
    @Override
    public E getRepresentative(E element) {
        return recursiveGetRepresentative(backingMap.get(element)).element;
    }

    /**
     * private recursive function for finding the representative node of the given node.
     * uses path compression to optimize future calls.
     *
     * @param node the node to find the representative for.
     * @return The representative node of the given node.
     */
    private Node<E> recursiveGetRepresentative(Node<E> node) {
        if (node.parent.equals(node)) {
            return node;
        }
        Node<E> rep = recursiveGetRepresentative(node.parent);
        node.parent = rep; // Path compression
        return rep;
    }

    /**
     * Gets the rank of the set containing the given element.
     *
     * @param element The element to find the rank for.
     * @return The rank of the set containing the given element.
     */
    private int getRank(E element) {
        return recursiveGetRepresentative(backingMap.get(element)).rank;
    }

    /**
     * Unions the sets containing the given elements.
     * Uses union by rank to optimize the operation.
     *
     * @param e1 The first element.
     * @param e2 The second element.
     * @return true if the union was successful, false otherwise.
     */
    @Override
    public boolean union(E e1, E e2) {
        Node<E> n1 = backingMap.get(e1);
        Node<E> n2 = backingMap.get(e2);
        if (n1 == null || n2 == null) {
            return false;
        }
        Node<E> rep1 = recursiveGetRepresentative(n1);
        Node<E> rep2 = recursiveGetRepresentative(n2);

        // Union by rank
        if (rep1.rank < rep2.rank) {
            rep1.parent = rep2;
        } else if (rep1.rank > rep2.rank) {
            rep2.parent = rep1;
        } else {
            rep2.parent = rep1;
            rep1.rank++;
        }
        return true;
    }

    /**
     * A private class representing the nodes in the disjoint set forest.
     */
    private class Node<E> {
        public int rank;
        public Node<E> parent;
        public E element;

        /**
         * Constructs a new node with
         * the given element, initial rank of 0, and sets itself as the parent.
         *
         * @param item The element to be stored in the node.
         */
        Node(E item) {
            this.element = item;
            rank = 0;
            this.parent = this;
        }
    }
}