import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private List<Zone> zones;
    private List<ServiceProvider> serviceProviders;

    public void setup(Cell[][] grid) {
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

    public void run() {
        for (Zone z : zones) {
            z.hasSecurity = false;
            z.hasHealth = false;
            z.hasEducation = false;
        }

        for (ServiceProvider s : serviceProviders) {
            int r = s.getRadius();

            for (Zone z : zones) {
                if (z.getX() >= s.getX() - r &&
                        z.getX() <= s.getX() + r &&
                        z.getY() >= s.getY() - r &&
                        z.getY() <= s.getY() + r) {

                    if (s instanceof PoliceStation) {
                        z.setHasSecurity(true);
                    }
                    else if (s instanceof Hospital) {
                        z.setHasHealth(true);
                    }
                    else if (s instanceof School) {
                        z.setHasEducation(true);
                    }
                }
            }
        }
    }
}