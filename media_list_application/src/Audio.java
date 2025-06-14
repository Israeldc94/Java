public class Audio extends Media {
    private int duration;
    private String artist;

    public Audio(String name, int duration, String artist) {
        this.name = name;
        this.duration = duration;
        this.artist = artist;
    }
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public void play() {

    }

    @Override
    public String getDescription() {
        return "Description: Song: " + getName() + " Duration: " + getDuration() + " Artist: " + getArtist();
    }

    @Override
    public String toString(){
        return "\nSong: " + getName() + "\n" + getDescription();
    }
}
