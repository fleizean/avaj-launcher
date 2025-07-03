package files.ui.avaj.coordinates;

public class Coordinates {
    int longitude;
    int latitude;
    int height;

    public Coordinates(int p_longitude, int p_latitude, int p_height) {
        // Subject.pdf
        // • Coordinates are positive numbers.
        // • The height is in the 0-100 range.
        // • If an aircraft needs to pass the upper limit height it remains at 100.
        this.longitude = p_longitude;
        this.latitude = p_latitude;
        this.height = (p_height > 100) ? 100 : (p_height < 0) ? 0 : p_height;
    }

    public int getLongitude() { return longitude; }

    public int getLatitude() { return latitude; }

    public int getHeight() { return height; }
}
