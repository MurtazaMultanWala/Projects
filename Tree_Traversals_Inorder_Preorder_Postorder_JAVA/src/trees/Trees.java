package trees;

public class Trees {

    Node root;
    int top=-1;
    int front=-1,rear=-1,rearcopy=-1;
    Node array[];
   
    class Node{
       
       Node left;
       Node right;
       int data;
       int top=-1;
       
       Node()
       {
           data=0;
           left=null;
           right=null;
           
       }
       
       Node(int data)
       {
           this.data=data;
           left=null;
           right=null;
       }
    }
   
    
    Trees(){
        array=new Node [100];
    }

    
    public boolean isemptystack()
    {
        return (top==-1);
    }
    
    public boolean isemptyqueue()
    {
        return (rearcopy==-1);
    }
    
    
   public void push(Node n)
   {
       array[++top]=n;
   }
   
   public void enqueue(Node n)
   {
       rearcopy++;
        array[++rear]=n;
        
        if(front==-1)
            front=0;
   }
   
   public Node pop(){
        
       return array[top--];
       
   }
   
   public Node dequeue(){
        rearcopy--;
       return array[front++];
   }
   
   public void add(Node r,int data)
   {
       
       if (root==null)
       {
           Node n=new Node(data);
           root=n;
           return;
       }
       
       
       Node n=new Node(data);
    
      
     
       if(r.data>=data)
       {
           if(r.left!=null)
                add(r.left,data);
            
           else
               r.left=n;
       }
       
       else
       {  
          if(r.right!=null) 
              add(r.right,data); 
          else
              r.right=n;
       }
   }
   
   
   public void add(Node r,Node n)
   {
       
       if(r==null)
           r=n;
       
       if(r.data>=n.data)
       {
           if(r.left!=null)
                add(r.left,n);
            
           else
               r.left=n;
       }
       
       else
       {  
          if(r.right!=null) 
              add(r.right,n); 
          else
              r.right=n;
       }
   }
   
   
   public Node search(int data)
   {
       Node temp=root;
       
       if(root==null){
           System.out.println("No Tree Exists");
           return null;
       }
       while(temp!=null)
       {
           if(data==temp.data)
           {
               return temp;
           }
           
           else if(temp.data>data)
           {
               temp=temp.left;
           }
           
           else if(temp.data<data)
           {
               temp=temp.right;
           }
           
       }
       
       System.out.println("Data is not is tree");
       return null;
   }
   
   
   
   
   
   
   public void delete(int data)
   {
       Node parent=findparent(data);
       Node r=findnode(data);
       
       if(r==null)
       {
           System.out.println("No Tree Exists");
           return;
       }
       
       else if(parent==null)
       {
           Node nr=r.right;
           Node nl=r.left;
           
           if(nr!=null)
           {
              root=nr;
              
              if(nl!=null)
                add(nr,nl);
           }
           
           else
           {
               if(nl!=null)
               {
                   root=nl;
               }
               
               else
               {
                   root=null;
               }
           }
       }
       
       else if(parent.left.data==data)     //parent !=null
       {
           Node nl=r.left;
           Node nr=r.right;
           
           if(nr!=null)
           {
                parent.left=nr;

                if(nl!=null)
                    add(nr,nl);
           }

           else if(nl!=null)
           {
                parent.left=nl;
           }

           else
           {
               parent.left=null;
           }
        } 
    
    
        else if(parent.right.data==data)
        {
           Node nl=r.left;
           Node nr=r.right;
           
           if(nr!=null)
           {
                parent.right=nr;

                if(nl!=null)
                    add(nr,nl);
           }

           else if(nl!=null)
           {
               parent.right=nl;
           }

           else
           {
                parent.right=null;
           }
        }  
          
    }
   
   

   public Node findparent(int data)
   {
        Node temp=root;
        
        if(temp==null)
        {
            System.out.println("No Tree Exist");
            return null;
        }
        
        if(temp.data==data)
        {
            return null;
        }
        
       while(temp!=null)
       {
           if(temp.right==null && temp.left==null)
               break;
           
           else if(temp.right.data==data)
           {
               return temp;
           }
           
           else if(temp.left.data==data)
           {
               return temp;
           }
           
           else if(temp.data<data)
           {
               temp=temp.right;
           }
           
           else
           {
               temp=temp.left;
           }
           
           
        }
       
       System.out.println("No data found");
       return null;
    }
   
   
    
    public Node findnode(int data)
    {
        if(root==null)
        {
            System.out.println("No Tree Exists");
            return null;
        }
        
        Node temp=root;
        
        while(temp!=null)
        {
            if(temp.data==data)
            {
                return temp;
            }
            
            else if(temp.data>data)
            {
                temp=temp.left;
            }
            else             
            {
                temp=temp.right;
            }
        }
        
        System.out.println("No Such Data Exists");
        return null;
    }
   
   
    public void addnode(int data)
    {
        add(root, data);
    }
   
    
    
    public void inorders()
    {
        inorder(root);
    }
    
    public void inorder(Node r)
    {
        if(r==null)
        {
           return;
        }
        
        inorder(r.left);
        System.out.println(r.data);
        inorder(r.right);
        
    }
  
    
    public void preorders()
    {
        preorder(root);
    }
    
    public void preorder(Node r)
    {
        if(r==null)
        {
           return;
        }
        
        System.out.println(r.data);
        preorder(r.left);
        preorder(r.right);
        
    }
    
    
    public void postorders()
    {
        postorder(root);
    }
    
    public void postorder(Node r)
    {
        if(r==null)
        {
           return;
        }
        
        
        postorder(r.left);
        postorder(r.right);
        System.out.println(r.data);
        
    }
    
    
    public void iterativepreorder(){
        
        if(root==null)
        {
            System.out.println("sorry no tree exeist");
            return;
        }
        
        push(root);
        
        while(!isemptystack())
        {
            Node n=pop();
            System.out.println(n.data);
            
            if(n.right!=null)
                push(n.right);
            if(n.left!=null)
                push(n.left);
        }
        
    }
    
    
    public void iterativepostorder(){
        
        if(root==null)
        {
            System.out.println("sorry no tree exeist");
            return;
        }
        
        push(root);
        
        
       Node array2[];
       array2= new Node[50];
       int i=0;
       
       while(!isemptystack())
       {
           Node n=pop();
           
           array2[i++]=n;
           
           if(n.left!=null)
                push(n.left);
           if(n.right!=null)
                push(n.right);
       }
       
       i--;
       while(i!=-1)
       {
           System.out.println((array2[i--]).data);
       }
    }
    
    
    
    public void iterativeinorder()
    {
        if(root==null)
        {
            System.out.println("sorry no tree exeist");
            return;
        }
        
         Node temp=root;
         
         while(temp!=null)
         {
            push(temp);
            temp=temp.left;
         }
        
        while(!isemptystack())
        {
            Node n=pop();
            System.out.println(n.data);
            
            if(n.right!=null)
            {
                n=n.right;
           
                while(n!=null)
                {
                    push(n);
                    n=n.left;
                }
            }
        }
    }
    
    
   public void bfs()
   {
       enqueue(root);
       
       while(!isemptyqueue())
       {
           Node n=dequeue();
           
           System.out.println(n.data);
           
           if(n.left!=null)
                enqueue(n.left);
            if(n.right!=null)
                enqueue(n.right);
            
       }
   }
    
   public static void main(String[] args) 
    {
        Trees obj=new Trees();
        
        obj.addnode(5);
        obj.addnode(7);
        obj.addnode(3);
        obj.addnode(6);         //     5
        obj.addnode(10);        //  3      7
        obj.addnode(2);         //2   4   6   10
        obj.addnode(4);
        
        obj.delete(9);
       
        System.out.println("\n\nDFS\n\n");

        System.out.println("InOrder");
        obj.inorders();
        System.out.println("\n\nInOrder Iterative");
        obj.iterativeinorder();

        System.out.println("\n\nPreOrder Recursive");
        obj.preorders();
        System.out.println("\n\nPreOrder Iterative");
        obj.iterativepreorder();
      
        System.out.println("\n\nPostOrder");
        obj.postorders();
        System.out.println("\n\nPostOrder Iterative");
        obj.iterativepostorder();    

        System.out.println("\n\nBFS");
        obj.bfs();
    }   
}
