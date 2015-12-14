import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by rybkinrolla on 02.12.2015.
 */
public class HashSetTest {
    private HashSet testHashSet;
    private final Object testObject = new Object();
    private final Object testObjectOther = new Object();

    @Before
    public void initializeHashSet() {
        testHashSet = new HashSet();
    }

    @Test
    public void shouldAddElementToHashSet() {
        testHashSet.add(testObject);
        assertTrue(testHashSet.contains(testObject));
    }

    @Test
    public void shouldRemoveElementFromHashSet() {
        testHashSet.add(testObject);
        testHashSet.add(testObjectOther);
        testHashSet.remove(testObject);
        assertFalse(testHashSet.contains(testObject));
    }

    @Test
    public void shouldClearHashSet() {
        testHashSet.add(testObject);
        HashSet hashSetNew = new HashSet();
        testHashSet.clear();
        assertEquals(testHashSet, hashSetNew);
    }

    @Test
    public void shouldContainUniqueValues() {
        testHashSet.add(testObject);
        testHashSet.add(testObjectOther);
        testHashSet.add(testObject);
        testHashSet.add(testObjectOther);
        assertTrue(testHashSet.size() == 2);
    }

    @Test
    public void shouldBeCorrectlyCloned() {
        testHashSet.add(testObject);
        testHashSet.add(testObjectOther);
        HashSet hashSetNew = (HashSet) testHashSet.clone();
        assertEquals(testHashSet, hashSetNew);
    }

    @Test
    public void shouldBeRightIterated() {
        testHashSet.add(testObject);
        testHashSet.add(testObjectOther);
        Iterator it = testHashSet.iterator();
        int counter = 0;
        while(it.hasNext()){
            it.next();
            counter++;
        }
        assertEquals(counter,2);
    }

    @Test
    public void shouldBeEmptyAfterCreated() {
        assertTrue(testHashSet.isEmpty());
    }

    @Test
    public void shouldBeRightSized() {
        testHashSet.add(testObject);
        testHashSet.add(testObjectOther);
        assertTrue(testHashSet.size() == 2);
    }

}
