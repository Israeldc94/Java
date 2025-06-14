public class ListAllMediaCommand {
    TerminalUtils io = new TerminalUtils();

    public void execute(MediaService service){
        io.displayMediaList(service.getAllMedia());


    }
}
