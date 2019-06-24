#include<iostream>
#include<windows.h>
#include<conio.h>
//#include<cstring>
#include<fstream>
#include<exception>
//#include<iomanip>
//#include <cstdio>

using namespace std;

int k=7,r=0,flag=0;//count=0;

COORD coord = {0, 0};
 
	void gotoxy(int x, int y){
		
	 COORD coord;
	 coord.X = x;
	 coord.Y = y;
 
 	 SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
	}

class date
{
	public: 
		int mm,dd,yy;
};
 
ofstream fout;
ifstream fin;
//int counter=0;

//***********************_____________________ LOGIN BLOCK ____________________***************************

class LOGIN{
	
	private:		
		string userID;
		string password;
		string b,a;
		char yes;
		bool loginstatus;
			
	public :
		
	void verify_login(void);	
   	 void reset(void);	
			
		
			
};

 
void LOGIN :: verify_login(void)	
{
	ifstream afile;
	system("cls");
	system("color B");
	afile.open("password.txt");
		
	gotoxy(22,10);
		
    cout << "Username : ";
    cin >> userID;
    cout << "\n\n";
    
	gotoxy(22,12);
		
	cout << "Password : ";
	cin >> password;
		
	loginstatus=false;
	
	afile >> a;
	afile >> b;
	        
	if(userID==a && password==b) 
	{				
		loginstatus=true;			
	}
	
	else 
	{				
		loginstatus=false;			
	}
			
	if(loginstatus==true) 
	{				
		gotoxy(22,20);
	}
	
	else
	{						
		gotoxy(22,20);					  
		cout <<"Incorrect Password, Enter again..!! ";					  
		gotoxy(22,21);					  			 
		getch();					  
		verify_login();				    
	}		
				
	afile.close();
}

//***********************_____________________ THIS IS OUR INTERFACE ____________________***************************
 
class Supermarket
{	
	public:		
	 // << interface >>  named as Supermarket		
		virtual void add(void)=0;		
		virtual void show(void)=0;		
		virtual void report(void)=0;		
};


class Items : public Supermarket
{	
	private:
	
    	int itemno;
    	char name[25];
    	date d;

    public:

       void add() {
           cout<<"\n\n\tItem No: ";
           cin>>itemno;

           cout<<"\n\n\tName of the item: ";
           cin>>name;

           cout<<"\n\n\tManufacturing Date(dd-mm-yy): ";
           cin>>d.mm>>d.dd>>d.yy;
		}
 		
		void show() { 
			cout<<"\n\tItem No: ";
            cout<<itemno;
            cout<<"\n\n\tName of the item: ";
            cout<<name;
            cout<<"\n\n\tDate : ";
            cout<<d.mm<<"-"<<d.dd<<"-"<<d.yy;
        }
		
		void report() {
            gotoxy(3,k);
            cout<<itemno;
            gotoxy(13,k);
            puts(name);
        }

        int retno() {
            return(itemno);
        }
};

class Inventory: public Items
{	
	private:
		float price,qty,tax,gross,dis,netamt,gtotal;
    	int ch,ff;
    
	public:
        void add();
        void show();
        void report();
    	void calculate();
    	void pay();
    	void start(string filename);		
		float retnetamt() {
		return(netamt);
        }
        
        
        friend ostream& operator <<(ostream &os, Inventory &obj);
        friend istream& operator >>(istream &is, Inventory &obj);
}inv;
 
    
	istream& operator >>(istream &is, Inventory &obj)
	{
		cout<<"\n\n\tPrice: ";
		is>>obj.price;		
		cout<<"\n\n\tQuantity: ";
		is>>obj.qty;		
		cout<<"\n\n\tTax percent: ";
		is>>obj.tax;		
		cout<<"\n\n\tDiscount percent: ";
		is>>obj.dis;
	}
	
	
	ostream& operator <<(ostream &os, Inventory &obj)
	{	
		gotoxy(23,k);
		os<<obj.price;
	
		gotoxy(33,k);
		os<<obj.qty;
	
		gotoxy(44,k);
		os<<obj.tax ;
	
		gotoxy(52,k);
		os<<obj.dis;
	
		gotoxy(64,k);
		os<<obj.netamt;

	}
	
 
    void Inventory::add()
	{
		Items::add();
	
		cin>>inv;			//overloading 
		
		calculate();						// line no. 187
		fout.write((char *)&inv,sizeof(inv));		
		fout.close();
	}
		
	void Inventory::calculate()	{
		gross=price+(price*(tax/100));
		netamt=qty*(gross-(gross*(dis/100)));
	}

	void Inventory::show() {				
//		fin.open("itemstore.txt",ios::binary);
//		fin.read((char*)&inv,sizeof(inv));
	
		Items::show();						// line no. 129 
	
		cout<<"\n\n\tNet amount: ";
		cout<<netamt;
		
		fin.close();
	} 

	void Inventory::report() {
	
		Items::report();
		
		cout<<inv;		//operator overloading
		
		k=k+1;
		if(k==50) {			
			gotoxy(25,50);
			
			cout<<"PRESS ANY KEY TO CONTINUE...";
			
			getch();
			
			k=7;
			
			system("cls");
			gotoxy(30,3);
			
			cout<<" ITEM DETAILS ";
			gotoxy(3,5);
			
			cout<<"NUMBER";
			gotoxy(13,5);
			
			cout<<"NAME";
			gotoxy(23,5);
			
			cout<<"PRICE";
			gotoxy(33,5);
			
			cout<<"QUANTITY";
			gotoxy(44,5);
			
			cout<<"TAX";
			gotoxy(52,5);
			
			cout<<"DEDUCTION";
			gotoxy(64,5);
			
			cout<<"NET AMOUNT";			
		}		
	}

	void Inventory::pay(){
		show();
		cout <<"\n\n\n\t\t*********************************************";
		cout <<"\n\t\t                 DETAILS                  ";
		cout <<"\n\t\t*********************************************";
		cout <<"\n\n\t\tPRICE                     : " <<price;
		cout <<"\n\n\t\tQUANTITY                  : " <<qty;
		cout <<"\n\t\tTAX PERCENTAGE              : " <<tax;
		cout <<"\n\t\tDISCOUNT PERCENTAGE         : " <<dis;
		cout <<"\n\n\n\t\tNET AMOUNT              : Rs." <<netamt;

		cout <<"\n\t\t*********************************************";
	}
	
//class WEAR				:	public Inventory {};
//class Food				:	public Inventory {};
//class HouseHold			:	public Inventory {};
//class ELECTRINOCS	:	public Inventory {};

class Item_menu	{
	
	private:
	    int choice;
	    
    public:
	   int itemmenu(void);	 
	   
};


//*****************************_______________ FIRST PAGE ______________********************************

int Item_menu :: itemmenu(void) 
{
	system("cls");	
    gotoxy(25,2);
    
    cout<<"TILL MANAGEMENT SYSTEM";
    gotoxy(22,3);
    
    cout<<"====================================\n\n";
    gotoxy(17,6);
    
//
//	cout << "1. WEAR\n";
//	gotoxy(17,8);
//	
//	cout << "2. FOOD\n";
//	gotoxy(17,10);
//	
//	cout << "3. HOUSE HOLDS\n";
//	gotoxy(17,12);
//	
//	cout << "4. ELECTRONICS\n";
//	gotoxy(17,14);
//	
//	cout << "5. EXIT\n";
//	gotoxy(17,17);
//	
	cout << "Enter 1 to continue...\n\n\n\n\n\n";
	cin >> choice;
	
	return choice;
}

void Inventory :: start(string filename)
{		
	system("color B");
	
//	cout.setf(ios::fixed);
//	cout.setf(ios::showpoint);
//	cout<<setprecision(2);
	
	fstream tmp("temp.txt",ios::binary|ios::out);
	menu:
	system("cls");
	gotoxy(25,2);
	
	cout<<"TILL MANAGEMENT SYSTEM";
	
	gotoxy(22,3);
	cout <<"====================================\n\n";	
	cout <<"\n\t\t1.Bill Report\n\n";
	cout <<"\t\t2.Add/Remove/Edit Item\n\n";
	cout <<"\t\t3.Show Item Details\n\n";
	cout  <<"\t\t4.Go back to items portion\n\n";
	cout <<"\n\n\t\tPlease Enter Required Option: ";
	cin>>ch;
	
	switch(ch)
    {
    	case 1:
			int cho;
			ss:
			system("cls");
			gotoxy(25,2);
			
			cout <<"\tBill Details";
			gotoxy(25,3);
			
			cout <<"=========================\n\n";
			cout <<"\n\t\t1.All Items\n\n";
			cout <<"\t\t2.Back to Main menu\n\n";
			
			cout <<"\t\tPlease Enter Required Option: ";
			cin>>cho;
			
			if(cho==1) {
				system("cls");
				gotoxy(30,2);
				
				cout <<" \t BILL DETAILS "<<endl;
				
				gotoxy(30,3);
				cout <<"================\n\n";
				
				gotoxy(3,5);
				
				cout <<"ITEM NO";
				gotoxy(13,5);
				
				cout <<"NAME";
				gotoxy(23,5);
				
				cout <<"PRICE";
				gotoxy(33,5);
				
				cout <<"QUANTITY";
				gotoxy(44,5);
				
				cout <<"TAX %";
				gotoxy(52,5);
				
				cout <<"DISCOUNT %";
				gotoxy(64,5);
				
				cout <<"NET AMOUNT";
				
				fin.open(filename.c_str(),ios::binary);
				     
				if(!fin) {                	
                   cout<<"\n\nFile Not Found...";
                  
                   goto menu;                   
                }
                
                fin.seekg(0);                  
                gtotal=0;
                  
                while(!fin.eof())
				{
                    fin.read((char*)&inv,sizeof(inv));
                   
				    if(!fin.eof()){
						inv.report();
                        gtotal+=inv.retnetamt();
                        ff=0;
                    }
                    if(ff!=0){
						gtotal=0;
					}
                } 
                
				gotoxy(17,k);
                
				cout<<"\n\n\n\t\t\tGrand Total="<<gtotal;
                
                getch();
                
				fin.close();                
            }
                        
			if(cho==2){
				goto menu;
			} 			
			goto ss;


        case 2:
		  
		  db:			
			system("cls");
			gotoxy(25,2);
			int apc;  
			
			cout <<"\tBill Editor";
			gotoxy(25,3);
			
			cout <<"=======================\n\n";
			cout <<"\n\t\t1.Add Item Details\n\n";
			cout <<"\t\t2.Edit Item Details\n\n";
			cout <<"\t\t3.Delete Item Details\n\n";
			cout <<"\t\t4.Back to Main Menu ";
			
			gotoxy(16,15);
			cout << "Enter your choice : "; 
			cin>>apc;
	
			switch(apc) 
			{
				
		        case 1:
				  fout.open(filename.c_str(),ios::binary|ios::app);
				  
		          inv.add();						//  line no. 169
		          
				  cout<<"\n\t\tItem Added Successfully!";
		          getch();
		          goto db;
		
		        case 2:
		           int ino;
		           flag=0;
		           
				   cout<<"\n\n\tEnter Item Number to be Edited :";
		           cin>>ino;
		           
				   fin.open(filename.c_str(),ios::binary);
		           fout.open(filename.c_str(),ios::binary|ios::app);
		           
					if(!fin) 
					{
		            	cout<<"\n\nFile Not Found...";
		            	goto menu;
		        	}
		            
					else 
					{
						fin.seekg(0);
		            	r=0;
		
		                while(!fin.eof()) 
						{
		                    fin.read((char*)&inv,sizeof(inv));
		                
						    if(!fin.eof()) 
							{
								int x=inv.Items::retno();
		                        if(x==ino) 
								{
									flag=1;
		                            fout.seekp(r*sizeof(inv));
									
		                            system("cls");
		
		                            cout<<"\n\t\tCurrent Details are\n";
		                            inv.show();
		
		                            cout<<"\n\n\t\tEnter New Details\n";
		                            inv.add();
									
									cout<<"\n\t\tItem Details editted";
		                        }
		                    }
		                    
							r++;
		                }
		
			        	if(flag==0) 
						{
			            	cout<<"\n\t\tItem No does not exist...Please Retry!";
			            	getch();
			            	goto db;
			        	}
			          	
			        	fin.close();
			        	getch();
			        	goto db;
		      			flag=0;
			       	}
		
				case 3: 
		        
					cout<<"\n\n\tEnter Item Number to be deleted :";
		        	cin>>ino;
		         
					fin.open(filename.c_str(),ios::binary);
		               
					if(!fin)
		            {   	
		                cout<<"\n\nFile Not Found...";
		                goto menu;
		                  
					}
		            
					fin.seekg(0);
		            
					while(fin.read((char*)&inv, sizeof(inv)))
		            {
		                           
					    int x=inv.Items::retno();
		                   
					    if(x!=ino)
						{
		                    tmp.write((char*)&inv,sizeof(inv));
		            	}
						
						else
		                {
							flag=1;
						}       
					}
		              
					fin.close();
		            tmp.close();
		                   
					fout.open(filename.c_str(),ios::trunc|ios::binary);
		            fout.seekp(0);
		                   
					tmp.open("temp.txt",ios::binary|ios::in);
		
		            if(!tmp)
		            {
		            	cout<<"Error in File";
		            	goto db;
		            }
		
		            while(tmp.read((char*)&inv,sizeof(inv)))
		            	fout.write((char*)&inv,sizeof(inv));
		            
					tmp.close();
		            fout.close();
		
		            if(flag==1)
		            	cout<<"\n\t\tItem Succesfully Deleted";
		
		            else 
						if (flag==0)
		                	cout<<"\n\t\tItem does not Exist! Please Retry";
		
		            getch();
		            goto db;
		
		    	case 4:
		        	goto menu;
		
		   	    	default:
			    	cout<<"\n\n\t\tWrong Choice!!! Retry";
		        	getch();
		        	goto db;    
			}

	
		case 3:
		
			system("cls");
		    flag=0;
		    int ino;
		                   
			cout<<"\n\n\t\tEnter Item Number :";
		    cin>>ino;
		                   
			fin.open(filename.c_str(),ios::binary);
		                   
			if(!fin)
		    {
		        cout<<"\n\nFile Not Found...\nProgram Terminated!";
		        goto menu;
		    }
		    
			fin.seekg(0);
		    
			while(fin.read((char*)&inv,sizeof(inv)))
		    {
		    	int x=inv.Items::retno();
		    
			    if(x==ino)
		        {
		        	inv.pay();
		            flag=1;
		            break;
		        }
		    }
		                               
			if(flag==0)
			{
		    	cout<<"\n\t\tItem does not exist....Please Retry!";
		    }
		    
			getch();
		    fin.close();
		    goto menu;
					      
			
		case 4:
	
			Item_menu o;
			int b;
			b=o.itemmenu();
				                    
		default:
			
			cout<<"\n\n\t\tWrong Choice....Please Retry!";
			getch(); 
			goto menu;			                 
	}
}


int main() 
{
  
   system("color 9");
   LOGIN log;
		
   log.verify_login();   			
   Item_menu obj;   
   Inventory a;
   
   string s;  int b=0;
   
   try
   {
	   b=obj.itemmenu();			//289
	   
	   switch(b)
	   {
	    	case 1: 
		        s="WEAR.txt";
				a.start(s);				//320
				break;
	//		
	//		case 2: {
	//			s="FOOD.txt";
	//			a.start(s);			//320
	//        }
	//		
	//		case 3: {
	//			s="Household.txt";
	//			a.start(s);			//320
	//		}
	//	
	//		case 4: {
	//			s="Crockery.txt";
	//			a.start(s);			//320
	//		}
	
		}
	}
	
	catch(exception &e){
		
	}
	
	catch(...){
		cout<<"!!! Exception !!!";
	}
	
	
	system("cls");
    gotoxy(12,14);
    
    cout<<"\t******************* THANKS *******************";
   
    getch();    
    exit(0);
}
