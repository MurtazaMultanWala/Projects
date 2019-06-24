import xlsxwriter
import sys, tweepy, csv, re
#from textblob import TextBlob
import matplotlib.pyplot as plt
import string
import nltk
from nltk.tokenize import RegexpTokenizer
from nltk.corpus import stopwords
import re
import openpyxl
import math
import csv

#Preprocessing the data for cleaning
def preprocess(sentence):
    sentence=sentence
    sentence = sentence.lower()
    tokenizer = RegexpTokenizer(r'\w+')
    tokens = tokenizer.tokenize(sentence)
    filtered_words = [w for w in tokens if not w in stopwords.words('english')]
    return filtered_words


# def preprocess(sentence):
# 	sentence=sentence
# 	sentence = sentence.lower()
# 	tokenizer = RegexpTokenizer(r'\w+')
# 	tokens = tokenizer.tokenize(sentence)
# 	filtered_words = [w for w in tokens if not w in stopwords.words('english')]
# 	return filtered_words


doc2vec=[] # dictionary of doc term frequency
dictionary=[] # all the terms in all docs
vector=[]  # vectors of each document
classes={} #class and corresponding cos similarity of document
totaldocs=0
def makeDict(newdoc): #making dictionary
    newdoc=preprocess(newdoc)
    for term in newdoc:
        if(term not in dictionary):
            dictionary.append(term)

def makedoc2vec(doc,docid):
    doc=preprocess(doc)
    t={}
    doc2vec.append({})
    for term in dictionary:
        doc2vec[docid][term]=0
    for term in doc:
        if(term in doc2vec[docid]):
            doc2vec[docid][term]+=1


def MakeDictionary(file):
    totaldocs=0
    with open(file, newline='') as file1:
        for row in csv.reader(file1):
            # print(row[2])
            totaldocs+=1
            # print(row[2])
            makeDict(row[0])
    return totaldocs
def train(file,ratio):
    docid=0
    totaldocs =  MakeDictionary(file)
    i=0
    with open(file, newline='') as file1:
        for row in csv.reader(file1):
            if(i<totaldocs-ratio):
                makedoc2vec(row[0],docid)
                classes[docid]={}
                classes[docid]['class']=row[1]
                docid+=1
                i+=1

    for i in doc2vec:
        # print(i)
        v=[]
        for j in i:
            v.append(i[j])
        vector.append(v)
    return totaldocs

def mod(vec):
    val=0
    for i in vec:
        val=val+i*i
    return math.sqrt(val)

def dotproduct(testvector,vec):
    i=0
    product=0
    while (i<len(vec)):
        product=product+testvector[i]*vec[i]
        i+=1
    div=mod(testvector)*mod(vec)
    if(div==0):
        return 0
    return product/div

def getMax(cosvalues,kbest):
    i=0
    max=-1
    maxin=0
    for i in cosvalues:
        maxin = i
        break
    # print(cosvalues)
    # print(maxin)
    i=1
    for x in cosvalues:
        if x not in kbest and x!=0:
            if(max<=cosvalues[x]['cosvalue']):
                maxin=x
                max=cosvalues[x]['cosvalue']
    return maxin

def selectkbest(cosvlues,k,kbest):
    i=0
    while(i<k):
        x=getMax(cosvlues,kbest)
        # print(x)
        kbest[x]=cosvlues[x]['class']
        i+=1

def test(testdoc,k):
    id=len(doc2vec)
    # print(id)
    kbest = {}  # k best of which the most frequent is selected
    makedoc2vec(testdoc,id)
    v=[]
    # for i in doc2vec:
    #     print(i)
    for j in doc2vec[id]:
        v.append(doc2vec[id][j])
    vector.append(v)



    cosvalues=[]
    testvector=vector[id]
    i=0
    while(i<len(classes)):
        val=dotproduct(testvector , vector[i])
        cosvalues.append(val)
        # print('---------------------------------')
        # print(classes)
        # print(i)
        # print('==========')
        classes[i]['cosvalue']=val
        i+=1
    selectkbest(classes,k,kbest)
    match={}
    for i in kbest:
        if(kbest[i] not in match):
            match[kbest[i]]=1
        else:
            match[kbest[i]]+=1

    max=0
    print('------------------------------')
    print(match)
    print('------------------------')
    for i in match:
        if(match[i]>max):
            max=match[i]
            matchclas=i

    # print('===============')
    # for i in doc2vec:
    #     print(i)
    # print(cosvalues)
    # print(vector)
    # print(classes)
    # print('The new belongs to',matchclas)
    # print(match)

    return matchclas



if __name__ == "__main__":

    ratio=18# divide trainig data in this ratio
    k=4 # value of k
    file='data4.csv'# training data
    totaldocs=train(file,ratio)
    print('----------------')
    print(doc2vec)
    print(dictionary)
    print(vector)
    i = 0
    print('------------------------')

    predicted={}# prdicted results
    actual={}
    #runing testing data
    with open(file, newline='') as file:
        for row in csv.reader(file):
            if (i >= totaldocs-ratio):
                predicted[i]=test(row[0],k)
                print(row[0])
                actual[i]=row[1]
            i+=1

    correctpredicted=0
    totalpredicted=0
    for i in actual:
        if(actual[i]==predicted[i]):
            correctpredicted+=1
        totalpredicted+=1
    print(classes)
    accuracy=correctpredicted/totalpredicted
    print('-----------------actual--------------')
    print(actual)
    print('------------predicted---------')
    print(predicted)
    print('------------------Accuracy-----------')
    print(accuracy*100,' percent')
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

#Preprocessing the data for cleaning
def preprocess(sentence):
    sentence=sentence
    sentence = sentence.lower()
    tokenizer = RegexpTokenizer(r'\w+')
    tokens = tokenizer.tokenize(sentence)
    filtered_words = [w for w in tokens if not w in stopwords.words('english')]
    return filtered_words


# def preprocess(sentence):
# 	sentence=sentence
# 	sentence = sentence.lower()
# 	tokenizer = RegexpTokenizer(r'\w+')
# 	tokens = tokenizer.tokenize(sentence)
# 	filtered_words = [w for w in tokens if not w in stopwords.words('english')]
# 	return filtered_words


doc2vec=[] # dictionary of doc term frequency
dictionary=[] # all the terms in all docs
vector=[]  # vectors of each document
classes={} #class and corresponding cos similarity of document
totaldocs=0
def makeDict(newdoc): #making dictionary
    newdoc=preprocess(newdoc)
    for term in newdoc:
        if(term not in dictionary):
            dictionary.append(term)

def makedoc2vec(doc,docid):
    doc=preprocess(doc)
    t={}
    doc2vec.append({})
    for term in dictionary:
        doc2vec[docid][term]=0
    for term in doc:
        if(term in doc2vec[docid]):
            doc2vec[docid][term]+=1


def MakeDictionary(file):
    totaldocs=0
    with open(file, newline='') as file1:
        for row in csv.reader(file1):
            # print(row[2])
            totaldocs+=1
            # print(row[2])
            makeDict(row[0])
    return totaldocs
def train(file,ratio):
    docid=0
    totaldocs =  MakeDictionary(file)
    i=0
    with open(file, newline='') as file1:
        for row in csv.reader(file1):
            if(i<totaldocs-ratio):
                makedoc2vec(row[0],docid)
                classes[docid]={}
                classes[docid]['class']=row[1]
                docid+=1
                i+=1

    for i in doc2vec:
        # print(i)
        v=[]
        for j in i:
            v.append(i[j])
        vector.append(v)
    return totaldocs

def mod(vec):
    val=0
    for i in vec:
        val=val+i*i
    return math.sqrt(val)

def dotproduct(testvector,vec):
    i=0
    product=0
    while (i<len(vec)):
        product=product+testvector[i]*vec[i]
        i+=1
    div=mod(testvector)*mod(vec)
    if(div==0):
        return 0
    return product/div

def getMax(cosvalues,kbest):
    i=0
    max=-1
    maxin=0
    for i in cosvalues:
        maxin = i
        break
    # print(cosvalues)
    # print(maxin)
    i=1
    for x in cosvalues:
        if x not in kbest and x!=0:
            if(max<=cosvalues[x]['cosvalue']):
                maxin=x
                max=cosvalues[x]['cosvalue']
    return maxin

def selectkbest(cosvlues,k,kbest):
    i=0
    while(i<k):
        x=getMax(cosvlues,kbest)
        # print(x)
        kbest[x]=cosvlues[x]['class']
        i+=1

def test(testdoc,k):
    id=len(doc2vec)
    # print(id)
    kbest = {}  # k best of which the most frequent is selected
    makedoc2vec(testdoc,id)
    v=[]
    # for i in doc2vec:
    #     print(i)
    for j in doc2vec[id]:
        v.append(doc2vec[id][j])
    vector.append(v)



    cosvalues=[]
    testvector=vector[id]
    i=0
    while(i<len(classes)):
        val=dotproduct(testvector , vector[i])
        cosvalues.append(val)
        # print('---------------------------------')
        # print(classes)
        # print(i)
        # print('==========')
        classes[i]['cosvalue']=val
        i+=1
    selectkbest(classes,k,kbest)
    match={}
    for i in kbest:
        if(kbest[i] not in match):
            match[kbest[i]]=1
        else:
            match[kbest[i]]+=1

    max=0
    print('------------------------------')
    print(match)
    print('------------------------')
    for i in match:
        if(match[i]>max):
            max=match[i]
            matchclas=i

    # print('===============')
    # for i in doc2vec:
    #     print(i)
    # print(cosvalues)
    # print(vector)
    # print(classes)
    # print('The new belongs to',matchclas)
    # print(match)

    return matchclas



if __name__ == "__main__":

    ratio=18# divide trainig data in this ratio
    k=5 # value of k
    file='data4.csv'# training data
    totaldocs=train(file,ratio)
    print('----------------')
    print(doc2vec)
    print(dictionary)
    print(vector)
    i = 0
    print('------------------------')

    predicted={}# prdicted results
    actual={}
    #runing testing data
    with open(file, newline='') as file:
        for row in csv.reader(file):
            if (i >= totaldocs-ratio):
                predicted[i]=test(row[0],k)
                print(row[0])
                actual[i]=row[1]
            i+=1

    correctpredicted=0
    totalpredicted=0
    for i in actual:
        if(actual[i]==predicted[i]):
            correctpredicted+=1
        totalpredicted+=1
    print(classes)
    accuracy=correctpredicted/totalpredicted
    print('-----------------actual--------------')
    print(actual)
    print('------------predicted---------')
    print(predicted)
    print('------------------Accuracy-----------')
    print(accuracy*100,' percent')
