package com.jjson.common.util.redblacktree;

/**
 * @author jiangjunshen
 * @date 10:56 AM 2019/4/24
 */
public class RedBlackTree<T extends Comparable<? super T>> {
    
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    
    public void insert(T node) {
        
    }
    
    public void delete(T node) {
        
    }
    
    public void get(T node) {
        
    }
    
    private class Node<T> {
        private T value;
        private Node parent;
        private Node left;
        private Node right;
        private boolean color;
    }
}
