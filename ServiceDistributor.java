import java.util.ArrayList;
import java.util.List;

public class ServiceDistributor {
    private List<Zone> zones;
    private List<ServiceProvider> serviceProviders;

    public void Start(Cell[][] grid) {

        zones = new ArrayList<>();
        serviceProviders = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Cell cell = grid[i][j];

                if (cell instanceof Zone) {
                    zones.add((Zone) cell);
                } else if (cell instanceof ServiceProvider) {
                    serviceProviders.add((ServiceProvider) cell);
                }


            }
        }
    }
}