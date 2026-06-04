import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        if (args.length != 2) {
                System.out.println("Usage : java Main <map_file> <ticks>");
        }
        String mapFilePath = args[0];
        int ticks = 0;
        try {
            ticks = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: The tick number (2. parameter) must be a valid integer");
            System.out.println("Example usage: java Main map.txt 3");
            return;
        }

        System.out.println("Map name: " + mapFilePath + " | Tick number: " + ticks);

        Cell[][] grid = loadMap(args[0]);
        GameEngine engine = new GameEngine(grid);

        for (int i = 1; i <=ticks; i++) {
            engine.tick(i);
        }

    }

    private static Cell[][] loadMap(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader buff = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = buff.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File can not be read: " + filePath);
        }

        if (lines.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        int row = lines.size();
        int col = lines.getFirst().length();
        Cell[][] grid = new Cell[row][col];

        for (int i = 0;i < row;i++) {
            String currentLine = lines.get(i);
            for (int j = 0;j < col;j++) {
                char type = currentLine.charAt(j);
                grid[i][j] = createCell(i, j,type);
            }
        }
        return grid;
    }

    private static Cell createCell(int x, int y, char type) {
        switch (type) {
            case 'H' -> {return new Housing(x, y);}
            case 'I' -> {return new Industrial(x, y);}
            case 'C' -> {return new Commercial(x, y);}
            case 'R' -> {return new Road(x, y);}
            case 'P' -> {return new PowerPlant(x, y);}
            case 'W' -> {return new WaterPumpingStation(x, y);}
            case 'T' -> {return new InternetHub(x, y);}
            case 'F' -> {return new PoliceStation(x, y);}
            case 'D' -> {return new Hospital(x, y);}
            case 'S' -> {return new School(x, y);}
            case 'E' -> {return new Empty(x, y);}
            default -> {
                throw new IllegalArgumentException("Unknown place type: " + type);
            }
        }
    }

}
