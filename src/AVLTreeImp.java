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
                increaseHeight(newNode);
                balance(posParent);
            }
            else if(posParent.data.compareTo(data)==-1) {
                posParent.rightChild = newNode;
                newNode.parent = posParent;
                increaseHeight(newNode);
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

    private void increaseHeight(AVLNode node) {
        while(node.parent!=null) {
            node.parent.height ++;
            node = node.parent;
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
                //System.out.println("Unbalanced node: "+nodeFrom.data+" case: "+bCase);
                //if(nodeFrom.parent==null) {System.out.println("Root is unbalanced");}
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
}