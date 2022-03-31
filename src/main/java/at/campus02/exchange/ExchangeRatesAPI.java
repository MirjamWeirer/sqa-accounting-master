package at.campus02.exchange;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ExchangeRatesAPI {
    private final String api_key;
    private final String api_url;

    public ExchangeRatesAPI(String api_url, String api_key) {
        this.api_key = api_key;
        this.api_url = api_url;
    }

    public static ExchangeRatesAPI fromConfig(String filename) throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(ExchangeRatesAPI.class.getClassLoader().getResourceAsStream(filename));
            return new ExchangeRatesAPI(properties.getProperty("api_url"), properties.getProperty("api_key"));
        } catch (IOException e) {
            System.out.println("Problem loading exchange rate API properties file");
            throw e;
        }
    }

    public ExchangeRates fromAPI() throws IOException {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(api_url + api_key);
            ResponseHandler<JSONObject> responseHandler = new JsonHandler();
            JSONObject json = httpclient.execute(httpget, responseHandler);
            return ExchangeRates.fromJson(json);
        } catch (IOException e) {
            System.out.println("An error occurred while loading exchange rates.");
            throw e;
        }
    }

    public static ExchangeRates fromFile(String filename) throws IOException, URISyntaxException {
        try {
            URL path = ExchangeRatesAPI.class.getClassLoader().getResource(filename);
            if (path == null) {
                throw new IOException("File '" + filename + "' not found");
            }
            String json = Files.readString(Path.of(path.toURI()));
            return ExchangeRates.fromJson(new JSONObject(json));
        } catch (NullPointerException | IOException | URISyntaxException e) {
            System.out.println("Problem loading exchange rates from file");
            throw e;
        }
    }
}
