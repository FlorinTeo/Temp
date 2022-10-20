package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.OpNode;
import main.OpNode.OpCode;

class OpNode_tests {

    @Test
    void test_createNode() {
        // Create OpNodes for each of the supported operators (i.e. "+",..)
        // and verify they reflect back the the correct operator code (i.e. OpCode.ADDITION)
        OpNode op = OpNode.createNode("^");
        assertNotNull(op);
        assertEquals(OpCode.POWER, op.getOpCode());
        
        op = OpNode.createNode("*");
        assertNotNull(op);
        assertEquals(OpCode.MULTIPLICATION, op.getOpCode());
        
        op = OpNode.createNode("/");
        assertNotNull(op);
        assertEquals(OpCode.DIVISION, op.getOpCode());
        
        op = OpNode.createNode("%");
        assertNotNull(op);
        assertEquals(OpCode.MODULO, op.getOpCode());
        
        op = OpNode.createNode("+");
        assertNotNull(op);
        assertEquals(OpCode.ADDITION, op.getOpCode());
        
        op = OpNode.createNode("-");
        assertNotNull(op);
        assertEquals(OpCode.SUBTRACTION, op.getOpCode());
        
        op = OpNode.createNode("abc");
        assertNull(op);
        
        op = OpNode.createNode("#");
        assertNull(op);
    }
    
    @Test
    void test_evaluate() {
        // TODO: Create a valid sequence of NumNode, OpNode, NumNode, chain them in a linked list
        // TODO: And verify the result of the evaluation of the operator node is as expected.
        fail("Test not implemented");
    }
}
