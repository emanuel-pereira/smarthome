package smarthome.model;

public class Address {

    private String mStreetName;
    private String mHouseNumber;
    private String mApartmentNumber;
    private String mZipCode;
    private Location mGPSLocation;

    /**
     * This constructor sets up the Address that will be used in the House
     *
     * @param streetName      Required street name
     * @param houseNumber     Required house number
     * @param apartmentNumber If necessary the apartment number
     * @param zipCode         Required zip code
     * @param gpsLocation     Required GPS location
     */
    public Address(String streetName, String houseNumber, String apartmentNumber, String zipCode, Location gpsLocation) {
        if (this.validateName (streetName)) {
            mStreetName = streetName;
        }
        if (this.validateNumber (houseNumber)) {
            mHouseNumber = houseNumber;
        }
        mApartmentNumber = apartmentNumber;
        if (this.validateZipCode (zipCode)) {
            mZipCode = zipCode;
        }
        mGPSLocation = gpsLocation;
    }

    public Address(String streetName, String houseNumber, String apartmentNumber, String zipCode, double latitude, double longitude, double altitude) {
        if (this.validateName (streetName)) {
            mStreetName = streetName;
        }
        if (this.validateNumber (houseNumber)) {
            mHouseNumber = houseNumber;
        }
        mApartmentNumber = apartmentNumber;
        if (this.validateZipCode (zipCode)) {
            mZipCode = zipCode;
        }
        mGPSLocation = new Location (latitude, longitude, altitude);
    }

    /**
     * Validates the name of the street
     *
     * @param name The name of the street
     * @return False if nulls, empty spaces and texts that start with spaces
     */
    public boolean validateName(String name) {
        if (name == null || name.trim ().isEmpty ()) {
            return false;
        }
        if (!name.matches ("^(?![\\s]).*")) {
            return false;
        }
        return true;
    }

    /**
     * Get de street name
     *
     * @return Name of the street
     */
    public String getName() {
        return mStreetName;
    }

    /**
     * Validates the number of the house. It accepts only numbers with no spaces
     *
     * @param number The number of the house
     * @return False if nulls, empty spaces and numbers that starts and ends with spaces
     */
    public boolean validateNumber(String number) {
        if (number == null || number.trim ().isEmpty ()) {
            return false;
        }
        if (!number.matches ("^(?![\\s])\\d*")) {
            return false;
        }
        return true;
    }

    /**
     * Get house number
     *
     * @return Number of the house
     */
    public String getHouseNumber() {
        return mHouseNumber;
    }

    /**
     * Validates the zip code of the house
     *
     * @param code The zip code of the house
     * @return False if nulls, empty spaces and if it starts with spaces
     */
    public boolean validateZipCode(String code) {
        if (code == null || code.trim ().isEmpty ()) {
            return false;
        }
        if (!code.matches ("^(?![\\s]).*")) {
            return false;
        }
        return true;
    }

    /**
     * Get de zip code
     *
     * @return The zip code of the house
     */
    public String getZipCode() {
        return mZipCode;
    }

    /**
     * Get GPS location
     *
     * @return The GPS location of the house
     */
    public Location getGPSLocation() {
        return mGPSLocation;
    }

}

