
public class Main {
    private static final int ADD_MEDIA = 1;
    private static final int REMOVE_MEDIA = 2;
    private static final int PLAY_MEDIA = 3;
    private static final int LIST_ALL_MEDIA = 4;
    private static final int QUIT = 5;

    public static void main(String[] args) {
        MediaService service = new MediaService();
        TerminalUtils io = new TerminalUtils();
        AddMediaCommand addMedia = new AddMediaCommand();
        ListAllMediaCommand listAllMedia = new ListAllMediaCommand();
        RemoveMediaCommand removeMedia = new RemoveMediaCommand();
        PlayMediaCommand playMedia = new PlayMediaCommand();

        boolean keepRunning = true;


        while (keepRunning) {
            io.displayMenu();
            int choice = io.getMenuChoice();

            switch (choice) {
                case ADD_MEDIA:
                    addMedia.execute(service);
                    break;

                case REMOVE_MEDIA:
                    removeMedia.execute(service);
                    break;

                case PLAY_MEDIA:
                    playMedia.execute(service);

                case LIST_ALL_MEDIA:
                    listAllMedia.execute(service);
                    break;

                case QUIT:
                    keepRunning = false;
                    break;


            }


        }
    }
}