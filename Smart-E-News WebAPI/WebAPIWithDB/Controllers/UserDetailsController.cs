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
    public class UserDetailsController : ApiController
    {
        
        public HttpResponseMessage Post([FromBody] JObject Email)
        {
            using (SmartNewsEntities entities = new SmartNewsEntities())
            {
                if (Email != null)
                {
                    string EmailAddress = Email["EmailAddress"].ToString();

                    entities.Configuration.ProxyCreationEnabled = false;
                    var entity = entities.Users.FirstOrDefault(e => e.EmailAddress.Equals(EmailAddress));

                    if (entity != null)
                    {
                        entity.Password = "**********";
                        return Request.CreateResponse(HttpStatusCode.OK, entity);
                    }
                    else
                    {
                        return Request.CreateErrorResponse(HttpStatusCode.NotFound, "User with EmailAddress " + EmailAddress + " Not Found");
                    }
                }
                return null;
            }
        }



    }
}
