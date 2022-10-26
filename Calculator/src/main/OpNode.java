package main;

import java.text.DecimalFormat;

public class OpNode extends RawNode {
    
    public enum OpCode {
        UNKNOWN,
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        POWER,
        MODULO
    }
    
    /**
     * Class fields:
     * Operator code for this node
     */
    private OpCode _operator = OpCode.UNKNOWN;
    
    /**
     * Class constructor. Builds a new operator node.
     * @param rawContent - the raw content stored in this node.
     */
    protected OpNode(String rawContent) {
        super(rawContent);
        // UNKNOWN initial value for this node's operator code
        // since the raw content is not parsed.
        switch(rawContent) {
        case "^":
            _operator = OpCode.POWER;
            break;
        case "*":
            _operator = OpCode.MULTIPLICATION;
            break;
        case "/":
            _operator = OpCode.DIVISION;
            break;
        case "%":
            _operator = OpCode.MODULO;
            break;
        case "+":
            _operator = OpCode.ADDITION;
            break;
        case "-":
            _operator = OpCode.SUBTRACTION;
            break;
        }
    }
    
    /**
     * OpNode factory method, returning a new operator node 
     * for the given raw content or null if the node could not be created.
     * @param rawContent - the raw content stored in this node.
     * @return the new operator node.
     */
    public static OpNode createNode(String rawContent) {
        // Tries to parse the raw content into a OpCode value
        // if successful, creates an OpNode for it and save the operator code value within,
        // otherwise returns null 
        String allOperators = "^*/%+-";
        if (rawContent.length() != 1 || allOperators.indexOf(rawContent) == -1) {
            return null;
        }
        
        return new OpNode(rawContent);
    }
    
    /**
     * Gets the node's operator code.
     * @return - the operator code.
     */
    public OpCode getOpCode() {
        // returns the operator code (OpCode)
        return _operator;
    }
    
    /**
     * Evaluates this operator node, returning the result of the evaluation
     * as a new numerical node.
     * @return the result as a new numerical node.
     */
    public NumNode evaluate() {
        // Precondition for evaluation is that this (operator) node is surrounded by two valid nodes.
        if (_prev == null || _next == null) {
            throw new RuntimeException("Missing operands for operator: " + _data);
        }
        
        // The two nodes should be of NumNode type
        if (!(_prev instanceof NumNode) || !(_next instanceof NumNode)) {
            throw new RuntimeException("Wrong operands for operator: " + _data);
        }
        
        // Now everything is verified. This operator has its operands so we can go ahead and evalute it.
        NumNode num1 = (NumNode)_prev;
        NumNode num2 = (NumNode)_next;
        
        double result;
        switch(_operator) {
        case POWER:
            result = Math.pow(num1.getNumValue(), num2.getNumValue());
            break;
        case MULTIPLICATION:
            result = num1.getNumValue() * num2.getNumValue();
            break;
        case DIVISION:
            if (num2.getNumValue() == 0) {
                throw new RuntimeException("Division by zero");
            }
            result = num1.getNumValue() / num2.getNumValue();
            break;
        case MODULO:
            result = num1.getNumValue() % num2.getNumValue();
            break;
        case ADDITION:
            result = num1.getNumValue() + num2.getNumValue();
            break;
        case SUBTRACTION:
            result = num1.getNumValue() - num2.getNumValue();
            break;
        default:
            throw new RuntimeException("Invalid operator node " + _operator);
        }
        
        // We have the result as a double. We need to convert it to a nicely formatted string
        // such that we can create a NumNode for it which is what we return.
        DecimalFormat df = new DecimalFormat("#0.######");
        String resultString = df.format(result);
        return NumNode.createNode(resultString);
    }
}
