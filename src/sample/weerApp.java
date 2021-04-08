package sample;
import java.net.URL;
import java.util.ArrayList;


public class weerApp<weerType> {
    static String apiKey = "YOUR_API_KEY";
    static String defaultCity = "Den+Bosch";

    // Main Method die de data via een HTTP client (class weerData) gaat ophalen
    public static ArrayList<String> weerAppStart(String place) {
        String antwoord = "";
        ArrayList<String> weerType = new ArrayList();
        String input = place;
        if (input.isEmpty()){
            input = defaultCity;
        }
        String stad = input.replaceAll(" ", "+").toLowerCase();

        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + stad + "&appid=" + apiKey);
            weerData ht = new weerData(url);
            //Connect to a HTTP url with "Connect Method", "User" and "Password" if required only.
            String json = ht.httpConnect("", "GET", "user", "password");
            antwoord = json.substring(0,(json.length()-1));
            try {
                weerDataParser parseData = new weerDataParser();
                weerType = parseData.getWeatherData(antwoord);
                return weerType;
            } catch (Exception e) {
                System.out.println("werkt niet");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //programma klaar
        System.out.println("***********>>>> Tot zover het weerbericht <<<<**************");
        return weerType;
    }

}
