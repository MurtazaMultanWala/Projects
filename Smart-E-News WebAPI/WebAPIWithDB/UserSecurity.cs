using SmartNewsWindows;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Security.Cryptography;
using System.Text;

namespace WebAPIwithDB
{
    public class UserSecurity
    {
        public static bool Login(string EmailAddress, string password)
        {
            password = ComputeSha256Hash(password);
            
            using (SmartNewsEntities entities = new SmartNewsEntities())
            {
                return entities.Users.Any(user => user.EmailAddress.Equals(EmailAddress) && user.Password == password);
            }
        }

        private static string ComputeSha256Hash(string data)
        {
            using(SHA256 sha256Hash = SHA256.Create())
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