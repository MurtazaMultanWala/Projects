import sys,tweepy,csv,re
from textblob import TextBlob
import matplotlib.pyplot as plt
import xlsxwriter
from nltk.tokenize import word_tokenize
import string
from nltk.corpus import stopwords
import nltk
# nltk.download('punkt')

def tokenize(tweet):
    tokenized_tweets=[];
    x=''
    for t in tweet:
        if(t==' '):
            tokenized_tweets.append(x)
            x=''
        else:
            x = x + t
    return tokenized_tweets
stopwords=['a', 'is', 'the', 'of', 'all', 'and', 'to', 'can' ,'be' ,'as' ,'once' ,'for',
           'at', 'am' ,'are', 'has' ,'have', 'had' ,'up' ,'his' ,'her', 'in' ,'on','we','do',' ','','https','.com']
def remove_stop_words(tweets):
    new=[]
    for x in tweets:
        if x not in stopwords:
            new.append(x)
    return new
def remove_personname(text):
    new_text=''
    i=0
    for x in text:
       if(x==':' and i==0):
           i=1
       elif(i==1):
           new_text=new_text+x
    return new_text
def removePuncts(tweet):
    """Strips punctuation from list of words"""
    puncList = [",",".",";",":","!","?","/","\\",",","#","@","$","&",")","(","\""]
    new_tweet=''
    for t in tweet:
        if t not in puncList:
            new_tweet=new_tweet+t
    return new_tweet
def textCleaning(text):
    text=remove_personname(text)# this will remove the author name from tweet
    text=removePuncts(text) # this will remove punctuation from tweet
    tokenized_tweets = tokenize(text)
    tokenized_tweets = [w.lower() for w in tokenized_tweets] #to lower all words
    tokenized_tweets = remove_stop_words(tokenized_tweets)# tokeniz the tweets
    cleaned_tweet=''
    for x in tokenized_tweets:
        cleaned_tweet=cleaned_tweet+x+' '
    return cleaned_tweet
