package loaders;

import java.io.IOException;
import java.io.InputStream;

public class ResourceLoader {

    private ResourceLoader() {
    }

    public static InputStream getResource(String path)
            throws IOException {

        InputStream stream =
                ResourceLoader.class.getResourceAsStream(path);

        if (stream == null) {
            throw new IOException(
                "Resource not found: " + path
            );
        }

        return stream;
    }
}
