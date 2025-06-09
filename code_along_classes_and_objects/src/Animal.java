public class Animal {
    private String name;
    private int popCount;
    private String sound;


    public Animal(String name, int popCount, String sound){
        this.name = name;
        this.popCount = popCount;
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public int getPopCount() {
        return popCount;
    }

    public String getSound() {
        return sound;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopCount(int popCount) {
        this.popCount = popCount;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}