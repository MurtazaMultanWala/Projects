import time
from datetime import datetime as dt


temp_host_path= "hosts"
host_path = r"C:\Windows\System32\drivers\etc\hosts"   # r= passing row string
redirect_IP= "127.0.0.1"
websites_List_toBlock = ["www.facebook.com","facebook.com","gmail.com","www.gmail.com"]

Starting_Blocking_Time = dt(dt.now().year, dt.now().month, dt.now().day, 15)    #14 = 24 hr format time
Ending_Blocking_Time = dt(dt.now().year, dt.now().month, dt.now().day, 20)
Current_Time = dt.now()

while True:
    if Starting_Blocking_Time < Current_Time < Ending_Blocking_Time :
        print("Working Hours")
        with open(host_path, "r+") as file:
            file_content = file.read()

            for Websites in websites_List_toBlock:
                if(Websites in file_content):
                    pass
                else:
                    file.write(redirect_IP+" "+ Websites+ "\n")
    else:
        with open(host_path, "r+") as file:
            file_content = file.readlines()   # reading each line from file iteratively
            file.seek(0)  #pointer to starting of file to re write without websites name
            for eachLine in file_content:
                # Checking for Blocking Websites name in each line extracted from the file
                if not any(Websites in eachLine for Websites in websites_List_toBlock):
                    file.write(eachLine) #if not in file write to the file

                file.truncate()  #remove all data after pointer means remove websites names in this case
        print("Fun Hours")
    time.sleep(5)
