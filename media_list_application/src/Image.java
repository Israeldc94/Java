public class Image extends Media{
    private String dimensions;
    private String fileFormat;

    public Image(String name, String dimensions, String fileFormat){
        this.name = name;
        this.dimensions = dimensions;
        this.fileFormat = fileFormat;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Override
    public void play() {

    }

    @Override
    public String getDescription() {
        return "Description: Image: " + getName() + " Dimensions: " + getDimensions() + " File format: " + getFileFormat();
    }

    @Override
    public String toString() {
        return "\nImage: " + getName() + "\n" + getDescription();
    }
}
