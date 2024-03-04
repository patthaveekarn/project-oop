package Backend.Region;

public class ConfigPosition implements Position{
    protected long x, y;

    public ConfigPosition(long x, long y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public long getPosX() {
        return x;
    }

    @Override
    public long getPosY() {
        return y;
    }

    @Override
    public boolean equals(Object point){
        if(!(point instanceof Position position)){
            return false;
        }
        return position.getPosX() == x && position.getPosY() == y;
    }

    @Override
    public String toString() {
        return String.format("x: %d, y: %d", x, y);
    }

    @Override
    public int hashCode(){
        long result = (y + (x + 1) / 2);
        return  (int) (x + (result * result));
    }
}
