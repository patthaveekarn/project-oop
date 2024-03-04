package Backend.Region;

import Backend.Player.player;

public interface Region {
    player getOwner();

    Position getLocation();

    boolean isCityCenter();

    long getDeposit();

    void changeCityCenter();
    void updateOwner(player owner);
    void updateDeposit(long amount);
    void setCityCenter(player owner);
}
