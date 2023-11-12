import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class DataExportFilter implements Filter<List<Winery>> {
    private Filter<List<Winery>> nextFilter;

    public void setNextFilter(Filter<List<Winery>> nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public List<Winery> execute(List<Winery> inputData) throws ParseException {
        // Export data to an external file (e.g., CSV)
        exportDataToCSV(inputData);

        // Pass the processed data to the next filter in the pipeline
        if (nextFilter != null) {
            return nextFilter.execute(inputData);
        } else {
            // This is the last filter in the pipeline, return the final result
            return inputData;
        }
    }

    // Example method to export data to CSV - customize as needed
    private void exportDataToCSV(List<Winery> wineries) {
        String csvFilePath = "exported_data.csv"; // Set your desired file path
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            // Write CSV header
            writer.append("Name,Address,City,Phone,Website,Email\n");

            // Write winery data
            for (Winery winery : wineries) {
                writer.append(String.format("%s,%s,%s,%s,%s,%s\n",
                        winery.getName(), winery.getAddress(), winery.getCity(),
                        winery.getPhone(), winery.getWebsite(), winery.getEmail()));
            }

            System.out.println("Data exported to CSV: " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception based on your needs
        }
    }
}