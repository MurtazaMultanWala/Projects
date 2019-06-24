import sys, tweepy, csv, re
from textblob import TextBlob
import matplotlib.pyplot as plt
import string
import nltk
from nltk.tokenize import RegexpTokenizer
from nltk.corpus import stopwords
import re
import apply_model as KNN

class SentimentAnalysis:
    def __init__(self):
        self.tweets = []
        self.tweetText = []

    def DownloadData(self):
        # authenticating

        consumerKey = 'w4IxegforKg938fchWeaUjFCq'
        consumerSecret = 'PdNodgTq1TLCvoYkgUa8CAAXN5byYLEHTbHzK8zK5voZPtj9gF'
        accessToken = '1316999414-f0f3Kg4dT7uXvnNW1clGbMJEZLW6tsge9zW49lw'
        accessTokenSecret = 'vSsc3D3UhtY76iUOl1XXyYAxMdqVJQkSxNNP6CRgvktNE'
        k=1
        KNN.train('latest_train.csv',0)

        auth = tweepy.OAuthHandler(consumerKey, consumerSecret)
        auth.set_access_token(accessToken, accessTokenSecret)
        api = tweepy.API(auth)

        # input for term to be searched and how many tweets to search
        searchTerm = input("Enter Keyword/Tag to search about: ")
        NoOfTerms = int(input("Enter how many tweets to search: "))

        # searching for tweets
        self.tweets = tweepy.Cursor(api.search, q=searchTerm, lang="en",tweet_mode='extended').items(NoOfTerms)

        # creating some variables to store info
        positive = 0
        negative = 0


        # iterating through tweets fetched
        for tweet in self.tweets:
            tweettext = str(tweet.full_text.lower().encode('ascii',errors='ignore'))  # encoding to get rid of characters that may not be able to be displayed
            analysis = int(KNN.test(tweettext,k))
            if(analysis>=1):
                positive += 1
            if (analysis <= 0):
                negative += 1
            print('------------------------------')
            print(tweettext, '      ', analysis)
            print('------------------------------')

        # finding average of how people are reacting

        print('total Positvie: ',positive)
        print('total negative: ',negative)
        positive = self.percentage(positive, NoOfTerms)
        negative = self.percentage(negative, NoOfTerms)

        # finding average reaction
        # polarity = polarity / NoOfTerms

        # printing out data
        print("How people are reacting on " + searchTerm + " by analyzing " + str(NoOfTerms) + " tweets.")
        print()
        print("General Report: ")
        if ( positive>=negative):
            print("Positive")
        else:
            print("Negative")

        print()
        print("Detailed Report: ")
        print(str(positive) + "% people thought it was positive")
        print(str(negative) + "% people thought it was negative")
        self.plotPieChart(positive, negative, searchTerm, NoOfTerms)

    # function to calculate percentage
    def percentage(self, part, whole):
        temp = 100 * float(part) / float(whole)
        return format(temp, '.2f')

    def plotPieChart(self, positive, negative,  searchTerm, noOfSearchTerms):
        labels = ['Positive [' + str(positive) + '%]',
                  'Negative [' + str(negative) + '%]']
        sizes = [positive, negative]
        colors = ['yellowgreen',  'red']
        patches, texts = plt.pie(sizes, colors=colors, startangle=90)
        plt.legend(patches, labels, loc="best")
        plt.title('How people are reacting on ' + searchTerm + ' by analyzing ' + str(noOfSearchTerms) + ' Tweets.')
        plt.axis('equal')
        plt.tight_layout()
        plt.show()


if __name__ == "__main__":
    sa = SentimentAnalysis()
    sa.DownloadData()