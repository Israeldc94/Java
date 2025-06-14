public abstract class Media {
    protected String name;

    //Regular methods with implementation
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Abstract methods - no implementation
    public abstract void play();
    public abstract String getDescription();

}
