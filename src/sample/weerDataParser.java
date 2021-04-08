package sample;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.DecimalFormat;

import java.util.ArrayList;

//classe om de weer info uit de String te halen en in de terminal te printen
public class weerDataParser {

    //JSON parser object aanmaken
    JSONParser parser = new JSONParser();
    String foutMelding = "er is iets mis gegaan";

    //formatter voor de temperaturen (1 getal achter de komma)
    DecimalFormat df = new DecimalFormat("0.0");

    public ArrayList<String> getWeatherData(String antwoord) throws Exception {
        ArrayList<String> weer = new ArrayList();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(antwoord);

            //algemene array uit object halen en in variabelen stoppen om daarna in de ArrayList te zetten
            String city = (String)jsonObject.get("name");
            JSONArray tempData = (JSONArray) jsonObject.get("weather");
            String weatherType = (String)((JSONObject) tempData.get(0)).get("main");
            System.out.println("Weertype: " + ((JSONObject) tempData.get(0)).get("main"));
            String weatherDesc = (String)((JSONObject) tempData.get(0)).get("description");
            System.out.println("Beschrijving: " + ((JSONObject) tempData.get(0)).get("description"));

            //array met alle meetgegevens uit JSON halen om in variabelen te steken en daarna in de ArrayList te zetten

            JSONObject tempData1 = (JSONObject) jsonObject.get("main");
            Double temperatureXL = (Double)tempData1.get("temp") - 273.15;
            Double minTempXL = (Double)tempData1.get("temp_min") - 273.15;
            Double maxTempXL = (Double)tempData1.get("temp_max") - 273.15;
            String temperature = df.format(temperatureXL);
            String minTemp = df.format(minTempXL);
            String maxTemp = df.format(maxTempXL);
            String pressure = tempData1.get("pressure").toString();
            String humidity = tempData1.get("humidity").toString();

/*
 de ArrayList heeft altijd de volgende structuur: index 0 - stad, 1 - weertype, 2 - weerbeschrijving,
  3 - temperatuur, 4 - minTemp, 5 - maxTemp, 6 - luchtdruk, 7 - luchtvochtigheid

 */
            weer.add(city);
            weer.add(weatherType);
            weer.add(weatherDesc);
            weer.add(temperature);
            weer.add(minTemp);
            weer.add(maxTemp);
            weer.add(pressure);
            weer.add(humidity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weer;
    }
}


