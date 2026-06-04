public class Housing extends Zone {

    public Housing(int x, int y) {
        super(x, y, 'H');
    }

    @Override
    public void updateLevelAndOutput(int populationPool, int goodsPool, int lifestylePool) {
        int m = Math.min(electricityReceived, Math.min(waterReceived, internetReceived));

        if (m == 0) {
            level = 0;
        } else {
            boolean canBeLevel1 = true;
            boolean canBeLevel2 = canBeLevel1 && hasSecurity && hasHealth && hasEducation;
            boolean canBeLevel3 = canBeLevel2 && (lifestylePool > 0);

            int targetLevel;

            if (canBeLevel3) {targetLevel = 3;}
            else if (canBeLevel2) {targetLevel = 2;}
            else if (canBeLevel1) {targetLevel = 1;}
            else {targetLevel = 0;}

            if (targetLevel > level) {
                level++;
                System.out.println("House at ("+x+","+y+") levels up from " +(level-1)+" to "+level);
            }
            else if (targetLevel < level) {
                level--;
                System.out.println("House at ("+x+","+y+") levels down from " +(level+1)+" to "+level);
            }
        }

        if (level == 0) generatedOutput = 0;
        else if (level == 1) generatedOutput = m;
        else if (level == 2) generatedOutput = 2 * m;
        else if (level == 3) generatedOutput = (2 * m) + lifestylePool;

        System.out.println("House at ("+x+","+y+") generated "+generatedOutput+" population");

        utilityDemand = Math.max(1, generatedOutput);
    }

}
