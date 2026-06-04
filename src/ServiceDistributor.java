import java.util.ArrayList;
import java.util.List;

public class ServiceDistributor {
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

        
            for(ServiceProvider s : serviceProviders) {
                double radius = s.getRadius();
                for(Zone z : zones) {
                    double distance = Math.sqrt(Math.pow(z.getX()-s.getX(),2)+(Math.pow(z.getY()-s.getY(),2)));//squareroot a2 +b2
                    if(distance<=radius) {
                        if (s instanceof PoliceStation) {
                            if(!z.isHasSecurity())z.setHasSecurity(true);
                        }
                        if (s instanceof Hospital) {
                            if(!z.isHasHealth())z.setHasHealth(true);
                        }
                        if (s instanceof School) {
                            if(!z.isHasEducation())z.setHasEducation(true);
                        }

                    }

                }
            }
        }
    }

