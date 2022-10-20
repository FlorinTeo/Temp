package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.NumNode;

class NumNode_tests {

    @Test
    void test_createNode() {
        // Create a few numerical nodes with various correct numerical values given strings
        // and verify they reflect their content correctly as a double value.
        // Try create a numerical node with an invalid string and
        // verify the result is a null reference.
        NumNode n = NumNode.createNode("123");
        assertNotNull(n);
        assertEquals(123.0, n.getNumValue());
        n = NumNode.createNode("abc");
        assertNull(n);
        n = NumNode.createNode("-123.5");
        assertNotNull(n);
        assertEquals(-123.5, n.getNumValue());
    }

}
