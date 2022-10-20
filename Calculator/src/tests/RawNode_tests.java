package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import main.RawNode;

class RawNode_tests {

    @Test
    void test_createRawNode() {
        // TODO: Create a new RawNode
        // TODO: and verify the content is as expected
        // TODO: and that its links are null.
        RawNode n = RawNode.createNode("abc");
        assertNotNull(n);
        assertEquals("abc", n.getRawContent());
        assertNull(n.getNext());
        assertNull(n.getPrev());
        
        //fail("Test not implemented");
    }
    
    @Test
    void test_addNext() {
        // TODO: Create two nodes, n1 and n2, add one next to the other and
        // TODO: verify their links are pointing to their respective nodes.
        // TODO: Create a third node n3, add it after n1 and verify its links
        // TODO: are pointing to n1 and n2 respectively, and that
        // TODO: the n1 and n2 links were adjusted accordingly as well.
        RawNode n1 = RawNode.createNode("first");
        RawNode n2 = RawNode.createNode("second");
        
        n1.addNext(n2);
        assertEquals(n2, n1.getNext());
        assertEquals(n1, n2.getPrev());
        assertNull(n2.getNext());
        
        RawNode n3 = RawNode.createNode("middle");
        n1.addNext(n3);
        assertEquals(n3, n1.getNext());
        assertEquals(n3, n2.getPrev());
        assertEquals(n1, n3.getPrev());
        assertEquals(n2, n3.getNext());
        
        //fail("Test not implemented");
    }
    
    @Test
    void test_addTail() {
        // TODO: Create three nodes, n1, n2 and n3, add them into a three nodes list
        // TODO: then create a fourth node n, adding it to the tail of the list pointed by n1.
        // TODO: verify n links are pointing to n3 and null, while n3 next link is now pointing to n.
        
        RawNode n1 = RawNode.createNode("first");
        RawNode n2 = RawNode.createNode("second");
        RawNode n3 = RawNode.createNode("third");
        
        n1.addTail(n2);
        n1.addTail(n3);
        
        // list is n1 > n2 > n3
        assertEquals(n2, n1.getNext());
        assertEquals(n1, n2.getPrev());
        assertEquals(n3, n2.getNext());
        assertEquals(n2, n3.getPrev());
        assertNull(n3.getNext());
        
        RawNode n = RawNode.createNode("inserted node");
        n1.addTail(n);
        
        // list is now n1 > n2 > n3 > n
        assertEquals(n3, n.getPrev());
        assertNull(n.getNext());
        assertEquals(n, n3.getNext());
        
        //fail("Test not implemented");
    }
}
