package core;

public class StenographyProcessor {

    private final Encrypter encrypter;
    private final Decrypter decrypter;

    public StenographyProcessor(SteganographyType type, String containerPath) {
        switch (type) {
            case IMAGE -> {
                encrypter = new ImageEncrypter();
                decrypter = new ImageDecrypter();
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
