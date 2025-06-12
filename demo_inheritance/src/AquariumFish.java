public class AquariumFish extends Fish{
    private String nickName;
    private String food;
    private double maxTemp;
    private double minTemp;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    @Override
    public String toString() {
        return "This aquarium fish has the nickname: " + nickName
                + " it is of the species: " + getScientificName();
    }
}
