public class PlayMediaCommand {
    TerminalUtils io = new TerminalUtils();

    public void execute(MediaService service) {
        io.displayMediaList(service.getAllMedia());
        String choice = io.getNonEmptyString("Enter name of Media file to play: ");
        Media media = service.findMediaByName(choice);
        media.play();
    }
}
