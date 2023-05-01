
/**
 * The first project for COS70006
 * A parking slot system: Class CarPark is responsible for maintaining a list of available parking slots. It is able to
 *                        find a slot, add a slot, delete a slot, and provide a list of all slots included in the car park.
 *                        It can also find a car, add a car and remove a car in the car park.
 * @author    Mona Ma (103699029)
 * @version   JDK1.8 , javac 11.0.14
 * @date      Created on 31 March 2022
 */
import java.util.ArrayList;

public class CarPark {

    private ArrayList<ParkingSlot> parkingSlots;  //create a new ParkingSlot arraylist object
    private ParkingSlot ps;

    /**
     * constructor for objects of class Car.
     */
    public CarPark(){
        parkingSlots = new ArrayList<>();
    }

    /**
     * list all parking slots
     * @return parkingSlots
     */
   public ArrayList<ParkingSlot> listAllSlots(){
       return parkingSlots;
   }

    /**
     * to find a parking slot in the car park
     * @param slotID  the parking slot number
     * @return ps   one objects in the arraylist
     */
   public ParkingSlot findASlot(String slotID){
       for(ParkingSlot ps : parkingSlots) {
           if(ps.getSlotNumber().equals(slotID)) {
               return ps;
           }
       }
       return null;
   }

    /**
     * add a parking slot to the car park
     * @param ps   a new parking slot
     */
   public void addASlot(ParkingSlot ps){
       parkingSlots.add(ps);
   }

    /**
     * remove a parking slot from the car park
     * @param ps  a parking slot
     */
   public void deleteASlot(ParkingSlot ps){
       parkingSlots.remove(ps);
   }

    /**
     * return a string if there is no parking slots in the car park
     * @return strings
     */
   public String toString(){
       String strings ="";
       for (ParkingSlot ps : parkingSlots){
           if (ps.getSlotType().equals("Staff"))
           strings += ps.toString();
       }
       for (ParkingSlot ps : parkingSlots){
           if (ps.getSlotType().equals("Visitor"))
               strings += ps.toString();
       }
       if (strings.equals("")){
           strings = "Not found any park slots.";
       }
       return strings;
   }
}

