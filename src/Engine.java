import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Engine {
    private Cell[][] grid;
    private int rows, cols;

    private int globalPopulation = 0;
    private int globalGoods = 0;
    private int globalLifestyle = 0;

    public Engine(Cell[][] grid, int rows, int cols) {
        this.grid = grid;
        this.rows = rows;
        this.cols = cols;
    }

    public void run(int currentTick) {
        System.out.println("Tick: " + currentTick);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] instanceof Zone) {
                    ((Zone) grid[i][j]).resetUtilitiesAndServices();
                }
            }
        }
        ServiceDistribute();
        distributeUtilities();

        int industrialCount = 0, commercialCount = 0, housingCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] instanceof Industrial) industrialCount++;
                if (grid[i][j] instanceof Commercial) commercialCount++;
                if (grid[i][j] instanceof Housing) housingCount++;
            }
        }

        int popPerZone,goodsPerCommercial,lifestylePerHousing;
        if (industrialCount + commercialCount > 0) {
            popPerZone = globalPopulation / (industrialCount + commercialCount);
        }else{popPerZone = 0;}

        if (commercialCount > 0) {
            goodsPerCommercial = globalGoods / commercialCount;
        } else {goodsPerCommercial = 0;}

        if (housingCount > 0) {
            lifestylePerHousing = globalLifestyle / housingCount;
        }else {lifestylePerHousing = 0;}

        int newPopulation = 0, newGoods = 0, newLifestyle = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] instanceof Zone) {
                    Zone zone = (Zone) grid[i][j];

                    if (zone instanceof Housing) {
                        zone.updateLevelAndOutput(0, 0, lifestylePerHousing);
                        newPopulation += zone.generatedOutput;
                    } else if (zone instanceof Industrial) {
                        zone.updateLevelAndOutput(popPerZone, 0, 0);
                        newGoods += zone.generatedOutput;
                    } else if (zone instanceof Commercial) {
                        zone.updateLevelAndOutput(popPerZone, goodsPerCommercial, 0);
                        newLifestyle += zone.generatedOutput;
                    }
                }
            }
        }

        globalPopulation = newPopulation;
        globalGoods = newGoods;
        globalLifestyle = newLifestyle;

        System.out.println("Pool -> Pop: " + globalPopulation + " | Goods: " + globalGoods + " | Life Style: " + globalLifestyle);
        System.out.println("-------------------------------------------------");

    }

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

    private void distributeUtilities() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] instanceof UtilityProvider) {
                    runBFS((UtilityProvider) grid[i][j]);
                }
            }
        }
    }
    private void runBFS(UtilityProvider provider) {
        int remainingCapacity = provider.capacity;
        char type = provider.getType();

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        queue.add(new int[]{provider.x, provider.y});
        visited[provider.x][provider.y] = true;

        while (!queue.isEmpty() && remainingCapacity > 0) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            if (grid[cx][cy] instanceof Zone) {
                Zone zone = (Zone) grid[cx][cy];
                int demand = zone.utilityDemand;
                int amountToGive = Math.min(demand, remainingCapacity);
                if (type == 'P') zone.setElectricityReceived(amountToGive);
                else if (type == 'W') zone.setWaterReceived(amountToGive);
                else if (type == 'T') zone.setInternetReceived(amountToGive);

                remainingCapacity -= amountToGive;
            }

            if (remainingCapacity <= 0) break;

            for (int[] dir : directions) {
                int nx = cx + dir[0];
                int ny = cy + dir[1];

                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny]) {
                    Cell neighbor = grid[nx][ny];
                    if (neighbor instanceof Road || neighbor instanceof Zone) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}

