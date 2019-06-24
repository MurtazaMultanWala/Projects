/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vector_Space_Model_IR;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 *
 * @author Murtaza
 */
public class Vector_Space_Model_IR {

    /**
     * @param args the command line arguments
     */
    //static DecimalFormat dfs = new DecimalFormat();
   static String term="",docID="",POS="",path= "C:\\Users\\Murtaza\\Desktop\\Projects\\Vector_Space_Model_IR_JAVA\\"; 
   static int docId,pos,occurence=0;
  // static double decimalPlaces=100000.0;
   static void TermDocPos(String Line)
   {
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
       // System.out.println(docID+"  "+ term);
    }
    
    static void SortFile(String Filename) throws IOException
    {
        
        BufferedReader reader = null; 
         
        BufferedWriter writer = null;
                 
        //Create an ArrayList object to hold the lines of input file
         
        ArrayList<String> lines = new ArrayList<String>();
         
        try
        {
            //Creating BufferedReader object to read the input file
             
            reader = new BufferedReader(new FileReader(path +"FinalFile\\"+Filename+".txt"));
             
            //Reading all the lines of input file one by one and adding them into ArrayList
             
            String currentLine = reader.readLine();
             
            while (currentLine != null) 
            {
                lines.add(currentLine);
                 
                currentLine = reader.readLine();
            }
             
            Collections.sort(lines);
             
            writer = new BufferedWriter(new FileWriter(path+"FinalFile\\"+Filename+"Sorted.txt"));
            
             
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
    
    
    public static Map<String, Double> sortByComparator(Map<String, Double> unsortMap, final boolean order)
    {

        List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Double>>()
        {
           
            public int compare(Entry<String, Double> o1, Entry<String, Double> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }

            @Override
            public Comparator<Entry<String, Double>> reversed() {
                return Comparator.super.reversed(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Comparator<Entry<String, Double>> thenComparing(Comparator<? super Entry<String, Double>> cmprtr) {
                return Comparator.super.thenComparing(cmprtr); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <U> Comparator<Entry<String, Double>> thenComparing(Function<? super Entry<String, Double>, ? extends U> fnctn, Comparator<? super U> cmprtr) {
                return Comparator.super.thenComparing(fnctn, cmprtr); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <U extends Comparable<? super U>> Comparator<Entry<String, Double>> thenComparing(Function<? super Entry<String, Double>, ? extends U> fnctn) {
                return Comparator.super.thenComparing(fnctn); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Comparator<Entry<String, Double>> thenComparingInt(ToIntFunction<? super Entry<String, Double>> tif) {
                return Comparator.super.thenComparingInt(tif); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Comparator<Entry<String, Double>> thenComparingLong(ToLongFunction<? super Entry<String, Double>> tlf) {
                return Comparator.super.thenComparingLong(tlf); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Comparator<Entry<String, Double>> thenComparingDouble(ToDoubleFunction<? super Entry<String, Double>> tdf) {
                return Comparator.super.thenComparingDouble(tdf); //To change body of generated methods, choose Tools | Templates.
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Entry<String, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    
   
    
    
    
    public static void main(String[] args) throws IOException {
        
         
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(path+"FinalFile\\Tokenz.txt"));
                
       
        int DocID=1,location=0;
        
       Scanner scan;
       
       File file; 
     
       
       String content="";
       Map<String, Integer> wordCounts[] = new Map[50]; 
       for(int i=0;i<50;i++)        
            wordCounts[i]=new TreeMap<String, Integer>();
        
       while(DocID<=50) 
       {  
           
            file = new File(path+"ShortStories\\"+DocID+".txt");
            
            scan = new Scanner(file);  
            scan.useDelimiter("\\Z"); 
            if(scan.hasNext())
            content = scan.next();
            
           content=content.replace("'","");
            
           StringTokenizer multiTokenizer = new StringTokenizer(content, "(,+!:\"\r?\n$.-*_;) ");
           
           while (multiTokenizer.hasMoreTokens())
           {
               String next = multiTokenizer.nextToken();
                fileWriter.write(next);
                fileWriter.write("$"+DocID+"$"+location++);  
                
                fileWriter.newLine(); 
               
                next=next.toLowerCase();
                
//                if (!wordCounts[DocID-1].containsKey(next)) 
//                {
//                    wordCounts[DocID-1].put(next, 1);
//                }
//                else 
//                {
//                    wordCounts[DocID-1].put(next, wordCounts[DocID-1].get(next) + 1);
//                }
                
                
           }
            
            DocID++;
            location=0;
        }
        
    
     
       fileWriter.close();
       
       
       //Dicitionary
       
       Map<String,LinkedList<Integer>> Dictionary = new HashMap<>();
       LinkedList<Integer> postinglist = new LinkedList<>();
           
         
       BufferedReader fileReader = new BufferedReader(new FileReader(path+"FinalFile\\Tokenz.txt"));
       BufferedWriter DictWriter = new BufferedWriter(new FileWriter(path+"FinalFile\\Dictionary.txt"));
       BufferedReader stopwordReader = new BufferedReader(new FileReader(path+"Stopword-List.txt"));
      
       int count=0,index=0,flags=0;
       String Line= fileReader.readLine();
       String stopwordLine= stopwordReader.readLine();
       LinkedList<String> StopWords = new LinkedList<>();
        
       while(stopwordLine!=null)
       {
           StopWords.addLast(stopwordLine);
           stopwordLine=stopwordReader.readLine();
       }
        
       while(Line != null)
       {
           TermDocPos(Line);
            
           if(!StopWords.contains(term.toLowerCase()))
           {
                // System.out.println(Line);
                String key = term.toLowerCase();

                if (!wordCounts[docId-1].containsKey(key)) 
                {
                    wordCounts[docId-1].put(key, 1);
                }
                else 
                {
                    wordCounts[docId-1].put(key, wordCounts[docId-1].get(key) + 1);
                }


                if(!Dictionary.containsKey(key)) 
                {
                     postinglist= new LinkedList();
                     postinglist.addLast(docId);
                     Dictionary.put(key,postinglist);
                } 
                else 
                {
 //                   postinglist.addLast(docId);
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
            
           term=""; docID=""; POS=""; occurence=0;
           
            Line= fileReader.readLine();
            count++;
        }
            
        
       
        
        for(Map.Entry<String, LinkedList<Integer>> temp : Dictionary.entrySet())
        {
           // System.out.println("term #"+ i++ +temp +"  " +temp.getValue()); // Or something as per temp defination. can be used
            DictWriter.write(temp.toString());
            DictWriter.newLine();
        }

       
        SortFile("Dictionary");
//        int i=0;
//        while(i<50)
//        {
//            System.out.println("Document#"+ (i+1));
//            
//            for (String words : wordCounts[i].keySet()) 
//            {
//                System.out.println(words + "\t "+ wordCounts[i].get(words));
//            }
//            System.out.println("\n\n");
//            i++;
//        }
//       
        //System.out.println(wordCounts[5].get(""));
        
        Map<String, LinkedList<Double>> TermFreqDoc = new HashMap<>();
        LinkedList<Double> TermFreqPostingList =  new LinkedList<>();
        BufferedWriter TermFreqDocWriter = new BufferedWriter(new FileWriter(path+"FinalFile\\TermFreqDoc.txt"));
        
        int docs=0;
        
        //----------------------------------- Term Frequency ----------------------------------------------------
        
        while(docs!=50)
        {
            
           for(Map.Entry<String, LinkedList<Integer>> temp : Dictionary.entrySet())
           {
               String docWord= temp.getKey();
               
                if(!TermFreqDoc.containsKey(docWord))
                {
                    TermFreqPostingList= new LinkedList();
                    if(wordCounts[docs].get(docWord)!=null)
                        //TermFreqPostingList.addLast((double)(Math.round((double)wordCounts[docs].get(docWord)*decimalPlaces)/decimalPlaces));
                        TermFreqPostingList.addLast((double)wordCounts[docs].get(docWord));
                    else
                        TermFreqPostingList.addLast(0.00000);
                    TermFreqDoc.put(docWord,TermFreqPostingList);
                } 
                else 
                {
                    TermFreqPostingList= TermFreqDoc.get(docWord);
                    if(wordCounts[docs].get(docWord)!=null)
                        //TermFreqPostingList.addLast((double)(Math.round((double)wordCounts[docs].get(docWord)*decimalPlaces)/decimalPlaces));
                        TermFreqPostingList.addLast((double)wordCounts[docs].get(docWord));
                    else
                        TermFreqPostingList.addLast(0.00000);
                    TermFreqDoc.put(docWord,TermFreqPostingList);   
                }                       
           }
            
            docs++;
        }      
        
        while(true)
        {
        
        //----------------------------------- Query Term Frequency ----------------------------------------------------
        Scanner scanner= new Scanner(System.in);
        System.out.println("\n\nEnter Query: ");
        String Query= scanner.nextLine();
        String query[]= Query.split(" ");
        
        Map<String, Integer> queryCounts = new TreeMap<>(); 
        for(int i=0;i<query.length;i++)
        {
            if (!queryCounts.containsKey(query[i])) 
            {
                queryCounts.put(query[i], 1);
            }
            else 
            {
                queryCounts.put(query[i], queryCounts.get(query[i]) + 1);
            }
        }
        
        //System.out.println(queryCounts.entrySet());
        
        LinkedList<String> queryList= new LinkedList(Arrays.asList(query));
        //System.out.println(queryList);
        
        
        for(Map.Entry<String, LinkedList<Double>> temp: TermFreqDoc.entrySet())
        {
            String TfDocTerm= temp.getKey();
            TermFreqPostingList= TermFreqDoc.get(TfDocTerm); 
           
            if(queryList.contains(TfDocTerm))
            {  // TermFreqPostingList.addLast((double)Math.round(queryCounts.get(TfDocTerm)*decimalPlaces)/decimalPlaces);    
                //System.out.println(TermFreqPostingList);
                 //System.out.println(TfDocTerm+" count:" + queryCounts.get(TfDocTerm));
              //  System.out.println(queryCounts.get(TfDocTerm+" term:"+TfDocTerm));
                TermFreqPostingList.addLast((double)queryCounts.get(TfDocTerm));
               
            }else
                TermFreqPostingList.addLast(0.00000);
            TermFreqDoc.put(TfDocTerm, TermFreqPostingList);     
            
        }
    
        
        //------------------------- Document Freqency And Inverse Document Frequency -----------------------------------------
        int N=50;
        
        for(Map.Entry<String, LinkedList<Integer>> temp: Dictionary.entrySet())
        {
            TermFreqPostingList= TermFreqDoc.get(temp.getKey());
           // TermFreqPostingList.addLast((double)(Math.round((double)temp.getValue().size()*decimalPlaces/decimalPlaces)));
            //TermFreqPostingList.addLast((double)(Math.round((double)Math.log10((double)(N/temp.getValue().size()))*decimalPlaces)/decimalPlaces));
            TermFreqPostingList.addLast((double)temp.getValue().size());
            
           
           //System.out.println(Math.log10((double)N/temp.getValue().size()));
          
            TermFreqPostingList.addLast( Math.log10( ((double) N/temp.getValue().size()) ) );
            TermFreqDoc.put(temp.getKey(), TermFreqPostingList);          
        }
        
        
        //---------------------------------------- TF * IDF ---------------------------------------------------------------
        
        
        
        for(Map.Entry<String, LinkedList<Double>> temp: TermFreqDoc.entrySet())
        {
            TermFreqPostingList= temp.getValue();
            for(int i=0; i<51; i++)
            {
                double tf_idf= (double)(TermFreqPostingList.get(i)*TermFreqPostingList.get(52));
                //TermFreqPostingList.addLast((double)Math.round(tf_idf*decimalPlaces)/decimalPlaces);
                TermFreqPostingList.addLast(tf_idf);
            }
            TermFreqDoc.put(temp.getKey(), TermFreqPostingList);
        }
        
        //------------------------------------- Writing Table To File -----------------------------------------------------
           
        for(Map.Entry<String, LinkedList<Double>> temp : TermFreqDoc.entrySet())
        {
            TermFreqDocWriter.write(temp.toString());
            TermFreqDocWriter.newLine();
        }

       
       //------------------------------------- Vectors -----------------------------------------------------
       
       
       double vectors[][] = new double[51][9783]; //50 docs abd 1 query
       int j=0;
        
       for(int i=0; i<51;i++)
       {
            for(Map.Entry<String, LinkedList<Double>> temp: TermFreqDoc.entrySet())
            {
                TermFreqPostingList= temp.getValue();
                //System.out.print(temp.getKey()+" ");
                vectors[i][j++]=TermFreqPostingList.get((i+53));      
            }
            
            j=0;
       }
       
       
       double dotProduct[]= new double[50];  //dot product of each doc with query
       double magnitude[] = new double [51];
       
       for(int k=0;k<51;k++) 
       {
            for(int i=0;i<9783;i++)
            {
                if(k!=50)
                {
                    dotProduct[k]+=vectors[k][i]*vectors[50][i];
                }
                
                magnitude[k]+=((double)(Math.pow(vectors[k][i],2)));
            }
            magnitude[k]=(double)Math.sqrt(magnitude[k]);
            //System.out.println("Magnitude of tf_idf_doc#"+(k+1)+": "+magnitude[k]);
       }
       
       
       System.out.println("Similarities Without Ranking"); 
       double magnitde=0.0,similarity=0.0;
       Map<String,Double> similarites=new HashMap<>();
       
       for(int i=0;i<50;i++)
       {
            magnitde = magnitude[i]*magnitude[50];
          //  magnitde= (double)(Math.round(magnitde*decimalPlaces)/decimalPlaces);
            
            similarity= ((double)dotProduct[i]/magnitde);
          //  similarity= (double) (Math.round(similarity*decimalPlaces)/decimalPlaces);
            
            similarites.put("Doc"+(i+1), similarity);
            //System.out.println("Sim(d"+(i+1)+",q): "+similarity);
            if(similarity!=0.0)
           System.out.println("Doc"+(i+1)+", "+similarity);
       }
       
       similarites=sortByComparator(similarites, false);
        
       System.out.println("\nSimilarities After Ranking");
        
        for(Map.Entry<String, Double> temp: similarites.entrySet())
        {
            if(temp.getValue()!=0.0 && temp.getValue()>0.005)
            System.out.println(temp.getKey()+", "+temp.getValue());
        }
       
      // System.out.println(Math.log10((double)50/9));
        }
    }
}

