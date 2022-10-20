package main;

public class NumNode extends RawNode {
    
    /**
     * Class fields:
     * Numerical content (double) of this node
     */
    private double _value;

    /**
     * Class constructor. Builds a new numerical node.
     * @param rawContent - the raw content stored in this node.
     */
    protected NumNode(String rawContent) {
        // Initializes the raw content of this node
        super(rawContent);
        // Constructor is protected, so an instance of this class
        // can be created only via the createNode method which verifies that
        // rawContent can be successfully parsed to a double.
        _value = Double.parseDouble(rawContent);
    }
    
    /**
     * NumNode factory method, returning a new numerical node 
     * for the given raw content or null if the node could not be created.
     * @param rawContent - the raw content stored in this node.
     * @return the new numerical node.
     */
    public static NumNode createNode(String rawContent) {
        // Tries to parse the raw content into a double value
        // if successful, creates a NumNode for it and save the value within,
        // otherwise returns null 
        double value;
        try {
            value = Double.parseDouble(rawContent);
        } catch (RuntimeException e) {
            return null;
        }
        
        return new NumNode(rawContent);
    }
    
    /**
     * Gets the node's numerical value.
     * @return
     */
    public double getNumValue() {
        // returns the numerical (double) content
        return _value;
    }
}
