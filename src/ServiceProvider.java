abstract class ServiceProvider extends Cell{
    protected int radius = 5;
    public ServiceProvider(int x, int y, char type) { super(x, y, type); }

    public int getRadius() {return radius;}
}
class PoliceStation extends ServiceProvider { public PoliceStation(int x, int y) { super(x, y, 'F'); } }
class Hospital extends ServiceProvider { public Hospital(int x, int y) { super(x, y, 'D'); } }
class School extends ServiceProvider { public School(int x, int y) { super(x, y, 'S'); } }