package gamingclaus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class CurrencyConvertApi {
    final private String UrlAPi = "https://v6.exchangerate-api.com/v6/bdef54a8d2be39da15c4e015/latest/USD";




    //this method has the key values for the currency rates.
    public List<String> CurrencyValueKeys() throws IOException, InterruptedException{
        HttpResponse<String> response = HTTPHandler();
            
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONObject conversion_ratesObject = jsonResponse.getJSONObject("conversion_rates");

            //Made an arraylist to store all the currency codes
            List<String> currencyCodes = new ArrayList<>();

            //need an iterator to look over all the keys
            Iterator<String> currencykeys = conversion_ratesObject.keys();

            //loops over the keys until it has none left
            while(currencykeys.hasNext()){
                String key = currencykeys.next();
                currencyCodes.add(key);
            }

            return currencyCodes;
    }
    
    
    
    //This makes it easy to make gather values easier and using it on other places
    private HttpResponse<String> HTTPHandler() throws IOException, InterruptedException{
          //Create a HTTP request
          HttpRequest request = HttpRequest.newBuilder()
          .GET()
          .uri(URI.create(UrlAPi))
          .build();
  
          //Create the HTTP CLIENT
          HttpClient client = HttpClient.newBuilder().build();
          
          //Send the request and get the response
          HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
        return response;
    }

}
