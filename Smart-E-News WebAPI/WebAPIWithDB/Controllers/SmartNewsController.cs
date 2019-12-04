
using SmartNewsWindows;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading;
using System.Web.Http;
using System.Security.Cryptography;
using System.Text;
using Newtonsoft.Json.Linq;

namespace WebAPIwithDB.Controllers
{

    public class SmartNewsController : ApiController
    {
        [BasicAuthentication]
        public IEnumerable<User> Get()
        {
            using (SmartNewsEntities entities = new SmartNewsEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                return entities.Users.ToList();
            }
        }

        private string ComputeSha256Hash(string data)
        {
            using(SHA256 sha256Hash = SHA256.Create())
            {
                byte[] bytes = sha256Hash.ComputeHash(Encoding.UTF8.GetBytes(data));
                
                StringBuilder builder = new StringBuilder();
                for(int i=0; i< bytes.Length; i++)
                {
                    builder.Append(bytes[i].ToString("x2"));
                }
                
                return builder.ToString();
            }
        }

        //[BasicAuthentication]
        

        
        
        public HttpResponseMessage Post([FromBody] User newUser)
        {
           /*  SmartNewsEntities nd = new  SmartNewsEntities();
            nd.Users.Add(new User()
            {
                UserID= e.UserID,
                TimeTakenForReading = e.TimeTakenForReading,
                EmailAddress= e.EmailAddress,
                Password = e.Password         
            });
            nd.SaveChanges();*/
            try
            {
                using (SmartNewsEntities entities = new SmartNewsEntities())
                {
                    newUser.Password = ComputeSha256Hash(newUser.Password);
                    entities.Users.Add(newUser);
                    entities.SaveChanges();

                    var message = Request.CreateResponse(HttpStatusCode.Created, newUser);
                    message.Headers.Location = new Uri(Request.RequestUri + newUser.UserID.ToString());
                    return message;
                }
            }
            catch(Exception e)
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, e);
            }
            
        }

      







    }
}
