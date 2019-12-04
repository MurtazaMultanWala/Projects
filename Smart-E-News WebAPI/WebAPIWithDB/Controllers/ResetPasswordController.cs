using Newtonsoft.Json.Linq;
using SmartNewsWindows;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Security.Cryptography;
using System.Text;
using System.Web.Http;

namespace WebAPIwithDB.Controllers
{
    public class ResetPasswordController : ApiController
    {
        public string Post([FromBody] JObject EmailPass)
        {
            string EmailAddress = EmailPass["EmailAddress"].ToString();
            string Password = EmailPass["Password"].ToString();

            using (SmartNewsEntities context = new SmartNewsEntities())
            {
                var RequiredUser = context.Users.First(e => e.EmailAddress.Equals(EmailAddress));

                if (RequiredUser != null)
                {
                    RequiredUser.Password = ComputeSha256Hash(Password);
                    context.SaveChanges();
                    return "Password Updated";
                }
                return "Invalid Email Address";
            }
        }

        private string ComputeSha256Hash(string data)
        {
            using (SHA256 sha256Hash = SHA256.Create())
            {
                byte[] bytes = sha256Hash.ComputeHash(Encoding.UTF8.GetBytes(data));

                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < bytes.Length; i++)
                {
                    builder.Append(bytes[i].ToString("x2"));
                }

                return builder.ToString();
            }
        }
    }
}
