package at.campus02.exchange;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

class JsonHandler implements ResponseHandler<JSONObject> {

    @Override
    public JSONObject handleResponse(HttpResponse httpResponse) throws IOException {
        int status = httpResponse.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = httpResponse.getEntity();
            return entity != null ? new JSONObject(EntityUtils.toString(entity)) : null;
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
    }
}
