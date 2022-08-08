package au.newton.cloudzipextractor.io;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B2File extends File {

    protected final String bucketName;
    protected final String fileName;

    /**
     * Creates a new {@code B2File} instance from a bucket name string
     * and a file name string.
     *
     * <p> Both {@code bucket} and {@code file} must be valid values.
     *
     * <p> Bucket names can consist of
     * upper-case letters, lower-case letters, numbers, and "-".
     * No other characters are allowed. Even though your bucket can
     * have upper-case letters, bucket names are case insensitive. A
     * bucket name must be at least 6 characters long, and can be at
     * most 50 characters long. These are all allowed bucket names:
     * myBucket, backblaze-images, and bucket-74358734. Bucket names
     * that start with "b2-" are reserved for Backblaze use.
     * re: <a href="https://www.backblaze.com/b2/docs/buckets.html">B2 Buckets</a>
     *
     * <p> Names can be pretty much any UTF-8 string up to 1024 bytes
     * long. There are a few picky rules: No character codes below 32
     * are allowed; DEL characters (127) are not allowed.
     * re: <a href="https://www.backblaze.com/b2/docs/files.html">B2 Files</a>
     *
     * @param bucket The bucket name string
     * @param file  The file name string
     * @throws NullPointerException If {@code bucket} or {@code file is {@code null} or empty
     */
    public B2File(String bucket, String file) {
        super(bucket, file);
        if (bucket == null || bucket.isBlank())
            throw new NullPointerException("bucket is null or empty");
        if (file.isBlank()) // super(bucket, file) ensures that file is not null - so I omitted the check
            throw new NullPointerException("file is null or empty");

        // check length
        if (bucket.length() < 6 || bucket.length() > 50)
            throw new IllegalArgumentException("bucket name length is below 6 or above 50 characters long");
        if (file.getBytes(StandardCharsets.UTF_8).length > 1024)
            throw new IllegalArgumentException("file name length is greater than 1024 characters long");

        // check reserved
        if (bucket.startsWith("b2-") || bucket.startsWith("B2-"))
            throw new IllegalArgumentException("bucket name cannot start with b2- or B2-");

        // check characters
        Pattern pattern = Pattern.compile("[^abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-]", Pattern.LITERAL);
        Matcher matcher = pattern.matcher(bucket);
        if (matcher.find())
                throw new IllegalArgumentException("bucket name contains illegal characters");

        for (char letter : file.toCharArray()) {
            if ( ((int)letter < 32) || ((int)letter == 127) )
                throw new IllegalArgumentException("file name contained illegal characters");
        }

        bucketName = bucket;
        fileName = file;

        // lazy open the B2 file - only when it is first accessed
        // - this should be only when exists() is called
    }

    @Override
    public File getParentFile() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("getParentFile() is not supported by B2File");
    }

    /**
     * Tests whether this abstract pathname is absolute.  The definition of
     * absolute pathname is system dependent.  On B2 all buckets are absolute.
     *
     * @return  {@code true} this abstract pathname is absolute,
     */
    @Override
    public boolean isAbsolute() {
        return true;
    }

    /**
     * Returns the absolute pathname string of this abstract pathname.
     *
     * <p> For B2 the abstract pathname is already absolute, then the pathname
     * string is simply returned as if by the {@link #getPath}
     * method.
     *
     * @return  The absolute pathname string denoting the same file or
     *          directory as this abstract pathname
     */
    @Override
    public String getAbsolutePath() {
        return this.getPath();
    }

    @Override
    public File getAbsoluteFile() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("getAbsoluteFile() is not supported by B2File");
    }

    /**
     * Returns the canonical pathname string of this abstract pathname.
     *
     * <p> A canonical pathname is both absolute and unique.  On B2 all
     * files are absolute and unique, then the pathname
     * string is simply returned as if by the {@link #getPath}
     * method.
     *
     * @return  The canonical pathname string denoting the same file or
     *          directory as this abstract pathname
     */
    @Override
    public String getCanonicalPath() throws IOException {
        return this.getPath();
    }

    @Override
    public File getCanonicalFile() throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException("getCanonicalFile() is not supported by B2File");
    }

    @Override
    @Deprecated
    public URL toURL() throws MalformedURLException, UnsupportedOperationException {
        throw new UnsupportedOperationException("toURL() is not supported by B2File");
    }

    @Override
    public URI toURI() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("toURI() is not supported by B2File");
    }

    /**
     * Tests whether the application can read the file denoted by this
     * abstract pathname.
     *
     * @return  {@code true} if and only if the file specified by this
     *          abstract pathname exists <em>and</em> can be read by the
     *          application; {@code false} otherwise
     *
     * @throws  UncheckedIOException
     *          If there is a failure determining readability
     */
    @Override
    public boolean canRead() throws UncheckedIOException {
        // TODO implement canRead()
        throw new UncheckedIOException(new IOException("canRead() is not implemented yet"));
    }

    /**
     * Tests whether the application can write the file denoted by this
     * abstract pathname.
     *
     * @return  {@code false} B2File does not support writing so false is
     *          always returned
     *
     * @throws  UncheckedIOException
     *          If there is a failure determining writability
     */
    @Override
    public boolean canWrite() throws UncheckedIOException {
        // TODO implement canWrite()
        throw new UncheckedIOException(new IOException("canWrite() is not implemented yet"));
    }

    /**
     * Tests whether the file denoted by this abstract pathname
     * exists.
     *
     * @return  {@code true} if and only if the file or directory denoted
     *          by this abstract pathname exists; {@code false} otherwise
     *
     * @throws  UncheckedIOException
     *          If there is a failure determining existence
     */
    @Override
    public boolean exists() throws UncheckedIOException {
        // TODO implement exists()
        throw new UncheckedIOException(new IOException("exists() is not implemented yet"));
    }

    /**
     * Tests whether the file denoted by this abstract pathname is a
     * directory.  B2 does not support directories so this method uses
     * the bucket concept instead.
     *
     * @return {@code true} if this object represents a B2 bucket
     *         {@code false}   otherwise
     */
    @Override
    public boolean isDirectory() {
        assert(bucketName != null);
        return (fileName == null); // TODO update constructor so an empty fileName is possible
    }

    /**
     * Tests whether the file denoted by this abstract pathname is a normal
     * file.
     *
     * @return  {@code true} if this object represents a B2 file
     *          {@code false}   otherwise
     */
    @Override
    public boolean isFile() {
        assert(bucketName != null);
        return (fileName != null);
    }

    /**
     * Tests whether the file named by this abstract pathname is a hidden
     * file.
     *
     * @return  {@code true} if and only if the file denoted by this
     *          abstract pathname is hidden according to the conventions of the
     *          underlying platform
     *
     * @throws  UncheckedIOException
     *          If there is a failure determining visibility
     */
    @Override
    public boolean isHidden() {
        // TODO implement isHidden()
        throw new UncheckedIOException(new IOException("isHidden() is not implemented yet"));
    }

    /**
     * Returns the time that the file denoted by this abstract pathname was
     * last modified.
     *
     * @return  A {@code long} value representing the time the file was
     *          last modified, measured in milliseconds since the epoch
     *          (00:00:00 GMT, January 1, 1970), or {@code 0L} if the
     *          file does not exist or if an I/O error occurs.  The value may
     *          be negative indicating the number of milliseconds before the
     *          epoch
     *
     * @throws  UncheckedIOException
     *          If there is a failure determining modification time
     */
    @Override
    public long lastModified() throws UncheckedIOException {
        if (isDirectory()) return 0;

        // TODO implement isHidden(); refer to https://www.backblaze.com/b2/docs/files.html
        throw new UncheckedIOException(new IOException("lastModified() is not implemented yet"));
    }

    /**
     * Returns the length of the file denoted by this abstract pathname.
     * The return value is unspecified if this pathname denotes a directory.
     *
     * @return  The length, in bytes, of the file denoted by this abstract
     *          pathname, or {@code 0L} if the file does not exist.  Some
     *          operating systems may return {@code 0L} for pathnames
     *          denoting system-dependent entities such as devices or pipes.
     *
     * @throws  UncheckedIOException
     *          If there is a failure determining modification time
     */
    @Override
    public long length() throws UncheckedIOException {
        if (isDirectory()) return 0;

        // TODO implement length()
        throw new UncheckedIOException(new IOException("length() is not implemented yet"));
    }

    @Override
    public boolean createNewFile() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("createNewFile() is not supported by B2File");
    }

    @Override
    public boolean delete() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("delete() is not supported by B2File");
    }

    @Override
    public void deleteOnExit() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("deleteOnExit() is not supported by B2File");
    }

    /**
     * Returns an array of strings naming the files in the
     * bucket denoted by this abstract pathname.
     *
     * <p> If this abstract pathname does not denote a bucket, then this
     * method returns {@code null}.  Otherwise an array of strings is
     * returned, one for each file in the bucket.  Names
     * denoting the bucket itself are
     * not included in the result.  Each string is a file name rather than a
     * complete path.
     *
     * <p> There is no guarantee that the name strings in the resulting array
     * will appear in any specific order; they are not, in particular,
     * guaranteed to appear in alphabetical order.
     *
     * @return  An array of strings naming the files in the
     *          bucket denoted by this abstract pathname.  The array will be
     *          empty if the directory is empty.  Returns {@code null} if
     *          this abstract pathname does not denote a directory.
     *
     * @throws  UncheckedIOException
     *          If there is a failure determining bucket contents
     */
    @Override
    public String[] list() throws UncheckedIOException {
        if (!isDirectory()) return null;

        // TODO implement list()
        throw new UncheckedIOException(new IOException("list() is not implemented yet"));
    }

    /**
     * Returns an array of strings naming the files in the
     * bucket denoted by this abstract pathname that satisfy the specified
     * filter.  The behavior of this method is the same as that of the
     * {@link #list()} method, except that the strings in the returned array
     * must satisfy the filter.  If the given {@code filter} is {@code null}
     * then all names are accepted.  Otherwise, a name satisfies the filter if
     * and only if the value {@code true} results when the {@link
     * FilenameFilter#accept FilenameFilter.accept(File,&nbsp;String)} method
     * of the filter is invoked on this abstract pathname and the name of a
     * file or directory in the directory that it denotes.
     *
     * @param  filter
     *         A filename filter
     *
     * @return  An array of strings naming the files in the
     *          bucket denoted by this abstract pathname that were accepted
     *          by the given {@code filter}.  The array will be empty if the
     *          directory is empty or if no names were accepted by the filter.
     *          Returns {@code null} if this abstract pathname does not denote
     *          a directory, or if an I/O error occurs.
     *
     * @throws  UncheckedIOException
     *          If there is a failure determining bucket contents
     */
    @Override
    public String[] list(FilenameFilter filter) throws UncheckedIOException {
        String[] names = list();
        if ((names == null) || (filter == null)) {
            return names;
        }
        List<String> v = new ArrayList<>();
        for (String name : names) {
            if (filter.accept(this, name)) {
                v.add(name);
            }
        }
        return v.toArray(new String[0]);
    }

    @Override
    public File[] listFiles() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("listFiles() is not supported by B2File");
    }

    @Override
    public File[] listFiles(FilenameFilter filter) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("listFiles() is not supported by B2File");
    }

    @Override
    public File[] listFiles(FileFilter filter) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("listFiles() is not supported by B2File");
    }

    @Override
    public boolean mkdir() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("mkdir() is not supported by B2File");
    }

    @Override
    public boolean mkdirs() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("mkdirs() is not supported by B2File");
    }

    @Override
    public boolean renameTo(File dest) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("renameTo() is not supported by B2File");
    }

    @Override
    public boolean setLastModified(long time) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("setLastModified() is not supported by B2File");
    }

    @Override
    public boolean setReadOnly() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("setReadOnly() is not supported by B2File");
    }

    @Override
    public boolean setWritable(boolean writable, boolean ownerOnly) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("setWritable() is not supported by B2File");
    }

    @Override
    public boolean setWritable(boolean writable) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("setWritable() is not supported by B2File");
    }

    @Override
    public boolean setReadable(boolean readable, boolean ownerOnly) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("setReadable() is not supported by B2File");
    }

    @Override
    public boolean setReadable(boolean readable) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("setReadable() is not supported by B2File");
    }

    @Override
    public boolean setExecutable(boolean executable, boolean ownerOnly) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("setExecutable() is not supported by B2File");
    }

    @Override
    public boolean setExecutable(boolean executable) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("setExecutable() is not supported by B2File");
    }

    /**
     * Tests whether the application can execute the file denoted by this
     * abstract pathname. Execution of B2 files is not supported.
     *
     * @return  {@code false} always
     */
    @Override
    public boolean canExecute() {
        return false;
    }

    public static File[] listRoots() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("listRoots() is not supported by B2File");
    }

    /**
     * Returns the size of the partition <a href="#partName">named</a> by this
     * abstract pathname. If the total number of bytes in the partition is
     * greater than {@link Long#MAX_VALUE}, then {@code Long.MAX_VALUE} will be
     * returned.
     *
     * For B2 storage 0 is always returned
     *
     * @return  The size, in bytes, of the partition or {@code 0L} if this
     *          abstract pathname does not name a partition or if the size
     *          cannot be obtained
     */
    @Override
    public long getTotalSpace() {
        return 0;
    }

    /**
     * Returns the number of unallocated bytes in the partition <a
     * href="#partName">named</a> by this abstract path name.  If the
     * number of unallocated bytes in the partition is greater than
     * {@link Long#MAX_VALUE}, then {@code Long.MAX_VALUE} will be returned.
     *
     * For B2 storage 0 is always returned
     *
     * @return  The number of unallocated bytes on the partition or {@code 0L}
     *          if the abstract pathname does not name a partition or if this
     *          number cannot be obtained.  This value will be less than or
     *          equal to the total file system size returned by
     *          {@link #getTotalSpace}.
     */
    @Override
    public long getFreeSpace() {
        return 0;
    }

    /**
     * Returns the number of bytes available to this virtual machine on the
     * partition <a href="#partName">named</a> by this abstract pathname.  If
     * the number of available bytes in the partition is greater than
     * {@link Long#MAX_VALUE}, then {@code Long.MAX_VALUE} will be returned.
     *
     * For B2 storage 0 is always returned
     *
     * @return  The number of available bytes on the partition or {@code 0L}
     *          if the abstract pathname does not name a partition or if this
     *          number cannot be obtained.  On systems where this information
     *          is not available, this method will be equivalent to a call to
     *          {@link #getFreeSpace}.
     */
    @Override
    public long getUsableSpace() {
        return 0;
    }

    public static File createTempFile(String prefix, String suffix, File directory)
            throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException("createTempFile() is not supported by B2File");
    }

    public static File createTempFile(String prefix, String suffix)
            throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException("createTempFile() is not supported by B2File");
    }

    /**
     * Compares two abstract pathnames lexicographically.  The ordering
     * defined by this method depends upon the underlying system.  On B2
     * case is not considered.
     *
     * @param   pathname  The abstract pathname to be compared to this abstract
     *                    pathname
     *
     * @return  Zero if the argument is equal to this abstract pathname, a
     *          value less than zero if this abstract pathname is
     *          lexicographically less than the argument, or a value greater
     *          than zero if this abstract pathname is lexicographically
     *          greater than the argument
     */
    @Override
    public int compareTo(File pathname) {
        return this.getPath().toLowerCase().compareTo(pathname.getPath().toLowerCase());
    }

    /**
     * Tests this abstract pathname for equality with the given object.
     * Returns {@code true} if and only if the argument is not
     * {@code null} and is an abstract pathname that is the same as this
     * abstract pathname.  Whether or not two abstract
     * pathnames are equal depends upon the underlying operating system.
     * On B2 case is not considered.
     *
     * @apiNote This method only tests whether the abstract pathnames are equal;
     *          it does not access the file system and the file is not required
     *          to exist.
     *
     * @param   obj   The object to be compared with this abstract pathname
     *
     * @return  {@code true} if and only if the objects are the same;
     *          {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof B2File b2file) {
            return compareTo(b2file) == 0;
        }
        return false;
    }

    /**
     * Computes a hash code for this abstract pathname.  Because equality of
     * abstract pathnames is inherently system-dependent, so is the computation
     * of their hash codes.  On B2 case is not considered.
     *
     * @return  A hash code for this abstract pathname
     */
    @Override
    public int hashCode() {
        return this.getPath().toLowerCase().hashCode();
    }

    @Override
    public Path toPath() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("toPath() is not supported by B2File");
    }


}