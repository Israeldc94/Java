public class AddMediaCommand {
    TerminalUtils io = new TerminalUtils();
    MediaFactory factory = new MediaFactory();
    public final int VIDEO = 1;
    public final int AUDIO = 2;
    public final int IMAGE = 3;
    public final int BOOK = 4;

    Media media;
   public void execute(MediaService service){
       io.displayMenu2();
       int choice = io.getMenuChoice2();

       switch (choice){
           case VIDEO:
             media = factory.createVideo();
             service.addMedia(media);
             break;

           case AUDIO:
             media = factory.createAudio();
             service.addMedia(media);
               break;

           case IMAGE:
               media = factory.createImage();
               service.addMedia(media);

           case BOOK:
               media = factory.createBook();
               service.addMedia(media);
       }


   }
}
