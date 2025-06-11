public class Result {
    //success boolean is mainly for readability, is not returned to the user
    private boolean success;
    private String message;
    private Locker locker;

    //allows for Results use in other files. Most of our methods will return these messages
    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    //returns the message in result when called
    public String getMessage(){
        return this.message;
    }

    public Result(String message, Locker locker){
        this.message = message;
        this.locker = locker;
    }
    public Result(Locker locker){
        this.locker = locker;
    }


}
