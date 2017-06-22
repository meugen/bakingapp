package ua.meugen.android.bakingapp.model;

public class Step {

    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(final String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(final String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(final String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
