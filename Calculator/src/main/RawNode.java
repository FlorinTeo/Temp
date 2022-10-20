package main;

public class RawNode {
    
    /**
     * Class fields:
     * TODO: Raw content in this node, as a string.
     * TODO: References to the previous and next node, or null respectively if nodes do not exist. 
     */
    private String _data;
    private RawNode _next;
    private RawNode _prev;
    
    /**
     * Class constructor. Builds a new generic raw content node.
     * @param rawContent - the raw content stored in this node.
     */
    protected RawNode(String rawContent) {
        // TODO: configures / initializes class fields.
        _data = rawContent;
    }
    
    /**
     * Node factory method, returning a new node for the given raw content
     * or null if the node could not be created.
     * @param rawContent - the row content stored in this node.
     * @return the new node.
     */
    public static RawNode createNode(String rawContent) {
        return new RawNode(rawContent);
    }
    
    /**
     * Gets the node's raw content.
     * @return the raw content.
     */
    public String getRawContent() {
        // TODO: returns the raw content
        return _data;
    }
    
    /**
     * Gets the previous node.
     * @return the reference to the previous node, or null if none exists.
     */
    public RawNode getPrev() {
        // TODO: returns the reference to the previous node
        return _prev;
    }
    
    /**
     * Gets the next node.
     * @return the reference to the next node, or null if none exists.
     */
    public RawNode getNext() {
        // TODO: returns the reference to the next node
        return _next;
    }
    
    /**
     * Adds another node right after this node.
     * @param other - the node to be added.
     * @return the node that's been added.
     */
    public RawNode addNext(RawNode other) {
        // TODO: code inserting the other node after this node
        other._next = _next;
        other._prev = this;
        // there may be no node following this node (this._next may be null!)
        if (_next != null) {
            _next._prev = other;
        }
        _next = other;
        return other;
    }
    
    /**
     * Adds another node to the tail of the list.
     * @param other - the node to be added.
     * @return the new tail of the list.
     */
    public RawNode addTail(RawNode other) {
        // TODO: code inserting the other node at the very end of
        // TODO: the list where this node is part of.
        
        // if there's no node following the one we're in (this) we just 
        // make use of addNext method
        if (_next == null) {
            return addNext(other);
        }
        // otherwise, we pass the task to "add other node to the end of the list"
        // to the node that follows (recursive approach)
        return _next.addTail(other);
        
        /*// other way of doing it (iteratively)
         * RawNode tail = this;
         * while (tail._next != null) {
         *     tail = tail._next;
         * }
         * return tail.addNext(other);
         */
    }
}
