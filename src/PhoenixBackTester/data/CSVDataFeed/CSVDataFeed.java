package PhoenixBackTester.data.CSVDataFeed;

import PhoenixBackTester.data.DataFeed;
import PhoenixBackTester.data.FinancialData;
import PhoenixBackTester.data.Price;

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

public class CSVDataFeed implements DataFeed {
    public CSVDataFeed(String fileLocation, CSVFormat fileFormat) {
        this.m_csvFileFormat = fileFormat;
        this.m_currentIndex = 0;

        try {
            File csv_file = new File(fileLocation);
            Scanner fileStream = new Scanner(csv_file);
            this.parseFile(fileStream);

        } catch(FileNotFoundException notFoundError) {
            System.err.println("Could not find file at " + fileLocation);
            System.exit(300);

        } catch(Exception error) {
           System.err.println("Failed to parse CSV file At" + fileLocation + " due to error:");
           System.err.println(error.getMessage());
           System.exit(300);
        }

    }

    @Override
    public FinancialData currentData() {
        if (reachedEnd())
            return null;

        return this.m_rawData.get(this.m_currentIndex);
    }

    @Override
    public boolean reachedEnd() {
       return this.m_currentIndex == this.m_rawData.size();
    }

    @Override
    public void advance() {
       int previousDataIndex = this.m_currentIndex;
       this.m_currentIndex ++;
       this.m_rawData.get(previousDataIndex).makeFullyAvailable();
    }

    private void discoverHeaderOffsets(Scanner fileStream) throws CSVParseException {
       this.m_headerColumnIndex = new HashMap<String,Integer>();
       String csvHeaderLine = fileStream.nextLine();
       String[] csvHeaders = csvHeaderLine.split(this.m_csvFileFormat.regexDelimiter);


        for (int i = 0; i < csvHeaders.length ; i++) {
           String header = csvHeaders[i];
           if (this.m_csvFileFormat.hasHeader(header)) {
               m_headerColumnIndex.put(header, i);
           }
        }
    }

    private void parseFile(Scanner fileStream) throws CSVParseException {
       this.m_rawData = new ArrayList<FinancialData>();
       this.discoverHeaderOffsets(fileStream);

       if (this.m_headerColumnIndex.size() < 4) {
           throw new CSVParseException("Insufficient number of headers");
       }

       while (fileStream.hasNextLine()) {
           String csvLine = fileStream.nextLine();
           String[] csvEntries = csvLine.split(this.m_csvFileFormat.regexDelimiter);

           try {
               FinancialData newData = new FinancialData(
                   getPrice(m_csvFileFormat.openPriceColumn, csvEntries)  ,
                   getPrice(m_csvFileFormat.closePriceColumn, csvEntries) ,
                   getPrice(m_csvFileFormat.highPriceColumn, csvEntries)  ,
                   getPrice(m_csvFileFormat.lowPriceColumn, csvEntries)
               );

               this.m_rawData.add(newData);

           } catch (Exception error) {
               throw new CSVParseException("Malformed csv line due to: " + error.toString());
           }
       }

    }
    private Price getPrice(String columnHeader, String[] entries) throws Exception{
       Integer dataIndex = this.m_headerColumnIndex.get(columnHeader);
       return new Price(entries[dataIndex], m_csvFileFormat.regexfloatingPoint);
    }

    private CSVFormat m_csvFileFormat;
    private HashMap<String,Integer> m_headerColumnIndex;
    private int m_currentIndex;
    private ArrayList<FinancialData> m_rawData;
}


