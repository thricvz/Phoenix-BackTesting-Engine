package PhoenixBackTester.data;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import java.lang.Exception;

class CSVParseException extends Exception{
   public CSVParseException(String message) {
      super(message);
   }

}
public class CSVDataFeed implements DataFeed{
    public CSVDataFeed(String fileLocation, CSVFormat fileFormat) {
        this.csvFileFormat = fileFormat;

        try {
            File csv_file = new File(fileLocation);
            Scanner fileStream = new Scanner(csv_file);
            this.parseFile(fileStream);

        } catch(FileNotFoundException notFoundError) {
            System.err.println("Could not find file at " + fileLocation);
            System.exit(303);

        } catch(Exception error) {
           System.err.println("Failed to parse CSV file At" + fileLocation + "due to error:");
           System.err.println(error.getMessage());
           System.exit(303);
        }

    }

    @Override
    public FinancialData currentData() {
        if (reachedEnd())
            return null;

        return this.rawData.get(this.currentIndex);
    }

    @Override
    public boolean reachedEnd() {
       return this.currentIndex == this.rawData.size();
    }

    @Override
    public void advance() {
        this.currentIndex++;
    }

    private void discoverHeaderOffsets(Scanner fileStream) throws CSVParseException {
       this.headerColumnIndex = new HashMap<String,Integer>();
       String csvHeaderLine = fileStream.nextLine();
       String[] csvHeaders = csvHeaderLine.split(this.csvFileFormat.delimiter);


        for (int i = 0; i < csvHeaders.length ; i++) {
           String header = csvHeaders[i];
           if (this.csvFileFormat.hasHeader(header)) {
               headerColumnIndex.put(header, i);
           }
        }
    }

    private void parseFile(Scanner fileStream) throws CSVParseException {
       this.rawData = new ArrayList<FinancialData>();
       this.discoverHeaderOffsets(fileStream);

       while (fileStream.hasNextLine()) {
           String csvLine = fileStream.nextLine();
           String[] csvEntries = csvLine.split(this.csvFileFormat.delimiter);

           try {
               FinancialData dataExtractedFromLine = new FinancialData(
                   csvEntries[headerColumnIndex.get(csvFileFormat.openPriceColumn)] ,
                   csvEntries[headerColumnIndex.get(csvFileFormat.closePriceColumn)] ,
                   csvEntries[headerColumnIndex.get(csvFileFormat.highPriceColumn)] ,
                   csvEntries[headerColumnIndex.get(csvFileFormat.lowPriceColumn)]
               );

               this.rawData.add(dataExtractedFromLine);

           } catch (Exception error) {
               throw new CSVParseException("Malformed csv line");
           }
       }
    }

    private Integer currentIndex = 0;

    private ArrayList<FinancialData> rawData;
    private HashMap<String,Integer> headerColumnIndex;
    private CSVFormat csvFileFormat;
}

