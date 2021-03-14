/**
 *
 */
package textgen;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

    private static final int LONG_LIST_LENGTH = 10;

    MyLinkedList<String> shortList;
    MyLinkedList<Integer> emptyList;
    MyLinkedList<Integer> longerList;
    MyLinkedList<Integer> list1;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // Feel free to use these lists, or add your own
        shortList = new MyLinkedList<>();
        shortList.add("A");
        shortList.add("B");
        emptyList = new MyLinkedList<>();
        longerList = new MyLinkedList<>();
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            longerList.add(i);
        }
        list1 = new MyLinkedList<Integer>();
        list1.add(65);
        list1.add(21);
        list1.add(42);

    }

    /** Test if the get method is working correctly.
     */
    /*You should not need to add much to this method.
     * We provide it as an example of a thorough test. */
    @Test
    public void testGet() {
        //test empty list, get should throw an exception
        try {
            emptyList.get(0);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        // test short list, first contents, then out of bounds
        assertEquals("Check first", "A", shortList.get(0));
        assertEquals("Check second", "B", shortList.get(1));

        try {
            shortList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            shortList.get(2);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        // test longer list contents
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
        }

        // test off the end of the longer array
        try {
            longerList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            longerList.get(LONG_LIST_LENGTH);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {
        }

    }

    /** Test removing an element from the list.
     * We've included the example from the concept challenge.
     * You will want to add more tests.  */
    @Test
    public void testRemove() {
        int a = list1.remove(0);
        assertEquals("Remove: check a is correct ", 65, a);
        assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
        assertEquals("Remove: check size is correct ", 2, list1.size());

        try {
            list1.remove(-10);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            list1.remove(100);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        list1.add(0, 500);
        int b = list1.remove(0);
        assertEquals("Remove: check a is correct ", 500, b);
    }

    /** Test adding an element into the end of the list, specifically
     *  public boolean add(E element)
     * */
    @Test
    public void testAddEnd() {
        list1.add(1);
        assertEquals("Invalid Size", 1, list1.get(list1.size - 1).intValue());

        try {
            list1.add(null);
            fail("Check out of bounds");
        } catch (NullPointerException e) {

        }
    }

    /** Test the size of the list */
    @Test
    public void testSize() {
        int startSize = list1.size;
        list1.add(1);
        assertEquals("Invalid Size", startSize + 1, list1.size);
    }

    /** Test adding an element into the list at a specified index,
     * specifically:
     * public void add(int index, E element)
     * */
    @Test
    public void testAddAtIndex() {
        list1.add(0, 10);
        assertEquals("Invalid Size", 10, list1.get(0).intValue());

        list1.add(list1.size / 2, 5);
        assertEquals("Invalid Size", 5, list1.get(list1.size / 2).intValue());

        try {
            list1.add(-10, 0);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            list1.add(1000, 0);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            list1.add(0, null);
            fail("Check out of bounds");
        } catch (NullPointerException e) {

        }
    }

    /** Test setting an element in the list */
    @Test
    public void testSet() {
        list1.set(0, 10);
        assertEquals("Invalid Size", 10, list1.get(0).intValue());

        try {
            list1.set(-1, 10);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            list1.set(100, 10);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            list1.set(0, null);
            fail("Check out of bounds");
        } catch (NullPointerException e) {

        }

    }
}
