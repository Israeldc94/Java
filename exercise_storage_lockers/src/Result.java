public class Result {

    //success boolean is mainly for readability, is not returned to the user
    private final boolean success;
    private final String message;

    //allows for Results use in other files. Most of our methods will return these messages
    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    //returns the message in result when called
    public String getMessage(){
        return this.message;
    }
}

