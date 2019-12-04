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
    public class ReturnRecommendationController : ApiController
    {
        public string Post([FromBody] JObject Email)
        {
            var jsonSerializerSettings = new JsonSerializerSettings
            {
                PreserveReferencesHandling = PreserveReferencesHandling.Objects
            };


            string EmailAddress = Email["EmailAddress"].ToString();

            using (SmartNewsEntities context = new SmartNewsEntities())
            {
                var UserTable = context.Users.First(e => e.EmailAddress.Equals(EmailAddress));

                if (UserTable != null)
                {
                    int userID = UserTable.UserID;

                    var RecommendNewsInTable = from b in context.NewsRecommendations
                                               where b.UserID == userID
                                               select b;

                   // List<News> RecommendedNewsFetched = new List<News>();
                    
                    if (RecommendNewsInTable != null)
                    {
                        string RecommendedNewsFetched = "";
                       
                        foreach (var news in RecommendNewsInTable)
                        {
                            int newsID = news.NewsID;
                            var FetchedNews = context.News.First(n => n.NewsID == newsID);
                           // RecommendedNewsFetched.Add(FetchedNews);

                            
                            if(FetchedNews!=null)
                            {

                               // RecommendedNewsFetched += JsonConvert.SerializeObject(FetchedNews, jsonSerializerSettings) + ",";
                              //  context.Configuration.ProxyCreationEnabled = false;
                               // return JsonConvert.SerializeObject(FetchedNews, jsonSerializerSettings);
                                RecommendedNewsFetched += "{";
                                RecommendedNewsFetched += "\"NewsID\":" + FetchedNews.NewsID + ",";
                                RecommendedNewsFetched += "\"NewsTitle\":\"" + FetchedNews.NewsTitle + "\"," ;
                                RecommendedNewsFetched += "\"Source\":\"" + FetchedNews.Source+ "\"" + ",";
                                RecommendedNewsFetched += "\"NewsDescription\":\"" + FetchedNews.NewsDescription + "\",";
                                RecommendedNewsFetched += "\"Genre\":\"" + FetchedNews.Genre + "\",";
                                RecommendedNewsFetched += "\"PublishedDate\":\"" + FetchedNews.PublishedDate + "\",";
                                RecommendedNewsFetched += "\"ImageInNews\":\"" + FetchedNews.ImageInNews + "\",";
                                RecommendedNewsFetched+= "\"Views\": []";


                                
                            }

                            RecommendedNewsFetched += "},";
                            
                        }
                        return RecommendedNewsFetched;
                        //context.Configuration.ProxyCreationEnabled = false;
                       /* string RecommendedNewsFetchedString = JsonConvert.SerializeObject(RecommendNewsInTable, jsonSerializerSettings);
                        return RecommendedNewsFetchedString;*/
                    }

                    return "No Recommendation";
                }
                return "Invalid EmailAddress";
           }
           
        }

       
    }
}
