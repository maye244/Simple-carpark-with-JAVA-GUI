
/**
 * The first project for COS70006
 * A parking slot system: Class ParkingSlot is identified for all parking slots in the car park system.
 *                        There are two types of parking slots: slots only for visitors and slots only for staff members. A
 *                        parking slot must have an identifier, which starts with a capital letter followed by a three-digit
 *                        number e.g. “S001”, “V127”. A parking slot also should know if a car and what car is parked in the slot.
 * @author    Mona Ma (103699029)
 * @version   JDK1.8 , javac 11.0.14
 * @date      Created on 31 March 2022
 */
public class ParkingSlot {
    private String slotType;                             //parking slot type which should be visitor and staff
    private String slotNumber;                           //parking slot number which should starts with a capital letter followed by a three-digit number
    private Car car;
    
        /**
     * getter method for variable studentFirstName
     * @return slotType
     */
    public String getSlotType() {
        return slotType;
    }

    /**
     * setter method for variable parkingSlotType
     * @param slotType
     */
    public void setSlotType(String slotType) {
         this.slotType = slotType;
     }

    /**
     * constructor for objects of class ParkingSlot.
     * @param slotType
     * @param slotNumber
     */
    public ParkingSlot(String slotType, String slotNumber) {
        this.slotType = slotType;
        this.slotNumber = slotNumber;
    }



    /**
     * getter method for variable parkingSlotNumber
     * @return slotNumber
     */
    public String getSlotNumber() {
        return slotNumber;
    }

    /**
     * setter method for variable parkingSlotNumber
     * @param slotNumber
     */
     public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    /**
     * getter method for variable parkingCarID
     * @return car
     */
    public Car findCar() {
        return car;
    }

    /**
     * setter method for variable parkingCarID
     * @param car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * remove the car from a parking slot
     * @param car
     */
    public void removeCar(Car car){
        this.car = null;
    }

    /**
     * print all parking slots details in the car park
     * @return string
     */
    public String toString(){

        String type = " for visitor";
        String occupied;

        if(slotType.equals("Staff")){
            type = " for staff";
        }

        if (car == null){
            occupied = "Not occupied";
        }
        else {
            occupied = "Car register number:" + car.getRegNumber() +"  " + "Owner: "+car.getOwner();
        }
        return "Slot ID is: " + slotNumber + "\n " + occupied +"\n";
    }

}


