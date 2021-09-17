package longdh.utils;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import longdh.registration.RegistrationDTO;

public class GoogleUtils {

    public static String getRedirect() {

        String redirectLink = "https://accounts.google.com/o/oauth2/auth"
                + "?scope=profile email"
                + "&redirect_uri=" + Constants.GOOGLE_REDIRECT_URI
                + "&response_type=code"
                + "&client_id=" + Constants.GOOGLE_CLIENT_ID
                + "&approval_prompt=force";
        return redirectLink;
    }

    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static RegistrationDTO getUserInfo2(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        RegistrationDTO dto = new Gson().fromJson(response, RegistrationDTO.class);
        return dto;
    }

}
