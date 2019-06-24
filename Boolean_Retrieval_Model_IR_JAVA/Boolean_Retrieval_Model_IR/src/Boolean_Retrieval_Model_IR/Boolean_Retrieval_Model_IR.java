package Boolean_Retrieval_Model_IR;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFrame;




public class Boolean_Retrieval_Model_IR extends JFrame
{
    
   
   static String term="",docID="",POS="",result=""; 
   static int docId,pos,occurence=0;
   static int resultset[]= new int [50]; 
   static int j=0;
   
   
   static void print(String result)
   {
       Result_page rp= new Result_page();
       rp.setVisible(true);
       
       rp.setField(result);
   }
   
   static void printresult(int size, LinkedList<Integer> p1, LinkedList<Integer> p2,String op, boolean not1, boolean not2)
   {
        int not1Postlist[]= new int[50-p1.size()];
        int not2Postlist[]= new int[50-p2.size()];
        
        //System.out.println(not1+ "  "+ not2);
        
        boolean found=false;
        int indexing=0;
        boolean processed=false;
        
           if(op.equals("and"))
           {
                if(p1.size()==size)
                {
                    processed=true;
                    if(not1!=true && not2!=true)
                    {
                        for(int k=0;k<size;k++)           
                        {
                            if(p2.contains(p1.get(k)))
                            {  //System.out.println(queryPostingList[0].get(k));
                               // System.out.println(p2.get(k)); 
                              resultset[j++]= p2.get(k);
                            }
                        }
                    }
                    
                    else if(not1==true && not2==true)
                    {
                       
                            for(int b=1;b<=50;b++)
                            {
                                for(int k=0;k<p1.size();k++)           
                                {         
                                    if(b==p1.get(k))
                                    {
                                        found=true;
                                        break;
                                    }
                                }
                                if(found==false)
                                {
                                    not1Postlist[indexing++]=b;
                                    
                                }  
                                found=false;
                            }
                            indexing=0;
                            
                            
                            
                            
                            for(int b=1;b<=50;b++)
                            {
                                for(int k=0;k<p2.size();k++)           
                                {         
                                    if(b==p2.get(k))
                                    {
                                        found=true;
                                        break;
                                    }
                                }
                                if(found==false)
                                {
                                    not2Postlist[indexing++]=b;
                                }  
                                found=false;
                            }
                            indexing=0;
                            
                            
                            
                            for (int i=0; i<not1Postlist.length-1; i++)
                            {
                                for (int j=0; j<not2Postlist.length-1; j++)
                                {
                                    if (not2Postlist[j]==not1Postlist[i]) 
                                    { 
                                        resultset[j++]=not2Postlist[j];
                                    }
                                    
                                 }
                            }                            
                    }
                    
                    else if(not1==true && not2==false)
                    {
                        for(int b=1;b<=50;b++)
                        {
                            for(int k=0;k<p1.size();k++)           
                            {         
                                if(b==p1.get(k))
                                {
                                    found=true;
                                   // break;
                                }
                            }
                            if(found==false)
                            {
                                not1Postlist[indexing++]=b;
                            }  
                            found=false;
                        }
                        indexing=0;
                            
                        for(int i=0;i<not1Postlist.length;i++)
                        {
                            if(not1Postlist[i]!=0)
                            {
                                System.out.print(not1Postlist[i]+" ");
                            }
                        }
                        
                        for(int m =0;m<not1Postlist.length;m++)
                        {
                            if(p2.contains(not1Postlist[m]))
                            {  //System.out.println(queryPostingList[0].get(k));
                              resultset[j++]=not1Postlist[m];
                            }
                        }
                        
                    }
                    else
                    {
                        for(int b=1;b<=50;b++)
                        {
                            for(int k=0;k<p2.size();k++)           
                            {         
                                if(b==p2.get(k))
                                {
                                    found=true;
                                    break;
                                }
                            }
                            if(found==false)
                            {
                                not2Postlist[indexing++]=b;
                            }  
                            found=false;
                        }
                        indexing=0;
                        
                        
                    }
                   

                    for(int l =0; l<resultset.length;l++)
                    {   
                        if(resultset[l]==0)
                            break;
                        result =result + Integer.toString(resultset[l]);
                        result+=" "; 
                       // System.out.print(" "+ resultset[l]);
                    } 
                    System.out.println(result);
                    print(result);
                    System.out.println(result);
                    j=0;
                }
                
                else if(processed==false && size==p2.size())
                {                    
                   
                    if(not1!=true && not2!=true)
                    {
                        for(int k=0;k<p2.size();k++)           
                        {
                            if(p1.contains(p2.get(k)))
                            {  
                              resultset[j++]= p2.get(k);
                            }
                        }
                    }
                    
                    else if(not1==true && not2==true)
                    {
                       
                            for(int b=1;b<=50;b++)
                            {
                                for(int k=0;k<p1.size();k++)           
                                {         
                                    if(b==p1.get(k))
                                    {
                                        found=true;
                                        break;
                                    }
                                }
                                if(found==false)
                                {
                                    not1Postlist[indexing++]=b;
                                }  
                                found=false;
                            }
                            indexing=0;
                            
                            
                            
                            
                            for(int b=1;b<=50;b++)
                            {
                                for(int k=0;k<p2.size();k++)           
                                {         
                                    if(b==p2.get(k))
                                    {
                                        found=true;
                                        break;
                                    }
                                }
                                if(found==false)
                                {
                                    not2Postlist[indexing++]=b;
                                }
                                found=false;
                            }
                            indexing=0;
                            
                            
                            
                            for (int i=0; i<not1Postlist.length-1; i++)
                            {
                                for (int j=0; j<not2Postlist.length-1; j++)
                                {
                                    if (not2Postlist[j]==not1Postlist[i]) 
                                    { 
                                        resultset[j++]=not2Postlist[j];
                                    }
                                    
                                 }
                            }                            
                    }
                    
                    else if(not1==true && not2==false)
                    {
                        for(int b=1;b<=50;b++)
                        {
                            for(int k=0;k<p1.size();k++)           
                            {         
                                if(b==p1.get(k))
                                {
                                    found=true;
                                    break;
                                }
                            }
                            if(found==false)
                            {
                                not1Postlist[indexing++]=b;
                            }  
                            found=false;
                        }
                        indexing=0;
                            
                      
                        for(int m =0;m<not1Postlist.length;m++)
                        {
                            if(p2.contains(not1Postlist[m]))
                            {  //System.out.println(queryPostingList[0].get(k));
                              resultset[j++]=not1Postlist[m];
                            }
                        }
                        
                    }
                    else
                    {
                        for(int b=1;b<=50;b++)
                        {
                            for(int k=0;k<p2.size();k++)           
                            {         
                                if(b==p2.get(k))
                                {
                                    found=true;
                                    break;
                                }
                            }
                            if(found==false)
                            {
                                not2Postlist[indexing++]=b;
                            }  
                            found=false;
                        }
                        indexing=0;
                        
                        for(int m =0;m<not2Postlist.length;m++)
                        {
                            if(p1.contains(not2Postlist[m]))
                            {  //System.out.println(queryPostingList[0].get(k));
                              resultset[j++]=not2Postlist[m];
                            }
                        }
                        
                    }
                    

                    for(int l =0; l<resultset.length;l++)
                    {   
                        if(resultset[l]==0)
                            break;

                        result =result + Integer.toString(resultset[l]);
                        result+=" "; 
                        //System.out.print(" "+ resultset[l]);
                    } 
                    System.out.println(result);
                    print(result);
                    
                    j=0;
                }
           }
           
           
           
           else if(op.equals("or"))
           {
               j=0;
               boolean checkin=false;
               if(not1!=true && not2!=true)
               {
                    for(int i=0;i<p1.size();i++)
                        resultset[j++]=p1.get(i);

                    for(int m=0;m<p2.size();m++)
                    {
                        for(int n=0;n<p1.size();n++)
                        {                            
                            if(resultset[n]==p2.get(m))
                            {
                                checkin=true;
                            }                            
                        }
                        if(checkin==false)
                             resultset[j++]=p2.get(m);
                        
                        checkin=false;
                    } 
               }
               
               else if(not1==true && not2==true)
               {
                    for(int b=1;b<=50;b++)
                    {
                        for(int k=0;k<p1.size();k++)           
                        {         
                            if(b==p1.get(k))
                            {
                                found=true;
                                break;
                            }
                        }
                        if(found==false)
                        {
                            not1Postlist[indexing++]=b;
                            
                        }  
                       found=false;
                    }
                    indexing=0;
                    
                    
                    
                    
                   for(int b=1;b<=50;b++)
                    {
                        for(int k=0;k<p2.size();k++)           
                        {         
                            if(b==p2.get(k))
                            {
                                found=true;
                                break;
                            }
                        }
                        if(found==false)
                        {
                            not2Postlist[indexing++]=b;
                        }  
                        found=false;
                    }
                    indexing=0;
                    
                    
                    for(int i=0;i<not1Postlist.length;i++)
                    {
                        resultset[j++]=not1Postlist[i];
                    }
                   
                    for(int k=0;k<not2Postlist.length-1;k++)
                    {
                        for(int i=0;i<not1Postlist.length-1;i++)
                        {
                            if(not2Postlist[k]==not1Postlist[i])
                            {
                                checkin=true;
                                break;
                            }
                            
                        }  
                        if(checkin==false)
                                resultset[j++]=not2Postlist[k];
                       checkin=false;
                    }             
               }
               
               
               else if(not1!=true && not2==true)
               {
                    
                   for(int b=1;b<=50;b++)
                    {
                        for(int k=0;k<p2.size();k++)           
                        {         
                            if(b==p2.get(k))
                            {
                                found=true;
                                break;
                            }
                        }
                        if(found==false)
                        {
                            not2Postlist[indexing++]=b;
                        }  
                        found=false;
                    }
                    indexing=0;
                    
                    
                    for(int i=0;i<p1.size();i++)
                    {
                        resultset[j++]=p1.get(i);
                    }
                    for(int k=0;k<not2Postlist.length;k++)
                    {
                        for(int i=0;i<p1.size();i++)
                        {
                            if(not2Postlist[k]==p1.get(i))
                            {
                                checkin=true;
                                break;
                            }
                            if(checkin==false)
                                resultset[j++]=not2Postlist[k];
                        }  
                    }           
               }
                
               else
               {
                   for(int b=1;b<=50;b++)
                    {
                        for(int k=0;k<p1.size();k++)           
                        {         
                            if(b==p1.get(k))
                            {
                                found=true;
                                break;
                            }
                        }
                        if(found==false)
                        {
                            not1Postlist[indexing++]=b;
                        }  
                        found=false;
                    }
                    indexing=0;
                    
                    
                    for(int i=0;i<p2.size();i++)
                    {
                        resultset[j++]=p2.get(i);
                    }
                    for(int k=0;k<not1Postlist.length;k++)
                    {
                        for(int i=0;i<p2.size();i++)
                        {
                            if(not1Postlist[k]==p2.get(i))
                            {
                                checkin=true;
                                break;
                            }
                            if(checkin==false)
                                resultset[j++]=not1Postlist[k];
                        }  
                    }
               }
               
                for(int l =0; l<resultset.length;l++)
                {   
                    if(resultset[l]==0)
                        break;

                    //System.out.print(" "+ resultset[l]);
                    result =result + Integer.toString(resultset[l]);
                    result+=" "; 
                } 
                System.out.println(result);
                print(result);
                j=0;
           }
            
           resultset=new int[50];
            
         
        }     
   
   
   
   
   static void printresult(int size, LinkedList<Integer> p1, LinkedList<Integer> p2, LinkedList<Integer>p3,String op, String op1, boolean not1, boolean not2, boolean not3)
   {
       
   }
   
   
   static void TermDocPos(String Line)
   {
      // System.out.println(Line);
        for(char i: Line.toCharArray() )
        {
            if(i=='$')
            {
                occurence+=1;
                
                if(occurence==2)
                {
                    docId= Integer.parseInt(docID);
                }
            }
            else if(i!='$' && occurence ==0)
            {
                term=term+i;                
            }
            else if(occurence==1 && i!='$')
            {
                docID+=i;
            }
            else
            {
                POS+=i;
            }
        }
        pos=Integer.parseInt(POS);
        
    }
   
  static String path= "C:\\Users\\Murtaza\\Desktop\\Projects\\Boolean_Retrieval_Model_IR_JAVA\\";
   
   public static void search() throws IOException{
       
        
       /* BufferedWriter fileWriter = new BufferedWriter(new FileWriter(path+ "FinalFile\\Tokenz.txt"));
       
        int DocID=1,location=0;
        
       Scanner scan;
       
       File file,stopword; 
       stopword= new File(path + "Stopword-List.txt");
       
       scan= new Scanner(stopword);
       scan.useDelimiter("\\Z"); 
       String stopword_List= scan.next();
       
       StringTokenizer stopwordTokenizerz = new StringTokenizer(stopword_List, "\n");
      
       Boolean flag=false;
       
       String content="";
       while(DocID<=50) 
       {  
           
            file = new File(path +"ShortStories\\"+DocID+".txt");
            
            scan = new Scanner(file);  
            scan.useDelimiter("\\Z"); 
            if(scan.hasNext())
            content = scan.next();
            
            //content=content.replace("-","");
            //content=content.replace("?", "");
            content=content.replace("'","");
            
           StringTokenizer multiTokenizer = new StringTokenizer(content, "(,+!:\"\r?\n$.-*;) ");
           //String check,check1;
            while (multiTokenizer.hasMoreTokens())
            {
//                 check = multiTokenizer.nextToken();
//                 check1=check.toLowerCase();
//                //System.out.println(check);
//                while(stopwordTokenizerz.hasMoreTokens())
//                {
//                    String x=stopwordTokenizerz.nextToken();
//                    
//                     if(check1.equals(x))
//                    {
//                        flag=true;
//                        break;
//                    }
//                }
//                
//                if(flag==false)
//                {
                    fileWriter.write(multiTokenizer.nextToken().toLowerCase());
                    fileWriter.write("$"+DocID+"$"+location++);  

                    fileWriter.newLine();
                    
//                }
//                flag=false;
            }
            
            DocID++;
            location=0;
        }
        
    
     
       fileWriter.close();
       */
       
       
      
        Map<String,LinkedList<Integer>> Dictionary = new HashMap<>();
        LinkedList<Integer> postinglist = new LinkedList<>();
           
         
        BufferedReader fileReader = new BufferedReader(new FileReader(path +"FinalFile\\Tokenz.txt"));
        BufferedWriter DictWriter = new BufferedWriter(new FileWriter(path + "FinalFile\\Dictionary.txt"));
        BufferedReader stopwordReader = new BufferedReader(new FileReader(path+ "Stopword-List.txt"));
       
        
        int count=1,index=0,flags=0;
        String Line= fileReader.readLine();
        String stopwordLine= stopwordReader.readLine();
        //System.out.println(stopwordLine);
        
        
        
        while(Line != null)
        {
            TermDocPos(Line);
            
           // index= term.hashCode();
            //System.out.println(index+"  %" +term+"  %"+docId);
            while(stopwordLine!=null)
            {
               
                if(stopwordLine.equals(term.toLowerCase()))
                {
                   flags=1; 
                    break;
                }  
                stopwordLine= stopwordReader.readLine();
            }
            
            
            String key = term.toLowerCase();
            if(flags!=1)
            {
               
                if(!Dictionary.containsKey(key)) 
                {
                    postinglist= new LinkedList();
                    postinglist.addLast(docId);
                    Dictionary.put(key,postinglist);
                } 
                else 
                {
//                    postinglist.addLast(docId);
                   postinglist= Dictionary.get(key);
                   
                   
                   if(!postinglist.contains(docId))
                   {
                        postinglist.addLast(docId);
                        //System.out.println(term);
                        Dictionary.put(key,postinglist);
                   }
                }
                //Dictionary.put(term, postinglist);
                
                
            }
            
            flags=0;
           // System.out.println("Term: "+ term + " -> Doc_Id: "+ docID +" -> Position: "+ POS);
            term=""; docID=""; POS=""; occurence=0;
           
            
            Line= fileReader.readLine();
            count++;
        }
        
        int i=1;
        
        for(Map.Entry<String, LinkedList<Integer>> temp : Dictionary.entrySet())
        {
           // System.out.println("term #"+ i++ +temp +"  " +temp.getValue()); // Or something as per temp defination. can be used
            DictWriter.write(temp.toString());
            DictWriter.newLine();
        }

        
         
        // Query breakfast and love
        
        
        
        int d=0,size;
        int resultset[] = new int[50];
        int j=0;
        String term1=gui.term1 ,term2=gui.term2, term3=gui.term3, operation1=gui.operation1, operation2=gui.operation2;
        boolean not1=gui.not1, not2=gui.not2, not3=gui.not3;
        
        if(term1.equals(""))
        {            
            
            if(!term2.equals("") && !term3.equals(""))
            {
                LinkedList<Integer> queryPostingList [] = new LinkedList[2];
                queryPostingList[0]=Dictionary.get(term2);
                queryPostingList[1]=Dictionary.get(term3);
                
                if(queryPostingList[0]!=null && queryPostingList[1]!=null)
                {
                    size=Integer.min(queryPostingList[0].size(), queryPostingList[1].size());
                    printresult(size, queryPostingList[0], queryPostingList[1],operation2, not2, not3);
                }
            }
        }
        
        else if(term2.equals(""))
        {      
            //error
           /* LinkedList<Integer> queryPostingList [] = new LinkedList[2];
            queryPostingList[0]=Dictionary.get(term1);
            queryPostingList[1]=Dictionary.get(term3);
             int size=Integer.min(queryPostingList[0].size(), queryPostingList[2].size());*/
            
            
        }
        
      else if(term3.equals(""))
        {   
            if(!term1.equals("") && !term2.equals(""))
            {
                LinkedList<Integer> queryPostingList [] = new LinkedList[2];
                queryPostingList[0]=Dictionary.get(term1);
                queryPostingList[1]=Dictionary.get(term2);     
                if(queryPostingList[0]!=null && queryPostingList[1]!=null)
                {       
                    size=Integer.min(queryPostingList[0].size(), queryPostingList[1].size());
                    printresult(size, queryPostingList[0], queryPostingList[1],operation1, not1, not2);
                }
            }
            else
            {
                //error
            }
        }
        
        else
        {
            LinkedList<Integer> queryPostingList [] = new LinkedList[3];
            queryPostingList[0]=Dictionary.get(term1);
            queryPostingList[1]=Dictionary.get(term2); 
            queryPostingList[2]=Dictionary.get(term3); 
            size=Integer.min(queryPostingList[0].size(), queryPostingList[1].size());
            size=Integer.min(size,queryPostingList[2].size());
            printresult(size, queryPostingList[0], queryPostingList[2], queryPostingList[3],operation1,operation2, not1,not2,not3);
        }
       
        
       
        
      
         
         
         
       //------------------------------------------------------------------
       
     
       BufferedReader reader = null; 
         
        BufferedWriter writer = null;
                 
        
        ArrayList<String> lines = new ArrayList<String>();
         
        try
        {
            
            reader = new BufferedReader(new FileReader(path + "FinalFile\\Dictionary.txt"));
             
            
            String currentLine = reader.readLine();
             
            while (currentLine != null) 
            {
                lines.add(currentLine);
                 
                currentLine = reader.readLine();
            }
             
            
            Collections.sort(lines);
            
            writer = new BufferedWriter(new FileWriter(path + "FinalFile\\Dictionary.txt"));
             
            
            for (String line : lines)
            {
                writer.write(line);
                 
                writer.newLine();
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        finally
        {
            //Closing the resources
             
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
                 
                if(writer != null)
                {
                    writer.close();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
       
       writer.close();
       reader.close();
       
   }
   
    static homePage gui = new homePage();
    
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        gui.setVisible(true);
     
      
        //System.out.println("skfgf");
       
        
        
    }      

    
}