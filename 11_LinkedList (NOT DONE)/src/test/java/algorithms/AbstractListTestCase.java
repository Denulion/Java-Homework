package algorithms;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractListTestCase {

    protected static final Object VALUE_A = "A";
    protected static final Object VALUE_B = "B";
    protected static final Object VALUE_C = "C";

    protected abstract List createList();

    @Test
    public void insertIntoEmptyList() {
        List list = createList();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        list.insert(0, VALUE_A);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(VALUE_A, list.get(0));
    }

    @Test
    public void insertBetweenElements() {
        List list = createList();
        list.insert(0, VALUE_A);
        list.insert(1, VALUE_B);
        list.insert(1, VALUE_C);
        assertEquals(3, list.size());
        assertSame(VALUE_A, list.get(0));
        assertSame(VALUE_C, list.get(1));
        assertSame(VALUE_B, list.get(2));
    }

    @Test
    public void insertBeforeFirstElement() {
        List list = createList();
        list.insert(0, VALUE_A);
        list.insert(0, VALUE_B);
        assertEquals(2, list.size());
        assertSame(VALUE_B, list.get(0));
        assertSame(VALUE_A, list.get(1));
    }

    @Test
    public void insertAfterLastElement() {
        List list = createList();
        list.insert(0, VALUE_A);
        list.insert(1, VALUE_B);
        assertEquals(2, list.size());
        assertSame(VALUE_A, list.get(0));
        assertSame(VALUE_B, list.get(1));
    }

    @Test
    public void insertOutOfBounds() {
        List list = createList();
        try {
            list.insert(-1, VALUE_A);
            fail();
        } catch (IndexOutOfBoundsException e) {
// expected
        }
        try {
            list.insert(1, VALUE_B);
            fail();
        } catch (IndexOutOfBoundsException e) {
// expected
        }
    }

    @Test
    public void add() {
        List list = createList();
        list.add(VALUE_A);
        list.add(VALUE_C);
        list.add(VALUE_B);
        assertEquals(3, list.size());
        assertSame(VALUE_A, list.get(0));
        assertSame(VALUE_C, list.get(1));
        assertSame(VALUE_B, list.get(2));
    }

    @Test
    public void set() {
        List list = createList();
        list.insert(0, VALUE_A);
        assertSame(VALUE_A, list.get(0));
        assertSame(VALUE_A, list.set(0, VALUE_B));
        assertSame(VALUE_B, list.get(0));
    }

    @Test
    public void getOutOfBounds() {
        List list = createList();
        try {
            list.get(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
// expected
        }
        try {
            list.get(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
// expected
        }
        list.add(VALUE_A);
        try {
            list.get(1);
            fail();
        } catch (IndexOutOfBoundsException e) {
// expected
        }
    }

    @Test
    public void setOutOfBounds() {
        List list = createList();
        try {
            list.set(-1, VALUE_A);
            fail();
        } catch (IndexOutOfBoundsException e) {
// expected
        }
        try {
            list.set(0, VALUE_B);
            fail();
        } catch (IndexOutOfBoundsException e) {
// expected
        }
        list.insert(0, VALUE_C);
        try {
            list.set(1, VALUE_C);
            fail();
        } catch (IndexOutOfBoundsException e) {
// expected
        }
    }

    @Test
    public void deleteOnlyElement() {
        List list = createList();
        list.add(VALUE_A);
        assertEquals(1, list.size());
        assertSame(VALUE_A, list.get(0));
        assertSame(VALUE_A, list.delete(0));
        assertEquals(0, list.size());
    }

    @Test
    public void deleteFirstElement() {
        List list = createList();
        list.add(VALUE_A);
        list.add(VALUE_B);
        list.add(VALUE_C);
        assertEquals(3, list.size());
        assertSame(VALUE_A, list.get(0));
        assertSame(VALUE_B, list.get(1));
        assertSame(VALUE_C, list.get(2));
        assertSame(VALUE_A, list.delete(0));
        assertEquals(2, list.size());
        assertSame(VALUE_B, list.get(0));
        assertSame(VALUE_C, list.get(1));
    }

    @Test
    public void deleteLastElement() {
        List list = createList();
        list.add(VALUE_A);
        list.add(VALUE_B);
        list.add(VALUE_C);
        assertEquals(3, list.size());
        assertSame(VALUE_A, list.get(0));
        assertSame(VALUE_B, list.get(1));
        assertSame(VALUE_C, list.get(2));
        assertSame(VALUE_C, list.delete(2));
        assertEquals(2, list.size());
        assertSame(VALUE_A, list.get(0));
        assertSame(VALUE_B, list.get(1));
    }

    @Test
    public void deleteMiddleElement() {
        List list = createList();
        list.add(VALUE_A);
        list.add(VALUE_C);
        list.add(VALUE_B);
        assertEquals(3, list.size());
        assertSame(VALUE_A, list.get(0));
        assertSame(VALUE_C, list.get(1));
        assertSame(VALUE_B, list.get(2));
        assertSame(VALUE_C, list.delete(1));
        assertEquals(2, list.size());
        assertSame(VALUE_A, list.get(0));
        assertSame(VALUE_B, list.get(1));
    }

    @Test
    public void deleteOutOfBounds() {
        List list = createList();
        try {
            list.delete(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            list.delete(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
// expected
        }
    }

    @Test
    public void deleteByValue() {
        List list = createList();
        list.add(VALUE_A);
        list.add(VALUE_B);
        list.add(VALUE_A);
        assertEquals(3, list.size());
        assertSame(VALUE_A, list.get(0));
        assertSame(VALUE_B, list.get(1));
        assertSame(VALUE_A, list.get(2));
        assertTrue(list.delete(VALUE_A));
        assertEquals(2, list.size());
        assertSame(VALUE_B, list.get(0));
        assertSame(VALUE_A, list.get(1));
        assertTrue(list.delete(VALUE_A));
        assertEquals(1, list.size());
        assertSame(VALUE_B, list.get(0));
        assertFalse(list.delete(VALUE_C));
        assertEquals(1, list.size());
        assertSame(VALUE_B, list.get(0));
        assertTrue(list.delete(VALUE_B));
        assertEquals(0, list.size());
    }

    @Test
    public void emptyIteration() {
        List list = createList();
        Iterator iterator = list.iterator();
        assertTrue(iterator.isDone());
        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
// expected
        }
    }

    @Test
    public void forwardIteration() {
        List list = createList();
        list.add(VALUE_A);
        list.add(VALUE_B);
        list.add(VALUE_C);
        Iterator iterator = list.iterator();
        iterator.first();
        assertFalse(iterator.isDone());
        assertSame(VALUE_A, iterator.current());
        iterator.next();
        assertFalse(iterator.isDone());
        assertSame(VALUE_B, iterator.current());
        iterator.next();
        assertFalse(iterator.isDone());
        assertSame(VALUE_C, iterator.current());
        iterator.next();
        assertTrue(iterator.isDone());
        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
// expected
        }
    }

    @Test
    public void reverseIteration() {
        List list = createList();
        list.add(VALUE_A);
        list.add(VALUE_B);
        list.add(VALUE_C);
        Iterator iterator = list.iterator();
        iterator.last();
        assertFalse(iterator.isDone());
        assertSame(VALUE_C, iterator.current());
        iterator.previous();
        assertFalse(iterator.isDone());
        assertSame(VALUE_B, iterator.current());
        iterator.previous();
        assertFalse(iterator.isDone());
        assertSame(VALUE_A, iterator.current());
        iterator.previous();
        assertTrue(iterator.isDone());
        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
// expected
        }
    }

    @Test
    public void indexOf() {
        List list = createList();
        list.add(VALUE_A);
        list.add(VALUE_B);
        list.add(VALUE_A);
        assertEquals(0, list.indexOf(VALUE_A));
        assertEquals(1, list.indexOf(VALUE_B));
        assertEquals(-1, list.indexOf(VALUE_C));
    }

    @Test
    public void contains() {
        List list = createList();
        list.add(VALUE_A);
        list.add(VALUE_B);
        list.add(VALUE_A);
        assertTrue(list.contains(VALUE_A));
        assertTrue(list.contains(VALUE_B));
        assertFalse(list.contains(VALUE_C));
    }

    @Test
    public void clear() {
        List list = createList();
        list.add(VALUE_A);
        list.add(VALUE_B);
        list.add(VALUE_C);
        assertFalse(list.isEmpty());
        assertEquals(3, list.size());
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

}
