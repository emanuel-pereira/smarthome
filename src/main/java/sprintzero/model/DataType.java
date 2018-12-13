package sprintzero.model;


import org.jetbrains.annotations.Contract;

import java.util.Objects;

public class DataType {


    private String mDataType;


    /**
     * Constructor method that defines a designation for a type of data
     *
     * @param dataTypeDesignation - String that names the type of data
     */
    public DataType(String dataTypeDesignation) {
        if (this.typeOfDataDesignationIsValid(dataTypeDesignation)) {
            this.mDataType = dataTypeDesignation;
        }
    }

    /**
     * Method to validate the designation given to a type of data
     *
     * @param dataTypeDesignation - String that names the type of data
     * @return true if the designation is not null or empty after trimming the spaces
     */
    public boolean typeOfDataDesignationIsValid(String dataTypeDesignation) {
        return dataTypeDesignation != null && !dataTypeDesignation.trim().isEmpty();
    }


    /**
     * Method to return a data type designation
     *
     * @return designation of a data type
     */
    public String getDataTypeDesignation() {
        return this.mDataType;
    }


    @Contract(value = "null -> false", pure = true)
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DataType))
            return false;
        DataType dataType = (DataType) o;
        return this.mDataType.equals(dataType.getDataTypeDesignation());
    }

    /**
     * hashCode() just returns the object's address in memory
     * This method OverRides the hashCode method defined by class
     * Object does also return distinct integers for distinct objects.
     * This is typically implemented by converting the internal
     * address of the object into an integer
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(mDataType);
    }
}


