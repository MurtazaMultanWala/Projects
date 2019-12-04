using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Timers;
using System.Web;
using System.Web.Mvc;
using System.Web.UI;
using SmartNewsWindows;
using System.Data.SqlClient;

namespace WebAPIwithDB.Controllers
{
    public class HomeController : Controller
    {
        List<string> categories = new List<string>{
            "Sports","Politics","Education","Technology","Weather","Business"
        };
       
        List<string> Endpoints = new List<string>
        {
            "v2/everything?","v2/top-headlines?"
        };
        
        public ActionResult Index()
        {
            System.Timers.Timer aTimer = new System.Timers.Timer();
            aTimer.Elapsed += new ElapsedEventHandler(OnTimedEvent);
            aTimer.Interval = 30 * 60000;
            aTimer.Enabled = true;
          //  InitiazeDatabase();
            return View();
        }

       
       
        private void OnTimedEvent(object sender, ElapsedEventArgs e)
        {
            InitiazeDatabase();      
        }

        private string InitiazeDatabase()
        {
            try
            {
                foreach (string endPoint in Endpoints)
                {
                    foreach (string category in categories)
                    {
                       /* var url = "https://newsapi.org/" + endPoint +
                                  "q=" + category + "&" +
                                  "from=2019-11-24&" +
                                  "sortBy=popularity&" +
                                  "apiKey=c2307e9035be4dfb9100f2beede67378";*/

                        var url = "https://newsapi.org/" + endPoint +
                                  "q=" + category + "&" +
                                  "from=2019-11-24&" +
                                  "sortBy=popularity&" +
                                  "apiKey=cc42647f4d544c518ec402db610f2833";
                       
                        WebClient client = new WebClient();
                        var fetchNews = client.DownloadString(url);
                        
                        if (fetchNews.Length > 0)
                        {
                            JToken parsedJson = JToken.Parse(fetchNews);
                            JArray innerValues = parsedJson["articles"].Value<JArray>();


                            dynamic albums = innerValues;

                            News newsDB = new News();

                            using (SmartNewsEntities entities = new SmartNewsEntities())
                            {


                                foreach (dynamic album in albums)
                                {
                                    string title = album.title;

                                    if (!entities.News.Any(n => n.NewsTitle.Equals(title)))
                                    {
                                        
                                        newsDB.NewsTitle = album.title;
                                        
                                        newsDB.Source = album.source.name;
                                        newsDB.Genre = category;
                                        newsDB.PublishedDate = album.publishedAt;
                                        newsDB.ImageInNews = album.urlToImage;

                                        string description = album.description;
                                        string content = album.content;

                                        if (content.Length > 0)
                                            newsDB.NewsDescription = content;
                                            
                                        else if (description.Length > 0)
                                            newsDB.NewsDescription = description;
                                        
                                        else
                                            newsDB.NewsDescription = "";
                                        
                                        

                                        entities.News.Add(newsDB);
                                        entities.SaveChanges();
                                    }
                                }
                            }
                        }
                    }
                }
                return "Success";
            }
            catch (Exception except)
            {
                return except.Message;
            }

            
        }
        
    }
}
