import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAggregationFilter implements Filter<List<Winery>> {
    private Filter<List<Winery>> nextFilter;

    public void setNextFilter(Filter<List<Winery>> nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public List<Winery> execute(List<Winery> inputData) throws ParseException {
        // Aggregate data (e.g., group wineries by city)
        Map<String, List<Winery>> aggregatedData = aggregateByCity(inputData);

        // Pass the aggregated data to the next filter in the pipeline
        if (nextFilter != null) {
            // Flatten the values from the map to a single list
            List<Winery> flattenedData = new ArrayList<>();
            for (List<Winery> wineryList : aggregatedData.values()) {
                flattenedData.addAll(wineryList);
            }
            return nextFilter.execute(flattenedData);
        } else {
            // This is the last filter in the pipeline, return the final result
            // In this case, you might want to return the flattenedData or the original inputData
            return inputData;
        }
    }

    // Example method to aggregate wineries by city
    private Map<String, List<Winery>> aggregateByCity(List<Winery> wineries) {
        Map<String, List<Winery>> aggregatedData = new HashMap<>();

        for (Winery winery : wineries) {
            // Group wineries by city
            String city = winery.getCity();
            aggregatedData.computeIfAbsent(city, k -> new ArrayList<>()).add(winery);
        }

        return aggregatedData;
    }
}