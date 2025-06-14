public class MediaFactory {
    TerminalUtils io = new TerminalUtils();

    public Video createVideo() {
        String name = io.getNonEmptyString("Enter video name: ");
        int duration = io.getInt("Enter duration (in minutes): ", 1, 51420);
        String resolution = io.getNonEmptyString("Enter resolution: ");
        Video video = new Video(name, duration, resolution);
        io.print("\nVideo added successfully");
        return video;
    }

    public Audio createAudio() {
        String name = io.getNonEmptyString("Enter the audio name: ");
        int duration = io.getInt("Enter duration (in minutes): ", 1, 500);
        String artist = io.getNonEmptyString("Enter the Artist name: ");
        Audio audio = new Audio(name, duration, artist);
        io.print("\nAudio added successfully");
        return audio;

    }

    public Image createImage() {
        String name = io.getNonEmptyString("Enter the image name: ");
        String dimensions = io.getNonEmptyString("Enter image dimensions: ");
        String fileFormat = io.getNonEmptyString("Enter file format: ");
        Image image = new Image(name, dimensions, fileFormat);
        io.print("\n image added successfully");
        return image;
    }

    public Book createBook(){
        String name = io.getNonEmptyString("Enter the book name: ");
        String author = io.getNonEmptyString("Enter the author name: ");
        int pageCount = io.getInt("Enter the number of pages: ", 1, 51240);
        Book book = new Book(name, author, pageCount);
        io.print("\n book added successfully");
        return book;

    }


}
