/**
 * Self Balancing binary tree
 * @author slondonoq
 * @param <T> the type of the elements in the tree
 */
public class AVLTreeImp<T extends Comparable<T>>{
    public class AVLNode{
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

    public AVLNode root;

    public AVLTreeImp() {
        root = null;
    }

    public void insert(T data) {
        AVLNode newNode = new AVLNode(data);
        if(root==null) {
            root = newNode;
        }
        else {
            AVLNode posParent = find(data);
            if(posParent.data.compareTo(data)==1) {
                posParent.leftChild = newNode;
                newNode.parent = posParent;
                updateHeights(root);
                balance(posParent);
            }
            else if(posParent.data.compareTo(data)==-1) {
                posParent.rightChild = newNode;
                newNode.parent = posParent;
                updateHeights(root);
                balance(posParent);
            }
        }
    }

    public AVLNode find(T data) {
        if(root==null) {
            return root;
        }

        AVLNode currentNode = root;
        while(currentNode.leftChild!=null || currentNode.rightChild!=null) {
            if(currentNode.data.compareTo(data)==0) {
                return currentNode;
            }
            else if(currentNode.data.compareTo(data)==1) {
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

    private int calcBalanceFactor(AVLNode node) {
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
            if(pivot.parent.data.compareTo(pivot.data)==1){
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
            if(pivot.parent.data.compareTo(pivot.data)==1){
                pivot.parent.leftChild = pivot;
            } else {
                pivot.parent.rightChild = pivot;
            }
        }
        pivot.leftChild.parent = pivot;
    }

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
        while(current.parent!=null&&current.parent.data.compareTo(node.data)==-1) {
            current = current.parent;
        }

        if(current.parent== null) {return current.data.compareTo(node.data)==1?current:null;}

        return current.parent;
    }

    public void delete(AVLNode node) {
       //Simplest case is if the node is a leaf
       if(node.height==1) {
           //If it is we just set to null what points to it and reduce height if needed
           if(node.parent == null) {
               root = null;
           }
           else {
               if(node.parent.data.compareTo(node.data)==1) {
                   node.parent.leftChild = null;
                   updateHeights(root);
               }
               else {
                   node.parent.rightChild = null;
                   updateHeights(root);
               }
           }
       }
       else {
           //If it's not there's two options
           if(node.rightChild==null) {
               //If the node has no right child we simply promote it's left child,
               //reduce height and call balance to make sure we don't unbalance the tree
               if(node.parent!=null){
                    node.leftChild.parent = node.parent;
                    if(node.parent.data.compareTo(node.data)==1) {
                        node.parent.leftChild = node.leftChild;
                        
                    }
                    else {
                        node.parent.rightChild = node.leftChild;
                    }
                    updateHeights(root);
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
                if(node.rightChild.equals(next)) {
                    next.parent = node.parent;
                    if(node.parent.data.compareTo(node.data)==1) {
                        node.parent.leftChild = next;
                    
                    }
                    else {
                        node.parent.rightChild = next;
                    }
                    updateHeights(root);
                    balance(next);
                } else {
                    //If it's not we have to consider it having a right child
                    AVLNode possUnbalanced = next.parent;
                    if(next.rightChild!=null) {
                        next.parent.leftChild = next.rightChild;
                        next.rightChild.parent = next.parent;
                    }
                    else {
                        next.parent.leftChild = null;
                        if(next.parent.parent.equals(node)) {
                            possUnbalanced = next;
                        }
                        else {
                            possUnbalanced = next.parent.parent;
                        }
                    }
                    if(node.parent.data.compareTo(node.data)==1) {
                        node.parent.leftChild = next;
                    }
                    else {
                        node.parent.rightChild = next;
                    }
                    next.leftChild = node.leftChild;
                    next.rightChild = node.rightChild;
                    updateHeights(root);
                    balance(possUnbalanced);
                }       
           }
       }
    }

    public D_ArrayImp<T> rangeSearch(T rangeStart, T rangeEnd) {
        D_ArrayImp<T> arr = new D_ArrayImp<>();
        AVLNode next = next(find(rangeStart));
        while(next.data.compareTo(rangeEnd)!=1) {
            arr.add(next.data);
            next = next(next);
        }
        return arr;
    }
}