abstract class Zone extends Cell {
    protected int level = 0;
    protected boolean hasSecurity, hasHealth, hasEducation;
    protected int electricityReceived, waterReceived, internetReceived;
    protected int generatedOutput = 0;
    protected int utilityDemand = 1;

    public Zone(int x, int y, char type) {
        super(x, y, type);
    }

    public abstract void updateLevelAndOutput(int populationPool, int goodsPool, int lifestylePool);

    public void resetUtilitiesAndServices() {
        hasSecurity = hasHealth = hasEducation = false;
        electricityReceived = waterReceived = internetReceived = 0;
    }

    public void setElectricityReceived(int electricityReceived) {
        this.electricityReceived = electricityReceived;
        if(type == 'H'){
            System.out.println("House at ("+x+","+y+") received "+electricityReceived+ " electricity");
        }
        if(type == 'I'){
            System.out.println("Industrial at ("+x+","+y+") received "+electricityReceived+ " electricity");
        }
        if(type == 'C'){
            System.out.println("Commercial at ("+x+","+y+") received "+electricityReceived+ " electricity");
        }

    }

    public void setInternetReceived(int internetReceived) {
        this.internetReceived = internetReceived;
        if(type == 'H'){
            System.out.println("House at ("+x+","+y+") received "+internetReceived+ " internet");
        }
        if(type == 'C'){
            System.out.println("Commercial at ("+x+","+y+") received "+internetReceived+ " internet");
        }
    }

    public void setWaterReceived(int waterReceived) {
        this.waterReceived = waterReceived;
        if(type == 'H'){
            System.out.println("House at ("+x+","+y+") received "+waterReceived+ " water");
        }
        if(type == 'I'){
            System.out.println("Industrial ("+x+","+y+") received "+waterReceived+ " water");
        }
        if(type == 'C'){
            System.out.println("Commercial at ("+x+","+y+") received "+waterReceived+ " water");
        }
    }

    public void setHasEducation(boolean hasEducation) {
        this.hasEducation = hasEducation;
        if(type == 'H'){
            if(hasEducation) System.out.println("House at ("+x+","+y+") received education service");
        }
    }

    public void setHasHealth(boolean hasHealth) {
        this.hasHealth = hasHealth;
        if(type == 'H'){
            if(hasHealth) System.out.println("House at ("+x+","+y+") received health service");
        }
    }

    public void setHasSecurity(boolean hasSecurity) {
        this.hasSecurity = hasSecurity;
        if(type == 'H'){
            if(hasEducation) System.out.println("House at ("+x+","+y+") received security service");
        }
        if(type == 'I'){
            if(hasSecurity) System.out.println("Industrial at ("+x+","+y+") received security service");
        }
        if(type == 'C'){
            if(hasSecurity) System.out.println("Commercial at ("+x+","+y+") received security service");
        }

    }

    public boolean isHasEducation() {return hasEducation;}
    public boolean isHasHealth() {return hasHealth;}
    public boolean isHasSecurity() {return hasSecurity;}
}
