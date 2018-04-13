package nexumcorp.projectnexum;

public class location {

    public String name;
    public String address;
    public String location_id;
    public double latitude;
    public double longitude;

    public location(){

    }

    public location(String name, String address, String location_id, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
