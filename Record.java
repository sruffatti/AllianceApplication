/**
 * The record class it an object that will hold the address from the database, and then retrieve
 * the ward, alderman, police district, and police beat.
 */

/**
 * @author Sean Ruffatti
 * @version 1.3
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
public class Record extends Observable{
	
	private int id;
	private String address;
	private String ward;
	private String alderman;
	private String policeDistrict;
	private String policeBeat;
	private String lng;
	private String lat;
	private String apiKey;
	
	/**
	 * Constructor for record class. 
	 * @param address
	 */
	public Record(int id, String address) {
		this.id = id;
		this.address = address;
		this.apiKey = "AIzaSyDBMTCxp7f3iz-tM4kDH2mFpOMlDwDsGG0";
		this.lat = "";
		this.lng = "";
	}

	/**
	 * no-Arg constructor for Record class
	 */
	public Record() {}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the ward
	 */
	public String getWard() {
		return ward;
	}

	/**
	 * @param ward the ward to set
	 */
	public void setWard(String ward) {
		this.ward = ward;
	}

	/**
	 * @return the alderman
	 */
	public String getAlderman() {
		return alderman;
	}

	/**
	 * @param alderman the alderman to set
	 */
	public void setAlderman(String alderman) {
		this.alderman = alderman;
	}

	/**
	 * @return the policeDistrict
	 */
	public String getPoliceDistrict() {
		return policeDistrict;
	}

	/**
	 * @param policeDistrict the policeDistrict to set
	 */
	public void setPoliceDistrict(String policeDistrict) {
		this.policeDistrict = policeDistrict;
	}

	/**
	 * @return the policeBeat
	 */
	public String getPoliceBeat() {
		return policeBeat;
	}

	/**
	 * @param policeBeat the policeBeat to set
	 */
	public void setPoliceBeat(String policeBeat) {
		this.policeBeat = policeBeat;
	}

	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * Method to initiate the information gathering process. 
	 * <p>
	 * Calls the methods, queryGoogle, queryWard, queryPoliceDistrict, queryPoliceBeat
	 */
	public void gatherInformation() {
		//format address
		String[] fields = address.split(" ");
		String formattedAddress = "";
		
		formattedAddress = formatAddress(fields, formattedAddress);
		queryGoogle(formattedAddress);
		queryWard(lat, lng);
		queryPoliceDistrict(lat, lng);
		queryPoliceBeat(lat, lng);
	}

	/**
	 * @param fields
	 * @param formattedAddress
	 * @return
	 */
	private String formatAddress(String[] fields, String formattedAddress) {
		for(int i = 0; i < fields.length; i++) {
			if(i != fields.length -1) {
				formattedAddress += fields[i]+ "+";
			} 
			else {
				formattedAddress += fields[i];
			}
		}
		return formattedAddress;
	}

	/**
	 * Method to retrieve police beat information
	 * @param lat
	 * @param lng
	 */
	private void queryPoliceBeat(String lat, String lng) {
		try {
			URL url = new URL("http://boundaries.tribapps.com/1.0/boundary/?contains="+lat+","+lng+"&sets=police-beats");
			JsonObject obj = jsonRetrieval(url);
			JsonArray results = obj.getJsonArray("objects");
			for(JsonObject result : results.getValuesAs(JsonObject.class)) {
				JsonObject x1 = result.getJsonObject("metadata");
				setPoliceBeat(x1.getJsonString("BEAT_NUM").getString());
			}
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Error in retrieving police beat information. Malformed URL Exception");
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error in retrieving police beat information. IO Exception");
		}
	}

	
	/**
	 * method to retrieve police district
	 * @param lat
	 * @param lng
	 */
	private void queryPoliceDistrict(String lat, String lng) {
		try {
			URL url = new URL("http://boundaries.tribapps.com/1.0/boundary/?contains="+lat+","+lng+"&sets=police-districts");
			JsonObject obj = jsonRetrieval(url);
			JsonArray results = obj.getJsonArray("objects");
			for(JsonObject result : results.getValuesAs(JsonObject.class)) {
				JsonObject x1 = result.getJsonObject("metadata");
				setPoliceDistrict(x1.getJsonString("DIST_NUM").getString());
			}
		} catch (MalformedURLException e) {
			System.out.println("Error in retrieving police district information. Malformed URL");
		}
		catch (IOException e) {
			System.out.println("Error in retrieving police district information. IO Exception");
		}
	}

	/**
	 * Method for retrieving the ward and alderman from tribapps
	 * @param lat
	 * @param lng
	 */
	private void queryWard(String lat, String lng) {
		try {
			URL url = new URL("http://boundaries.tribapps.com/1.0/boundary/?contains="+lat+","+
					lng+"&sets=wards");
			JsonObject obj = jsonRetrieval(url);
			JsonArray results = obj.getJsonArray("objects");
			for(JsonObject result : results.getValuesAs(JsonObject.class)) {
				JsonObject x1 = result.getJsonObject("metadata");
				setAlderman(x1.getJsonString("ALDERMAN").getString());
				setWard(x1.getJsonString("WARD").getString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("Error in retrieving ward information. IO Exception.");
		}
	}

	/**
	 * method for retrieving lat and lng for a given address
	 * @param formattedAddress
	 */
	private void queryGoogle(String formattedAddress) {
			try {
				URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+formattedAddress+
						"&key="+getApiKey());
				JsonObject obj = jsonRetrieval(url);
				JsonArray results = obj.getJsonArray("results");
				for(JsonObject result : results.getValuesAs(JsonObject.class)) {
					JsonObject x = result.getJsonObject("geometry");
					setLat(x.getJsonObject("location").getJsonNumber("lat").toString());
					setLng(x.getJsonObject("location").getJsonNumber("lng").toString());
				}
			} 
			catch (MalformedURLException e) {
				e.printStackTrace();
				System.out.println("Error in retrieving latitude and longitude. Malformed URL Exception.");
			}
			catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error in retrieving latitude and longitude. IO Exception.");
			}
		}
	
	/**
	 * method used to set up Json parser
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private JsonObject jsonRetrieval(URL url) throws IOException {
		InputStream is = url.openStream();
		JsonReader rdr = Json.createReader(is);
		JsonObject obj = rdr.readObject();
		return obj;
	}

	/**
	 * toString method of record class
	 */
	public String toString() {
		String formatted = String.format("ID: %s Address: %s Lat: %s Lng: %s Ward: %s Alderman: %s Police District: %s Police Beat: %s",
				id, address, lat, lng, ward, alderman, policeDistrict, policeBeat);
		return formatted;
	}
}
