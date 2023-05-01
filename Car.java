
/**
 * The first project for COS70006
 * A parking slot system: Class Car will be identified by a car registration number. A registration number always starts with a
 *                        capital letter, followed by a five-digit number e.g. “T12345”. It also have an owner and knows if the
 *                        owner is a staff member. If the car park in a slot, the slot number should be recorded.
 * @author    Mona Ma (103699029)
 * @version   JDK1.8 , javac 11.0.14
 * @date      Created on 31 March 2022
 */
public class Car {
    private String regNumber;         //register number of the car which is the ID of a car
    private String ownerType;         //owner type of the car, which should be a staff or visitor
    private String owner;             //owner name of the car


    /**
     * constructor for objects of class Car.
     * @param regNumber
     * @param ownerType
     * @param owner
     */
    public Car(String regNumber, String ownerType, String owner) {
        this.regNumber = regNumber;
        this.ownerType = ownerType;
        this.owner = owner;
    }

    /**
     * getter method for variable registerNumber
     * @return regNumber
     */
    public String getRegNumber() {
        return regNumber;
    }

    /**
     * setter method for variable registerNumber
     * @param regNumber
     */
    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    /**
     * getter method for variable ownerType
     * @return ownerType
     */
    public String getOwnerType() {
        return ownerType;
    }

    /**
     * setter method for variable ownerType
     * @param ownerType
     */
    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    /**
     * getter method for variable owner
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * setter method for variable owner
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}

