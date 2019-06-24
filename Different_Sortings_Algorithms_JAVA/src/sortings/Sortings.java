/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortings;



public class Sortings {

    public void quicksort(int arr[], int start, int end)
    {
        if(start<end)
        {
            int pindex= partition(arr, start, end);
            
            quicksort(arr,start,pindex-1);
            quicksort(arr,pindex+1,end);
        }
    }

    public int partition(int arr[], int start, int end)
    {
        int pivot=arr[end];
        int pindex=start;
        int i;
        
        for( i=start;i<end;i++)
        {
            if(arr[i]<pivot)
            {
                int temp=arr[pindex];
                arr[pindex]=arr[i];
                arr[i]=temp;
                pindex++;
            }
        }
        int temp=arr[pindex];
        arr[pindex]=arr[end];
        arr[end]=temp;
        
        return pindex;
    }
    
    
    public void shellsort(int arr[])
    {
        int i,j, gap=arr.length/2;
        
        while(gap!=0)
        {
            for(i=gap;i<arr.length;i++)
            {
                int temp=arr[i];
                j=i;
                while(j>=gap && arr[j-gap]>temp)
                {
                    arr[j]=arr[j-gap];
                    j=j-gap;
                }
                
                arr[j]=temp;
            }
            
            gap/=2;        
        }
    }
    
    
    
    
    public void mergesort(int arr[])
    {
	int n=arr.length;
        
        if(n<2)
            return;
        
        int mid=n/2;
        
       int arrleft[]=new int[mid];
       int arrright[]=new int[mid];
       
       for(int i=0;i<mid;i++)
           arrleft[i]=arr[i];
       
       for(int i=mid;i<n;i++)
           arrright[i-mid]=arr[i];
       
       mergesort(arrleft);
       mergesort(arrright);
       merge(arrleft,arrright,arr);
    }

    public void merge(int  arrleft[], int arrright[], int arr[])
    {
        int i=0,j=0,k=0;
        
        while(i<arrleft.length && j<arrright.length)
        {
            if(arrleft[i]<=arrright[j])
            {
                arr[k]=arrleft[i];
                i++;
            }
            else
            {
                arr[k]=arrright[j];
                j++;
            }
            k++;
        }
        
        while(i<arrleft.length)
        {
            arr[k]=arrleft[i];
            i++;
            k++;
        }
        
        while(j<arrright.length)
        {
            arr[k]=arrright[j];
            j++;
            k++;
        }
    }
    
    
    public void selectionsort(int arr[])
    {
        int i,temp,j;
        
        for(i=0;i<arr.length;i++)
        {
            temp=i;
            
            for(j=i+1;j<arr.length;j++)
            {
                if(arr[j]<arr[temp])
                {
                    temp=j;
                }
            }
            
            if(temp!=i)
            {
                int tempo=arr[i];
                arr[i]=arr[temp];
                arr[temp]=tempo;
            }
        }
    }
    
    
    
    public void insertionsort(int arr[])
    {
        int i,temp,j;
        
        for(i=1;i<arr.length;i++)
        {
            temp=i;
            
            while(temp>0 && arr[temp]<arr[temp-1])
            {
                int tempo=arr[temp];
                arr[temp]=arr[temp-1];
                arr[temp-1]=tempo;
                temp--;
            }
        }
    }
    
    
    
    public void bubblesort(int arr[])
    {
        int i,temp,j;
        
        for(i=0;i<arr.length;i++)
        {
            
            for(j=i+1;j<arr.length;j++)
            {
                if(arr[i]>arr[j])
                {
                    temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp; 
                }
            }     
        }
    }
    
    
    
    
    public void print(int arr[])
    {
        for(int i=0;i<arr.length;i++)
            System.out.print(arr[i] +" ");
    }

    public static void main(String[] args) 
    {
        int arr[]={1,9,8,7,4,6,2,4};
        
        Sortings obj = new Sortings();
        
        System.out.println("Arr");
        obj.print(arr);
        
        System.out.println("\n\nQuick");
        
        obj.quicksort(arr, 0, arr.length-1);
        
        obj.print(arr);
        
        System.out.println("\n\nShell");
        
        int arr1[]={1,9,8,7,4,6,2,4};
        
        obj.shellsort(arr1);
        
        obj.print(arr1);
       
        System.out.println("\n\nMerge");
        
        int arr2[]={1,9,8,7,4,6,2,4};
        
        obj.mergesort(arr2);
        obj.print(arr2);
        
        System.out.println("\n\nSelection");
        
        int arr3[]={1,9,8,7,4,6,2,4};
        
        obj.selectionsort(arr3);
        obj.print(arr3);
        
        
        System.out.println("\n\nInsertion");
        
        int arr4[]={1,9,8,7,4,6,2,4};
        
        obj.insertionsort(arr4);
        obj.print(arr4);
        
        
        System.out.println("\n\nBubble");
        
        int arr5[]={1,9,8,7,4,6,2,4};
        
        obj.bubblesort(arr5);
        obj.print(arr5);
        
    }
    
}
