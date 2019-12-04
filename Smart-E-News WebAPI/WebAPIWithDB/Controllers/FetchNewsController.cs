using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Mvc;
using Newtonsoft.Json.Linq;
using System.Web;
using System.Collections;
using System.Web.Script.Serialization;
using Newtonsoft.Json;
using SmartNewsWindows;

namespace WebAPIwithDB.Controllers
{
    //ngrok http -host-header=localhost 50726
    
    public class FetchNewsController : ApiController
    {
      //  HttpConfiguration config = GlobalConfiguration.Configuration;
        
       
        public IEnumerable<News> Get()
        {
            using (SmartNewsEntities entities = new SmartNewsEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                return entities.News.ToList();
            }
        }

        #region Not In Use Method 
        /*   public string Get(string category)
        {
            using (var context = new SmartNewsEntities())
            {
                // Query for all blogs with names starting with B
                var blogs = from b in context.News
                            where b.Genre==category
                            select b;

                return "andr hai";
            }
        }*/
         /*   using (SmartNewsEntities entities = new SmartNewsEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                var entity = entities.News.Find(category);
                return entity;
            }
        }*/

       /* public JObject Get(string category="Sports")     
        {
           var url = "https://newsapi.org/v2/everything?" + 
                      "q="+category + 
                      "from=2019-11-24&" + 
                      "sortBy=popularity&" + 
                      "apiKey=c2307e9035be4dfb9100f2beede67378";
           
           WebClient client = new WebClient();  
           
           var fetchNews = client.DownloadString(url);*/
      
         /*  var TranslateNewsEngURL =  string.Format(@"https://translate.googleapis.com/translate_a/single?client=gtx&sl={0}&tl={1}&dt=t&q={2}",
                                   "en","en",Uri.EscapeUriString(fetchNews));
           HttpClient httpClient = new HttpClient();
            
           string result = httpClient.GetStringAsync(TranslateNewsEngURL).Result;
           
           var jsonData = new JavaScriptSerializer().Deserialize<List<dynamic>>(result);
           JsonSerializer jsonSerializer = new JsonSerializer();
           var json = JsonConvert.SerializeObject(jsonData, Formatting.Indented);
           var FetchedNewsEng = client.DownloadString(TranslateNewsEngURL);*/
        /*
           return JObject.Parse(fetchNews);
        }*/

        #endregion


        public string Post([FromBody] JObject CategoryTimeEmail)
        {
            #region Not Important
            /*  var jsonSerializerSettings = new JsonSerializerSettings
            {
                PreserveReferencesHandling = PreserveReferencesHandling.Objects
            };*/

            //return CategoryAndTime["category"];
            //return CategoryAndTime["freeTime"];
            #endregion

            try
            {
                string category = CategoryTimeEmail["category"].ToString();

                using (SmartNewsEntities context = new SmartNewsEntities())
                {
                    var CategoryNews = from b in context.News
                                where b.Genre.Equals(category)
                                select b;
                 
                    // ****************************************************************************************************************                
                    #region Temp Hide Important Code
                    /* List<News> NewsOfCategory = new List<News>();
                    foreach(News EachNews in CategoryNews)
                    {
                        NewsOfCategory.Add(EachNews);

                    }*/

                    
                    /*
                 //   context.Configuration.ProxyCreationEnabled = false;
                    string NewsOfCategoryString = "";
                  
                    
                    if(NewsOfCategory.Count>0)
                    {
                        NewsOfCategoryString = JsonConvert.SerializeObject(NewsOfCategory, jsonSerializerSettings);
                        foreach (var eachnews in newsofcategory)
                        {
                            context.configuration.proxycreationenabled = false;
                            newsofcategorystring += jsonconvert.serializeobject(eachnews,jsonserializersettings);
                            break;
                        }
                    }*/

                    #endregion
                    ///****************************************************************************************************************                
                   
                    // ******************************* logic of time contrainst ****************************************

                    
                    string emailAddress = CategoryTimeEmail["EmailAddress"].ToString();
                    double ReadingSpeed_User_WordPerSec = 0;
                    double FreeTimeToRead = Convert.ToDouble(CategoryTimeEmail["freeTime"].ToString());
                    
                    if (emailAddress.Length > 0)  //Registered User
                    {
                        using (SmartNewsEntities entities = new SmartNewsEntities())
                        {
                            var CurrentUser = entities.Users.First(c => c.EmailAddress.Equals(emailAddress));
                            if (CurrentUser != null)
                            {
                                ReadingSpeed_User_WordPerSec = CurrentUser.TimeTakenForReading;
                            }

                        }
                    }
                    else
                    {
                        ReadingSpeed_User_WordPerSec = 0.26;   //average words per minute user can read are 200-250. Here Selected 233. 
                    }


                    int DataLengthToFetch = Convert.ToInt32((ReadingSpeed_User_WordPerSec * (FreeTimeToRead *60)));

                    string EligibleNewsString="";
                    context.Configuration.ProxyCreationEnabled = false;
                    
                    foreach (var EachNews in CategoryNews)
                    {
                        if (WordsInString(EachNews.NewsDescription)<= DataLengthToFetch)
                        {
                            EligibleNewsString+= JsonConvert.SerializeObject(EachNews)+",";
                        }
                    }

                    EligibleNewsString = EligibleNewsString.Remove(EligibleNewsString.Length - 1, 1);
#region   ExtraCode HTTP CONFIG GLOBAL
                    /* config.Formatters.JsonFormatter
                        .SerializerSettings
                        .ReferenceLoopHandling = Newtonsoft.Json.ReferenceLoopHandling.Ignore;*/
#endregion

                   /*string EligibleNewsString = JsonConvert.SerializeObject(EligibleNews);//, jsonSerializerSettings);*/

                    return EligibleNewsString;
                    // *************************************************************************************************
                    #region ExtraCode 
                    //context.Configuration.ProxyCreationEnabled = false;
                  //  return JsonConvert.SerializeObject(NewsOfCategory);//, jsonSerializerSettings);

//                    return NewsOfCategory.ElementAt(0);
                //return EligibleNewsString;
                   // return NewsOfCategoryString;
                    //return NewsOfCategory.FirstOrDefault();
                    #endregion
                }
            }
            catch(Exception e)
            {
               // return Request.CreateErrorResponse(HttpStatusCode.BadRequest, e);
                return "Error " + e.Message;
            }         
        }

        #region Words In String Method

        public int WordsInString(string str)
        {
            int length = 0;
            int words = 1;

            if (str.Length > 0)
            {
                while (length <= str.Length - 1)
                {
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

        #endregion

    }
}
