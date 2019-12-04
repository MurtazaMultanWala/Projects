using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using SmartNewsWindows;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace WebAPIwithDB.Controllers
{
    public class RecommendationController : ApiController
    {
        

        

        public string Post([FromBody] JObject EmailAndNewsID)
        {

            
            try
            {
                string EmailAddress = EmailAndNewsID["EmailAddress"].ToString();
                string NewsIDString = EmailAndNewsID["NewsID"].ToString();
                int NewsID = Convert.ToInt32(NewsIDString);

                using (SmartNewsEntities context = new SmartNewsEntities())
                {
                    var entityUserTable = context.Users.First(e => e.EmailAddress.Equals(EmailAddress));
                    var ValidateNewsID = context.News.First(n => n.NewsID == NewsID);

                    if (entityUserTable != null && ValidateNewsID!=null)
                    {
                        int UserID = entityUserTable.UserID;
                        var CheckExistInViewTable = context.Views.FirstOrDefault(v=> v.UserID == UserID && v.NewsID == NewsID);

                        if (CheckExistInViewTable == null)
                        {
                            View entityViewTable = new View();
                            entityViewTable.UserID = UserID;
                            entityViewTable.NewsID = NewsID;
                            entityViewTable.ViewingDateTime = DateTime.Now;

                            context.Views.Add(entityViewTable);
                            context.SaveChanges();
                        }


                        string currentNewsTitle = ValidateNewsID.NewsTitle;

                       // List<News> RecommendedNews = new List<News>();

                        //news user previously watched
                        var UserAlreadyViewedNews = from b in context.Views
                                                    where b.UserID == UserID && b.NewsID!= NewsID
                                                    select b;


                        if (UserAlreadyViewedNews != null)
                        {
                            string[] WordsInTitle = currentNewsTitle.Split(' ');

                            foreach (string word in WordsInTitle)
                            {
                                if (!stopWords.Contains(word))
                                {
                                    foreach (var viewedNews in UserAlreadyViewedNews)  //news user previously watched
                                    {
                                        var newsOfID = context.News.First(f => f.NewsID == viewedNews.NewsID);

                                        if (newsOfID.NewsTitle.Contains(word))
                                        {
                                            //RecommendedNews.Add(news);
                                            NewsRecommendation RecommendedTable = new NewsRecommendation();
                                            RecommendedTable.UserID = UserID;
                                            RecommendedTable.NewsID = newsOfID.NewsID;

                                            context.NewsRecommendations.Add(RecommendedTable);
                                            context.SaveChanges();
                                            
                                        }
                                    }
                                }
                            }

                            return "New Recommendations Added";
                        }
                        return "No Recommendations Added";
                    }
                    
                    else
                    {
                        return "Invalid EmailAddress : " + EmailAddress +" OR NewsID: "+ NewsID;
                    }
                }
            }
            catch(Exception e)
            {
                return e.ToString();
            }
        }
        
        public int WordsInString(string str)
        {
            int length = 0;
            int words = 1;

            /* loop till end of string */
            if (str.Length > 0)
            {
                while (length <= str.Length - 1)
                {
                    /* check whether the current character is white space or new line or tab character*/
                    if (str[length] == ' ' || str[length] == '\n' || str[length] == '\t')
                    {
                        words++;
                    }

                    length++;
                }

                return words;
            }

            return 0;

        }

        static List<string> stopWords = new List<string>{
        "a",
        "about",
        "above",
        "across",
        "after",
        "afterwards",
        "again",
        "against",
        "all",
        "almost",
        "alone",
        "along",
        "already",
        "also",
        "although",
        "always",
        "am",
        "among",
        "amongst",
        "amount",
        "an",
        "and",
        "another",
        "any",
        "anyhow",
        "anyone",
        "anything",
        "anyway",
        "anywhere",
        "are",
        "around",
        "as",
        "at",
        "back",
        "be",
        "became",
        "because",
        "become",
        "becomes",
        "becoming",
        "been",
        "before",
        "beforehand",
        "behind",
        "being",
        "below",
        "beside",
        "besides",
        "between",
        "beyond",
        "bill",
        "both",
        "bottom",
        "but",
        "by",
        "call",
        "can",
        "cannot",
        "cant",
        "co",
        "computer",
        "con",
        "could",
        "couldnt",
        "cry",
        "de",
        "describe",
        "detail",
        "do",
        "done",
        "down",
        "due",
        "during",
        "each",
        "eg",
        "eight",
        "either",
        "eleven",
        "else",
        "elsewhere",
        "empty",
        "enough",
        "etc",
        "even",
        "ever",
        "every",
        "everyone",
        "everything",
        "everywhere",
        "except",
        "few",
        "fifteen",
        "fify",
        "fill",
        "find",
        "fire",
        "first",
        "five",
        "for",
        "former",
        "formerly",
        "forty",
        "found",
        "four",
        "from",
        "front",
        "full",
        "further",
        "get",
        "give",
        "go",
        "had",
        "has",
        "have",
        "he",
        "hence",
        "her",
        "here",
        "hereafter",
        "hereby",
        "herein",
        "hereupon",
        "hers",
        "herself",
        "him",
        "himself",
        "his",
        "how",
        "however",
        "hundred",
        "i",
        "ie",
        "if",
        "in",
        "inc",
        "indeed",
        "interest",
        "into",
        "is",
        "it",
        "its",
        "itself",
        "keep",
        "last",
        "latter",
        "latterly",
        "least",
        "less",
        "ltd",
        "made",
        "many",
        "may",
        "me",
        "meanwhile",
        "might",
        "mill",
        "mine",
        "more",
        "moreover",
        "most",
        "mostly",
        "move",
        "much",
        "must",
        "my",
        "myself",
        "name",
        "namely",
        "neither",
        "never",
        "nevertheless",
        "next",
        "nine",
        "no",
        "nobody",
        "none",
        "nor",
        "not",
        "nothing",
        "now",
        "nowhere",
        "of",
        "off",
        "often",
        "on",
        "once",
        "one",
        "only",
        "onto",
        "or",
        "other",
        "others",
        "otherwise",
        "our",
        "ours",
        "ourselves",
        "out",
        "over",
        "own",
        "part",
        "per",
        "perhaps",
        "please",
        "put",
        "rather",
        "re",
        "same",
        "see",
        "seem",
        "seemed",
        "seeming",
        "seems",
        "serious",
        "several",
        "she",
        "should",
        "show",
        "side",
        "since",
        "sincere",
        "six",
        "sixty",
        "so",
        "some",
        "somehow",
        "someone",
        "something",
        "sometime",
        "sometimes",
        "somewhere",
        "still",
        "such",
        "system",
        "take",
        "ten",
        "than",
        "that",
        "the",
        "their",
        "them",
        "themselves",
        "then",
        "thence",
        "there",
        "thereafter",
        "thereby",
        "therefore",
        "therein",
        "thereupon",
        "these",
        "they",
        "thick",
        "thin",
        "third",
        "this",
        "those",
        "though",
        "three",
        "through",
        "throughout",
        "thru",
        "thus",
        "to",
        "together",
        "too",
        "top",
        "toward",
        "towards",
        "twelve",
        "twenty",
        "two",
        "un",
        "under",
        "until",
        "up",
        "upon",
        "us",
        "very",
        "via",
        "was",
        "we",
        "well",
        "were",
        "what",
        "whatever",
        "when",
        "whence",
        "whenever",
        "where",
        "whereafter",
        "whereas",
        "whereby",
        "wherein",
        "whereupon",
        "wherever",
        "whether",
        "which",
        "while",
        "whither",
        "who",
        "whoever",
        "whole",
        "whom",
        "whose",
        "why",
        "will",
        "with",
        "within",
        "without",
        "would",
        "yet",
        "you",
        "your",
        "yours",
        "yourself",
        "yourselves"};
    }
}
