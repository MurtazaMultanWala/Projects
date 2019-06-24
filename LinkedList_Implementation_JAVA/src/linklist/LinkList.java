/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linklist;


public class LinkList {
    
    Node head;
    
    public void add(int data)
    {
       Node n=new Node();
       n.data=data;
       
       if (head==null)
       {
           head=n;
           return;
       }
       
       Node temp=head;
       while(temp.next!=null)
       {
           temp=temp.next;
       }
       temp.next=n;
    }
    
    public Node search(int data)
    {
        Node temp=head;
        while(temp.next!=null && temp.data!=data)
        {
            temp=temp.next;
        }
        if(temp.data==data)
        return temp;
        else
            return null;
    }
    
    
    public Node searchpre(int data)
    {
        Node temp=head;
        Node ahead=temp.next;
        
        if(head.data==data)
            return null;
        while(ahead.next!=null && ahead.data!=data)
        {
            temp=temp.next;
            ahead=ahead.next;
        }
        
        return temp;
    }
    
    
    public void RemoveDuplicates(){
        Node temp=head; //first node;
        Node ahead=temp.next; //second node
        
        while(temp.next!=null)
        {
            if(temp.data==ahead.data)
            {
                temp.next=ahead.next;
                ahead=null;
            }
            temp=temp.next;
            if(temp.next!=null)
            ahead=ahead.next;
        }
        
    }
    
    
    public void delete(int data)
    {
        Node n=new Node();
        Node p=new Node();
        
        n=search(data);
        
        if(n!=null)
        {
            p=searchpre(data);
            if(p==null)
            {
                head=head.next;
                n=null;
            }
            
            else
            {
                p.next=n.next;
                n=null;
            }
        }
        else
            System.out.println("Cannot Delete No Node With Data: "+data+ " found in list\n\n");
    }
    
    
    public void swapNodes(Node n)
    {
        
    }
    
    
    public void reverse()
    {
        if(head.next==null)
        {
        }
        else
        {
            Node temp=head;
            Node temp1=null;
            
            while(temp.next!=null)
            {
                temp1=temp.next;
                temp.next=temp;
                temp=temp1;
                
                temp=temp.next;
            }
            
            
        }
    }
    
    
    public void printlist()
    {
        Node temp=head;
      while(temp.next!=null)
      {
          System.out.println("Data: "+temp.data+"\t\tNext Address: "+temp.next);
          temp=temp.next;
      }
      System.out.println("Data: "+temp.data+"\t\tNext Address: "+temp.next);
    }
    
    public static void main(String[] args) 
    {
        LinkList obj= new LinkList();
        
        obj.add(5);
       // obj.printlist();
        
        obj.add(6);
        obj.add(7);
        obj.add(7);
        obj.add(10);
        obj.printlist();
        System.out.println("\n\n");
        obj.RemoveDuplicates();
        obj.printlist();
    }
    
}
