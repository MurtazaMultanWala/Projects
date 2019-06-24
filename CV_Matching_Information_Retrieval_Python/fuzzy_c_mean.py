import xlsxwriter
import sys, tweepy, csv, re

from sklearn.feature_extraction.text import CountVectorizer
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
dictionary=[]
corpus=[]
center=[]
points=[]
membership_matrix=[]
import csv
from nltk.stem import WordNetLemmatizer

lemmatizer = WordNetLemmatizer()


def unique(list1):
    unique_list = []
    for x in list1:
        if x not in unique_list:
            unique_list.append(x)
    return unique_list
def preprocess(sentence):
    sentence=sentence
    sentence = sentence.lower()
    tokenizer = RegexpTokenizer(r'\w+')
    tokens = tokenizer.tokenize(sentence)
    filtered_words = [lemmatizer.lemmatize(w) for w in tokens if not w in stopwords.words('english')]
    # filtered_words = list(dict.fromkeys(filtered_words))
    return filtered_words

def intersection(lst1, lst2):
    lst3 = [value for value in lst1 if value in lst2]
    return lst3
def initialize_Matrix(total_docs,assin_c1):
    a=[]
    b=[]
    j=0
    f=open('JD Research Assistants.txt')
    jdr=f.read()
    f.close()
    f=open('JD-Instructors.txt')
    jdi=f.read()
    f.close()
    jdr=(preprocess(jdr))
    jdi=unique(preprocess(jdi))

    while(j<total_docs):
        r_c=len(intersection(jdr,(corpus[j]))) # assitance length
        i_c=len(intersection(jdi,(corpus[j]))) # instructor length
        print(r_c ,i_c)
        if(r_c<i_c):
            a.append(1)
            b.append(0)
        else:
            a.append(0)
            b.append(1)
        j+=1
    membership_matrix.append(a)
    membership_matrix.append(b)
def initial_centers(X):
    x=0
    # for n in X:
    #     x+=1
    #     print(n)
    y=0
    # print(x)
    x=0
    # print('asdad',len(X))
    # print(X.shape[0])
    sum_ux=0
    sum_u=0
    ux=[]
    for i in membership_matrix:
        sum_u=0
        ux_x=[]
        x=0
        y=0
        for m in i:
            sum_u+=m*m
        for w in X[0]:
            for j in i:
                # print(x,y)
                # print(j,'   ',X[x][y])
                # print()
                sum_ux=sum_ux+X[x][y]*j*j
                # print(x)
                x+=1
            z=sum_ux/sum_u
            sum_ux=0
            ux_x.append(z)
            x=0
            y+=1
        # print('here')
        ux.append(ux_x)
    # center=ux.copy()
    for x in ux:
        print(x)
        center.append(x)
def calc_distance(p1,p2):
    i=0
    sum=0
    while(i<len(p1)):
         sum=sum+pow((p1[i]-p2[i]),2)
         i+=1

    dist= math.sqrt(sum)
    # print('================distance=================')
    # print(p1)
    # print(p2)
    # print(dist)
    # print('------------------------------')
    return dist

def update_membership(end): # end is number of iterations
    r=0
    y=0
    x=0
    # print('here')
    while(r<end):
        # print('here1')
        x=0
        y=0
        initial_centers(points)
        while(x<len(membership_matrix)):
            # print('here2')
            y=0
            while(y<len(membership_matrix[1])):
                # print('here3')
                s=calc_u(r,x,y)
                membership_matrix[x][y]=s
                # print(s)

                y+=1
            x+=1
        r+=1
        print('-------------------------------New Itr---------------------')
        for i in membership_matrix:
            print(i)
        print('-------------------------------')
def calc_u(r,i,k):
    sum=0
   # print('here U')
    # print(center)
    for x in center:
        # print('asad')
        m=pow(calc_distance(center[i],points[k]),2)
        # print('asaddd')
        n=pow(calc_distance(x,points[k]),2)
        # print('ahe')
        # print(m,n)
        if(n!=0):
            sum=sum+m/n

    if(sum==0):
        return sum
    sum=pow(sum,-1)
    # print('a')
    return sum







if __name__ == "__main__":

    file='data4.csv'# training data
    i=0
    with open(file, newline='') as file:
        for row in csv.reader(file):
            corpus.append(row[0])


    vectorizer = CountVectorizer()
    X = vectorizer.fit_transform(corpus)
    print(vectorizer.get_feature_names())
    # print(X.toarray())
    # print(X)
    j=0
    a=[]
    for i in X :
        j+=1
        x=i.toarray()
        print(x[0])
        points.append(x[0])
        a.append(x[0])
    print(j)
    initialize_Matrix(23,10)
    points=a
    print(a[0])

    # initial_centers(a)
    print(membership_matrix)
    update_membership(10)
    i=0
    c1=membership_matrix[0]
    c2=membership_matrix[1]
    for j in c1:
        if(c2[i]>c1[i]):
            print('c',i,'c2')
            print("Value from C2 : ",c2[i], " Value from Cluster 1: ", c1[i])
        elif (c2[i]<c1[i]):
            print('c', i, 'c1')
            print("Value from C2 : ",c2[i], " Value from Cluster 1: ", c1[i])
        else:
            print('c', i, 'c2  c1')
            print("Value from C2 : ",c2[i], " Value from Cluster 1: ", c1[i])
        i+=1