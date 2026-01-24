package PhoenixBackTester.data;

import java.lang.Comparable;
import java.lang.Exception;

public class Price implements Comparable<Price> {
   public Price(long dollars, int cents, int fractionOfCents) throws Exception{
       if (cents >= 100 || fractionsOfCents >= 100)
           throw new Exception("Cents and FractionOfCents cannot have more than 2 digits");

       this.dollars = dollars;
       this.cents = (byte) cents;
       this.fractionsOfCents = (byte) fractionOfCents;
   }

   public Price(String priceDescription, String notationPoint) throws Exception {
       this(0L, 0, 0);

       String[] priceString = priceDescription.split(notationPoint);

       switch (priceString.length) {
           case 0 : throw new Exception("Price is not in correct format");
           case 2 :
               {
                   String floatPart = priceString[1];
                   int centsEnd = Math.min(2, floatPart.length());
                   if (centsEnd != 0) {
                       this.cents = Byte.parseByte(floatPart.substring(0, centsEnd));
                       int fractionsOfCentsEnd = Math.min(
                               centsEnd,
                               Math.min(floatPart.length(), centsEnd + 2)
                       );

                       if (fractionsOfCentsEnd != centsEnd) {
                           this.fractionsOfCents = Byte.parseByte(floatPart.substring(
                                   fractionsOfCentsEnd,
                                   floatPart.length()
                           ));
                       }

                   }
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
       short totalFractionsOfPenny = (short)(fractionsOfCents + price.fractionsOfCents);
       short totalCents = (short) (cents + price.cents + (totalFractionsOfPenny / 100));

       this.fractionsOfCents = (byte) (totalFractionsOfPenny % 100);
       this.cents = (byte) (totalCents % 100);
       this.dollars = this.dollars + price.dollars + (totalCents / 100);
   }

   public void divide(int factor) throws Exception{
        if (factor == 0 )
            throw new Exception("Cannot divide price By zero");

        long totalValue = this.valueInCentFractions() / factor;

        this.dollars = totalValue / 10000;
        totalValue = totalValue % 10000;

        this.cents =  (byte) (totalValue / 100);
        this.fractionsOfCents = (byte) (totalValue % 100);
   }

   public void multiply(int factor) {
   }



   @Override
   public int compareTo(Price other) {
        // to avoid arithmetic overflow
        Long difference = this.valueInCentFractions() - other.valueInCentFractions();
        if (difference == 0)
            return 0;

        return difference < 0L ? -1 : 1;
   }

   @Override
   public String toString() {
      return ((Long) this.dollars).toString() + "." +
             ((Byte) this.cents).toString() +
             ((Byte) this.fractionsOfCents).toString();
   }

   private long valueInCentFractions() {
       return (this.dollars * 10000) +  (this.cents * 100) + this.fractionsOfCents;
   }
   private long dollars;
   private byte cents;
   private byte fractionsOfCents; // up to 2f

}
