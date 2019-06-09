
public class airports implements Comparable<airports> {
	private String iata;
	private String name;
	private double longitude;
	private double latitude;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public int compareTo(airports o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getIata());
	}
	
	
}
