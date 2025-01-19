package algorithms.linked;

import algorithms.AbstractListTestCase;
import algorithms.LinkedList;
import algorithms.List;

public class LinkedListTest extends AbstractListTestCase {
    @Override
    protected List createList() {
        return new LinkedList();
    }
}
