abstract class Cell {
    protected int x, y;
    protected char type;

    public Cell(int x, int y, char type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public char getType() {return type;}
    public int getX() {return x;}
    public int getY() {return y;}


}
class Road extends Cell { public Road(int x, int y) { super(x, y, 'R'); } }
class Empty extends Cell { public Empty(int x, int y) { super(x, y, 'E'); } }