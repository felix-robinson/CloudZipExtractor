package au.newton.cloudzipextractor.io;

import java.io.IOException;
import java.io.InputStream;

public class B2InputStream extends InputStream {
    protected static final String DEFAULT_USER_AGENT = "CloudZipExtractor";

    public B2InputStream(String applicationKeyIdentifier, String applicationKey) {
        this(applicationKeyIdentifier, applicationKey, DEFAULT_USER_AGENT);
    }

    public B2InputStream(String applicationKeyIdentifier, String applicationKey, String UserAgent) {
        // creates an open input stream
        // - log into B2
        // - know which file to read
    }

    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an {@code int} in the range {@code 0} to
     * {@code 255}. If no byte is available because the end of the stream
     * has been reached, the value {@code -1} is returned. This method
     * blocks until input data is available, the end of the stream is detected,
     * or an exception is thrown.
     *
     * <p> A subclass must provide an implementation of this method.
     *
     * @return the next byte of data, or {@code -1} if the end of the
     * stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int read() throws IOException {
        // TODO Implement B2InputStream.read()
        throw new UnsupportedOperationException("B2InputStream.read() needs to be implemented");
//        return -1; // no byte is available
    }

    // For supporting BufferedInputStream all that is needed is read(buffer, offset, length)
}
