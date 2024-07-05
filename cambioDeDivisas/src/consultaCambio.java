import com.google.gson.*;
import com.sun.net.httpserver.HttpsConfigurator;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class consultaCambio {
    private final Gson gson;

    public consultaCambio(){
        this.gson = new GsonBuilder()
                .registerTypeAdapter(cambio.class,new cambioTypeAdapter())
                .create();
    }

    public cambio cambioDivisa(String base_code,String target_code,float amount) {
        URI dirreccion = URI.create("https://v6.exchangerate-api.com/v6/727008b47eb14eb25fbd1af6/pair/" + base_code + "/" + target_code+"/"+amount);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(dirreccion)
                .build();


        try{
            HttpResponse<String> response = null;
            response=client
                    .send(request,HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), cambio.class);
        }catch(Exception e){
            throw new RuntimeException("No se encontró ese cambio.");
        }
    }

    public cambio cambioDivisa(String base_code, String target_code) {
        return cambioDivisa(base_code, target_code, 1); // Llama al método principal con `amount = 1`
    }
    private static class cambioTypeAdapter implements JsonDeserializer<cambio> {

        @Override
        public cambio deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String timeLastUpdateUtc = jsonObject.get("time_last_update_utc").getAsString();
            String baseCode = jsonObject.get("base_code").getAsString();
            String targetCode = jsonObject.get("target_code").getAsString();
            float conversionRate = jsonObject.get("conversion_rate").getAsFloat();
            float conversionResult = jsonObject.get("conversion_result").getAsFloat();
            return new cambio(timeLastUpdateUtc, baseCode, targetCode, conversionRate, conversionResult);
        }
    }

}
