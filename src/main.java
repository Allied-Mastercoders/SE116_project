import java.util.ArrayList;
public class main {
    private static ArrayList<Zone> buildings = new ArrayList<>();
    private static ArrayList<UtilityProvider> utilityProviders = new ArrayList<>();
    private static ArrayList<ServiceProvider> serviceProviders = new ArrayList<>();
    private static Cell[][] grid = new Cell[2][5];



    public static void serviceProvider() {
        for (ServiceProvider serviceProvider : serviceProviders) {
            int r = serviceProvider.getRadius();
            for (int i = Math.max(0, serviceProvider.getX() - r); i <= Math.min(2 - 1, serviceProvider.getX() + r); i++) {
                for (int j = Math.max(0, serviceProvider.getY() - r); j <= Math.min(5 - 1, serviceProvider.getY() + r); j++) {
                    if (grid[i][j] instanceof Zone) {
                        Zone zone = (Zone) grid[i][j];

                        if (serviceProvider instanceof PoliceStation) {
                            if (!zone.isHasSecurity()) zone.setHasSecurity(true);
                        }
                        if (serviceProvider instanceof School) {
                            if (!zone.isHasEducation()) zone.setHasEducation(true);
                        }
                        if (serviceProvider instanceof Hospital) {
                            if (!zone.isHasHealth()) zone.setHasHealth(true);
                        }
                    }
                }
            }
        }
    }
}