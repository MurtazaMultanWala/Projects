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
    public class UpdateReadingTimeController : ApiController
    {

        public string Post([FromBody] JObject EmailTime)
        {
            if (EmailTime != null)
            {
                string EmailAddress = EmailTime["EmailAddress"].ToString();
                string TimeTakenForReadingInString = EmailTime["TimeTakenForReading"].ToString();
                double TimeTakenForReading = Convert.ToDouble(TimeTakenForReadingInString);

                using (SmartNewsEntities context = new SmartNewsEntities())
                {
                    var RequiredUser = context.Users.First(e => e.EmailAddress.Equals(EmailAddress));

                    if (RequiredUser != null)
                    {
                        RequiredUser.TimeTakenForReading = TimeTakenForReading;
                        context.SaveChanges();
                        return "Time Updated";
                    }
                    return "Invalid Email Address";
                }
            }
            return "Null";
        }
    }
}
