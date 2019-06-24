
package Node;

/**
 *
 * @author Murtaza
 */

class Node{
        
        
        Node next;
        String name;
        int pCost;
        
        Node()
        {
            next=null;
            name="";
            pCost=0;
        }
        
        Node (String name, int cost)
        {
            this.next = new Node(); 
            this.name = name;
            pCost =  cost;
        }   
        
        
        
    
    public static void main(String[] args) {
        Node obj[];
        
        obj =  new Node[20];
        
        //Arad Neigbours
        
        
        obj[0] = new Node("Arad", 0);
        obj[0].next= new Node("Zerind", 75);
        obj[0].next.next= new Node("Sibiu",140);
        obj[0].next.next.next= new Node("Timiseaura",118);
        
               
        //Zerind Neighbours
        
        obj[1] = new Node("Zerind",0);
        obj[1].next = new Node("Arad",75);
        obj[1].next.next = new Node("Oradea",71);
       
       
        //Oradea  Neighbours
        
        obj[2] = new Node("Oradea",0);
        obj[2].next = new Node("Zerind",71);
        obj[2].next.next = new Node("Sibiu",151);
       
       
        //Sibiu  Neighbours
        
        obj[3] = new Node("Sibiu",0);
        obj[3].next = new Node("Arad",140);
        obj[3].next.next = new Node("Oradea",151);
        obj[3].next.next.next = new Node("Fagaras",99);
        obj[3].next.next.next.next = new Node("Rimnicu Vilcea",80);
       

        //Timisoara  Neighbours
 
        obj[4] = new Node("Timisoara",0);
        obj[4].next = new Node("Arad",118);
        obj[4].next.next = new Node("Lugoj",111);
        
        
        
        
        //Lugoj  Neighbours
        
        obj[5] = new Node( "Lugoj",0);
        obj[5].next= new Node( "Timisoara",111);
        obj[5].next.next= new Node( "Mehadia", 70);
        
       
        //Mehadia  Neighbours
        
        obj[6] = new Node( "Mehadia", 0);
        obj[6].next= new Node( "Lugoj",70);
        obj[6].next.next= new Node( "Drobeta",75);
        
        
        //Drobeta  Neighbours
        
        obj[7] = new Node( "Drobeta",0);
        obj[7].next= new Node( "Craiova", 120);
        obj[7].next.next= new Node( "Mehadia",75);
        
        
        //Craiova  Neighbours
        
        obj[8] = new Node( "Craiova",0);
        obj[8].next= new Node( "Drobeta",120);
        obj[8].next.next= new Node( "Rimnicu Vilcea",146);
        obj[8].next.next.next= new Node( "Pitesti",138);
        
        
        //Rimnicu Vilcea  Neighbours
        
        obj[9] = new Node( "Rimnicu Vilcea",0);
        obj[9].next= new Node( "Sibiu",80);
        obj[9].next.next= new Node( "Craiova",146);
        obj[9].next.next.next= new Node( "Pitesti",97);
        
        //Pitesti  Neighbours
        
        obj[10]=new Node("Pitesti", 0);        
        obj[10].next=new Node("Rimnicu Vilcea", 97);       
        obj[10].next.next= new Node( "Craiova",138);
        obj[10].next.next.next= new Node( "Bucharest",101);
        
        
        //Bucharest  Neighbours
        
        obj[11] = new Node( "Bucharest",0);
        obj[11].next= new Node( "Giurgiu",90);
        obj[11].next.next= new Node( "Urziceni",85);
        obj[11].next.next.next= new Node( "Fagaras",211);
        obj[11].next.next.next.next= new Node( "Pitesti",101);
        
        
        
        //Giurgiu  Neighbours
        
        obj[12] = new Node( "Giurgiu", 0);
        obj[12].next=new Node( "Bucharest",90);
        
        //Urziceni  Neighbours
        
        obj[13] = new Node( "Urziceni",0);
        obj[13].next= new Node( "Bucharest",85);
        obj[13].next.next= new Node( "Hirsova",98);
        obj[13].next.next.next= new Node( "Vaslui",142);
        
        
        //Hirsova  Neighbours
        
        obj[14] = new Node( "Hirsova",0);
        obj[14].next= new Node( "Urziceni",98);
        obj[14].next.next= new Node( "Eforie",86);
        
        
        //Eforie  Neighbours
        
        obj[15] = new Node( "Eforie",0);
        obj[15].next= new Node( "Hirsova",86);
        
        
        //Valsui Neighbours
        
        obj[16] = new Node( "Valsui",0);
        obj[16].next= new Node( "Urziceni",142);
        obj[16].next.next= new Node( "Iasi",92);
        
        
        //Iasi Neighbours
        
        obj[17] = new Node( "Iasi",0);
        obj[17].next= new Node( "Valsui",92);
        obj[17].next.next= new Node( "Neamt", 87);
        
        
        //Neamt Neighbours
        
        obj[18] = new Node( "Neamt", 0);
        obj[18].next= new Node( "Iasi",87);
        
        
        //Fagaras Neighbours
        
        obj[19] = new Node( "Fagaras",0);
        
        obj[19].next= new Node( "Sibiu", 99);
        obj[19].next.next= new Node( "Bucharest", 211);
        
       
        // print
       /* 
        for(int i=0;i<20;i++)
        {
          
            System.out.println("\n\nNeighbours of "+ obj[i].name);
            obj[i]= obj[i].next;
            
            while(obj[i].next!=null)
            {
                System.out.println(obj[i].name + "---" + obj[i].pCost);
                obj[i]= obj[i].next;
            }
        } */
       
       // BFS
       
       Node Queue[]= new Node[20];
       String Visited[]=  new String[20];
       
       Queue[0]= obj[0];
       int i=0,j=0;
       int k=0,flag=0;
       
       while(Queue!=null) 
       {
           Node dequeue = Queue[k];
           Visited[j++]= dequeue.name;
           
           if(dequeue.name == "Bucharest")
           {
               break;
           }
           
           dequeue=dequeue.next;
           i--;
           while(dequeue.next!=null)
           {
               for(int l=0;l<Visited.length;l++)
               {  
                    if(dequeue.name==Visited[l])
                    {
                        flag=1;
                    }
               }
               
               if(flag==0)
               {
                    i++;
                    Queue[i]= dequeue; 
               }
               dequeue=dequeue.next;
               flag=0;
            }
       }
       
       
        
        
    }
}
