from os import walk

import xlsxwriter
import sys, tweepy, csv, re
from textblob import TextBlob
import matplotlib.pyplot as plt
import string
import nltk
from nltk.tokenize import RegexpTokenizer
from nltk.corpus import stopwords
import re
import openpyxl
import math
import csv
from nltk.stem import WordNetLemmatizer

lemmatizer = WordNetLemmatizer()
def preprocess(sentence):
    sentence=sentence
    sentence = sentence.lower()
    tokenizer = RegexpTokenizer(r'\w+')
    tokens = tokenizer.tokenize(sentence)
    filtered_words = [lemmatizer.lemmatize(w) for w in tokens if not w in stopwords.words('english')]
    # filtered_words = list(dict.fromkeys(filtered_words))
    return filtered_words

for dirpath, dirnames, filesname in walk(r'cvs txt'):#reading files
    print()
ins=0
re=0
for i in filesname:#iterating files
    f=open("cvs txt/"+i,errors="ignore")
    cv=f.read()
    f.close()
    if("instructor" in i.lower() or "instructors" in i.lower()):
        f=open("cvs/"+str(1)+"_"+str(ins)+".txt","w")
        str1 = ' '.join(preprocess(cv))
        f.write(str1)
        f.close()
        ins+=1
    else:
        f = open("cvs/" + str(0) + "_" + str(re)+".txt", "w")
        str1 = ' '.join(preprocess(cv))
        f.write(str1)
        f.close()
        re+=1
import csv

for dirpath, dirnames, filesname in walk(r'cvs'):
    print()
arr=[]
for i in filesname:
    f=open("cvs/"+i,errors="ignore")
    cv=f.read()
    f.close()
    row = []
    row.append(cv)
    if(i[0]=='1'):
        row.append('1')
    else:
        row.append('0')
    arr.append(row)
with open('data4.csv', 'w', newline='') as csvFile:
    writer = csv.writer(csvFile)
    writer.writerows(arr)
csvFile.close()





