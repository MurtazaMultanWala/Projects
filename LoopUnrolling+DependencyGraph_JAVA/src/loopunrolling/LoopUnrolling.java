
package loopunrolling;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.awt.SystemColor.text;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



public class LoopUnrolling extends JPanel 
{
    
    public static class register
    {
        public boolean isUsed;
        public String registerName;
        public String RenameName;
        
        public register(){
            isUsed = false;
            RenameName="";
        }
        
        public void AssignName(String r)
        {
            registerName=r;
        }    
        public void setRegister(){
            this.isUsed=true;
        }
        
        public String getAssignRename(){
            return RenameName;
        }
        
        public boolean isAvailable(register r){
            
            if(r.isUsed == false)
            return true;
            
            return false;
        }
        
        public void AssignRename(String str)
        {
            this.RenameName=str;
        }
    }   
    

    static String operations[] = 
    {
        "ADD",
        "SUB",
        "SW",
        "LD",
        "MUL",
        "DIV"
    };
 
    static String registers[] = new String[32];


    static JComboBox operation = new JComboBox(operations);

    static Instruction arrays[] = new Instruction[6];

    static JFrame jf = new JFrame();

    static int length = 6;

    static String graph[][];

    int xArray[] = new int[length];

    int yArray[] = new int[length];

    static String hazards = "";
    @Override
    public void paintComponent(Graphics g) 
    {

        
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);

        g.drawRect(600+600-20, 5, 150, 150);
        int minH = 50;
        int minW = 50;
        int maxH = jf.getHeight() - 50;
        int maxW = jf.getWidth() - 50;

        int dx = (maxW - minW) / length;
        int dy = (maxH - minH) / length;

        int x = minW;
        int y = minH;

        int r = 50;

        for (int i = 0; i < length; ++i) 
        {
            xArray[i] = x;
            yArray[i] = y;

            g.setColor(Color.black);
            g.drawOval(x, y, r, r);

            Font myFont = new Font("Courier New", 1, 17);

            g.setFont(myFont);


            if (i == 5) 
            {
             //                System.out.println("value of i == " + i);
                g.drawString(arrays[i].getOperation() + " " + arrays[i].getO1() + " " + arrays[i].getO2() + " " + arrays[i].getO3() + " ", x - 180, y + 50);
            } 
            else 
            {
                g.drawString(arrays[i].getOperation() + " " + arrays[i].getO1() + " " + arrays[i].getO2() + " " + arrays[i].getO3() + " ", x + 60, y);
            }

            x += dx;
            y += dy;
        }
       
        int temp = 0;
        
        try 
        {
            int delta = r / 2;
            int temp2 = 0;
            for (int k = 0; k < length; k++) 
            {
                for (int l = k + 1; l < length; l++) 
                {
                    if (graph[k][l] == null || graph[k][l].isEmpty()) continue;

                    // looping over the hazards number string here
                    // ohh
                    // then wheres the errorrr :'(
                    // i am thinking, wait

                    for (int m = 0; m < graph[k][l].length(); m++) 
                    {
                        g2.setStroke(new BasicStroke(3));
                        
                        switch (graph[k][l].charAt(m)) 
                        {
                            case '1':
                                Color c1 = new Color(0,0,0,127);
                                g.setColor(c1);
//                                g.setColor(Color.BLACK);
                                g.fillRect(xArray[k] + temp2 + 35, yArray[k], 10, 10);
                                g.drawArc(xArray[k] - (xArray[l] - xArray[k]) + delta, yArray[k], (xArray[l] - xArray[k]) * 2, (yArray[l] - yArray[k]) * 2, 0, 90);
                                // g.drawArc(xArray[k], yArray[k] - (yArray[l] - yArray[k]) + delta, (xArray[l] - xArray[k]) * 2, (yArray[l] - yArray[k]) * 2, 180, 90);
                                System.out.println("black");
                                break;
                            
                            case '2':
                                System.out.println("blue");
                                Color c2 = new Color(0,0,255,126);
                                g.setColor(c2);
//                                g.setColor(Color.BLUE);
                                g.fillOval(xArray[k], yArray[k] + 30, 10, 10);
                                g.drawArc(xArray[k], yArray[k] - (yArray[l] - yArray[k]) + delta, (xArray[l] - xArray[k]) * 2, (yArray[l] - yArray[k]) * 2, 180, 90);
                                break;
                            
                            case '3':
                                Color c3 = new Color(255,0,0,126);
                                g.setColor(c3);
//                                g.setColor(Color.RED);
                                System.out.println("red");


                            System.out.println("delta: " + delta);
            //                              

                            g.fillOval(xArray[l], yArray[l] +20, 10, 10);
                            g.drawArc(xArray[k], yArray[k] - (yArray[l] - yArray[k]) + delta, (xArray[l] - xArray[k]) * 2, (yArray[l] - yArray[k]) * 2, 180, 90);
                    //        g.drawLine(xArray[k] + delta - temp + 20, yArray[k] + delta - temp + 18, xArray[l] + delta - 10, yArray[l] + delta - 18);

//                            JLabel label1 = new JLabel("BLUE= WAR");
//                            JLabel label2 = new JLabel("RED=WAW");
                            temp += 150;

                            break;
                        }
                    }

                }
            }
        } 
        
        catch (Exception e) 
        {
            e.printStackTrace();
            return;
        }
    }

    public static class Instruction 
    {
        static int instruction_no = 1;
        int instructionnumber;
        String operation;
        String operand1;
        String operand2;
        String operand3;

        public String getOperation() 
        {
            return operation;
        }
        
        public String getO1() 
        {
            return operand1;
        }
        
        public String getO2() 
        {
            return operand2;
        }
        
        public String getO3() 
        {
            return operand3;
        }
        
        public Instruction() 
        {
//            System.out.println("done");
        }

        public void printInstruction() 
        {
            System.out.println(instructionnumber + ": " + operation + " " + operand1 + " " + operand2 + " " + operand3);
        }
        
        public Instruction(String op, String s1, String s2, String s3) 
        {
            instructionnumber = instruction_no;
            instruction_no++;
            operation = op;
            operand1 = s1;
            operand2 = s2;
            operand3 = s3;
            
//            System.out.println(op + " " + s1+","+s2+","+s3);
        }
    }
    
    
    public static String detectRawHazard(Instruction previous, Instruction current) 
    {

        //RAW
        String hazard = "";

        if( previous.operand1.equals(current.operand3) ||
            previous.operand1.equals(current.operand2) ||
           (current.operation.equals("SW") && !previous.operation.equals("SW") && previous.operand1.equals(current.operand1)) ||
           (current.operation.equals("SW") && previous.operation.equals("LD") && current.operand1.equals(previous.operand1)) ||
//           (previous.operation.equals("SW") && !current.operation.equals("SW") && previous.operand1.equals(current.operand2)) ||
//           (previous.operation.equals("SW") && !current.operation.equals("SW") && previous.operand1.equals(current.operand3)) ||
          (previous.operation.equals("SW") && current.operand2.equals(previous.operand2)) ||
          (previous.operation.equals("SW") && current.operand3.equals(previous.operand2)) ) 
        {
            previous.printInstruction();
            current.printInstruction();
            
            hazards+=previous.instructionnumber + " " +previous.operation+" "+  previous.operand1 + " " + previous.operand2 + " " + previous.operand3 + "\n";
            hazards+=current.instructionnumber + " " +current.operation+" " + current.operand1 + " " + current.operand2 + " " + current.operand3 + "\n";
            hazards+="Raw Hazard\n\n";
            
            System.out.println("raw hazard");
            hazard += "1";
        }
        
        return hazard;
    }
    
    public static String detectWarHazard(Instruction previous, Instruction current) 
    {

        String hazard = "";
     //WAR

        if (current.operand1.equals(previous.operand3) ||
         current.operand1.equals(previous.operand2) ||
         (current.operation.equals("LD") && previous.operation.equals("SW") && current.operand1.equals(previous.operand1)) ||
         (previous.operation.equals("SW") && !current.operation.equals("SW") && current.operand1.equals(previous.operand1)) ||
         (current.operation.equals("SW") && current.operand2.equals(previous.operand2)) ||
         (current.operation.equals("SW") && current.operand2.equals(previous.operand3))) 
        {

            previous.printInstruction();
            current.printInstruction();
            System.out.println("war hazard");
            
            hazards+=previous.instructionnumber + " " +previous.operation+" "+  previous.operand1 + " " + previous.operand2 + " " + previous.operand3 + "\n";
            hazards+=current.instructionnumber + " " +current.operation+" " + current.operand1 + " " + current.operand2 + " " + current.operand3 + "\n";
            hazards+="War Hazard\n\n";
            
            hazard += "2";
        }

        return hazard;
    }
    
    
    public static String detectWawHazard(Instruction previous, Instruction current) {

     //WAW
        String hazard = "";
        
        if (current.operand1.equals(previous.operand1) && !(previous.operation.equals("SW") || current.operation.equals("SW"))) 
        {
            previous.printInstruction();
            current.printInstruction();
            System.out.println("waw hazard");
            
            hazards+=previous.instructionnumber + " " +previous.operation+" "+  previous.operand1 + " " + previous.operand2 + " " + previous.operand3 + "\n";
            hazards+=current.instructionnumber + " " +current.operation+" " + current.operand1 + " " + current.operand2 + " " + current.operand3 + "\n";
            hazards+="Waw Hazard\n\n";
            
            hazard += "3";
        }
        
        return hazard;
    }


    public static void main(String[] args) 
    {

        String output = "";
//        String hazards = "";
        
        graph = new String[length][length];
        LoopUnrolling paintObject = new LoopUnrolling();

        jf.setTitle("Dependancy Graph");
        jf.setSize(720, 1080);


      //  JLabel label = new JLabel("Input");
      //  label.setBounds(50, 400, 50, 50);
//        JTextArea textField2 = new JTextArea();
//        //textField2.setBounds(25,400,300,290);
//        jf.add(textField2); 
//
//        JScrollPane sp = new JScrollPane(textField2);
//        sp.setBounds(25,400,300,290);
//        sp.setViewportView(textField2);

//        jf.add(sp);
        
       


        operation.setBounds(50, 450, 50, 50);
        operation.setSelectedIndex(0);


      //  jf.add(operation);
      //  jf.add(label);
        


        String hazard = "";

        //LOAD = LD
        //ADD = ADD
        //STORE = SW
        //SUB = SUB
        // BNE JMP etc not applicable here.

        //Works On Only Six Instructions Right Now


         int noOfTimesUnroll= 3;
         register registers[] = new register[32];

         for(int x=0;x<registers.length;x++)
         {
             registers[x]= new register();
             registers[x].AssignName("F"+x);
         }

        //// INPUT //////
        Instruction i0 = new Instruction("LD", "F0", "0(R1)", "");
        Instruction i1 = new Instruction("DIV", "F4", "F0", "F2");
        Instruction i2 = new Instruction("LD", "F4", "0(R1)", "");
        Instruction i3 = new Instruction("ADD", "F0", "F6", "F2");
        Instruction i4 = new Instruction("ADD", "F4", "F0", "F2");
        Instruction i5 = new Instruction("ADD", "F4", "F0", "F2");

        
        
        
        
        arrays[0] = i0;
        arrays[1] = i1;
        arrays[2] = i2;
        arrays[3] = i3;
        arrays[4] = i4;
        arrays[5] = i5;
       

        registers[0].isUsed=true;
        registers[2].isUsed=true;
        registers[4].isUsed=true;
        registers[6].isUsed=true;
//        registers[9].isUsed=true;

        
        
        int count[][] = new int [6][3];
        
        for(int i=0;i<arrays.length;i++)
        {
            String opernd1 = arrays[i].operand1;
            String opernd2 = arrays[i].operand2;
            String opernd3 = arrays[i].operand3;
            
            for(int j=0;j<arrays.length;j++)
            {
                if(!opernd1.equals("") && ( arrays[j].operand1.equals(opernd1) || arrays[j].operand2.equals(opernd1) || arrays[j].operand3.equals(opernd1)) )
                {
                    count[i][0]++;
                }
                
                if(!opernd2.equals("") && ( arrays[j].operand1.equals(opernd2) || arrays[j].operand2.equals(opernd2) || arrays[j].operand3.equals(opernd2)))
                {
                    count[i][1]++;
                }
                 
                if(!opernd3.equals("") && ( arrays[j].operand1.equals(opernd3) || arrays[j].operand2.equals(opernd3) || arrays[j].operand3.equals(opernd3)))
                {
                    count[i][2]++;
                }
            }
        }


        
        Instruction[] grapharray = new Instruction[6];


      //Making Copy OF Original Instruction
        for(int i = 0;i<6;i++)
        {
            grapharray[i] = arrays[i];
//            grapharray[i].printInstruction();
        }

        //for(int i=0;i<length;i++){
      //      arrays[i].printInstruction();
        //}


        //Finding Hazards For Dependency Graph
        for (int i = 0; i < length; i++) 
        {
            for (int j = i + 1; j < length; j++) 
            {
                hazard += detectRawHazard(grapharray[i], grapharray[j]);
                hazard += detectWarHazard(grapharray[i], grapharray[j]);
                hazard += detectWawHazard(grapharray[i], grapharray[j]);

                if (hazard.length() == 0) 
                {
                    graph[i][j] = "0";
                } 
                else 
                {
                    graph[i][j] = hazard;
                }
                hazard = "";
            }
        }

        Instruction[] array1 = new Instruction[6*(noOfTimesUnroll-1)];

        System.out.println("\n\n-----------------------------");

        System.out.println("Before Unroll");

        int w=0;
        
        output+="\n----------\nBefore Unrolling\n";
        
        for(int i = 0;i<6;i++)
        {
            grapharray[i].printInstruction();
            
            output+=grapharray[i].operation+ " " + grapharray[i].operand1 + " " + grapharray[i].operand2 + " " + grapharray[i].operand3 + "\n";
        }


      // Unrolling
       System.out.println("\nAfter Unroll");
       output+="\n-----\nAfter Unrolling\n";
   int m =0;
        
        for (int i=0;i<array1.length;i++) 
        {
            if(i>=6){
                if(i%6==0)
                      m=0;
                array1[i] = (arrays[m++]);
            }
            else
            {
                array1[i] = arrays[i];
            }


            if(!array1[i].operand1.equals(""))
            {
                int index= i%6;
                if(!array1[i].operand1.endsWith(")") && count[index][0]>1 )
                {
                    String RegistertoAssign = array1[i].operand1;
                    RegistertoAssign=RegistertoAssign.replaceAll("F", ""); 

                    int RegisertoAssignInt = Integer.parseInt(RegistertoAssign);
                  //  System.out.println("\nString Operand 1: "+RegistertoAssign + " Integer Version: "+ RegisertoAssignInt+"\n");
                    
                    
                    for(w=0;w<32;w++)
                    {
                        if(registers[RegisertoAssignInt].isAvailable(registers[w]))
                        {
                            registers[RegisertoAssignInt].AssignRename("F"+w);
                            registers[w].isUsed=true;
                            array1[i].operand1= registers[RegisertoAssignInt].getAssignRename();

                            for(int n=0;n<6;n++)
                            {
                                if(arrays[n].operand1.equals(RegistertoAssign)  )
                                {
                                    array1[n].operand1 = registers[RegisertoAssignInt].getAssignRename();
                                }
                                if( arrays[n].operand2.equals(RegistertoAssign))
                                {
                                     array1[n].operand2 = registers[i].getAssignRename();
                                }
                                if( arrays[n].operand3.equals(RegistertoAssign))
                                {
                                     array1[n].operand3 = registers[i].getAssignRename();
                                }
                            }

                            break;
                        }
                       
                        else if(!registers[RegisertoAssignInt].isAvailable(registers[w]) && !registers[RegisertoAssignInt].RenameName.equals(""))
                        {
                             array1[i].operand1= registers[RegisertoAssignInt].getAssignRename();
                             break;
                        }
                    }
                }
                else
                {
                    String RegistertoAssign = array1[i].operand1;
//                    System.out.println("\n"+RegistertoAssign+"\n");
                    
                    int endPoint= RegistertoAssign.indexOf("(");
                    
                    if(endPoint!=-1)
                    {
                        String AddressOffset= RegistertoAssign.substring(0, endPoint);
                        int AddressOffsetInt= Integer.parseInt(AddressOffset);

    //                    System.out.println("------------------------ :" +AddressOffsetInt+"\n\n");
                        AddressOffsetInt += 8;

                        String NewAddressOffset = Integer.toString(AddressOffsetInt);
                        String newRegistertoAssign= RegistertoAssign.replace(AddressOffset, NewAddressOffset);
                        
                        array1[i].operand1=newRegistertoAssign;
//                        System.out.println("-------------------\n Character : "+ NewAddressOffset + "\n---------------------------");
                        
//                        System.out.println("\n Integer value of "+ RegistertoAssign + " is: "+ AddressOffsetInt+ "\n");
//                        System.out.println("\n Renamed Memory Address of "+ RegistertoAssign + " is: "+ newRegistertoAssign+ "\n");
                    }
                }
                
            }


            if( !array1[i].operand2.equals("") )
            {
                int index= i%6;
                 
                if(!array1[i].operand2.endsWith(")") && count[index][1]>1)
                {
                    String RegistertoAssign = array1[i].operand2;
                    RegistertoAssign=RegistertoAssign.replaceAll("F", ""); 

                    int RegisertoAssignInt = Integer.parseInt(RegistertoAssign);
//                    System.out.println("\nString Operand 1: "+RegistertoAssign + " Integer Version: "+ RegisertoAssignInt+"\n");
                   
                    
                    for(w=0;w<32;w++)
                    {
                        if(registers[RegisertoAssignInt].isAvailable(registers[w]))
                        {
                            registers[RegisertoAssignInt].AssignRename("F"+w);
                            registers[w].isUsed=true;
                            array1[i].operand2= registers[RegisertoAssignInt].getAssignRename();

                            for(int n=0;n<6;n++)
                            {
                                if(arrays[n].operand1.equals(RegistertoAssign)  )
                                {
                                    array1[n].operand1 = registers[RegisertoAssignInt].getAssignRename();
                                }
                                
                                if( arrays[n].operand2.equals(RegistertoAssign))
                                {
                                     array1[n].operand2 = registers[i].getAssignRename();
                                }
                                
                                if( arrays[n].operand3.equals(RegistertoAssign))
                                {
                                     array1[n].operand3 = registers[i].getAssignRename();
                                }
                            }
                             
                            break;
                        } 
                      
                        else if(!registers[RegisertoAssignInt].isAvailable(registers[w]) && !registers[RegisertoAssignInt].RenameName.equals(""))
                        {
                           array1[i].operand2= registers[RegisertoAssignInt].getAssignRename();
                           break;
                        }
                    }
                }
                else
                {
                    String RegistertoAssign = array1[i].operand2;
//                    System.out.println("\n"+RegistertoAssign+"\n");
                    
                    int endPoint= RegistertoAssign.indexOf("(");
                    
                    if(endPoint!=-1)
                    {
                        String AddressOffset= RegistertoAssign.substring(0, endPoint);
                        int AddressOffsetInt= Integer.parseInt(AddressOffset);

    //                    System.out.println("------------------------ :" +AddressOffsetInt+"\n\n");
                        AddressOffsetInt += 8;

                        String NewAddressOffset = Integer.toString(AddressOffsetInt);
                        String newRegistertoAssign= RegistertoAssign.replace(AddressOffset, NewAddressOffset);
                        
                        array1[i].operand2=newRegistertoAssign;
//                        System.out.println("-------------------\n Character : "+ NewAddressOffset + "\n---------------------------");
                        
//                        System.out.println("\n Integer value of "+ RegistertoAssign + " is: "+ AddressOffsetInt+ "\n");
//                        System.out.println("\n Renamed Memory Address of "+ RegistertoAssign + " is: "+ newRegistertoAssign+ "\n");
                    }                  
                }
            }

            if(!array1[i].operand3.equals(""))
            {
                 int index= i%6;
                
                if(!array1[i].operand3.endsWith(")") && count[index][2]>1)
                {

                    String RegistertoAssign = array1[i].operand3;
                    RegistertoAssign=RegistertoAssign.replaceAll("F", ""); 

                    int RegisertoAssignInt = Integer.parseInt(RegistertoAssign);
//                    System.out.println("\nString Operand 1: "+RegistertoAssign + " Integer Version: "+ RegisertoAssignInt+"\n");

                    
                    for(w=0;w<32;w++)
                    {
                        if(registers[RegisertoAssignInt].isAvailable(registers[w]))
                        {
                            registers[RegisertoAssignInt].AssignRename("F"+w);
                            registers[w].isUsed=true;
                            array1[i].operand3= registers[RegisertoAssignInt].getAssignRename();

                            for(int n=0;n<6;n++)
                            {

                                if(arrays[n].operand1.equals(RegistertoAssign))
                                {
                                    array1[n].operand1 = registers[RegisertoAssignInt].getAssignRename();
                                }

                                if( arrays[n].operand2.equals(RegistertoAssign))
                                {
                                     array1[n].operand2 = registers[i].getAssignRename();
                                }

                                if( arrays[n].operand3.equals(RegistertoAssign))
                                {
                                     array1[n].operand3 = registers[i].getAssignRename();
                                }
                            }
                            
                            break;
                        } 
                        
                        else if(!registers[RegisertoAssignInt].isAvailable(registers[w]) && !registers[RegisertoAssignInt].RenameName.equals(""))
                        {
                             array1[i].operand3= registers[RegisertoAssignInt].getAssignRename();
                             break;
                        }
                    } 
                }
                else
                {
                    String RegistertoAssign = array1[i].operand3;
//                    System.out.println("\n"+RegistertoAssign+"\n");
                    
                    int endPoint= RegistertoAssign.indexOf("(");
                    
                    if(endPoint!=-1)
                    {
                        String AddressOffset= RegistertoAssign.substring(0, endPoint);
                        int AddressOffsetInt= Integer.parseInt(AddressOffset);

    //                    System.out.println("------------------------ :" +AddressOffsetInt+"\n\n");
                        AddressOffsetInt += 8;

                        String NewAddressOffset = Integer.toString(AddressOffsetInt);
                        String newRegistertoAssign= RegistertoAssign.replace(AddressOffset, NewAddressOffset);
                        
                        array1[i].operand3=newRegistertoAssign;
//                        System.out.println("-------------------\n Character : "+ NewAddressOffset + "\n---------------------------");
                        
//                        System.out.println("\n Integer value of "+ RegistertoAssign + " is: "+ AddressOffsetInt+ "\n");
//                        System.out.println("\n Renamed Memory Address of "+ RegistertoAssign + " is: "+ newRegistertoAssign+ "\n");
                    }                  
                }
            }
            

           
            if(i%6==0)
            {
                 System.out.println("\n\n");
            }
             
            if(w==32)
            {
                System.out.println("All Register Used Cannot Rename Further so No. of Possible Unroll is :" + i/6);
                break;
            }
            
            array1[i].printInstruction();   
            output+=array1[i].operation+ " " + array1[i].operand1 + " " + array1[i].operand2 + " " + array1[i].operand3+"\n";

        }
        
        m=0;


//        System.out.println("\n\n------------------------ Checking ------------------------"); 
//        
//        for(int i = 0;i<6;i++)
//        {
//            grapharray[i] = arrays[i];
//            arrays[i].printInstruction();
//        }

        System.out.println("\n\n-----------------------------");   

        for (int i = 0; i < length; i++) 
        {
            for (int j = 0; j < length; j++) 
            {
                if (graph[i][j] == null) 
                {
                    System.out.print("0\t");
                }
                
                if (graph[i][j] != null)
                    System.out.print(graph[i][j] + "\t");
            }
         
            System.out.println();
        }
        
        JFrame frame = new JFrame ();
        JTextArea textArea = new JTextArea (output);
        JScrollPane scroll = new JScrollPane (textArea, 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(15,460,300,200);
        
        
        JTextArea textArea2 = new JTextArea (hazards);

        JScrollPane scroll2 = new JScrollPane (textArea2, 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll2.setBounds(600+200+50,5,300,150);
        
        
        jf.add(scroll);
        jf.add(scroll2);
        
        textArea.setEditable(false);
        textArea2.setEditable(false);
        
        JLabel label1 = new JLabel("RAW=BLACK");
        label1.setBounds(600+600, 100, 100, 50);

        JLabel label2 = new JLabel("WAR=BLUE");
        label2.setBounds(600+600, 50, 100, 50);

        JLabel label3 = new JLabel("WAW=RED");
        label3.setBounds(600+600, 0, 100, 50);

        JLabel label4 = new JLabel("Loop Unrolling Output");
        label4.setBounds(15,400,160,100);

        JLabel label5 = new JLabel("Hazards Output (if instruction x and instruction y means x is dependant on y)");
        label5.setBounds(600+600-200-100-30, 100, 600, 150);



        
        jf.add(label1);
        jf.add(label2);
        jf.add(label3);
        jf.add(label4);
        jf.add(label5);
        jf.add(paintObject);
          
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("MAIN IS DONE ---------------------------");
    }

}

// that blue line should go from 3 to 5 right???
// ?????