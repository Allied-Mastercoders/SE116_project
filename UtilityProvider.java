import java.util.LinkedList;
import java.util.Queue;

abstract class UtilityProvider extends Cell{
    protected int capacity = 100;
    public UtilityProvider(int x, int y, char type) {
        super(x, y, type);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void distributeUtility(UtilityProvider provider, Cell[][] grid){
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        Queue<int[]> queue=new LinkedList<>();
        int remaining=provider.getCapacity();
        char utilityType=provider.getType();
        queue.add(new int[]{provider.x, provider.y});
        visited[provider.x][provider.y] = true;
        int[][] neighbors = {{1,0},{0,1},{-1,0},{0,-1}};

        while (!queue.isEmpty() && remaining>0) {
            int[] currentCoord =queue.poll();
            int x = currentCoord[0];
            int y = currentCoord[1];

            for (int[] direction : neighbors) {
                int neighborX = x + direction[0];
                int neighborY = y + direction[1];

                if ((neighborX <0 || neighborY <0) || (neighborX >=grid.length || neighborY >= grid[0].length)){
                    continue;
                }
                if (visited[neighborX][neighborY]) {
                    continue;
                }
                Cell neighbor = grid[neighborX][neighborY];
                if (neighbor instanceof Empty) {
                    continue;
                }

                visited[neighborX][neighborY] = true;

                if (neighbor instanceof Zone) {
                    Zone zone = (Zone) neighbor;
                    int demand=zone.getUtilityDemand();
                    int given;
                    if(demand>remaining) given=remaining;
                    else given=demand;
                    zone.receiveUtility(utilityType, given);
                    remaining=remaining-given;
                }

                if ((neighbor instanceof Road)||(neighbor instanceof Zone)){
                    queue.add(new int[]{neighborX, neighborY});
                }
            }
        }
    }
}

class PowerPlant extends UtilityProvider {
    public PowerPlant(int x, int y) {
        super(x, y, 'P');
    }
}


class WaterPumpingStation extends UtilityProvider {
    public WaterPumpingStation(int x, int y) {
        super(x, y, 'W');
    }
}
class InternetHub extends UtilityProvider{
    public InternetHub(int x, int y) {
        super(x, y, 'T');
    }
}