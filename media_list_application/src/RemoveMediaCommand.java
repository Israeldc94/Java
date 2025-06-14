public class RemoveMediaCommand {
    TerminalUtils io = new TerminalUtils();

public void execute(MediaService service){
    io.displayMediaList(service.getAllMedia());
    String choice = io.getNonEmptyString("Enter name of Media file to remove: ");
    Media media = service.findMediaByName(choice);
    service.getAllMedia().remove(media);

        }
    }



