package Back_End.Project.Region;

import Back_End.Project.Player.Player;

public class ConfigRegion implements Region{
    protected Player owner;
    protected Position location;
    protected boolean isCityCenter;
    protected long maxDeposit;
    protected long deposit;

    public ConfigRegion(Position location, long max_Deposit){
        this.location = location;
        this.maxDeposit = max_Deposit;
        this.owner = null;
        this.isCityCenter = false;
        this.deposit = 0;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public void updateOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public boolean isCityCenter() {
        return isCityCenter;
    }

    @Override
    public void setCityCenter(Player owner){
        this.isCityCenter = true;
        this.owner = owner;
    }

    @Override
    public void changeCityCenter() {
        isCityCenter = false;
    }

    @Override
    public Position getLocation() {
        return this.location;
    }

    @Override
    public long getDeposit() {
        return deposit;
    }

    @Override
    public void updateDeposit(long amount) {
        deposit = Math.max(0, amount + deposit);
        deposit = Math.min(maxDeposit, deposit);
    }

    @Override
    public String toString(){
        return String.format("Owner : %s, location: %s", owner, location);
    }
}
