package main;

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
        // TODO: precondition for evaluation is that this (operator) node is surrounded
        // TODO: by numerical nodes. Check and throw runtime exceptions if these are not true.
        // TODO: Otherwise get the two numerical (operand) nodes, execute the operator and
        // TODO: return the result as a new numerical node.
        return null;
    }

}
