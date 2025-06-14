public class Video extends Media{
    private int duration;
    private String resolution;


    public Video(String name, int duration, String resolution){
        this.name = name;
        this.duration = duration;
        this.resolution = resolution;
    }
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + name + " using video player software.");
    }

    @Override
    public String getDescription() {
        return "Description: Video " + getName() + " Duration: " + getDuration() + " Resolution: " + getResolution();
    }

    @Override
    public String toString() {
        return "\nVideo: " + getName() + "\n" + getDescription();
    }
}
