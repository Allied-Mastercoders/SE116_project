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

    public void distributeUtility(UtilityProvider provider, Cell[][] grid, int i, int j){
        int lowerLimit=-1;
        int upperLimit=1;
        while(provider.getCapacity()>=0){
            for (int i2 = lowerLimit; i2 <= upperLimit; i2++) {
                for (int j2 = lowerLimit; j2 <= upperLimit; j2++) {
                    if(i2==0 && j2==0) continue;
                    if(grid[i][j].isProvided) continue;
                    int newI = i + i2;
                    int newJ = j + j2;
                    if (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[newI].length) {
                        provider.setCapacity(provider.getCapacity())-grid[i][j].getDemand);
                    }

                }
            }
            lowerLimit--;
            upperLimit++;
        }
    }
}
//[][][]
//[][][]
//[][][]
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