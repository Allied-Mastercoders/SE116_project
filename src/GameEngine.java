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
} else if (cell instanceof UtilityProvider) {
    utilityProviders.add((UtilityProvider) cell);
} else if (cell instanceof ServiceProvider) {
    serviceProviders.add((ServiceProvider) cell);
}
                                        }
                                    }
                                }






}
