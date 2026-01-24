package PhoenixBackTester.data;

import java.lang.Comparable;
import java.lang.Exception;

public class Price implements Comparable<Price> {
   public static final Integer FloatPrecision = 4;

   public Price(Long dollars, Integer cents) {
       this.dollars = dollars;
       this.cents = cents;
   }

   public Price(String priceDescription, String notationPoint) throws Exception {
       this(0L, 0);

       String[] priceString = priceDescription.split(notationPoint);

       switch (priceString.length) {
           case 0 : throw new Exception("Price is not in correct format");
           case 2 :
               {
                   String strFloatPart = priceString[1];
                   int maxIndex = Math.min(FloatPrecision, strFloatPart.length());
                   this.cents = Integer.parseInt(priceString[1].substring(0,maxIndex));
               }
           case 1 :
              this.dollars = Long.parseLong(priceString[0]);
              break;
       }

   }

   public Price(Price price) {
      this.dollars = price.dollars;
      this.cents = price.cents;
   }

   public void add(Price price) {
      int totalCents = this.cents + price.cents;
      int carryOver = totalCents / (int)( Math.pow(10, FloatPrecision - 1));
      int truncatedCents = totalCents % (int)( Math.pow(10, FloatPrecision - 1));

      this.cents = truncatedCents;
      this.dollars += price.dollars + carryOver;
   }

   public void divide(int factor) {
        Long valueInCents = this.valueInCents() / factor;
        this.dollars = valueInCents / (long) Math.pow(10, FloatPrecision - 1);
        this.cents = (int) (valueInCents %  Math.pow(10, FloatPrecision - 1));
   }

   public void multiply(int factor) {
       Long valueInCents = this.valueInCents() * factor;
       this.dollars = valueInCents / (long) Math.pow(10, FloatPrecision - 1);
       this.cents = (int) (valueInCents %  Math.pow(10, FloatPrecision - 1));
   }



   @Override
   public int compareTo(Price other) {
        // to avoid arithmetic overflow
        Long difference = this.valueInCents() - other.valueInCents();
        if (difference == 0)
            return 0;

        return difference < 0L ? -1 : 1;
   }

   @Override
   public String toString() {
      return this.dollars.toString() + "." + this.cents.toString();
   }

   private Long valueInCents() {
       return this.dollars * this.cents;
   }
   private Long dollars;
   private Integer cents;

}
