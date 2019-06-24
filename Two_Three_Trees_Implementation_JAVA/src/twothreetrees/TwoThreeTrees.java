
package twothreetrees;


public class TwoThreeTrees {

    Node root;
    
    public class Node
    {
        int data1;
        int data2;
        Node left, middle,right;
        
        
        Node()
        {
            data1=99999;
            data2=99999;
            left=middle=right=null;
        }       
    }
    
    
    public void add(int data)
    {
        add(data,root);
    }
    
    
    public void add(int data, Node temp)
    {
        
        
        if(root==null)
        {
            Node n = new Node();
            root=n;
            root.data1=data;
            return;
        }

        
       if(temp.left ==null && temp.right ==null && temp.middle== null)  
       {          
            if(temp.data1!=99999 && temp.data2==99999)
            {
                if(temp.data1<data)        // if left data is smaller than data
                    temp.data2=data;

                else            //if left data is greater data
                {
                    int swap=temp.data1;
                    temp.data1=data;
                    temp.data2=swap;
                }
            }

            else if(temp.data1!=99999 && temp.data2!=9999)
            {
                split(temp,data);
            }
        }
       
        else if(data<temp.data1)
        {
            add(data,temp.left);
        }
        
        else if(data>temp.data1 && data<temp.data2 && temp.data2!=99999)
        {
            add(data,temp.middle);
        }
       
        else if(data>temp.data2)
        {
            add(data,temp.right);
        }        
    }
    
    
    public void split(Node toSplit,int datatoAdd)
    {
      
        if(toSplit==root)
        {  
            if(toSplit.data1<datatoAdd)         //first value of node is smaller than datatoAdd
            {
                if(toSplit.data2<datatoAdd && toSplit.data2>toSplit.data1)     //second value is also smaller than datatoAdd but bigger than left
                {
                    Node parent= new Node();        //making parent node and assigning middle value
                    parent.data1=toSplit.data2;

                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=toSplit.data1;

                    toSplit.data1=datatoAdd;    // making right value to left of its own node and making data2 as predefined value
                    toSplit.data2=99999;

                    parent.left=leftsplited;
                    parent.right=toSplit;
                    root=parent;

                }

                else if(toSplit.data2<datatoAdd && toSplit.data2<toSplit.data1)     //second value is also smaller than datatoAdd and left
                {
                    Node parent= new Node();        //making parent node and assigning middle value
                    parent.data1=toSplit.data1;

                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=toSplit.data2;

                    toSplit.data1=datatoAdd;    // making right value to left of its own node and making data2 as predefined value
                    toSplit.data2=99999;

                    parent.left=leftsplited;
                    parent.right=toSplit;
                   root=parent;

                }

                else        // second value is bigger than datatoAdd
                {
                    Node parent= new Node();        //making parent node and assigning middle value
                    parent.data1=toSplit.data1;

                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=datatoAdd;

                    toSplit.data1=toSplit.data2;    // making right value to left of its own node and making data2 as predefined value
                    toSplit.data2=99999;

                    parent.left=leftsplited;
                    parent.right=toSplit;

                    root=parent;

                }
            }

            else        // if first value is larger than datatoAdd
            {
                if(toSplit.data2<datatoAdd )     //second value is smaller than datatoAdd
                {
                    Node parent= new Node();        //making parent node datatoAdd
                    parent.data1=datatoAdd;

                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=toSplit.data2;

                    toSplit.data2=99999;

                    parent.left=leftsplited;
                    parent.right=toSplit;
                   root=parent;

                }


                else if(toSplit.data2>datatoAdd && toSplit.data2<toSplit.data1)     //second value is also larger than datatoAdd but smaller than left
                {
                    Node parent= new Node();        //making parent node and assigning middle value
                    parent.data1=toSplit.data2;

                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=datatoAdd;

                    toSplit.data2=99999;

                    parent.left=leftsplited;
                    parent.right=toSplit;
                    root=parent;
                }


                else        // second value is larger than datatoAdd and left
                {
                    Node parent= new Node();        //making parent node and assigning middle value
                    parent.data1=toSplit.data1;

                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=datatoAdd;

                    toSplit.data1=toSplit.data2;    // making right value to left of its own node and making data2 as predefined value
                    toSplit.data2=99999;

                    parent.left=leftsplited;
                    parent.right=toSplit;
                    root=parent;
                }
            }
        }
        
       
        else            // toSplit is not root
        {
            if(toSplit.data1<datatoAdd)         //first value of node is smaller than datatoAdd
            {
                if(toSplit.data2<datatoAdd && toSplit.data2>toSplit.data1)     //second value is also smaller than datatoAdd but bigger than left
                {
                    
                    Node parent=findparent(toSplit);        //making parent node and assigning middle value
                    
                    if(parent!=null)
                        addparent(toSplit.data2,parent);
                    
                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=toSplit.data1;

                    toSplit.data1=datatoAdd;    // making right value to left of its own node and making data2 as predefined value
                    toSplit.data2=99999;
                }

                else if(toSplit.data2<datatoAdd && toSplit.data2<toSplit.data1)     //second value is also smaller than datatoAdd and left
                {
                    Node parent=findparent(toSplit);        //making parent node and assigning middle value
                    
                    if(parent!=null)
                        addparent(toSplit.data1,parent);
                    
                    
                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=toSplit.data2;

                    toSplit.data1=datatoAdd;    // making right value to left of its own node and making data2 as predefined value
                    toSplit.data2=99999;
                }

                else        // second value is bigger than datatoAdd
                {
                    Node parent=findparent(toSplit);        //making parent node and assigning middle value
                    
                    if(parent!=null)
                        addparent(toSplit.data1,parent);

                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=datatoAdd;

                    toSplit.data1=toSplit.data2;    // making right value to left of its own node and making data2 as predefined value
                    toSplit.data2=99999;
                }
            }

            else        // if first value is larger than datatoAdd
            {
                if(toSplit.data2<datatoAdd )     //second value is smaller than datatoAdd
                {
                    Node parent=findparent(toSplit);        //making parent node and assigning middle value
                    
                    if(parent!=null)
                        addparent(datatoAdd,parent);
                   
                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=toSplit.data2;

                    toSplit.data2=99999; 
                }


                else if(toSplit.data2>datatoAdd && toSplit.data2<toSplit.data1)     //second value is also larger than datatoAdd but smaller than left
                {
                    Node parent=findparent(toSplit);        //making parent node and assigning middle value
                    
                    if(parent!=null)
                        addparent(toSplit.data2,parent);


                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=datatoAdd;

                    toSplit.data2=99999;
                }


                else        // second value is larger than datatoAdd and left
                {
                    Node parent=findparent(toSplit);        //making parent node and assigning middle value
                    
                    if(parent!=null)
                        addparent(toSplit.data1,parent);

                    Node leftsplited= new Node();   // creating left node splited and assigning smaller value
                    leftsplited.data1=datatoAdd;

                    toSplit.data1=toSplit.data2;    // making right value to left of its own node and making data2 as predefined value
                    toSplit.data2=99999;
                }
            }
        }
    }
    
    
    public void addparent(int data, Node n)
    {
        if(n.data1!=99999 && n.data2==99999)
        {
            if(n.data1<data)        // if left data is smaller than data
                    n.data2=data;

            else            //if left data is greater data
           {
                int swap=n.data1;
                n.data1=data;
                n.data2=swap;
            }
        }
        
        else if(n.data1!=99999 && n.data2!=9999)
        {
            
            split(n,data);
        }
    }
    
    
    public Node findparent(Node n)
    {
            if(n==null || n==root)
            {
                return null;
            }
        
            Node temp=root;
            
            while(temp!=null)       // checking in left part
            {
                if(temp.left==n || temp.right==n || temp.middle==n)
                {
                    return temp;
                }
                
                temp=temp.left;
                    
            }
            
            
            temp=root;
            
            while(temp!=null)           // checking in right part
            {
                if(temp.left==n || temp.right==n || temp.middle==n)
                {
                    return temp;
                }
                
                temp=temp.right;
                    
            }
            
            
            temp=root;
            
            while(temp!=null)       // checking in middle part
            {
                if(temp.left==n || temp.right==n || temp.middle==n)
                {
                    return temp;
                }
                
                temp=temp.middle;    
            }
            
            
            System.out.println("No Such Data Exist");
            return null;
    }
        
  
    
    public static void main(String[] args) {
        TwoThreeTrees obj = new TwoThreeTrees();
        
        obj.add(6);
        obj.add(10);
        obj.add(15);
        obj.add(8);
        obj.add(17);
        obj.add(7);
        obj.add(16);
        
       
    }
    
}
