// Name: J1-10
// Date: 9/12/20
 
import java.text.DecimalFormat;
import java.lang.Math;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
   
      SmartCard jimmy = new SmartCard(20.00); 
      jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
      jimmy.exit(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
      jimmy.exit(uptown);				//Error:  did not board?!
      System.out.println();
   			
      SmartCard susie = new SmartCard(1.00); 
      susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
      susie.exit(suburbia);				//Insuffient funds to exit. Please add more money.
      System.out.println();
   
      SmartCard kim = new SmartCard(.25);    
      kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
      System.out.println();
   
      SmartCard b = new SmartCard(10.00);   
      b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
      b.exit(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
      System.out.println();
        
      SmartCard mc = new SmartCard(10.00);  
      mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
      mc.exit(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
      System.out.println();
    
      //Make more test cases.  What else needs to be tested?
   }
} 	

//Note SmartCard is not denoted as public.  Why?
class SmartCard 
{
   
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
     /* enter your code below */
   public final static double COST_OF_ONE_ZONE = 0.75;
   double money;
   boolean boarded;
   Station station = new Station();
   SmartCard (double money) {
      this.money = money;
   }
   SmartCard () {
      money = 0.00;
   }
   void addMoney(double d) {
      money += d;
   }
//   double getBalance () {
//      return money;
//   }
     String getBalance() {
         return df.format(money);
         
     }
   boolean isBoarded () {
      return boarded;
   }
   void board (Station s) {
      if (boarded) {
         System.out.println("Error: already boarded");
         return;
      }
      if (money < MIN_FARE) {
         System.out.println("Insufficient funds to board. Please add more money.");
         return;
      }
      station = s;
      boarded = true;
      System.out.println("Boarded at " + s.getName() + ". SmartCard has $" + money);
   }
   double cost (Station s) {
      
      double fare = MIN_FARE + COST_OF_ONE_ZONE*Math.abs(s.getZone()-station.getZone());
      return fare;
      
   }
   void exit (Station s) {
      double fare = cost(s);
      if (!boarded) {
         System.out.println("Error: Did not board.");
         return;
      }

      if (money < fare) {
         System.out.println("Insufficient funds to exit. Please add more money.");
         return;
      }
      money -= fare;
      
      boarded = false;
      System.out.println("From " + station.getName() + " to " + s.getName() + " costs $" + fare + ". SmartCard has $" + money);
      if (s.getName().equals(station.getName())) {
         station = null;
         return;
      }
      station = s;
   }

   
   //the next 3 methods are for use ONLY by Grade-It
   //these accessor methods only return your private data
   //they do not make any changes to your data
   double getMoneyRemaining()
   {
    //enter your code here and replace the return with yours
      return money;
   }
   
   Station getBoardedAt()
   {
    //enter your code here and replace the return with yours
      return station;   
   }
  
   boolean getIsOnBoard()
   {
   //enter your code here and replace the return with yours
      return boarded;
   }
}
   
//Note Station is not a public class.  Why?
class Station {
      
      String stationName;
      int zone;
      
      Station() {
        
         zone = 0;
         stationName = "";
      }
            
      Station(String stationName, int zone) {
      
             this.stationName = stationName;
            this.zone = zone;
      }
      int getZone() {
         return zone;
      } 
      String getName() {
         return stationName;
      }
      
      
   
} // end of station class

 /*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!
 
 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.
 
 Insufficient funds to board. Please add more money.
 
 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50
 
 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25
 
 ************************************************/