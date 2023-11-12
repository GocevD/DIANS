import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PipeAndFilter {
    public static void main(String[] args) throws ParseException {
        // Step 1: Read raw data from the web scraper or file

        List<Winery> rawData = readRawData();

        // Step 2: Create filters
        DataCleaningFilter dataCleaningFilter = new DataCleaningFilter();
        AddressTransformationFilter addressTransformationFilter = new AddressTransformationFilter();
        PhoneTransformationFilter phoneTransformationFilter = new PhoneTransformationFilter();
        EmailTransformationFilter emailTransformationFilter = new EmailTransformationFilter();
        WebsiteTransformationFilter websiteTransformationFilter = new WebsiteTransformationFilter();
        DataSelectionFilter dataSelectionFilter = new DataSelectionFilter();
        DataQueryFilter dataQueryFilter = new DataQueryFilter();
        DataExportFilter dataExportFilter = new DataExportFilter();
        DataValidationFilter dataValidationFilter = new DataValidationFilter();
        DataAggregationFilter dataAggregationFilter = new DataAggregationFilter();

        // Step 3: Connect filters in a pipeline
        dataCleaningFilter.setNextFilter(addressTransformationFilter);
        addressTransformationFilter.setNextFilter(phoneTransformationFilter);
        phoneTransformationFilter.setNextFilter(emailTransformationFilter);
        emailTransformationFilter.setNextFilter(websiteTransformationFilter);
        websiteTransformationFilter.setNextFilter(dataSelectionFilter);
        dataSelectionFilter.setNextFilter(dataQueryFilter);
        dataQueryFilter.setNextFilter(dataExportFilter);
        dataExportFilter.setNextFilter(dataValidationFilter);
        dataValidationFilter.setNextFilter(dataAggregationFilter);

        // Step 4: Execute the pipeline
        List<Winery> transformedData = dataCleaningFilter.execute(rawData);

        // Step 5: Optional - Perform additional actions with the transformed data
        // (e.g., insert into a database, export to a file, display statistics)

        // Example: Display transformed data
        for (Winery winery : transformedData) {
            System.out.println(winery);
        }
    }

    private static List<Winery> readRawData() {
        List<Winery> rawWineries = new ArrayList<>();

        // Specify the path to your CSV file
        String csvFilePath = "Domasna 1/PipeAndFilter/src/main/resources/winery_data.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            // Read each line from the CSV file
            while ((line = br.readLine()) != null) {
                // Split the line into columns
                String[] columns = line.split(",");

                // Create a Winery object from the columns
                Winery winery = new Winery(columns[0],  columns[1], columns[2], columns[3], columns[4], columns[5]);


                // Add the Winery to the list
                rawWineries.add(winery);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception based on your needs
        }

        return rawWineries;
    }
}