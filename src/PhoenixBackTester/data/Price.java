package PhoenixBackTester.data;

import java.lang.Comparable;
import java.lang.Exception;

public class Price implements Comparable<Price> {
   public Price(String priceDescription, String notationPoint) throws Exception {
       String[] priceString = priceDescription.split(notationPoint);

       this.dollars = 0;
       this.cents = 0;

       switch (priceString.length) {
           case 0 : throw new Exception("Price is not in correct format");
           case 2 :
               {
                   String strFloatPart = priceString[1];
                   int maxIndex = Math.min(4, strFloatPart.length());
                   this.cents = Integer.parseInt(priceString[1].substring(0,maxIndex));
               }
           case 1 :
              this.dollars = Integer.parseInt(priceString[0]);
              break;
       }

   }

   @Override
   public int compareTo(Price other) {
        return (this.dollars * this.cents) - (other.dollars * other.cents);
   }

   @Override
   public String toString() {
      return this.dollars.toString() + "." + this.cents.toString();
   }
   private Integer dollars;
   private Integer cents;
}
