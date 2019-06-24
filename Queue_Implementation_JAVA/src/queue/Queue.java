/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

import java.util.Scanner;
/**
 *
 * @author OWNER
 */


public class Queue {

   static int front;
   static int rear;
   static int size;
   static int arr[];
    
    
    Scanner myobj= new Scanner(System.in);
  
    Queue()
    {    
        front=-1;
        rear=0;
        System.out.print("Enter Size Of array you want to create:\t");
        size=myobj.nextInt();
        arr= new int[size];
    }
    
    
    
    public static boolean isfull(){
        if(rear==size-1)
            return true;
        else
            return false;
    }
    
    
    public static boolean isempty(){
        if(front==-1 || front==rear)
            return true;
        else
            return false;
    }
    
    
    
    public static void enqueue(int data)
    {
        if(!isfull()){
            arr[rear++]=data;
                
            if(front==-1)
                front=0;
        }
        else
        {
            System.out.println("Sorry Queue Is full cannot enqueue");
        }
    }
    
    
    public static int dequeue()
    {
        if(!isempty()){
           return arr[front++];
        }
        else
        {
            System.out.println("Sorry Queue Is empty cannot dequeue");
            return -1;
        }
    }
    
    public static int getsize(){
        return size;
    }
   
    

    public static void palindrome(int a[])
    {
        int i=0;
        int j=size-1;
        while(i!=size-1)
        {
            if(a[i]!=arr[j])
            {
                System.out.println("Not a palindrome");
                return;
            }
            
            i++;
            j--;
        }
        
        System.out.println("Its A Palindrome");
        
        
    }
    
    public static void main(String[] args) {
       
        System.out.println("Question 1");
        
        Queue obj = new Queue();

        obj.enqueue(10);
        obj.enqueue(10);
        obj.enqueue(10);
        obj.dequeue();
        obj.dequeue();
        obj.dequeue();
       // obj.enqueue(5);
        
       System.out.println("Question 2");
       
       Queue obj1= new Queue();
       
       obj1.enqueue(1);
       obj1.enqueue(0);
       obj1.enqueue(0);
       obj1.enqueue(1);
       
       int arr[]= new int [obj1.getsize()];
       
       arr[0]=obj1.dequeue();
       arr[1]=obj1.dequeue();
       arr[2]=obj1.dequeue();
       arr[3]=obj1.dequeue();
       
       obj1.palindrome(arr);
    }
    
}
