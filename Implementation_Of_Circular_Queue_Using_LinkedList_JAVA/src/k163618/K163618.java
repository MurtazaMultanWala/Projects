
package k163618;

import java.util.Scanner;


public class K163618 
{

    Scanner myobj= new Scanner(System.in); 
    Node head;
    Node tail;
    int stacksize;
    int counterstack;
    int counterqueue;
    int queuesize;
    int circulardequeue;
    
    K163618()
    {
        stacksize=-1;
        queuesize=-1;
        counterstack=0;
        counterqueue=0;
        circulardequeue=0;
    }
    
    class Node
    {
        
        Node pre;
        Node next;
        int data;
        
        
        Node()
        {
           data=0;
           next=null;
           pre=null;
        }
        
        Node(int data)
        {
            this.data=data;
            next=null;
            pre=null;
        }
    }

    
    
    public boolean isEmptystack()
    {
        if(head==null)
        return true;
        
        else
            return false;
    }
    
    public boolean isEmptyqueue()
    {
        if(head==null /*|| head==tail*/)
        return true;
        
        else
            return false;
    }
    
    
    public boolean isfullstack()
    {
        if(counterstack==stacksize)
        return true;
        
        else
            return false;
    }
    
    public boolean isfullqueue()
    {
        if(counterqueue==queuesize)
        return true;
        
        else
            return false;
    }
    
    
    public boolean isfullcircularqueue()
    {
        if(head==tail)
            return true;
        
        else
            return false;
                 
    }
    
    public void push(int data)
    {
        if(stacksize==-1)
        {
            System.out.println("Enter Size Of Stack:\t");
            stacksize=myobj.nextInt();
        }
        
        Node n=new Node(data);
        if (!isfullstack())
        {
            if(head!=null)
            {
                n.next=head;
                head.pre=n;
                head=n;
                counterstack++;
                
                System.out.println("Successfully Pushed Data Into The Stack -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
            }
            
            else
            {
                head=n;
                counterstack++;
                
                System.out.println("Successfully Pushed Data Into The Stack -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
            }
        }
        
        else
        {
            System.out.println("Sorry Stack Is Full.\n");
           
        }
    }
    
    
    
    public void enqueue(int data)
    {
        if(queuesize==-1)
        {
            System.out.println("Enter Size Of Queue:\t");
            queuesize=myobj.nextInt();
        }
        
        Node n=new Node(data);
        if (!isfullqueue())
        {
            if(tail!=null)
            {
                n.pre=tail;
                tail.next=n;
                tail=n;
                counterqueue++;
                
                System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
            }
            
            else
            {
                tail=n;
                head=tail;
                counterqueue++;
                
                System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
            }
            
            
            
        }
        
        else
        {
            System.out.println("Sorry Queue Is Full.\n");
           
        }
    }
    
    
    
    
    
    public void enqueuecircular(int data)
    {
            if(queuesize==-1)
            {
                System.out.println("Enter Size Of Queue:\t");
                queuesize=myobj.nextInt();
            }
        
            Node n=new Node(data);

            if(counterqueue<queuesize)
            {
                if (!isfullqueue())
                {
                    if(tail!=null)
                    {
                        n.pre=tail;
                        tail.next=n;
                        tail=n;
                        counterqueue++;
                        System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
                    }

                    else
                    {
                        tail=n;
                        head=tail;
                        counterqueue++;

                        System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
                    } 
                }

                else
                {
                    System.out.println("Sorry Queue Is Full.\n");
                }
            }
            
            else
            {
            
                if(circulardequeue!=0)
                {
                   if(head!=null)
                   {
                        n.next=head;
                        head.pre=n;
                        head=n;
                        circulardequeue--;
                        System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
                   }
                   else
                   {
                       head=n;
                       tail=head;
                       circulardequeue--;
                       System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
                   }
                }
                else
                {
                    System.out.println("Sorry Queue Is Full");
                }
            }
            

    } 
    
    
    public void enqueuepriority(int data)
    {
        int swapindication=0;
        if(queuesize==-1)
        {
            System.out.println("Enter Size Of Queue:\t");
            queuesize=myobj.nextInt();
        }
        
        Node n=new Node(data);
       
        if(!isfullqueue())
        {
            if(tail!=null)
            {
                Node temp=tail;
                
                if(tail!=head)
                {
                    while(temp!=head)
                    {
                        
                        if(n.data>=temp.data)
                        {
                            temp=temp.pre;
                        }
                        else
                        {
                            if(temp.pre==null)
                            {
                                if(n.data>temp.data)
                                {
                                    n.next=temp;
                                    temp.pre=n;
                                    head=n;
                                    counterqueue++;
                                    swapindication++;
                                    System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
                                    break;
                                }
                                else
                                {   
                                    Node tempnext=temp.next;
                                    n.pre=temp;
                                    n.next=tempnext;
                                    tempnext.pre=n;
                                    temp.next=n;
                                    
                                    counterqueue++;
                                    swapindication++;
                                    System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
                                   break;
                                }
                            }

                            else if(temp==tail)
                            {
                                temp.next=n;
                                n.pre=temp;
                                tail=n;
                                counterqueue++;
                                swapindication++;
                                System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
                                break;
                            }
                          
                            else  
                            {
                               
                                Node tempnext=temp.next;
                                n.next=tempnext;
                                n.pre=temp;
                                temp.next=n;
                                tempnext.pre=n;
                                counterqueue++;
                                swapindication++;
                                System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
                                
                            }
                         temp=temp.pre;
                         break;
                        }
                       
                        
                    }
                        
                    
                    if(n.data>temp.data)
                    {
                        n.next=temp;
                        n.pre=null;
                        temp.pre=n;
                        head=n;
                        counterqueue++;
                        System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
 
                    }
                    
                    else if(n.data<temp.data && swapindication==0)
                    {
                        Node tempnext=temp.next;
                                n.next=tempnext;
                                n.pre=temp;
                                temp.next=n;
                                tempnext.pre=n;
                                counterqueue++;
                                System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
                    }
    
                    swapindication=0;
                }
                
                else
                {
                    if(n.data>tail.data)
                    {
                        n.next=tail;
                        tail.pre=n;
                        head=n;
                        counterqueue++;
                        System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
                    }
                    
                    else
                    {
                        tail.next=n;
                        n.pre=tail;
                        tail=n;
                        counterqueue++;
                        System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
                    }
                }
            }
            
            else
            {
                tail=n;
                head=tail;
                counterqueue++;
                System.out.println("Successfully Enqueued Data Into The Queue -- Data: "+ n.data+ " N.Next: "+ n.next +" ");
            }
        }
        
        else    
        {
            System.out.println("Sorry Queue Is Full.\n");
        }
        
    }
    
        
    
    public Node pop()
    {
        
        if (!isEmptystack())
        {
            if(head.next!=null)
            {
                Node temp=head;
                head=head.next;
                head.pre=null;
                counterstack--;
                
                System.out.println("Successfully Poped Data From The Stack -- Data: "+ temp.data+ " N.Next: "+ temp.next +" ");
                return temp;
            }
            else
            {
                Node temp=head;
                head=head.next;
                counterstack--;
                System.out.println("Successfully Poped Data From The Stack -- Data: "+ temp.data+ " N.Next: "+ temp.next +" ");
                return temp;
            }
        }
        else
        {
            System.out.println("Cannot Pop Stack is Empty.\n");
            return null;
        }
    }
    
    
    public Node dequeue()
    {
        
        if (!isEmptyqueue())
        {
            if(head.next!=null)
            {
                Node temp=head;
                head=head.next;
                head.pre=null;
                counterqueue--;
                
                System.out.println("Successfully Dequeued Data From The Queue -- Data: "+ temp.data+ " N.Next: "+ temp.next +" ");
                return temp;
            }
            else
            {
                Node temp=head;
                head=head.next;
                tail=tail.next;
                counterqueue--;
                System.out.println("Successfully Dequeued Data From The Queue -- Data: "+ temp.data+ " N.Next: "+ temp.next +" ");
                return temp;
            }
        }
        else
        {
            System.out.println("Cannot Dequeue Queue is Empty.\n");
            return null;
        }
    }
    
    
    public Node dequeuecircular()
    {
        
        if (!isEmptyqueue())
        {
            if(head.next!=null)
            {
                Node temp=head;
                head=head.next;
                head.pre=null;
                circulardequeue++;
                
                System.out.println("Successfully Dequeued Data From The Queue -- Data: "+ temp.data+ " N.Next: "+ temp.next +" ");
                return temp;
            }
            else
            {
                Node temp=head;
                head=head.next;
                tail=tail.next;
                circulardequeue++;
                System.out.println("Successfully Dequeued Data From The Queue -- Data: "+ temp.data+ " N.Next: "+ temp.next +" ");
                return temp;
            }
        }
        else
        {
            System.out.println("Cannot Dequeue Queue is Empty.\n");
            return null;
        }
    }
    
    
    public void printstack()
    {
        Node Temp=head;
        
        System.out.println("\n\nPrinting Data In TheStacks\n");
        
        if(head==null)
        {
            System.out.println("No Data In Stack To Print\n");
            return;
        }
        
        while(Temp.next!=null)
        {
            System.out.println("Data: "+Temp.data+" Next: "+Temp.next);
            Temp=Temp.next;
        }
        
        System.out.println("Data: "+Temp.data+" Next: "+Temp.next);
    }
    
    
    public Node gethead()
    {
        if(head!=null)
        return head;
        else
            return null;
    }
    
    public Node gettail()
    {
        if(tail!=null)
            return tail;
        else
            return null;
    }
    
    public void searchItem(int data)
    {
        Node temp=head;
        
        if(head==null)
        {
            System.out.println("Sorry No Queue Exists \n");
            return;
        }
            
        
        while(temp!=tail && temp.data!=data)
        {
            temp=temp.next;
        }
        
        if(temp.data==data)
            System.out.println("Data Found: "+temp.data+"\tAt Queue Address: "+temp);
       
        else
            System.out.println("No Data Found In The Queue");
            
    }
    
    
    
    
    
    public void sort()
    {
        
        if(head==null)
        {
            System.out.println("\nNo Queue Exist\n");
            return;
        }
        
        if(head.next==null)
        {
            System.out.println("\nOnly Single Data So No Sorting Required");
            return;
        }
        
        
     
       for(Node headcopy=head; headcopy!=null; headcopy=headcopy.next)
        {
            Node temp=headcopy;
            
            //for(Node copy=head.next; copy!=tail ; copy=copy.next)
            while(temp!=null && temp.pre!=null && temp.data<temp.pre.data)
            {
                    
                if(temp.next==null) // last node
                {
                    if(temp.pre!=head)  //not first node;
                    {
                        Node tempo=temp.pre.pre;
                        Node tempp=temp.pre;
                        
                        tempo.next=temp;
                        temp.pre=tempo;
                        temp.next=tempp;
                        tempp.pre=temp;
                        tempp.next=null;
                        tail=tempp;
                        
                    }
                   
                    else      //first node
                    {
                        Node tempo=temp.pre;
                        
                        temp.next=tempo;
                        tempo.pre=temp;
                        temp.pre=null;
                        tempo.next=null;
                        head=temp;
                        tail=tempo;
                    }     
                }


                       
                    
                    
                else if(temp.next!=null )  //temp is not a last node
                {
                    if(temp.pre==head) //temp is a second last node 
                    {
                        Node tempo=temp.next;
                        Node temppre=temp.pre;
                           
                        tempo.pre=temppre;
                        temppre.next=tempo;
                        temppre.pre=temp;
                        temp.next=temppre;
                        temp.pre=null;
                        head=temp;
                    }
                        
                        
                    else        //temp is not a second node nor last node
                    {
                        Node TempPre=temp.pre;
                        Node PreTempPre=temp.pre.pre;
                        Node TempNext=temp.next;
                        
                        
                        PreTempPre.next=temp;
                        temp.pre=PreTempPre;
                        TempNext.pre=TempPre;
                        TempPre.next=TempNext;
                        temp.next=TempPre;
                        TempPre.pre=temp;
                                
                    }
                }
            }
        }
    }
    
    
    public Node getmaxpriority()
    {
        return head;
    }
    
    public void printqueue()
    {
        Node Temp=head;
        
        
        System.out.println("\n\nPrinting Data In The Queue\n");
        
        if(head==null)
        {
	    System.out.println("Sorry No Queue To Print\n");
            return;
        }
        
        while(Temp!=tail)
        {
            System.out.println("Data: "+Temp.data+" Next: "+Temp.next);
            Temp=Temp.next;
        }
        
        System.out.println("Data: "+Temp.data+" Next: "+Temp.next);
        
    }
    
    
   
    
    public static void main(String[] args) 
    {
    
        System.out.println("\n\nImplementation Of Stack Using Linked List\n\n");
        K163618 stackobj =new K163618();
     
        stackobj.push(10);
        stackobj.pop();
        stackobj.push(786);     
        stackobj.push(51);
        stackobj.push(52);
        stackobj.push(53);
        stackobj.push(44);
        stackobj.pop();
        stackobj.printstack();
        
        
        
        System.out.println("\n\nImplementation Of Queues Using Linked List\n\n");
        K163618 queueobj= new K163618();
        
        queueobj.enqueue(10);
        queueobj.dequeue();

        System.out.println("\n\n");
        queueobj.enqueue(786);
        queueobj.enqueue(51);
        queueobj.enqueue(52);
        queueobj.enqueue(53);
        queueobj.enqueue(44);
        System.out.println("\n\n");
        queueobj.dequeue();
        queueobj.enqueue(59);
        queueobj.enqueue(777);
        queueobj.enqueue(555);
        
        Node temp=queueobj.gethead();
        if(temp!=null)
        {
            System.out.println("\n\nThe Data On Head is:\t"+queueobj.gethead().data+ "\tAddress Of Head Of The Queue is:\t"+queueobj.gethead());
            System.out.println("\nThe Data On Tail is:\t"+queueobj.gettail().data+ "\tAddress Of Tail Of The Queue is:\t"+queueobj.gettail());
        }
        else
        {
            System.out.println("\n\nThe Data On Head is:\t"+0+ "\tAddress Of Head Of The Queue is:\t"+queueobj.gethead());
            System.out.println("\nThe Data On Tail is:\t"+0+ "\tAddress Of Tail Of The Queue is:\t"+queueobj.gettail());
        }
        
        queueobj.printqueue();
        
        Scanner myobj= new Scanner(System.in);
  
        System.out.println("\n\nEnter Which Item You Want To Search:\t");
        int data=myobj.nextInt();
        
        queueobj.searchItem(data);
        
        System.out.print("\n\n----------------------- Before Sorting ----------------------------------");
        queueobj.printqueue();
        
        queueobj.sort();
        
        System.out.print("\n\n----------------------- After Sorting ----------------------------------");
        queueobj.printqueue();
        
        
        
       System.out.println("\n\nImplementation Of Priority Queues Using Linked List\n\n");
       K163618 priorityqueueobj=new K163618();
        
       priorityqueueobj.enqueuepriority(10);
       priorityqueueobj.enqueuepriority(9);
       priorityqueueobj.enqueuepriority(1);
       priorityqueueobj.enqueuepriority(17);
       priorityqueueobj.enqueuepriority(15);
       priorityqueueobj.enqueuepriority(20);
       priorityqueueobj.enqueuepriority(17);
       priorityqueueobj.enqueuepriority(0);
       priorityqueueobj.enqueuepriority(12);
       priorityqueueobj.printqueue();
        
       priorityqueueobj.getmaxpriority();
        
       
       
       System.out.println("\n\nImplementation Of Circular Queues Using Linked List\n\n");
       K163618 circularqueueobj=new K163618(); 
        
       circularqueueobj.enqueuecircular(10);
       circularqueueobj.enqueuecircular(10);
       circularqueueobj.enqueuecircular(10);
       circularqueueobj.enqueuecircular(10);
       circularqueueobj.printqueue();
       
       circularqueueobj.dequeuecircular();
       circularqueueobj.enqueuecircular(100);
       circularqueueobj.enqueuecircular(55);
       circularqueueobj.printqueue();
       
    }
    
}
