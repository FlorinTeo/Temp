package main;

import java.util.Arrays;

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
        // scan each row in the operators precedence table
        for (OpNode.OpCode[] ops: _opPrecedence) {
            // scan each node in the expression list
            for(RawNode node = _exprList; node != null; node = node._next) {
                // skip nodes which are not operators
                if (!(node instanceof OpNode)) {
                    continue;
                }
                
                OpNode opNode = (OpNode)node;
                
                // skip operator nodes which are not in the current precedence row
                if (!Arrays.asList(ops).contains(opNode.getOpCode())) {
                    continue;
                }
                
                // evaluate the operator
                NumNode resultNode = opNode.evaluate();
                
                // since evaluation succeeded, it means operator
                // has valid _prev and _next numerical operand nodes.
                
                // set the _prev, _next link of the new result node
                resultNode._prev = opNode._prev._prev;
                resultNode._next = opNode._next._next;
                
                // link the new result node into the list
                if (resultNode._prev != null) {
                    resultNode._prev._next = resultNode;
                } else {
                    _exprList = resultNode;
                }
                if (resultNode._next != null) {
                    resultNode._next._prev = resultNode;
                }
                
                // since an operator has just been evaluated and the
                // expression list changed, add a trace frame.
                addTraceFrame();
            }
        }
        
        // at this point the entire list should be reduced to one element only, which is the expression result.
        return _exprList.getRawContent();
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
