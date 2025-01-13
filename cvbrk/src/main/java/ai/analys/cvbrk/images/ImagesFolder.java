package ai.analys.cvbrk.images;

public enum ImagesFolder {
    USER("user"),
    POST("post"),
    PDF("pdf");
    private final String value;

    ImagesFolder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Méthode pour obtenir une constante à partir de sa valeur
    public static ImagesFolder fromValue(String value) {
        for (ImagesFolder file : ImagesFolder.values()) {
            if (file.value.equalsIgnoreCase(value)) {
                return file;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
