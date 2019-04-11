package smarthome.model;

import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static smarthome.model.TypeGAList.newTypeGA;

@Entity
@Table(name = "Geo_Area")
public class GeographicalArea {

    @Id
    @Column(name = "ID")
    private String identification;

    private String designation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID")
    private TypeGA typeOfGa;

    @Embedded
    private Location location;

    @Transient
    private SensorList sensorListInGa;

    @Embedded
    private OccupationArea occupation;

    @Transient
    private GeographicalArea parentGa;


    protected GeographicalArea() {
    }

    /**
     * Constructor required to create a new Geographical Area
     *
     * @param id             String parameter representing the id of the Geographical Area
     * @param name           String parameter representing the name of the Geographical Area
     * @param typeGA         String parameter representing the type of geographical area
     * @param location       central location of the Geographical Area represented by GPS coordinates
     * @param occupationArea Oc
     */
    public GeographicalArea(String id, String name, String typeGA, OccupationArea occupationArea, Location location) {
        this.identification = id;
        this.designation = name;
        this.typeOfGa = newTypeGA(typeGA.toLowerCase());
        this.occupation = occupationArea;
        this.location = location;
        this.sensorListInGa = new SensorList();
    }

    public GeographicalArea(String id, String name, TypeGA typeGA, OccupationArea occupationArea, Location location) {
        this.identification = id;
        this.designation = name;
        this.typeOfGa = typeGA;
        this.occupation = occupationArea;
        this.location = location;
        this.sensorListInGa = new SensorList();
    }

    public GeographicalArea(String id, String name) {
        this.identification = id;
        this.designation = name;
    }

    public String getId() {
        return identification;
    }


    /**
     * method to get this Geographical Area designation
     *
     * @return return this geographical area designation
     */
    public String getGAName() {
        return this.designation;
    }

    /**
     * method to get this Geographical Area Type designation
     *
     * @return return this geographical Area Type designation
     */
    public String getTypeName() {
        return this.typeOfGa.toString();
    }

    public boolean setType(TypeGA newtype) {
        if (newtype != null) {
            this.typeOfGa = newtype;
            return true;
        }
        return false;
    }

    /**
     * Method to return the GA type
     *
     * @return GAType
     */
    public TypeGA getType() {
        return this.typeOfGa;
    }

    /**
     * Method to get this Geographical Area Parent Geographical Area
     *
     * @return return this geographical Area Parent
     */
    public GeographicalArea getGeographicalParentGA() {
        return this.parentGa;
    }


    /**
     * Method to get list of Sensors in GA attribute
     *
     * @return the list of sensors in a Geographical Area
     */
    public SensorList getSensorListInGA() {
        return this.sensorListInGa;
    }

    /**
     * Method to calculate the distance between to Geographical Areas
     *
     * @param anotherGA second Geographical Area
     * @return returns the linear distance already calculated
     */
    public double calculateDistanceTo(GeographicalArea anotherGA) {
        Location anotherLocation = anotherGA.getLocation();
        double distance;
        distance = this.calculateDistance(anotherLocation);
        //return this.mLocation.calcLinearDistanceBetweenTwoPoints(anotherLocation); advanced method
        return distance;
    }

    /**
     * method to get this Geographical Area location
     *
     * @return return this geographical area location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Method to call the method in Location which executes the calculation of the linear distance between two locations
     *
     * @param aLocation second geographical area location
     * @return returns the linear distance already calculated
     */
    private double calculateDistance(Location aLocation) {
        return this.location.calcLinearDistanceBetweenTwoPoints(this.location, aLocation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeographicalArea)) {
            return false;
        }
        GeographicalArea that = (GeographicalArea) o;
        return Objects.equals(this.identification, that.identification) /*&&
                Objects.equals(this.designation, that.designation) &&
                Objects.equals(this.typeOfGa, that.typeOfGa)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.identification/*, this.designation, this.typeOfGa*/);
    }

    /**
     * US07
     * Method that tell´s if a Geographical Area is parented or contained on other.
     *
     * @param ga1 is defined as an geographical area parented with other.
     */
    public void setParentGA(GeographicalArea ga1) {
        this.parentGa = ga1;
    }

    public OccupationArea getOccupation() {
        return this.occupation;
    }

    public GeographicalAreaDTO toDTO() {
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        for (Sensor sensor : this.sensorListInGa.getSensorList()) {
            SensorDTO sensorDTO = sensor.toDTO();
            sensorListDTO.add(sensorDTO);
        }
        return new GeographicalAreaDTO(this.identification, this.designation, sensorListDTO);
    }

    public void setIdentification(String id) {
        this.identification = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    //TODO: tests

    public String gaInString(){

        StringBuilder output = new StringBuilder();
        String space = "    ";
        String comma = ", ";

        output.append(space);
        output.append(designation);
        output.append(comma);
        output.append(identification);


        return output.toString();
    }
}







