import java.util.LinkedList;
import java.util.Queue;

abstract class UtilityProvider extends Cell{
    protected int capacity = 100;
    public UtilityProvider(int x, int y, char type) {
        super(x, y, type);
    }
    public abstract String getUtilityType();

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void distributeUtility( Cell[][] grid, int i, int j){//utilty provider is deleted because of we have this command
        boolean[][] visited = new boolean[i][j];
        Queue<int[]> queue = new LinkedList<>();//list of control
        int remaining =  capacity;
        visited[x][y] = true;







    }
}

class PowerPlant extends UtilityProvider {
    public PowerPlant(int x, int y) {
        super(x, y, 'P');
    }
    @Override
    public String getUtilityType(){
        return "P";
    }
}


class WaterPumpingStation extends UtilityProvider {
    public WaterPumpingStation(int x, int y) {
        super(x, y, 'W');
    }
    @Override
    public String getUtilityType(){
        return "W";
    }
}


class InternetHub extends UtilityProvider {
    public InternetHub(int x, int y) {
        super(x, y, 'T');
    }
    @Override
    public String getUtilityType(){
        return "T";
    }
}