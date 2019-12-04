using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http.Filters;
using System.Net.Http;
using System.Net;
using System.Text;
using System.Threading;
using System.Security.Principal;

namespace WebAPIwithDB
{
    public class BasicAuthenticationAttribute : AuthorizationFilterAttribute
    {
        public override void OnAuthorization(System.Web.Http.Controllers.HttpActionContext actionContext)
        {
            if(actionContext.Request.Headers.Authorization == null)
            {
                actionContext.Response = actionContext.Request.CreateResponse(HttpStatusCode.Unauthorized);
            }
            else
            {
                //AuthenticationToken will be base 64 encoded string [ email:password ]
                string AuthenticationToken = actionContext.Request.Headers.Authorization.Parameter;
                string DecodedAuthenticationToken = Encoding.UTF8.GetString(Convert.FromBase64String(AuthenticationToken));
                string[] UserNamePasswordArray = DecodedAuthenticationToken.Split(':');
                string Username = UserNamePasswordArray[0];
                string Password = UserNamePasswordArray[1];

                if(UserSecurity.Login(Username,Password))
                {
                    Thread.CurrentPrincipal = new GenericPrincipal(new GenericIdentity(Username), null);
                    actionContext.Response = actionContext.Request.CreateResponse(HttpStatusCode.OK, "UserFound");
                }
                else
                {
                    actionContext.Response = actionContext.Request.CreateResponse(HttpStatusCode.Unauthorized,"Invalid Credentials");
                }
            }
        }
    }
}