import java.util.ArrayList;

public class MediaService {
    private ArrayList<Media> mediaList;


    public MediaService() {
        mediaList = new ArrayList<>();
    }

    public void addMedia(Media media) {
        mediaList.add(media);
    }

    public Media removeMedia(int index) {
        if (index < 0 || index >= mediaList.size()) {
            return null;
        }
        Media removedMedia = mediaList.remove(index);
        return removedMedia;
    }

    public Media findMediaByName(String name) {
        if (!mediaList.isEmpty()) {
            for (int i = 0; i <= mediaList.size(); i++) {
                if (mediaList.get(i).getName().equals(name)) {
                    return mediaList.get(i);
                }
            }
        }
        return null;
    }

    public ArrayList<Media> getAllMedia(){
        return mediaList;
    }

    public int getMediaCount(){
        return mediaList.size();
    }


}