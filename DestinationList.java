import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;
@SuppressWarnings("rawtypes")
public class DestinationList {
	private static ArrayList<airports> airportList = new ArrayList<airports>();
	
	public static void dest() {
		//Change this string to read the file from the proper location
		String File = "C:\\Users\\Felicia\\git\\airplaneflightscheduler\\newdoc.json";
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(File)) {
        	ArrayList<airports> airportTempList = new ArrayList<airports>();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray destinations = (JSONArray) jsonObject.get("airports");
            
            Iterator it = destinations.iterator();
            
            JSONObject jo = new JSONObject();
            
            System.out.println();
            while(it.hasNext()) {
            	airports airport = new airports();
            	jo = (JSONObject) it.next();
            	String iata = (String) jo.get("iata");
                airport.setIata(iata);

                String name = (String) jo.get("name");
                airport.setName(name);
                
                String lat = (String) jo.get("lat");
                double la = Double.parseDouble(lat);
                airport.setLatitude(la);
                String lon = (String) jo.get("lon");
                
                double lo = Double.parseDouble(lon);
                airport.setLongitude(lo);
                airportTempList.add(airport);

            }
            sortAirports(airportTempList);
            setAirportList(airportTempList);
            String[] d = new String[airportTempList.size()];
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

	public static void sortAirports(ArrayList<airports> alist) {
		Collections.sort(alist);
	}

	public static ArrayList<airports> getAirportList() {
		return airportList;
	}

	public static void setAirportList(ArrayList<airports> airportList) {
		DestinationList.airportList = airportList;
	}
	//getters for airport object array individual values
	public static String getAirportListName(ArrayList<airports> airportList, int i) {
		return airportList.get(i).getName();
	}
	
	public static String getAirportListIata(ArrayList<airports> airportList, int i) {
		return airportList.get(i).getIata();
	}
	public static double getAirportListlongitude(ArrayList<airports> airportList, int i) {
		return airportList.get(i).getLongitude();
	}
	
	public static double getAirportListlatitude(ArrayList<airports> airportList, int i) {
		return airportList.get(i).getLatitude();
	}
	
	//String Array for JCombo drop down menu
	public static List<String> destList(ArrayList<airports> airportList) {
		List<String> iata = new ArrayList<String>();
		for(int i = 0; i < airportList.size(); i++)
		{
			iata.add(airportList.get(i).getIata());
		}
		return iata;
	}
	//Calculate the distance between two points given destination codes
	//TODO create a binary search for this function
	public static double distance(String coor1, String coor2, ArrayList<airports>port)
	{
		double long1 = 0;
		double long2 = 0;
		double lat1 = 0;
		double lat2 = 0;
		double earthrad = 6371;
		for(int i = 0; i < port.size(); i++ )
		{
			int found = 0;
			if(port.get(i).getIata().equals(coor1))
			{
				long1 = port.get(i).getLongitude();
				lat1 = port.get(i).getLatitude();
				found = found + 1;
			}
			else if(port.get(i).getIata().equals(coor2))
			{
				long2 = port.get(i).getLongitude();
				lat2 = port.get(i).getLatitude();
				found = found + 1;
			}
			else if(found == 2)
			{
				break;
			}
		}
		double dlat = Math.toRadians(lat2-lat1);
		double dlong = Math.toRadians(long2-long1);
		double a = Math.sin(dlat/2) * Math.sin(dlat/2)
				+ Math.cos(Math.toRadians(lat1)) *Math.cos(Math.toRadians(lat2))
				* Math.sin(dlong/2)*Math.sin(dlong/2);
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = earthrad * c * 1000; //meters
		distance = Math.pow(distance, 2);
		return Math.sqrt(distance);
	}
	//For Testing purposes
	public static void display(String[] li) {
		for(int i = 0; i < airportList.size(); i++)
		{
			System.out.println(li[i]);
		}
	}
	
	
	


}