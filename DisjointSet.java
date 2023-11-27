package comprehensive;

/**
 * a disjoint set interface that has the basic operations
 * for a disjoint set data structure.
 *
 * @param <E> the element type stored in the disjoint set.
 */
public interface DisjointSet<E> {

    /**
     * makes a new set with the given element.
     * if the element is already in the disjoint set, this method does nothing.
     *
     * @param element the element to create a set for.
     */
    public void makeSet(E element);

    /**
     * gets the representative element of the set with the given element.
     * the representative element is used to know which set it is.
     *
     * @param element the element to find the representative for.
     * @return the representative element of the set.
     */
    public E getRepresentative(E element);

    /**
     * unions the sets that have the given elements.
     * after the union, the two elements will be in the same set.
     *
     * @param e1 the first element.
     * @param e2 the second element.
     * @return true if the union was successful, false if not (if the elements aren't in the disjoint set).
     */
    public boolean union(E e1, E e2);
}
