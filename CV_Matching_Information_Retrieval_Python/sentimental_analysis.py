import sys, tweepy, csv, re
from textblob import TextBlob
import matplotlib.pyplot as plt
import string
import nltk
from nltk.tokenize import RegexpTokenizer
from nltk.corpus import stopwords
import re


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

        auth = tweepy.OAuthHandler(consumerKey, consumerSecret)
        auth.set_access_token(accessToken, accessTokenSecret)
        api = tweepy.API(auth)

        # input for term to be searched and how many tweets to search
        searchTerm = input("Enter Keyword/Tag to search about: ")
        NoOfTerms = int(input("Enter how many tweets to search: "))

        # searching for tweets
        self.tweets = tweepy.Cursor(api.search, q=searchTerm, lang="en",tweet_mode='extended').items(NoOfTerms)

        # Open/create a file to append data to
        csvFile = open('result.csv', 'a')

        # Use csv writer
        csvWriter = csv.writer(csvFile)

        # creating some variables to store info
        polarity = 0
        positive = 0
        negative = 0
        neutral = 0
        row=0

        # iterating through tweets fetched
        for tweet in self.tweets:
            # Append to temp so that we can store in csv later. I use encode UTF-8
            tweettext = str(tweet.full_text.lower().encode('ascii',errors='ignore'))  # encoding to get rid of characters that may not be able to be displayed
            self.tweetText.append(self.cleanTweet(tweettext))
            # print(self.tweetText)


            print('--------------------')
            print(self.tweetText[len(self.tweetText)-1])

            print('===')
            print(tweet.full_text)

            # print (tweet.text.translate(non_bmp_map))    #print tweet's text
            analysis = TextBlob(tweettext)
            # print(analysis.sentiment)  # print tweet's polarity
            polarity += analysis.sentiment.polarity  # adding up polarities to find the average later

            if (analysis.sentiment.polarity == 0):  # adding reaction of how people are reacting to find average later
                neutral += 1
            elif (analysis.sentiment.polarity > 0):
                positive += 1
            elif (analysis.sentiment.polarity < 0):
                negative += 1

        # Write to csv and close csv file
        csvWriter.writerow(self.tweetText)
        csvFile.close()

        # finding average of how people are reacting
        positive = self.percentage(positive, NoOfTerms)
        negative = self.percentage(negative, NoOfTerms)
        neutral = self.percentage(neutral, NoOfTerms)

        # finding average reaction
        polarity = polarity / NoOfTerms

        # printing out data
        print("How people are reacting on " + searchTerm + " by analyzing " + str(NoOfTerms) + " tweets.")
        print()
        print("General Report: ")

        if (polarity == 0):
            print("Neutral")
        elif (polarity > 0):
            print("Positive")
        elif (polarity <= 0):
            print("Negative")

        print()
        print("Detailed Report: ")
        print(str(positive) + "% people thought it was positive")
        print(str(negative) + "% people thought it was negative")
        print(str(neutral) + "% people thought it was neutral")

        self.plotPieChart(positive, negative, neutral, searchTerm, NoOfTerms)

    def cleanTweet(self, tweet):
        # Remove Links, Special Characters etc from tweet
        return ' '.join(re.sub("(@[A-Za-z0-9]+)|([^0-9A-Za-z \t]) | (\w +:\ / \ / \S +)", " ", tweet).split())

    # function to calculate percentage
    def percentage(self, part, whole):
        temp = 100 * float(part) / float(whole)
        return format(temp, '.2f')

    def plotPieChart(self, positive, negative, neutral, searchTerm, noOfSearchTerms):
        labels = ['Positive [' + str(positive) + '%]', 'Neutral [' + str(neutral) + '%]',
                  'Negative [' + str(negative) + '%]']
        sizes = [positive, neutral, negative]
        colors = ['yellowgreen', 'gold', 'red']
        patches, texts = plt.pie(sizes, colors=colors, startangle=90)
        plt.legend(patches, labels, loc="best")
        plt.title('How people are reacting on ' + searchTerm + ' by analyzing ' + str(noOfSearchTerms) + ' Tweets.')
        plt.axis('equal')
        plt.tight_layout()
        plt.show()


if __name__ == "__main__":
    sa = SentimentAnalysis()
    sa.DownloadData()