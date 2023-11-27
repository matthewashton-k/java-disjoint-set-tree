package comprehensive;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisjointSetForesttest {
    @Test
    public void testMakeSet() {
        DisjointSetForest<Integer> set = new DisjointSetForest<Integer>();
        set.makeSet(1);
        set.makeSet(1);
        set.makeSet(2);
        set.makeSet(3);
        set.makeSet(4);

        assertEquals(1, (int)set.getRepresentative(1));
        assertEquals(1, (int)set.getRepresentative(1));
        assertEquals(2, (int)set.getRepresentative(2));
        assertEquals(3, (int)set.getRepresentative(3));
        assertEquals(4, (int)set.getRepresentative(4));
    }

    @Test
    public void bigTest() {
        DisjointSetForest<Integer> set = new DisjointSetForest<Integer>();
        Set<Set<Integer>> setset = new HashSet<>();
        Random ran = new Random(1239);
        for (int n = 0; n < 600; n += 1) {
            if (n % 100 == 0) {
                System.out.println(n);
            }

            setset.clear();
            set = new DisjointSetForest<>();

            for (int i = 0; i < n; i += 1) {
                int a = ran.nextInt();
                Set<Integer> s = new HashSet<Integer>();
                s.add(a);
                setset.add(s);
                set.makeSet(a);
            }

            for (int k = 0; k < 10; k += 1) {
                for (int i = 0; i < n/3; i += 1) {
                    int a = ran.nextInt();
                    Set<Integer> s = new HashSet<Integer>();
                    s.add(a);
                    setset.add(s);
                    set.makeSet(a);
                }
                for (int i = 0; i < n/3; i += 1) {
                    int a = ran.nextInt();
                    int b = ran.nextInt();
                    Set as = null;
                    Set bs = null;
                    for (Set q : setset) {
                        if (q.contains(a)) {
                            as = q;
                        }
                        if (q.contains(b)) {
                            bs = q;
                        }
                    }
                    if (as != null && bs != null) {
                        setset.remove(bs);
                        as.addAll(bs);
                    }
                    set.union(a, b);
                }
            }

            for (Set q : setset) {
                Integer myInt = (Integer) q.toArray()[0];
                myInt = set.getRepresentative(myInt);
                for (Object i : q) {
                    assertEquals(myInt, set.getRepresentative((Integer) i));
                }
            }
        }
    }
}
