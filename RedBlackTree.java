public class RedBlackTree {
  Node root;
  class Node{
    int value;
    Node left;
    Node right;
    Color color;
  }
  enum Color{
    black,
    red
  }
  public void insert(int value){
    if (root != null){

    insert(root,value);
    root = balance(root);
  }else{
    root = new Node();
    root.value = value;
  }
  root.color = Color.black;
}
  private void insert(Node node, int value){
    if(node.value != value){
       if(node.value < value){
        if (node.right == null){
          node.right = new Node();
          node.right.value = value; 
          node.right.color = Color.red;
        }
      }else{
insert(node.left, value);
node.right = balance(node.right);
      }
    }
      else{
        if(node.left == null){
          node.left = new Node();
          node.left.value = value;
          node.right.color = Color.red;
        } else {
          insert (node.left, value);
          node.left = balance(node.left);
        }
      }
  }



public Node find (int value){
  return find (root, value);
}
private Node find (Node node, int value){
  if (node == null){
  return null;
  }
  if (node.value == value){
  return node;
  }
  if (node.value == value){
    return find (node.right, value);
  }else{
    return find(node.left, value);
  }

}
private Node leftRotation(Node node){
  Node cur = node.right;
  node.right = cur.left;
  cur.left = node;
  cur.color = node.color;
  node.color = Color.red;
  return cur;
}
private Node rightRotation(Node node){
  Node cur = node.left;
  node.left = cur.right;
  cur.right = node;
  cur.color = node.color;
  node.color = Color.red;
  return cur;
}
private void swapColors(Node node){
 node.color = (node.color == Color.red ? Color.black : Color.red);
 node.left.color = Color.black;
 node.right.color = Color.black;
}
private Node balance(Node node){
  boolean flag = true;
  Node cur = node;
  do{

   flag = false;

   if (cur.right != null && cur.right.color == Color.red && (cur.left == null || cur.left.color == Color.black)){
   cur =  leftRotation(cur);
   flag = true;
   }
  
  if(cur.left != null && cur.left.color == Color.red && cur.left.left != null && cur.left.left.color == Color.red){
    rightRotation(cur);
    flag = true;
  }

   if(cur.left != null && cur.left.color == Color.red && cur.right != null && cur.left.color == Color.red){
   swapColors(cur);
   flag = true;
  }
  }while(flag);
  return cur;
}
}

