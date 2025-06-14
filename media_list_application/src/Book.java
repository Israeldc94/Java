public class Book extends Media{
  private String author;
  private int pageCount;

    public Book(String name, String author, int pageCount){
        this.name = name;
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public void play() {

    }

    @Override
    public String getDescription() {
        return "Description: Book: " + getName() + " Author: " + getAuthor() + " Pages: " + getPageCount();
    }
    @Override
    public String toString() {
        return "\nBook: " + getName() + "\n" + getDescription();
    }
}
