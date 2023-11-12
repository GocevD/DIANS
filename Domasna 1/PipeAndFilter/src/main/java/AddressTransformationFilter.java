import java.text.ParseException;
import java.util.List;

public class AddressTransformationFilter implements Filter<List<Winery>> {
    private Filter<List<Winery>> nextFilter;

    public void setNextFilter(Filter<List<Winery>> nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public List<Winery> execute(List<Winery> inputData) throws ParseException {
        // Standardize the address format in inputData
        for (Winery winery : inputData) {
            winery.setAddress(transformAddress(winery.getAddress()));
        }

        // Pass the processed data to the next filter in the pipeline
        if (nextFilter != null) {
            return nextFilter.execute(inputData);
        } else {
            // This is the last filter in the pipeline, return the final result
            return inputData;
        }
    }

    // Example address transformation - customize as needed
    private String transformAddress(String originalAddress) {
        // Perform address transformation (e.g., capitalize letters, remove unnecessary spaces)
        // Example: Capitalize the first letter of each word
        String[] words = originalAddress.split(" ");
        StringBuilder transformedAddress = new StringBuilder();
        for (String word : words) {
            transformedAddress.append(word.substring(0, 1).toUpperCase())
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return transformedAddress.toString().trim();
    }
}

