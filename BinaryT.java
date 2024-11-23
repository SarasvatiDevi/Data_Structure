import java.util.Queue;
import java.util.LinkedList;
class Node{
  int data;
  Node left;
  Node right;
  Node(int data){
    this.data=data;
    left=right=null;
}

}
public class BinaryT{
 public Node Insert(Node root ,int data){
    if(root==null){
      return new Node(data);
    }
    if(root.data==data){
      return root;
}
 if (data<root.data){
	root.left=Insert(root.left,data);
   }else{
	root.right=Insert(root.right,data);
	}	
	return root;

}

 public Node search(Node node,int item){
if(node==null){
  return null;
}
      if(item==node.data){
        return node;
      }
      else if(item<node.data){
       return search(node.left,item);
      }else{
         return search(node.right,item);
      }
    }
 public void preorder(Node node){
         if(node!=null){
    System.out.print(node.data + "->");
    preorder(node.left);
    preorder(node.right);    
    }
    }
 public void postorder(Node node){
         if(node!=null){
    preorder(node.left);
    preorder(node.right);
 System.out.print(node.data + "->");    
    }
    }

 public void inorder(Node node){
         if(node!=null){
    preorder(node.left);
    System.out.print(node.data + "->");
    preorder(node.right);
    
    }
    }
  public int height(Node node){
    if(node==null){
 return 0;
}
 else{
  int dl=height(node.left);
  int dr=height(node.right);
  return Math.max(dl,dr)+1;
  }
}
 
  
public int CountNodes(Node node){
  if(node==null){
 return 0;
}
else{
   int l=CountNodes(node.left);
   int r=CountNodes(node.right);
	return 1+l+r;

}
}
public void levelOrder(Node root){
 Queue<Node> q=new LinkedList<>();
 if(root==null){
   return;
}
 q.add(root);
q.add(null);
while(!q.isEmpty()){
  Node curr=q.remove();
  if(curr==null)
{
    System.out.print(" ");
  if(q.isEmpty()){
   break;
}else{
  q.add(null);
}
}
else{
   System.out.print(curr.data+" ");
if(curr.left!=null){
  q.add(curr.left);
}
 if(curr.right!=null){
  q.add(curr.right);
}
}
}
}
public Node Delete(Node node ,int x){
        if(node==null){
         return node;
        }
        else if(node.data>x){
          node.left=Delete(node.left,x);
        }
        else if(node.data<x){
          node.right=Delete(node.right,x);
        }
        else{
          if(node.left==null){
           return node.right;
          }
          if(node.right==null){
           return node.left;
          }
          
         Node successor= getSuccessor(node);
         node.data=successor.data;
         node.right=Delete(node.right,successor.data);
        }
        
        return node;
    }
public Node getSuccessor(Node curr){
     curr=curr.right;
     while(curr!=null && curr.left!=null){
        curr=curr.left;
     }
     return curr;
     }
  public static void main(String args[]){
  Node root=null;
       BinaryT bst= new BinaryT();
   root=bst.Insert(root, 50);
   root=bst.Insert(root, 30);
   root= bst.Insert(root, 20);
    root= bst.Insert(root, 40);
     root= bst.Insert(root, 70);
      root= bst.Insert(root, 60);
      root=  bst.Insert(root, 80);
System.out.println("PreOrder");     
   bst.preorder(root);
System.out.println("\nPostOrder");
   bst.postorder(root);
System.out.println("\nInOrder");
   bst.inorder(root);
System.out.println("\nLevelOrder");
   bst.levelOrder(root);
System.out.println();
System.out.println( "Count nodes: "+bst.CountNodes(root));
System.out.println("Height: "+bst.height(root));
int item_Search = 20;
    Node result = bst.search(root, item_Search);
    if (result != null) {
        System.out.println("Item " + item_Search + " found in the tree.");
    } else {
        System.out.println("Item " + item_Search + " not found in the tree.");
    }
       
 System.out.print("");
    bst.Delete(root,70);
System.out.println("After delete");
    bst.inorder(root);

}

}