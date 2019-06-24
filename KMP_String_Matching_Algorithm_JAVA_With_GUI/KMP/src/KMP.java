


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;






public class KMP extends JPanel{

    String path="D:\\Semester 4\\DS LAB\\DS Project\\Finalized Project (View String Pattern Pending)\\KMP\\src\\";
    
   File file = new File(path + "KMPFILE.txt");
   
   String patternInput;
   private JButton searchbutton,patternbutton,morealgobutton,About,Members;
   private JLabel pattern, label, title,string;
   private JTextField patterntextfield;
   String patternX="";
   int z=0;
   int y=0;
   int click=0;
    
   
   KMP()
    {
        setLayout(new FlowLayout());
        

        this.setBackground(new Color(198,241,231));
        title= new JLabel ("KMP PATTERN SEARCH\n" ,SwingConstants.CENTER);
        title.setForeground(new Color(75, 75, 75));
        title.setFont(new Font("Agency Fb",Font.BOLD,40));
        add(title);

       
        
        patternbutton= new JButton("<html><b>Press To View Strings<html>");
        patternbutton.setFont(new Font("Times New Roman",Font.ITALIC,18));
        patternbutton.setPreferredSize(new Dimension(250,30));
        
        add(patternbutton);
        
        
        
        string= new JLabel("");
        string.setFont(new Font("Times New Roman",Font.PLAIN,30));
        string.setForeground(new Color(61, 61, 61));
        add(string);
        
        
        
        pattern= new JLabel ("<html><br>Search your pattern below<html>");
        pattern.setFont(new Font("Times New Roman",Font.PLAIN,22));
        pattern.setForeground(new Color(61, 61, 61));
        add(pattern);
        
        
        
        patterntextfield= new JTextField(20);
        patterntextfield.setBackground(Color.WHITE);
        patterntextfield.setForeground(Color.RED);
        patterntextfield.setFont(new Font("Georgia",Font.ITALIC,15));
        add(patterntextfield);
        
        
        
        
        searchbutton= new JButton("Click To Start Searching!");
        searchbutton.setFont(new Font("Georgia",Font.ITALIC,15));
        searchbutton.setPreferredSize(new Dimension(250,30));
        add(searchbutton);
        
        
        
        label= new JLabel("");
        label.setForeground(new Color(75, 75, 75));
        label.setFont(new Font("Gabriola",Font.PLAIN,19));
        add(label);
        
        morealgobutton= new JButton("Click Here For More Algos");
        morealgobutton.setFont(new Font("Georgia",Font.ITALIC,15));
        morealgobutton.setPreferredSize(new Dimension(250,30));
        add(morealgobutton);
        
        About= new JButton("About Project");
        About.setFont(new Font("Georgia",Font.ITALIC,15));
        About.setPreferredSize(new Dimension(250,30));
        add(About);
        
       
        Members= new JButton("Team Members");
        Members.setFont(new Font("Georgia",Font.ITALIC,15));
        Members.setPreferredSize(new Dimension(250,30));
        add(Members);

        
       eventsearchbutton es = new eventsearchbutton();
        searchbutton.addActionListener(es);
        
        
        eventpatternbutton ep = new eventpatternbutton();
        patternbutton.addActionListener(ep);  
        
        eventmorealgobutton em = new eventmorealgobutton();
        morealgobutton.addActionListener(em);
        
        eventAbout eb = new eventAbout();
        About.addActionListener(eb);
        
        eventMembers eM= new eventMembers();
        Members.addActionListener(eM);
        
    }

    

    

    
    class KMPPatternPage extends JPanel implements ActionListener{
        
       
        private JLabel Counterlabel, label1,TimeComp;
        private JTextField Counterfield,TimeComplexity;
        int z=0;
        int y=0; 
       
        int xaxis=0, xmov=5;
        
       
        KMPPatternPage()
        {
            setLayout(new FlowLayout());
            this.setBackground(new Color(75, 75, 75));

           
            label1= new JLabel("");
            label1.setFont(new Font("Georgia",Font.ITALIC,15));
            add(label1);   
            
            
            Counterlabel= new JLabel("Numbers Of Pattern Found: ");
            Counterlabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
            Counterlabel.setForeground(new Color(255, 255, 255));
            add(Counterlabel); 
            
            
            Counterfield= new JTextField(5);
            Counterfield.setBackground(Color.WHITE);
            Counterfield.setForeground(new Color(113, 88, 226));
            Counterfield.setFont(new Font("Agency Fb",Font.BOLD,25));
            Counterfield.setEditable(false);
            Counterfield.setText("0");
            add(Counterfield);
            
            
            TimeComp= new JLabel ("<html><br><br><br><br><br><br>Time Complexity For This Algorithm <br><div style='text-align: center;'>In Nano-Seconds<br><html>");
            TimeComp.setFont(new Font("Times New Roman",Font.PLAIN,20));
            TimeComp.setForeground(new Color(255, 255, 255));
            add(TimeComp);  
          
            TimeComplexity= new JTextField(8);
            TimeComplexity.setBackground(Color.WHITE);
            TimeComplexity.setForeground(new Color(113, 88, 226));
            TimeComplexity.setFont(new Font("Agency Fb",Font.BOLD,25));
            TimeComplexity.setEditable(false);
            TimeComplexity.setText("-");
            add(TimeComplexity);                     
        }
        
        Timer t= new Timer(50,this);
        
        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            
           // --------------------------------------------------------------------
             t.start();
            
            patternInput=patterntextfield.getText();
            int x=0;
            int s=0;
            if(patternInput.length()==0)
            {
                label.setText("Please Enter Something To Search");
                s=1;
                
                return;
            }
            
            label.setText("");
            int  arrayIndex = -1; 
            int[] array = new int[500];
             g.setColor(Color.RED);
            g.fillOval(xaxis, 100, 60, 60);
            
         
            char[] cArray1 = new char[1000000];
            
            
            if (!file.exists()) 
            {
                label.setText( "file does not exist.");
                z=1;
                return;
            }
                 
            if (!(file.isFile() && file.canRead())) 
            {
               label.setText(file.getName() + " cannot be read from.");
                return;
            }
            
            try 
            {
                FileInputStream fis = new FileInputStream(file);
                char current;
                int count = 0;
                     
                while (fis.available() > 0) 
                {
                    current = (char) fis.read();
                    
                    cArray1[count] = current;
                    
                    count++;
                }
            } 
                 
            catch (IOException ev) 
            {
            }

            char[] cArray2 = patternInput.toCharArray();
            int [] countpichart= new int [cArray2.length];
            countpichart[0]=0; // always have zero.
               
            //construct pi chart;
            //int count=0;
            long startTime = System.nanoTime();   
        
            int j=0;
            int i=1; //so can compare value at zeroth index and first index
            int len=0;
            
            while(i<cArray2.length)
            {
                if(cArray2[i]==cArray2[len])
                { 
                    //{A,A} // {0,1}
                    len++;
                    countpichart[i]=len;
                    i++;
                }
                   
                else
                {   
                    //adjacent elments not equal.
                    if(len!=0)
                    {
                        len=countpichart[len-1];    //{A,A,B} //{0,1,0}//assiging index?
                    }
                    
                    else
                    {
                        countpichart[i]=len;        //{A,B} -> {0,0}
                        i++;
                    }
                }
            }
           
            int variable=0;
               
            while(x<cArray1.length)
            {
               
                
                if(cArray2[j]==cArray1[x])
                {
                    //case when value at ith index matches value at jth index
                    j++;
                    x++;
             
                }
                   
                if(j==cArray2.length)
                { 
                    //when jth index reaches size of pattern
                    variable=1;
                    Counterfield.setText(Integer.toString(++arrayIndex+1));
                    //g.setColor(Color.PINK);
                    //g.fillOval(xaxis, 60 , 100, 100);
                   array[arrayIndex] =  ((x-j) + 1);
                   
                    j=countpichart[j-1]; //method
                    
                }
                
                
                else if((cArray1[x]!= cArray2[j]) && i<cArray1.length)
                {
                    if(j>0)
                    {
                        j=countpichart[j-1];
                    }   
                    else
                    {
                        x=x+1;
                    }
                    
                    
                }
                
                
            }
            
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            TimeComplexity.setText(Long.toString(totalTime));
        }
        
        public void actionPerformed(ActionEvent e)
        {
            if(xaxis<0 || xaxis>300)
                xmov=-xmov;
            
            xaxis+=xmov;
            repaint();
        }
    }
     
    
   
    
    public class eventsearchbutton implements ActionListener
    {
       
               
        public void actionPerformed(ActionEvent e)
        {
                       
            patternX=patterntextfield.getText();
            int x=0;
            
            if(patternX.length()==0)
            {
                label.setText("Please Enter Something To Search");
                x=1;
               
                return;
            }
            
            label.setText("");
            
            
             
            KMPPatternPage obj= new KMPPatternPage();
            JFrame newwindow = new JFrame();
            newwindow.setTitle("Searching Process");
            newwindow.setSize(400,420);
            newwindow.setVisible(true);
            newwindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            newwindow.add(obj);
        }
        
    }
    
    
    public class KMPMoreAlgoPage extends JPanel{
        
        JLabel Algo1, Algo2, Algo3, Algo4, Algo5, Algo6;
        JButton Algo1button, Algo2button, Algo3button, Algo4button, Algo5button, Algo6button; 
        
        
        KMPMoreAlgoPage()
        {
            this.setBackground(new Color(255,250,255));
          
            Algo1 = new JLabel("Rabin-Karp Algorithm");
            Algo1.setForeground(Color.red);
            Algo1.setFont(new Font("Georgia",Font.PLAIN,15));
            add(Algo1);
            
            Algo1button = new JButton("Click To Implement Rabin-Karp");
            Algo1button.setFont(new Font("Helvetica",Font.ITALIC,14));
            Algo1button.setPreferredSize(new Dimension(300,30));
            add(Algo1button);
            
          
            
            
            
            Algo2 = new JLabel("Boyer-Moore String Searching Algorithm");
            Algo2.setForeground(Color.red);
            Algo2.setFont(new Font("Georgia",Font.PLAIN,15));
            add(Algo2);
            
            Algo2button = new JButton("Click To Implement Boyer-Moore");
            Algo2button.setFont(new Font("Helvetica",Font.ITALIC,14));
            Algo2button.setPreferredSize(new Dimension(300,30));
            add(Algo2button);
            
            
            
            
            Algo3 = new JLabel("Brute-Force Searching Algorithm");
            Algo3.setForeground(Color.red);
            Algo3.setFont(new Font("Georgia",Font.PLAIN,15));
            add(Algo3);
            
            Algo3button = new JButton("Click To Implement Brute-Force");
            Algo3button.setFont(new Font("Helvetica",Font.ITALIC,14));
            Algo3button.setPreferredSize(new Dimension(300,30));
            add(Algo3button);
            
            
            
            
            Algo4 = new JLabel("Finite Automata Searching Algorithm");
            Algo4.setForeground(Color.red);
            Algo4.setFont(new Font("Georgia",Font.PLAIN,15));
            add(Algo4);
            
            Algo4button = new JButton("Click To Implement Finite Automata");
            Algo4button.setFont(new Font("Helvetica",Font.ITALIC,14));
            Algo4button.setPreferredSize(new Dimension(300,30));
            add(Algo4button);
            
            
            
            
            
            Algo5 = new JLabel("Naive Searching Algorithm");
            Algo5.setForeground(Color.red);
            Algo5.setFont(new Font("Georgia",Font.PLAIN,15));
            add(Algo5);
            
            Algo5button = new JButton("Click To Implement Naive");
            Algo5button.setFont(new Font("Helvetica",Font.ITALIC,14));
            Algo5button.setPreferredSize(new Dimension(300,30));
            add(Algo5button);
            
            
            
            
            Algo6 = new JLabel("Anagram Searching Algorithm");
            Algo6.setForeground(Color.red);
            Algo6.setFont(new Font("Georgia",Font.PLAIN,15));
            add(Algo6);
            
            Algo6button = new JButton("Click To Implement Anagram Search");
            Algo6button.setFont(new Font("Helvetica",Font.ITALIC,14));
            Algo6button.setPreferredSize(new Dimension(300,30));
            add(Algo6button);
            
            
            eventAlgo1 eA1= new eventAlgo1();
            Algo1button.addActionListener(eA1);
            
            eventAlgo2 eA2= new eventAlgo2();
            Algo2button.addActionListener(eA2);
            
            eventAlgo3 eA3= new eventAlgo3();
            Algo3button.addActionListener(eA3);
            
            eventAlgo4 eA4= new eventAlgo4();
            Algo4button.addActionListener(eA4);
            
            eventAlgo5 eA5= new eventAlgo5();
            Algo5button.addActionListener(eA5);
            
            eventAlgo6 eA6= new eventAlgo6();
            Algo6button.addActionListener(eA6);
  
        }
    }
    
    
    
    public class KMPAbout extends JPanel
    {
        JLabel detail;
       
        KMPAbout()
        {
            this.setBackground( new Color(245, 246, 250));
            setLayout(new FlowLayout());
            detail= new JLabel  ("<html>The Knuth–Morris–Pratt string searching algorithm <br>(or KMP algorithm) searches for\n" +
                                "occurrences of <br>a word 'W' within a main text string 'S' <br> by employing the observation "
                                + "that when a <br> mismatch occurs. Futher more our project will  <br> compare KMP with more different algorithms.<html>");
            detail.setFont(new Font("Gabriola",Font.PLAIN,28));
            
            detail.setForeground(new Color(39, 60, 117));
            add(detail);
        }
    }
    
    
    public class KMPMembers extends JPanel
    {
        JLabel members;
       
        KMPMembers()
        {
            this.setBackground(new Color(245, 246, 250));
            setLayout(new FlowLayout());
            members= new JLabel  ("<html>"Murtaza MultanWala <br><br><html>");
            members.setFont(new Font("Gabriola",Font.PLAIN,28));
            
            members.setForeground(new Color(39, 60, 117));
            add(members);
        }
    }
    
    
    
    public class KMPAlgo1 extends JPanel
    {
        public JLabel labelResult,PatternFound,TimeComp;
        public JTextField textfieldResult,TimeComplexity;
        public JButton AboutAlgo1;
        int z;
        int d=249;
        int q=101;
        
        KMPAlgo1()
        {
            this.setBackground(new Color(255,250,255));       
            setLayout(new FlowLayout());
            
            PatternFound= new JLabel ("Number Of Pattern Found ");
            PatternFound.setForeground(Color.red);
            PatternFound.setFont(new Font("Georgia",Font.PLAIN,15));
            add(PatternFound);
            
            
            textfieldResult= new JTextField(5);
            textfieldResult.setBackground(Color.WHITE);
            textfieldResult.setForeground(new Color(113, 88, 226));
            textfieldResult.setFont(new Font("Agency Fb",Font.BOLD,25));
            textfieldResult.setEditable(false);
            textfieldResult.setText("0");
            add(textfieldResult);
            
            
            labelResult= new JLabel ("<html><br><br><html>");
            labelResult.setForeground(Color.red);
            labelResult.setFont(new Font("Georgia",Font.PLAIN,15));
            add(labelResult);  
            
            TimeComp= new JLabel ("<html><br><br>Time Complexity For This In Nano-Seconds<br><html>");
            TimeComp.setForeground(Color.red);
            TimeComp.setFont(new Font("Georgia",Font.PLAIN,15));
            add(TimeComp);  
          
            TimeComplexity= new JTextField(8);
            TimeComplexity.setBackground(Color.WHITE);
            TimeComplexity.setForeground(new Color(113, 88, 226));
            TimeComplexity.setFont(new Font("Agency Fb",Font.BOLD,25));
            TimeComplexity.setEditable(false);
            TimeComplexity.setText("-");
            add(TimeComplexity);
             
            AboutAlgo1 = new JButton("Click For More Details");
            AboutAlgo1.setFont(new Font("Helvetica",Font.ITALIC,14));
            AboutAlgo1.setPreferredSize(new Dimension(300,30));
            add(AboutAlgo1);
            
            eventAboutAlgo1 eAA1= new eventAboutAlgo1();
            AboutAlgo1.addActionListener(eAA1);
            
            char[] cArray1 = new char[1000000];

            if (!file.exists()) 
            {
                labelResult.setText( "file does not exist.");
                z=1;
                return;
            }

            if (!(file.isFile() && file.canRead())) 
            {
                     
                labelResult.setText(file.getName() + " cannot be read from.");
                return;
            }

            try 
            {
                FileInputStream fis = new FileInputStream(file);
                char current;
                int count = 0;

                while (fis.available() > 0) 
                {
                    current = (char) fis.read();
                    cArray1[count] = current;
                    count++;
                }
                        
            }
            
            catch (IOException ev) 
            {
            }

            String array= new String(cArray1);
            long startTime = System.nanoTime();
            search(patternX,array,q);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            TimeComplexity.setText(Long.toString(totalTime));
        }


        public void search(String pat, String txt, int q)
        {
            int M = pat.length();
            int N = txt.length();
            int i, j;
            int p = 0; // hash value for pattern
            int t = 0; // hash value for txt
            int h = 1;
            int check=0,count=0;
            
            for (i = 0; i < M-1; i++)
                h = (h*d)%q;

            // Calculate the hash value of pattern and first
            // window of text
            
            for (i = 0; i < M; i++)
            {
                p = (d*p + pat.charAt(i))%q;
                t = (d*t + txt.charAt(i))%q;
            }

            // Slide the pattern over text one by one
            for (i = 0; i <= N - M; i++)
            {

                // Check the hash values of current window of text
                // and pattern. If the hash values match then only
                // check for characters on by one
                if ( p == t )
                {
                            /* Check for characters one by one */
                    for (j = 0; j < M; j++)
                    {
                        if (txt.charAt(i+j) != pat.charAt(j))
                            break;
                    }

                    // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
                    if (j == M)
                    {
                        labelResult.setText("Pattern found at index " + (i+1));
                        check=1;
                        count++;
                        textfieldResult.setText(Integer.toString(count));
                    }
                }

                // Calculate hash value for next window of text: Remove
                // leading digit, add trailing digit
                if ( i < N-M )
                {
                   t = (d*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q;

                    // We might get negative value of t, converting it
                    // to positive
                    if (t < 0)
                    t = (t + q);
                }                    
            }
        }
    }
    
    
    public class KMPAlgo2 extends JPanel
    {
        public JLabel labelResult,PatternFound,TimeComp;
        public JTextField textfieldResult,TimeComplexity;
        public JButton AboutAlgo2;
        int count2=0;
        
        KMPAlgo2()
        {
            this.setBackground(new Color(255,250,255));       
            setLayout(new FlowLayout());
            
            PatternFound= new JLabel ("Number Of Pattern Found ");
            PatternFound.setForeground(Color.red);
            PatternFound.setFont(new Font("Georgia",Font.PLAIN,15));
            add(PatternFound);
            
            
            textfieldResult= new JTextField(5);
            textfieldResult.setBackground(Color.WHITE);
            textfieldResult.setForeground(new Color(113, 88, 226));
            textfieldResult.setFont(new Font("Agency Fb",Font.BOLD,25));
            textfieldResult.setEditable(false);
            textfieldResult.setText("0");
            add(textfieldResult);
            
            
            labelResult= new JLabel("");
            labelResult.setForeground(Color.red);
            labelResult.setFont(new Font("Georgia",Font.PLAIN,15));
            add(labelResult);
            
            
            TimeComp= new JLabel ("<html><br><br>Time Complexity For This Algorithm<br><div style='text-align: center;'> In Nano-Seconds<br><html>");
            TimeComp.setForeground(Color.red);
            TimeComp.setFont(new Font("Georgia",Font.PLAIN,15));
            add(TimeComp);  
          
            TimeComplexity= new JTextField(8);
            TimeComplexity.setBackground(Color.WHITE);
            TimeComplexity.setForeground(new Color(113, 88, 226));
            TimeComplexity.setFont(new Font("Agency Fb",Font.BOLD,25));
            TimeComplexity.setEditable(false);
            TimeComplexity.setText("-");
            add(TimeComplexity);
            
            AboutAlgo2 = new JButton("Click For More Details");
            AboutAlgo2.setFont(new Font("Helvetica",Font.ITALIC,14));
            AboutAlgo2.setPreferredSize(new Dimension(300,30));
            add(AboutAlgo2);
            
            eventAboutAlgo2 eAA2= new eventAboutAlgo2();
            AboutAlgo2.addActionListener(eAA2);
            
            char[] cArray1 = new char[1000000];
             
            if (!file.exists()) 
            {
                labelResult.setText( "file does not exist.");
                return;
            }
            if (!(file.isFile() && file.canRead())) 
            {
                labelResult.setText(file.getName() + " cannot be read from.");
                return;
            }
            
            char current;
            int count = 0;
           
            try 
            {
                FileInputStream fis = new FileInputStream(file); 
             
                while (fis.available() > 0)
                {
                    current = (char) fis.read();                   
                    cArray1[count] = current;                    
                    count++;
                }
            }
            
            catch (IOException ev) 
            {
            }

            
            char [] CArray1= new char[count];
            
            for(int i=0;i<count;i++)
            {
                CArray1[i]=cArray1[i];    
            }
            
            char[] cArray2 = patternX.toCharArray();
            long startTime = System.nanoTime();
            indexOf(CArray1,cArray2);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            TimeComplexity.setText(Long.toString(totalTime));
        }
        
        
        public void indexOf(char[] haystack, char[] needle) 
        {
            /*if (needle.length == 0) 
            {
                return 0;
            }*/
            
            int charTable[] = makeCharTable(needle);
            int offsetTable[] = makeOffsetTable(needle);
            for (int i = needle.length - 1, j; i < haystack.length;) 
            {
                for (j = needle.length - 1; needle[j] == haystack[i]; --i, --j) 
                {
                    if (j == 0) 
                    {
                        
                        labelResult.setText("Pattern Found At Index " + (i+1));
                        count2++;
                        textfieldResult.setText(Integer.toString(count2));
                        break;
                    }
                }
                // i += needle.length - j; // For naive method
                i += Math.max(offsetTable[needle.length - 1 - j], charTable[haystack[i]]);
            }
            if(count2==0)
                labelResult.setText("Pattern Not Found");
        }
    
        /**
         * Makes the jump table based on the mismatched character information.
         */
        private  int[] makeCharTable(char[] needle) 
        {
            final int ALPHABET_SIZE = Character.MAX_VALUE + 1; // 65536
            int[] table = new int[ALPHABET_SIZE];
            for (int i = 0; i < table.length; ++i) 
            {
                table[i] = needle.length;
            }
            for (int i = 0; i < needle.length - 1; ++i) 
            {
                table[needle[i]] = needle.length - 1 - i;
            }
            return table;
        }

        /**
         * Makes the jump table based on the scan offset which mismatch occurs.
         */
        private  int[] makeOffsetTable(char[] needle) 
        {
            int[] table = new int[needle.length];
            int lastPrefixPosition = needle.length;
            for (int i = needle.length; i > 0; --i) 
            {
                if (isPrefix(needle, i)) 
                {
                    lastPrefixPosition = i;
                }
                table[needle.length - i] = lastPrefixPosition - i + needle.length;
            }
            
            for (int i = 0; i < needle.length - 1; ++i) 
            {
                int slen = suffixLength(needle, i);
                table[slen] = needle.length - 1 - i + slen;
            }
            return table;
        }

        /**
         * Is needle[p:end] a prefix of needle?
         */
        private  boolean isPrefix(char[] needle, int p) 
        {
            for (int i = p, j = 0; i < needle.length; ++i, ++j) 
            {
                if (needle[i] != needle[j]) 
                {
                    return false;
                }
            }
            return true;
        }

        /**
         * Returns the maximum length of the substring ends at p and is a suffix.
         */
        private  int suffixLength(char[] needle, int p) {
            int len = 0;
            for (int i = p, j = needle.length - 1; i >= 0 && needle[i] == needle[j]; --i, --j) 
            {
                len += 1;
            }
            return len;
        }
    }

    
    
    
    public class KMPAlgo3 extends JPanel
    {
        public JLabel labelResult,PatternFound,TimeComp;
        public JTextField textfieldResult,TimeComplexity;
        public JButton AboutAlgo3;
        int count2=0;
        
        KMPAlgo3()
        {
            this.setBackground(new Color(255,250,255));       
            setLayout(new FlowLayout());
            
            PatternFound= new JLabel ("Number Of Pattern Found ");
            PatternFound.setForeground(Color.red);
            PatternFound.setFont(new Font("Georgia",Font.PLAIN,15));
            add(PatternFound);
            
            
            textfieldResult= new JTextField(5);
            textfieldResult.setBackground(Color.WHITE);
            textfieldResult.setForeground(new Color(113, 88, 226));
            textfieldResult.setFont(new Font("Agency Fb",Font.BOLD,25));
            textfieldResult.setEditable(false);
            textfieldResult.setText("0");
            add(textfieldResult);
            
            labelResult= new JLabel("");
            labelResult.setForeground(Color.red);
            labelResult.setFont(new Font("Georgia",Font.PLAIN,15));
            add(labelResult);
            
            TimeComp= new JLabel ("<html><br><br>Time Complexity For This Algorithm<br><div style='text-align: center;'> In Nano-Seconds<br><html>");
            TimeComp.setForeground(Color.red);
            TimeComp.setFont(new Font("Georgia",Font.PLAIN,15));
            add(TimeComp);  
          
            TimeComplexity= new JTextField(8);
            TimeComplexity.setBackground(Color.WHITE);
            TimeComplexity.setForeground(new Color(113, 88, 226));
            TimeComplexity.setFont(new Font("Agency Fb",Font.BOLD,25));
            TimeComplexity.setEditable(false);
            TimeComplexity.setText("-");
            add(TimeComplexity);
            
            AboutAlgo3 = new JButton("Click For More Details");
            AboutAlgo3.setFont(new Font("Helvetica",Font.ITALIC,14));
            AboutAlgo3.setPreferredSize(new Dimension(300,30));
            add(AboutAlgo3);
            
            eventAboutAlgo3 eAA3= new eventAboutAlgo3();
            AboutAlgo3.addActionListener(eAA3);
            
            
            char[] cArray1 = new char[1000000];
            //store(cArray1);
            
            if (!file.exists()) 
            {
                labelResult.setText( "file does not exist.");
                return;
            }
        
            if (!(file.isFile() && file.canRead())) 
            {
                labelResult.setText(file.getName() + " cannot be read from.");
                return;
            }
          
            try
            {
                FileInputStream fis = new FileInputStream(file); 
        
                char current;
                int count = 0;
                while (fis.available() > 0)
                {
                    current = (char) fis.read();
                    //System.out.print(current);
                    cArray1[count] = current;
                    //+System.out.print(cArray1[count]);
                    count++;
                }
            }
            catch(IOException e)
            {
                
            }
            
            char[] cArray2 = patternX.toCharArray();
            
            //int position= bruteforce(cArray1,cArray2);
            
            int length = cArray1.length;//length of the text
            int plength = cArray2.length;//length of the pattern;
            //loop condition
            long startTime = System.nanoTime();
            for(int i=0;i<length-plength;i++)
            {
                //initialization of j
                int j=0;
                
                while((j < plength) && (cArray1[i+j] == cArray2[j]))
                {
                    j++;
                }
                
                if(j == plength)
                {
                    labelResult.setText("Pattern Found At Position:" + (i+1));
                    count2++;
                    textfieldResult.setText(Integer.toString(count2));
                }
            }
	long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        TimeComplexity.setText(Long.toString(totalTime));

            if(count2==0)
                labelResult.setText("Pattern Not Found");              
        }
    }
    
    
    public class KMPAlgo4 extends JPanel
    {
        public JLabel labelResult,PatternFound,TimeComp;
        public JTextField textfieldResult,TimeComplexity;
        public JButton AboutAlgo4;
        int count2=0;
        int NO_OF_CHARS = 256;
        
        KMPAlgo4()
        {
            this.setBackground(new Color(255,250,255));       
            setLayout(new FlowLayout());
            
            PatternFound= new JLabel ("Number Of Pattern Found ");
            PatternFound.setForeground(Color.red);
            PatternFound.setFont(new Font("Georgia",Font.PLAIN,15));
            add(PatternFound);
            
            
            textfieldResult= new JTextField(5);
            textfieldResult.setBackground(Color.WHITE);
            textfieldResult.setForeground(new Color(113, 88, 226));
            textfieldResult.setFont(new Font("Agency Fb",Font.BOLD,25));
            textfieldResult.setEditable(false);
            textfieldResult.setText("0");
            add(textfieldResult);
            
            labelResult= new JLabel("");
            labelResult.setForeground(Color.red);
            labelResult.setFont(new Font("Georgia",Font.PLAIN,15));
            add(labelResult);
            
            TimeComp= new JLabel ("<html><br><br>Time Complexity For This Algorithm<br><div style='text-align: center;'> In Nano-Seconds<br><html>");
            TimeComp.setForeground(Color.red);
            TimeComp.setFont(new Font("Georgia",Font.PLAIN,15));
            add(TimeComp);  
          
            TimeComplexity= new JTextField(8);
            TimeComplexity.setBackground(Color.WHITE);
            TimeComplexity.setForeground(new Color(113, 88, 226));
            TimeComplexity.setFont(new Font("Agency Fb",Font.BOLD,25));
            TimeComplexity.setEditable(false);
            TimeComplexity.setText("-");
            add(TimeComplexity);
            
            AboutAlgo4 = new JButton("Click For More Details");
            AboutAlgo4.setFont(new Font("Helvetica",Font.ITALIC,14));
            AboutAlgo4.setPreferredSize(new Dimension(300,30));
            add(AboutAlgo4);
            
            eventAboutAlgo4 eAA4= new eventAboutAlgo4();
            AboutAlgo4.addActionListener(eAA4);
            
            char[] cArray1 = new char[1000000];
            
            if (!file.exists()) 
            {
                labelResult.setText( "file does not exist.");
                return;
            }
        
            if (!(file.isFile() && file.canRead())) 
            {
                labelResult.setText(file.getName() + " cannot be read from.");
                return;
            }
                        
            try
            {
                 
                FileInputStream fis = new FileInputStream(file); 
                char current;
                int count = 0;
                while (fis.available() > 0)
                {
                    current = (char) fis.read();
                    //System.out.print(current);
                    cArray1[count] = current;
                    //+System.out.print(cArray1[count]);
                    count++;
                }

                char[] cArray2 = patternX.toCharArray();
                long startTime = System.nanoTime();
                search(cArray2, cArray1);
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
               TimeComplexity.setText(Long.toString(totalTime));


            }
            catch(IOException e)
            {
                
            }
        }
        
  
 
        public int getNextState(char[] pat, int M, int state, int x)
        {
            /*
             * If the character c is same as next character in pattern,
             * then simply increment state
             */
            if (state < M && x == pat[state])
                return state + 1;
            
            int ns, i;
            /*
             * ns stores the result which is next state
             * ns finally contains the longest prefix which is also suffix
             * in "pat[0..state-1]c"
             * Start from the largest possible value and stop when you find
             * a prefix which is also suffix
             */
            for (ns = state; ns > 0; ns--)
            {
                if (pat[ns - 1] == x)
                {
                    for (i = 0; i < ns - 1; i++)
                    {
                        if (pat[i] != pat[state - ns + 1 + i])
                            break;
                    }
                    if (i == ns - 1)
                        return ns;
                }
            }
            return 0;
        }

        /*
         * This function builds the TF table which represents Finite Automata for a
         * given pattern
         */
        public void computeTF(char[] pat, int M, int[][] TF)
        {
            int state, x;
            for (state = 0; state <= M; ++state)
                for (x = 0; x < NO_OF_CHARS; ++x)
                    TF[state][x] = getNextState(pat, M, state, x);
        }

        /*
         * Prints all occurrences of pat in txt
         */
        public void search(char[] pat, char[] txt)
        {
            int M = pat.length;
            int N = txt.length;
            int[][] TF = new int[M + 1][NO_OF_CHARS];
            computeTF(pat, M, TF);
            // Process txt over FA.
            int i, state = 0;
            for (i = 0; i < N; i++)
            {
                state = TF[state][txt[i]];
                if (state == M)
                {
                    //System.out.print(pat);
                    labelResult.setText("Pattern Found At " + (i - M + 2));
                    count2++;
                    textfieldResult.setText(Integer.toString(count2));
                }
            }
            
            if(count2==0)
                labelResult.setText("Pattern Not Found");
        }
        
    }
    
    public class KMPAlgo5 extends JPanel
    {
        public JLabel labelResult,PatternFound,TimeComp;
        public JTextField textfieldResult,TimeComplexity;
        public JButton AboutAlgo5;
        int count2=0;
        
        KMPAlgo5()
        {
            this.setBackground(new Color(255,250,255));       
            setLayout(new FlowLayout());
            
            PatternFound= new JLabel ("Number Of Pattern Found ");
            PatternFound.setForeground(Color.red);
            PatternFound.setFont(new Font("Georgia",Font.PLAIN,15));
            add(PatternFound);
            
            
            textfieldResult= new JTextField(5);
            textfieldResult.setBackground(Color.WHITE);
            textfieldResult.setForeground(new Color(113, 88, 226));
            textfieldResult.setFont(new Font("Agency Fb",Font.BOLD,25));
            textfieldResult.setEditable(false);
            textfieldResult.setText("0");
            add(textfieldResult);
            
            labelResult= new JLabel("");
            labelResult.setForeground(Color.red);
            labelResult.setFont(new Font("Georgia",Font.PLAIN,15));
            add(labelResult);
            
            TimeComp= new JLabel ("<html><br><br>Time Complexity For This Algorithm<br><div style='text-align: center;'> In Nano-Seconds<br><html>");
            TimeComp.setForeground(Color.red);
            TimeComp.setFont(new Font("Georgia",Font.PLAIN,15));
            add(TimeComp);  
          
            TimeComplexity= new JTextField(8);
            TimeComplexity.setBackground(Color.WHITE);
            TimeComplexity.setForeground(new Color(113, 88, 226));
            TimeComplexity.setFont(new Font("Agency Fb",Font.BOLD,25));
            TimeComplexity.setEditable(false);
            TimeComplexity.setText("-");
            add(TimeComplexity);
            
            AboutAlgo5 = new JButton("Click For More Details");
            AboutAlgo5.setFont(new Font("Helvetica",Font.ITALIC,14));
            AboutAlgo5.setPreferredSize(new Dimension(300,30));
            add(AboutAlgo5);
            
            eventAboutAlgo5 eAA5= new eventAboutAlgo5();
            AboutAlgo5.addActionListener(eAA5);
            
            char[] cArray1 = new char[1000000];
            
            if (!file.exists()) 
            {
                labelResult.setText( "file does not exist.");
                return;
            }
            if (!(file.isFile() && file.canRead())) 
            {
                labelResult.setText(file.getName() + " cannot be read from.");
                return;
            }
            
            try
            {
                FileInputStream fis = new FileInputStream(file); 
                char current;
                int count = 0;
                while (fis.available() > 0)
                {
                    current = (char) fis.read();
                    //System.out.print(current);
                    cArray1[count] = current;
                    //+System.out.print(cArray1[count]);
                    count++;
                }

                
                char[] cArray2 = patternX.toCharArray();
                long startTime = System.nanoTime();
                search(cArray1,cArray2); 
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                TimeComplexity.setText(Long.toString(totalTime));
            }
            catch(IOException e)
            {
                
            }    
        }
        
        public void search(char txt[], char pat[])
        {
            int M = pat.length;
            int N = txt.length;

            /* A loop to slide pat one by one */
            for (int i = 0; i <= N - M; i++) 
            {
                int j;

                /* For current index i, check for pattern 
                  match */
                for (j = 0; j < M; j++)
                    if (txt[i+j] != pat[j])
                        break;

                if (j == M) // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
                {
                    labelResult.setText("Pattern Found At Index " + (i+1));
                    count2++;
                    textfieldResult.setText(Integer.toString(count2));
                }
            }
            
            if(count2==0)
                labelResult.setText("Pattern Not Found");
        }        
    }
    
    
    public class KMPAlgo6 extends JPanel
    {
        public JLabel labelResult,PatternFound,TimeComp;
        public JTextField textfieldResult,TimeComplexity;
        public JButton AboutAlgo6;
        int count2=0;
        int MAX = 256;
        
        KMPAlgo6()
        {
            this.setBackground(new Color(255,250,255));       
            setLayout(new FlowLayout());
            
            PatternFound= new JLabel ("Number Of Pattern Found ");
            PatternFound.setForeground(Color.red);
            PatternFound.setFont(new Font("Georgia",Font.PLAIN,15));
            add(PatternFound);
            
            
            textfieldResult= new JTextField(5);
            textfieldResult.setBackground(Color.WHITE);
            textfieldResult.setForeground(new Color(113, 88, 226));
            textfieldResult.setFont(new Font("Agency Fb",Font.BOLD,25));
            textfieldResult.setEditable(false);
            textfieldResult.setText("0");
            add(textfieldResult);
            
            labelResult= new JLabel("");
            labelResult.setForeground(Color.red);
            labelResult.setFont(new Font("Georgia",Font.PLAIN,15));
            add(labelResult);
            
            TimeComp= new JLabel ("<html><br><br>Time Complexity For This Algorithm <br><div style='text-align: center;'> In Nano-Seconds</div><br><html>",SwingConstants.CENTER);
            TimeComp.setForeground(Color.red);
            TimeComp.setFont(new Font("Georgia",Font.PLAIN,15));
            add(TimeComp);  
          
            TimeComplexity= new JTextField(8);
            TimeComplexity.setBackground(Color.WHITE);
            TimeComplexity.setForeground(new Color(113, 88, 226));
            TimeComplexity.setFont(new Font("Agency Fb",Font.BOLD,25));
            TimeComplexity.setEditable(false);
            TimeComplexity.setText("-");
            add(TimeComplexity);
            
            
            AboutAlgo6 = new JButton("Click For More Details");
            AboutAlgo6.setFont(new Font("Helvetica",Font.ITALIC,14));
            AboutAlgo6.setPreferredSize(new Dimension(300,30));
            add(AboutAlgo6);
            
            eventAboutAlgo6 eAA6= new eventAboutAlgo6();
            AboutAlgo6.addActionListener(eAA6);
            
            char[] cArray1 = new char[1000000];
            
            if (!file.exists()) 
            {
                labelResult.setText( "file does not exist.");
                return;
            }
            if (!(file.isFile() && file.canRead())) 
            {
                labelResult.setText(file.getName() + " cannot be read from.");
                return;
            }
            
            try
            {
                FileInputStream fis = new FileInputStream(file); 
                char current;
                int count = 0;
                while (fis.available() > 0)
                {
                    current = (char) fis.read();
                    //System.out.print(current);
                    cArray1[count] = current;
                    //+System.out.print(cArray1[count]);
                    count++;
                }

               
                char[] cArray2 = patternX.toCharArray();
                long startTime = System.nanoTime();
                search(cArray1, cArray2);
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
               TimeComplexity.setText(Long.toString(totalTime));
            }
            catch(IOException e)
            {
                
            }
        }
        
       
     
    // This function returns true if contents
    // of arr1[] and arr2[] are same, otherwise
    // false.
    public boolean compare(char arr1[], char arr2[])
    {
        for (int i = 0; i < MAX; i++)
            if (arr1[i] != arr2[i])
                return false;
        return true;
    }
 
    // This function search for all permutations
    // of pat[] in txt[]
    public void search(char txt1[], char pattern[])
    {
        
        String pat=String.valueOf(pattern);
        String txt= String.valueOf(txt1);
        int M = pat.length();
        int N = txt.length();
 
        // countP[]:  Store count of all 
        // characters of pattern
        // countTW[]: Store count of current
        // window of text
        char[] countP = new char[MAX];
        char[] countTW = new char[MAX];
        for (int i = 0; i < M; i++)
        {
            (countP[pat.charAt(i)])++;
            (countTW[txt.charAt(i)])++;
        }
 
        // Traverse through remaining characters
        // of pattern
        for (int i = M; i < N; i++)
        {
            // Compare counts of current window
            // of text with counts of pattern[]
            if (compare(countP, countTW))
            {
                labelResult.setText("Pattern Found at Index " + ((i - M)+1));
                count2++;
                textfieldResult.setText(Integer.toString(count2));
            } 
            // Add current character to current 
            // window
            (countTW[txt.charAt(i)])++;
 
            // Remove the first character of previous
            // window
            countTW[txt.charAt(i-M)]--;
        }
 
        // Check for the last window in text
        if (compare(countP, countTW))
            labelResult.setText("Pattern Found at Index " + (N - M)); 
        if(count2==0)
        {
            labelResult.setText("Pattern Not Found");
        }
    }
     
    }
    
    
    
    public class KMPAboutAlgo1 extends JPanel
    {
        JLabel aboutalgo1;
        
        KMPAboutAlgo1()
        {
            this.setBackground(new Color(245, 246, 250));
            setLayout(new FlowLayout());
            aboutalgo1 = new JLabel("<html>A practical application of the algorithm is detecting plagiarism.<br> Given source material, "
                    + "the algorithm can rapidly search through a <br>paper for instances of sentences from the source material, "
                    + "ignoring <br>details such as case and punctuation. Because of the abundance  <br> of the sought strings, single-string "
                    + "searching algorithms <br>  are impractical.\nThis algorithm works well in many practical cases<br> , but can exhibit "
                    + "relatively long running times on certain examples,<br> such as earching for a pattern string of 10,000 \"a\"s"
                    + "followed by a<br> single \"b\" in a search string of 10 million \"a\"s.,<br>"
                    + "Worst-Case =  O(mn) <br> Best-Case = o(m) time.<br><html>");
            
            aboutalgo1.setFont(new Font("Gabriola",Font.PLAIN,25));
            
            aboutalgo1.setForeground(new Color(39, 60, 117));
            add(aboutalgo1);
        }
    }
    
    public class KMPAboutAlgo2 extends JPanel
    {
        JLabel aboutalgo2;
        
        KMPAboutAlgo2()
        {
            this.setBackground(new Color(245, 246, 250));
            setLayout(new FlowLayout());
            aboutalgo2 = new JLabel("<html>This is easy to see when both pattern and text consist<br> solely of the same repeated character."
                    + "In general, <br>the algorithm runs faster as the<br> pattern length increases.<br>Best-Case = Ω(n/m)<br>Worst-Case = O(mn)<br><html>");
            
            aboutalgo2.setFont(new Font("Gabriola",Font.PLAIN,25));
            
            aboutalgo2.setForeground(new Color(39, 60, 117));
            add(aboutalgo2);
        }
    }
    
    public class KMPAboutAlgo3 extends JPanel
    {
        JLabel aboutalgo3;
        
        KMPAboutAlgo3()
        {
            this.setBackground(new Color(245, 246, 250));
            setLayout(new FlowLayout());
            aboutalgo3 = new JLabel("<html>Brute force string matching can be very ineffective,<br> but it can also be very handy in some cases.<br>"
                    +"It can be very useful because it doesn’t require <br> pre-processing of the text – Indeed if we search the<br> text only once "
                    + "we don’t need to pre-process it.<br> Most of the algorithms for string matching need to <br>build an index of the text in order "
                    + "to search quickly.<br>perhaps (for short texts) brute force matching is<br> great!. But In general brute force algorithms are slow "
                    + "<br>and brute force matching isn’t an exception.<br>Its complexity is O(n.m).<br><html>");
            
            aboutalgo3.setFont(new Font("Gabriola",Font.PLAIN,25));
            aboutalgo3.setForeground(new Color(39, 60, 117));
            add(aboutalgo3);
        }
    }
    
    public class KMPAboutAlgo4 extends JPanel
    {
        JLabel aboutalgo4;
        
        KMPAboutAlgo4()
        {
            this.setBackground(new Color(245, 246, 250));
            setLayout(new FlowLayout());
            aboutalgo4 = new JLabel("<html>If we reach the final state, then the pattern is found<br>"
                    + "in the text. The time complexity of the search process is O(n).<br>"
                    + "Consider ﬁnding all occurrences of a short string (pattern string)<br> within a "
                    + "long string (text string). This can be done<br> by processing the text through "
                    + "a DFA: the DFA for all strings<br> that end with the pattern string. Each time "
                    + "the accept<br> state is reached, the current position in the text is output.<br><br><html>");
            
            aboutalgo4.setFont(new Font("Gabriola",Font.PLAIN,25));
            aboutalgo4.setForeground(new Color(39, 60, 117));
            add(aboutalgo4);
        }
    }
    
    public class KMPAboutAlgo5 extends JPanel
    {
        JLabel aboutalgo5;
        
        KMPAboutAlgo5()
        {
            this.setBackground(new Color(245, 246, 250));
            setLayout(new FlowLayout());
            aboutalgo5 = new JLabel("<html>Naive search is used for most practical purposes,<br> which deal with texts based on human languages,<br> "
                    + "this approach is much faster since the inner<br> loop usually quickly finds a mismatch and breaks.<br> A problem arises when "
                    + "we are faced with different<br> kinds of “texts,” such as the genetic code.<br><br>Its complexity is Θ(nm)<br><html>");
            
            aboutalgo5.setFont(new Font("Gabriola",Font.PLAIN,25));
            
            aboutalgo5.setForeground(new Color(39, 60, 117));
            add(aboutalgo5);
        }
    }
    
    public class KMPAboutAlgo6 extends JPanel
    {
        JLabel aboutalgo6;
        
        KMPAboutAlgo6()
        {
            this.setBackground(new Color(245, 246, 250));
            setLayout(new FlowLayout());
            aboutalgo6 = new JLabel("<html>This problem is slightly different from standard pattern<br>searching problem, here we need to search for<br>"
                + "anagrams as well.\n We can achieve O(n)<br> time complexity under the assumption that alphabet size<br> is fixed which is typical"                + "true as we have maximum<br> 256 possible characters \n in ASCII. The idea is to use two count arrays: \n <br> 1) The first count "
                + "array store frequencies of characters in pattern.\n <br>2) The second count array stores frequencies of characters in current<br> "
                + "window of text. The important thing to note is, time complexity<br> to compare two count arrays is O(1) as the number of<br> "
                + "elements in them are fixed (independent of pattern \nand text sizes).<br><html>");
            
            aboutalgo6.setFont(new Font("Gabriola",Font.PLAIN,22));
            
            aboutalgo6.setForeground(new Color(39, 60, 117));
            add(aboutalgo6);
        }
    }
    
    
    public class eventAboutAlgo1 implements ActionListener
    {
        public void actionPerformed(ActionEvent eAA1)
        {
            KMPAboutAlgo1 objAA1 = new KMPAboutAlgo1();
            JFrame jfAA1= new JFrame();
            jfAA1.setResizable(false);
            jfAA1.setTitle("About Rabin-Karp");
            jfAA1.setVisible(true);
            jfAA1.setSize(550,550);
            jfAA1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfAA1.add(objAA1);
        }
    }
    
    public class eventAboutAlgo2 implements ActionListener
    {
        public void actionPerformed(ActionEvent eAA2)
        {
            KMPAboutAlgo2 objAA2 = new KMPAboutAlgo2();
            JFrame jfAA2= new JFrame();
            jfAA2.setResizable(false);
            jfAA2.setTitle("About Boyer-Moore");
            jfAA2.setVisible(true);
            jfAA2.setSize(550,550);
            jfAA2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfAA2.add(objAA2);
        }
    }
    
    
    public class eventAboutAlgo3 implements ActionListener
    {
        public void actionPerformed(ActionEvent eAA3)
        {
            KMPAboutAlgo3 objAA3 = new KMPAboutAlgo3();
            JFrame jfAA3= new JFrame();
            jfAA3.setResizable(false);
            jfAA3.setTitle("About Brute-Force");
            jfAA3.setVisible(true);
            jfAA3.setSize(550,550);
            jfAA3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfAA3.add(objAA3);
        }
    }
    
    
    
    public class eventAboutAlgo4 implements ActionListener
    {
        public void actionPerformed(ActionEvent eAA4)
        {
            KMPAboutAlgo4 objAA4 = new KMPAboutAlgo4();
            JFrame jfAA4= new JFrame();
            jfAA4.setResizable(false);
            jfAA4.setTitle("About Finite Automata");
            jfAA4.setVisible(true);
            jfAA4.setSize(550,550);
            jfAA4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfAA4.add(objAA4);
        }
    }
    
    
    
    
    public class eventAboutAlgo5 implements ActionListener
    {
        public void actionPerformed(ActionEvent eAA5)
        {
            KMPAboutAlgo5 objAA5 = new KMPAboutAlgo5();
            JFrame jfAA5= new JFrame();
            jfAA5.setResizable(false);
            jfAA5.setTitle("About Naive");
            jfAA5.setVisible(true);
            jfAA5.setSize(550,550);
            jfAA5.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfAA5.add(objAA5);
        }
    }
    
    
    public class eventAboutAlgo6 implements ActionListener
    {
        public void actionPerformed(ActionEvent eAA6)
        {
            KMPAboutAlgo6 objAA6 = new KMPAboutAlgo6();
            JFrame jfAA6= new JFrame();
            jfAA6.setResizable(false);
            jfAA6.setTitle("About Anagram Search");
            jfAA6.setVisible(true);
            jfAA6.setSize(550,550);
            jfAA6.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfAA6.add(objAA6);
        }
    }
    
    
    
    public class eventAbout implements ActionListener
    {
        public void actionPerformed(ActionEvent eb)
        {
            KMPAbout about= new KMPAbout();
            JFrame jfabout = new JFrame();
            jfabout.setTitle("ABOUT");
            jfabout.setResizable(false);
            jfabout.setVisible(true);
            jfabout.setSize(550,420);
            jfabout.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfabout.add(about);          
        }
    }
    
        
    
    public class eventMembers implements ActionListener
    {
        public void actionPerformed(ActionEvent eM)
        {
            KMPMembers members= new KMPMembers();
            JFrame jfmem = new JFrame();
            jfmem.setTitle("Team Members");
            jfmem.setVisible(true);
            jfmem.setResizable(false);
            jfmem.setSize(350,400);
            jfmem.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfmem.add(members);
        }
    }    
    
    
    public class eventmorealgobutton implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            patternX=patterntextfield.getText();
            label.setText("");
            if(patternX.length()!=0)
            {
                KMPMoreAlgoPage objma = new KMPMoreAlgoPage();
                JFrame jfma= new JFrame(); 
                jfma.setTitle("More Algorithm");
                jfma.setVisible(true);
                jfma.setSize(350,420);
                jfma.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                jfma.add(objma);
            }
            
            else
            { 
                label.setText("Please Enter Something To Search");
            }
            
        }
    }
    
    public class eventpatternbutton implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            if(y==0)
            {
                string.setText("abcxabcdabcdabcy");
                y=1;
            }
            else
            {
                string.setText("");
                y=0;
            }
                
        }
    }
    
    
    
    public class eventAlgo1 implements ActionListener
    {
        public void actionPerformed(ActionEvent eA1)
        {
           
                KMPAlgo1 objA1 = new KMPAlgo1();
                JFrame jfA1= new JFrame(); 
                jfA1.setResizable(false);
                jfA1.setTitle("Rabin-Karp");
                jfA1.setVisible(true);
                jfA1.setSize(350,420);
                jfA1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                jfA1.add(objA1);
            
          
        }
    }
    
    
    public class eventAlgo2 implements ActionListener
    {
        public void actionPerformed(ActionEvent eA2)
        {
            KMPAlgo2 objA2 = new KMPAlgo2();
            JFrame jfA2= new JFrame(); 
            jfA2.setResizable(false);
            jfA2.setTitle("Boyer-Moore");
            jfA2.setVisible(true);
            jfA2.setSize(350,420);
            jfA2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfA2.add(objA2);   
        }
    }
        
    
   
    public class eventAlgo3 implements ActionListener
    {
        public void actionPerformed(ActionEvent eA3)
        {
            KMPAlgo3 objA3 = new KMPAlgo3();
            JFrame jfA3= new JFrame(); 
            jfA3.setResizable(false);
            jfA3.setTitle("Brute-Force");
            jfA3.setVisible(true);
            jfA3.setSize(350,420);
            jfA3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfA3.add(objA3);
        }
    }
    
    public class eventAlgo4 implements ActionListener
    {
        public void actionPerformed(ActionEvent eA4)
        {
            KMPAlgo4 objA4 = new KMPAlgo4();
            JFrame jfA4= new JFrame();
            jfA4.setResizable(false);
            jfA4.setTitle("Finite Automata");
            jfA4.setVisible(true);
            jfA4.setSize(350,420);
            jfA4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfA4.add(objA4);
        }
    }
    
    
    public class eventAlgo5 implements ActionListener
    {
        public void actionPerformed(ActionEvent eA5)
        {
            KMPAlgo5 objA5 = new KMPAlgo5();
            JFrame jfA5= new JFrame(); 
            jfA5.setResizable(false);
            jfA5.setTitle("Naive");
            jfA5.setVisible(true);
            jfA5.setSize(350,420);
            jfA5.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfA5.add(objA5);
        }
    }
    
    
    
    public class eventAlgo6 implements ActionListener
    {
        public void actionPerformed(ActionEvent eA6)
        {
            KMPAlgo6 objA6 = new KMPAlgo6();
            JFrame jfA6= new JFrame(); 
            jfA6.setResizable(false);
            jfA6.setTitle("Anagram Search");
            jfA6.setVisible(true);
            jfA6.setSize(350,420);
            jfA6.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            jfA6.add(objA6);
        }
    }
    
      
  
    public static void main(String[] args) 
    {
        KMP mainpage= new KMP();
        
        JFrame jffront= new JFrame(); 
        jffront.setResizable(false);
        jffront.setVisible(true);
        jffront.setSize(400,420);
        jffront.setTitle("Knuth-Morris-Pratt Searching Algorithm");
        jffront.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jffront.add(mainpage);   
    }
}



/*
Anagram Substring Search
*/