package Back_End.Project.Region;

import Back_End.Project.Player.Player;

public interface Region {
    Player getOwner();

    Position getLocation();

    boolean isCityCenter();

    long getDeposit();

    void changeCityCenter();
    void updateOwner(Player owner);
    void updateDeposit(long amount);
    void setCityCenter(Player owner);
}
