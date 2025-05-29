public class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author, boolean isAvailable){
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }
    public void displayBook(){
        System.out.printf("Book: %s, %s, (Available: %b) ",this.title, this.author, this.isAvailable);
    }

    public void borrowBook(){
        System.out.printf("%n Borrowing the book...%n");
        this.isAvailable = !isAvailable;
    }
}
