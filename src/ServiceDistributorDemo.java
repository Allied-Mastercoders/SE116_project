public class ServiceDistributorDemo {
    private Cell[][] grid;
    private int rows, cols;


    private void ServiceDistribute() {

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell cell = grid[r][c];
                if (cell instanceof ServiceProvider) {
                    ServiceProvider service = (ServiceProvider) cell;
                    int ra = service.getRadius();
                    for (int i = Math.max(0, service.getX() - ra); i <= Math.min(grid.length - 1, service.getX() + ra); i++) {
                        for (int j = Math.max(0, service.getY() - ra); j <= Math.min(grid.length - 1, service.getY() + ra); j++) {
                            if (grid[i][j] instanceof Zone) {
                                Zone zone = (Zone) grid[i][j];

                                if (service instanceof PoliceStation) {
                                    zone.setHasSecurity(true);
                                }
                                if (service instanceof School) {
                                    zone.setHasEducation(true);
                                }
                                if (service instanceof Hospital) {
                                    zone.setHasHealth(true);
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
