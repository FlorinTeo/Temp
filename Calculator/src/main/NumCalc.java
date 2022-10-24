package main;

import main.OpNode.OpCode;

public class NumCalc {
    
    // Operators precedence table, as a double array of operator codes (OpNode.OpCode).
    private OpNode.OpCode[][] _opPrecedence = {
            {OpCode.POWER},
            {OpCode.MULTIPLICATION, OpCode.DIVISION, OpCode.MODULO},
            {OpCode.ADDITION, OpCode.SUBTRACTION},
    };
    
    // Expression list: a double-linked list containing the numbers and operators to be evaluated.
    private RawNode _exprList;
    
    // Tracing list: a double-linked list containing raw nodes, each holding a trace frame as a string.
    private RawNode _tracesList;
    
    /**
     * Class constructor.
     */
    public NumCalc() {
        _exprList = null;
        _tracesList = null;
    }
    
    /**
     * Takes the expression strings and builds the internal
     * double-linked list storing the expression nodes.
     * @param exprStrings - components of the expression string 
     */
    private void buildExprList(String[] exprStrings) {
        // For each string in the expression...
        for(String exprString : exprStrings) {
            // ...try creating a numerical node or an operator node.
            RawNode node = OpNode.createNode(exprString);
            if (node == null) {
                node = NumNode.createNode(exprString);
            }
            
            // If neither were successful, the expression is invalid, token cannot be parsed
            if (node == null) {
                throw new RuntimeException("Unrecognized token '" + exprString + "'");
            }
            
            // here we have a valid node. Add it to the list, either as the first
            // node or at the tail of the list as we have it already.
            if (_exprList == null) {
                _exprList = node;
            } else {
                _exprList.addTail(node);
            }
        }
    }
    
    /**
     * Evaluates the expression stored in the double linked list
     * and returns the final result. 
     * @return - evaluation result.
     */
    private String evalExprList() {
        // TODO: For each row of operators in the precedence table,
        // TODO: Scan the expression lists and check each operator node.
        // TODO: If the operator code is found in the row of operators,
        // TODO: evaluate it, and replace the operator node and its operands
        // TODO: with the node for the result. After each such reduction of the list
        // TODO: add the entire expression list as a frame into the trace frames list.
        // TODO: When all loops are done, expression list should contain only one numerical node,
        // TODO: which is the final result. Method returns the numerical value, as a string.
        return "";
    }
    
    /**
     * Constructs a new trace frame string from the expression list
     * and adds it to the trace list.
     */
    private void addTraceFrame() {
        // Builds a trace frame from the expression list:
        String trace = "[";
        
        // Goes through the expression list nodes and adds their raw content
        // to a string accumulator, separated by one space character.
        RawNode crtNode = _exprList;
        while(crtNode != null) {
            trace += crtNode.getRawContent();
            if (crtNode.getNext() != null) {
                trace += " ";
            }
            crtNode = crtNode.getNext();
        }
        trace += "]";
        
        // The resulting string is added as a new raw node to the tail of the tracing list.
        RawNode traceNode = new RawNode(trace);
        if (_tracesList == null) {
            _tracesList = traceNode;
        } else {
            _tracesList.addTail(traceNode);
        }
    }
    
    /**
     * Takes a raw expression and returns the final evaluation result.
     * @param expression as entered by the user.
     * @return - evaluation result.
     */
    public String evaluate(String expression) {
        // Resets the expression list and the tracing list,
        _exprList = null;
        _tracesList = null;
        
        // Splits the expression string around the " " character, into its individual parts
        String[] exprParts = expression.split(" ");
        
        // Builds the expression list,
        buildExprList(exprParts);
        
        // Evaluates the expression and returns the result of the evaluation.
        return evalExprList();
    }
    
    /**
     * Gives the evaluation trace of the last evaluated expression,
     * as a multi-line string.
     */
    @Override
    public String toString() {
        // Goes through the tracing list nodes adding the node raw content to
        // an accumulator string, separated by "\n" (each trace frame on a new line)
        String output = "";
        RawNode crtTrace = _tracesList;
        while(crtTrace != null) {
            output += crtTrace.getRawContent() + "\n";
            crtTrace = crtTrace.getNext();
        }
        
        // in the end return the accumulated result.
        return output;
    }
}
