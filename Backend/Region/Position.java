package Backend.Region;

import Backend.Statement.DirectionNode;


public interface Position {
    long getPosX();
    long getPosY();
    default Position direction(DirectionNode direction){
        boolean Check_isEven = getPosX() % 2 == 0;
        long x = getPosX();
        long y = getPosY();

        return switch (direction) {
            case up -> Position.of(x, y-1);
            case upleft -> Check_isEven ? Position.of(x - 1, y) : Position.of(x - 1, y - 1);
            case upright -> Check_isEven ? Position.of(x + 1, y) : Position.of(x + 1, y - 1);
            case down -> Position.of(x, y + 1);
            case downleft -> Check_isEven ? Position.of(x - 1, y + 1) : Position.of(x - 1, y);
            case downright -> Check_isEven ? Position.of(x + 1, y + 1) : Position.of(x + 1, y);
        };
    }

    default boolean Check_isValidPosition(long rows, long cols){
        return getPosX() >= 0 && getPosY() >= 0 && getPosX() < rows && getPosY() < cols;
    }

    static Position of(long x, long y){
        return new ConfigPosition(x, y);
    }
}
