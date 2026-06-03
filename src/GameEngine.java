import java.util.ArrayList;

public class GameEngine {

private int row;
private int col;

private Cell[][] grid;
private ArrayList<Zone> zones = new ArrayList<>();
private ArrayList<UtilityProvider> utilityProviders = new ArrayList<>();
private ArrayList<ServiceProvider> serviceProviders = new ArrayList<>();

private int populationPool;
private int goodsPool;
private int lifestylePool;
public GameEngine(Cell[][] grid) {
    this.grid = grid;
    this.row = grid.length;
    this.col = grid[0].length;
scanGrid();
}



private void scanGrid() {
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
Cell cell = grid[i][j];
if(cell instanceof Zone) {
    zones.add((Zone) cell);
}
else if (cell instanceof UtilityProvider) {
    utilityProviders.add((UtilityProvider) cell);
}
else if (cell instanceof ServiceProvider) {
    serviceProviders.add((ServiceProvider) cell);
}
                                        }
                                    }









}
    public void tick(int numberT) {
        System.out.println("Tick " + numberT);
        for(Zone z : zones)
            z.resetUtilitiesAndServices();
distributeService();
for(UtilityProvider u : utilityProviders) {
    u.distributeUtility(grid);
}

        int popPerZone = 0;
        int lifePerZone = 0;
        int goodsPerZone = 0;
if(numberT>1) {
           int industrialCount = 0;
           int comercialCount = 0;
           int housingCount = 0;
for(Zone z: zones ) {
    if(z instanceof Industrial) industrialCount++;
    if(z instanceof Commercial) comercialCount++;
    if(z instanceof Housing) housingCount++;
}
if(industrialCount + comercialCount>0)
    popPerZone = populationPool/(industrialCount + comercialCount);
if(comercialCount>0)
    goodsPerZone = goodsPool/comercialCount;
if(housingCount>0)
    lifePerZone = lifestylePool/housingCount;
for(Zone z: zones ) {
if(z instanceof Industrial)
    System.out.println("Industrial at (" + z.getX() + "," + z.getY() + ")" + " received " + popPerZone + "population");
if(z instanceof Commercial) {
    System.out.println("Commercial at (" + z.getX() + "," + z.getY() + ")" + " received " + popPerZone + "population");
    System.out.println("Commercial at (" + z.getX() + "," + z.getY() + ")" + " received " + goodsPerZone + "goods");
}
if(z instanceof Housing)
    System.out.println("House at (" + z.getX() + "," + z.getY() + ")" + " received " + lifePerZone + "lifestyle");
        }





        }
for(Zone z:zones) {
            z.updateLevelAndOutput(popPerZone,goodsPerZone,lifePerZone);

        }


        populationPool = 0;
        goodsPool = 0;
        lifestylePool = 0;
for(Zone z:zones) {
            if(z instanceof Industrial) goodsPool +=z.generatedOutput;
            if(z instanceof Housing) populationPool +=z.generatedOutput;
            if(z instanceof Commercial) lifestylePool +=z.generatedOutput;
        }

}
private void distributeService() {
    for(ServiceProvider s : serviceProviders) {
        double radius = s.getRadius();
        for(Zone z : zones) {
            double distance = Math.sqrt(Math.pow(z.getX()-s.getX(),2)+(Math.pow(z.getY()-s.getY(),2)));//squareroot a2 +b2
            if(distance<=radius) {
                if(s instanceof PoliceStation) z.setHasSecurity(true);
                if(s instanceof Hospital) z.setHasHealth(true);
                if(s instanceof School) z.setHasEducation(true);

            }



        }
    }
}
}