package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.NumNode;
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
        // Create a set of tests in the form of a double array in which each
        // row contains the operand-operator-operand sequence, followed by the expected result or
        // the exception message if any is expected.
        String[][] tests = {
                // operand1, operator, "operand2", expected_result, expected_exception_message
                { "1", "+", "2", "3", null },
                { "1", "-", "2", "-1", null },
                { "3", "*", "4", "12", null },
                { "4", "/", "-5", "-0.8", null },
                { "7", "%", "5", "2", null },
                { "4", "^", "2", "16", null },
                { "8", "/", "0", null,"Division by zero" }
        };
        
        // Run through each of the tests, create necessary nodes, evaluate the operator node
        // and verify the node being returned is the actual result.
        for (String[] test : tests) {
            NumNode n1 = NumNode.createNode(test[0]);
            OpNode op = OpNode.createNode(test[1]);
            NumNode n2 = NumNode.createNode(test[2]);
            n1.addNext(op);
            op.addNext(n2);
            try {
                NumNode result = op.evaluate();
                assertNotNull(result);
                double expectedResult = Double.parseDouble(test[3]);
                double actualResult = result.getNumValue();
                assertEquals(expectedResult, actualResult);
            } catch (RuntimeException e) {
                assertNull(test[3]);
                assertEquals(test[4], e.getMessage());
            }
        }
    }
}
