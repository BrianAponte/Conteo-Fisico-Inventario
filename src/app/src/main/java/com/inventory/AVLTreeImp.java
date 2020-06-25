package com.inventory;

/**
 * Self Balancing binary tree
 * @author slondonoq
 * @param <T> the type of the elements in the tree (must implement Comparable)
 */


public class AVLTreeImp<T extends Comparable<T>>{
    private class AVLNode {
        T data;
        AVLNode parent;
        AVLNode leftChild;
        AVLNode rightChild;
        int height;

        public AVLNode(T data) {
            this.data = data;
            parent = null;
            leftChild =null;
            rightChild = null;
            height = 1;
        }
    }
    private AVLNode root;
    /**
     * Initializes an AVL tree of the type specified inside the 
     * diamond brackets (must implement Comparable<T>) 
     */
    public AVLTreeImp() {
        root = null;
    }
    /**
     * Adds an object to the tree
     * @param data data to be added
     */
    public void insert(T data) {
        AVLNode newNode = new AVLNode(data);
        if(root==null) {
            root = newNode;
        }
        else {
            AVLNode posParent = find(data);
            if(posParent.data.compareTo(data)>0) {
                posParent.leftChild = newNode;
                newNode.parent = posParent;
                if(data.compareTo(root.data)>0) {
                    updateRHeight();
                }
                else {
                    updateLHeight();
                }
                balance(posParent);
            }
            else if(posParent.data.compareTo(data)<0) {
                posParent.rightChild = newNode;
                newNode.parent = posParent;
                if(data.compareTo(root.data)>0) {
                    updateRHeight();
                }
                else {
                    updateLHeight();
                }
                balance(posParent);
            }
        }
    }
    /**
     * Looks for a specified element in the tree, if it's not in it
     * returns the node that would be it's parent.
     * @param data item to look for
     * @return node that either contains the specified element or would be the 
     * parent of that node
     */
    public AVLNode find(T data) {
        if(root==null) {
            return root;
        }

        AVLNode currentNode = root;
        while(currentNode.leftChild!=null || currentNode.rightChild!=null) {
            if(currentNode.data.compareTo(data)==0) {
                return currentNode;
            }
            else if(currentNode.data.compareTo(data)>0) {
                if(currentNode.leftChild==null) {
                    return currentNode;
                }
                else {
                    currentNode = currentNode.leftChild;
                }
            }
            else {
                if(currentNode.rightChild==null) {
                    return currentNode;
                }
                else {
                    currentNode = currentNode.rightChild;
                }
            }      
        }
        return currentNode;
    }

    private void updateHeights(AVLNode node) {
        if(node.leftChild==null) {
            if(node.rightChild ==null) {
                node.height=1;
            }
            else {
                updateHeights(node.rightChild);
                node.height = node.rightChild.height+1;
            }
        }
        else if(node.rightChild ==null) {
            updateHeights(node.leftChild);
            node.height = node.leftChild.height+1;
        }
        else {
            updateHeights(node.leftChild);
            updateHeights(node.rightChild); 
            node.height = Math.max(node.leftChild.height, node.rightChild.height)+1;
        }
    }

    private void updateLHeight() {
        updateHeights(root.leftChild);
        if(root.rightChild==null) {
            root.height = root.leftChild.height+1;
        }
        else {
            root.height = Math.max(root.rightChild.height, root.leftChild.height)+1;
        }
    }

    private void updateRHeight() {
        updateHeights(root.rightChild);
        if(root.leftChild==null) {
            root.height = root.rightChild.height+1;
        }
        else {
            root.height = Math.max(root.rightChild.height, root.leftChild.height)+1;
        }
    }

    private int calcBalanceFactor(AVLNode node) {
        if(node.height==1) {return 0;}

        if(node.leftChild!=null&&node.rightChild!=null) {
            return Math.abs(node.rightChild.height-node.leftChild.height);
        }

        if(node.leftChild==null) {
            return node.rightChild.height;
        }

        return node.leftChild.height;
    }

    private void balance(AVLNode nodeFrom) {
        boolean balanced = false;
        while(!balanced && nodeFrom!= null){
            int balanceFactor = calcBalanceFactor(nodeFrom);
            if(balanceFactor>1) {
                String bCase = getCase(nodeFrom);
                switch (bCase){
                    case "LL":
                        rightRotation(nodeFrom.leftChild);
                        break;
                    case "RR":
                        leftRotation(nodeFrom.rightChild);
                        break;
                    case "RL":
                        leftRotation(nodeFrom.leftChild.rightChild);
                        rightRotation(nodeFrom.leftChild);
                        break;
                    case "LR":
                        rightRotation(nodeFrom.rightChild.leftChild);
                        leftRotation(nodeFrom.rightChild);
                        break;
                }
                balanced = true;
                updateHeights(root);
            }
            nodeFrom = nodeFrom.parent;
        }
    }

    private String getCase(AVLNode node) {
        if(node.rightChild==null) {
            return node.leftChild.leftChild==null?"RL":"LL";
        }

        if(node.leftChild==null) {
            return node.rightChild.leftChild==null?"RR":"LR";
        }

        //If this point is reached it means both children exist
        if(node.rightChild.height>node.leftChild.height) {
            if(node.rightChild.leftChild!=null) {
                if(node.rightChild.leftChild.height>node.rightChild.rightChild.height) {
                    return "LR";
                }
                return "RR";
            }
            return "RR";
        }

        //If this point is reached it means we have to look on the left branch
        if(node.leftChild.rightChild!=null) {
            if(node.leftChild.leftChild==null||node.leftChild.rightChild.height>node.leftChild.leftChild.height) {
                return "RL";
            }
            return "LL";
        }
        return "LL";
    }

    private void rightRotation(AVLNode pivot) {
        pivot.parent.leftChild = pivot.rightChild;
        pivot.rightChild = pivot.parent;
        pivot.parent = pivot.parent.parent;
        if(pivot.parent==null) {
            root = pivot;
        } else {
            if(pivot.parent.data.compareTo(pivot.data)>0){
                pivot.parent.leftChild = pivot;
            } else {
                pivot.parent.rightChild = pivot;
            }
        }
        pivot.rightChild.parent = pivot;
    }

    private void leftRotation(AVLNode pivot) {
        pivot.parent.rightChild = pivot.leftChild;
        pivot.leftChild = pivot.parent;
        pivot.parent = pivot.parent.parent;
        if(pivot.parent==null) {
            root = pivot;
        } else {
            if(pivot.parent.data.compareTo(pivot.data)>0){
                pivot.parent.leftChild = pivot;
            } else {
                pivot.parent.rightChild = pivot;
            }
        }
        pivot.leftChild.parent = pivot;
    }
    /**
     * Returns the node that cointains the next element greater than the one that the node
     * passed as parameter has.
     * @param node Node used to look for the next greater element
     * @return Node with the next greater element
     */
    public AVLNode next(AVLNode node) {
        if(node.rightChild!=null) {
            node = node.rightChild;
            while(node.leftChild!=null) {
                node = node.leftChild;
            }
            return node;
        }
        
        //If this point is reached we need to search for it's ancestors
        AVLNode current = node;
        while(current.parent!=null&&current.parent.data.compareTo(node.data)<0) {
            current = current.parent;
        }

        if(current.parent== null) {return current.data.compareTo(node.data)>0?current:null;}

        return current.parent;
    }
    /**
     * Removes a node from the tree
     * @param node Node to be removed
     */
    public void delete(AVLNode node) {
        //First checking if the node is in the tree
        if(find(node.data).data.compareTo(node.data)!=0) {return;}

       //Simplest case is if the node is a leaf
       if(node.height==1) {
           //If it is we just set to null what points to it and reduce height if needed
            if(node.parent == null) {
                root = null;
            }
            else {
                if(node.parent.data.compareTo(node.data)>0) {
                   node.parent.leftChild = null;
                }
                else {
                    node.parent.rightChild = null;
                }

                if(node.data.compareTo(root.data)>0) {
                    updateRHeight();
                }
                else{
                    updateLHeight();
                }
                balance(node.parent);
           }
       }
       else {
           //If it's not there's two options
           if(node.rightChild==null) {
               //If the node has no right child we simply promote it's left child,
               //reduce height and call balance to make sure we don't unbalance the tree
               if(node.parent!=null){
                    node.leftChild.parent = node.parent;
                    if(node.parent.data.compareTo(node.data)>0) {
                        node.parent.leftChild = node.leftChild;
                        
                    }
                    else {
                        node.parent.rightChild = node.leftChild;
                    }

                    if(node.data.compareTo(root.data)>0) {
                        updateRHeight();
                    }
                    else{
                        updateLHeight();
                    }
                    balance(node.parent);
               }
               else {
                   node.leftChild.parent = null;
                   root = node.leftChild;
               }
           }
           else {
                //If a right child exists it means we can swap it with the next biggest element
                AVLNode next = next(node);
                //As it is the next element it has no left child, so we can update that pointer right away
                next.leftChild = node.leftChild;
                if(node.leftChild!=null) {
                    node.leftChild.parent = next;
                }
                //If node next happens to be the right child of our node things are simple
                if(node.rightChild.data.compareTo(next.data)==0) {
                    next.parent = node.parent;
                    if(node.parent != null) {
                        if(node.parent.data.compareTo(node.data)>0) {
                            node.parent.leftChild = next;
                        
                        }
                        else {
                            node.parent.rightChild = next;
                        }

                        if(node.data.compareTo(root.data)<0) {
                            updateLHeight();
                        }
                        else{
                            updateRHeight();
                        }
                        balance(next);
                    }
                    else {
                        root = next;
                    }
                } else {
                    //If it's not we have to consider it having a right child
                    AVLNode possUnbalanced = next.parent;
                    if(next.rightChild!=null) {
                        next.parent.leftChild = next.rightChild;
                        next.rightChild.parent = next.parent;
                    }
                    else {
                        next.parent.leftChild = null;
                        if(next.parent.parent.data.compareTo(node.data)==0) {
                            possUnbalanced = next;
                        }
                        else {
                            possUnbalanced = next.parent.parent;
                        }
                    }
                    next.rightChild = node.rightChild;
                    next.parent = node.parent;
                    if(node.parent!=null) {
                        if(node.parent.data.compareTo(node.data)>0) {
                            node.parent.leftChild = next;
                        }
                        else {
                            node.parent.rightChild = next;
                        }
                    }
                    else {
                        root = next;
                    }
                    

                    if(node.data.compareTo(root.data)<0) {
                        updateLHeight();
                    }
                    else{
                        updateRHeight();
                    }
                    balance(possUnbalanced);
                }       
           }
       }
    }
    /**
     * Returns a D_ArrayImp (dynamic array) with all the data that's between a specified range
     * @param rangeStart Start of the range
     * @param rangeEnd End of the range
     * @return Dynamic array with those elements
     */
    public D_ArrayImp<T> rangeSearch(T rangeStart, T rangeEnd) {
        D_ArrayImp<T> arr = new D_ArrayImp<>();
        AVLNode current = find(rangeStart);

        while(current.data.compareTo(rangeStart)<0) {
            current = next(current);
            if(current==null) {return arr;}
        }

        while(current!=null&&current.data.compareTo(rangeEnd)<=0) {
            arr.add(current.data);
            current = next(current);
        }
        return arr;
    }

    /**
     * Returns a D_ArrayImp (dynamic array) with all the data that's between a specified range,
     * does not include the range end
     * @param rangeStart Start of the range
     * @param rangeEnd End of the range
     * @return Dynamic array with those elements
     */
    public D_ArrayImp<T> rangeSearchN(T rangeStart, T rangeEnd) {
        D_ArrayImp<T> arr = new D_ArrayImp<>();
        AVLNode current = find(rangeStart);

        while(current.data.compareTo(rangeStart)<0) {
            current = next(current);
            if(current==null) {return arr;}
        }

        while(current!=null&&current.data.compareTo(rangeEnd)<0) {
            arr.add(current.data);
            current = next(current);
        }
        return arr;
    }

    /**
     * Returns a D_ArrayImp (dynamic array) with all the data that's greater than a specified
     * range start
     * @param rangeStart Start of the range
     * @return Dynamic array with those elements
     */
    public D_ArrayImp<T> rangeSearchB(T rangeStart) {
        D_ArrayImp<T> arr = new D_ArrayImp<>();
        AVLNode current = find(rangeStart);

        while(current.data.compareTo(rangeStart)<0) {
            current = next(current);
            if(current==null) {return arr;}
        }

        while(current!=null) {
            arr.add(current.data);
            current = next(current);
        }
        return arr;
    }

    /**
     * Returns a D_ArrayImp (dynamic array) with the In-Order traversal of the tree
     * @return dynamic array with the traversal
     */
    public D_ArrayImp<T> getInOrder() {
        D_ArrayImp<T> arr = new D_ArrayImp<>();
        inOrderT(root, arr);
        return arr;
    }

    private void inOrderT(AVLNode node, D_ArrayImp<T> arr) {
        if(node!=null) {
            inOrderT(node.leftChild, arr);
            arr.add(node.data);
            inOrderT(node.rightChild, arr);
        }
    }

    public T findData(T data) {
        AVLNode found = find(data);
        return found==null?null:found.data;
    }
}