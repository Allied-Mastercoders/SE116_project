abstract class ServiceProvider extends Cell{
    protected int radius;
    public ServiceProvider(int x, int y, char type) { super(x, y, type); }

    public int getRadius() {return radius;}
}
class PoliceStation extends ServiceProvider { public PoliceStation(int x, int y) { super(x, y, 'F');this.radius=5;}}

class Hospital extends ServiceProvider { public Hospital(int x, int y) { super(x, y, 'D');this.radius=3; } }

class School extends ServiceProvider { public School(int x, int y) { super(x, y, 'S');this.radius=4; } }
