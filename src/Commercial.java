public class Commercial extends Zone{

    public Commercial(int x, int y) {
        super(x, y, 'C');
    }

    @Override
    public void updateLevelAndOutput(int populationPool, int goodsPool, int lifestylePool) {
        int m = Math.min(electricityReceived, Math.min(waterReceived, internetReceived));

        if (m == 0) {
            level = 0;
        } else {
            boolean canBeLevel1 = (populationPool > 0 && goodsPool > 0);
            boolean canBeLevel2 = canBeLevel1 && hasSecurity;
            boolean canBeLevel3 = canBeLevel2 && populationPool>1;

            int targetLevel;

            if (canBeLevel3) {targetLevel = 3;}
            else if (canBeLevel2) {targetLevel = 2;}
            else if (canBeLevel1) {targetLevel = 1;}
            else {targetLevel = 0;}

            if (targetLevel > level) {
                level++;
                System.out.println("Commercial at ("+x+","+y+") levels up from " +(level-1)+" to "+level);
            }
            else if (targetLevel < level) {
                level--;
                System.out.println("Commercial at ("+x+","+y+") levels down from " +(level+1)+" to "+level);
            }
        }

        if (level == 0) generatedOutput = 0;
        else if (level == 1) generatedOutput = m;
        else if (level == 2) generatedOutput = 2 * m;
        else if (level == 3) generatedOutput = (2 * m) + Math.min(populationPool, goodsPool);

        System.out.println("Commercial at ("+x+","+y+") generated "+generatedOutput+" lifestyle");

        utilityDemand = Math.max(1, generatedOutput);
    }


}
