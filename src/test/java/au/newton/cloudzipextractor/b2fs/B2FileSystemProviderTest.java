package au.newton.cloudzipextractor.b2fs;

import au.newton.cloudzipextractor.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class B2FileSystemProviderTest extends TestUtils {
    final String accountQueryKey = "applicationKey";
    final String scheme = "b2";
    B2FileSystemProvider provider;

    @BeforeEach
    void setUp() {
        provider = new B2FileSystemProvider();
    }

    @AfterEach
    void tearDown() {
        provider = null;
    }

    @Test
    void defaultEnvironmentTest() {
        Map<String, ?> defaultEnvironment = provider.getDefaultEnv();
        assertNotNull(defaultEnvironment);
        assertEquals(defaultEnvironment.size(), 0); // one default environment entry
    }

    @Test
    void accountQueryKeyTest() {
        String accountQueryKey = provider.getAccountQueryKey();
        assertEquals(accountQueryKey, this.accountQueryKey);
    }

    @Test
    void schemeTest() {
        String scheme = provider.getScheme();
        assertEquals(scheme, this.scheme);
    }

    // [scheme:]scheme-specific-part[#fragment]
    // scheme-specific-part = [//authority][path][?query]
    // authority = [user-info@]host[:port]
    /*
     * The B2 filesystem URI must be in the form scheme://accountIdentifier?applicationKey=xxx
     * where: scheme = b2
     *  accountIdentifier = the B2 account identifier for the cloud service
     *  applicationKey = the application key that provides access to the cloud service
     */

    @Test
    void newFileSystemTest() {
        String scheme = this.scheme;
        String schemeSpecificPart = "//" + APPLICATION_KEY_ID + "?" + this.accountQueryKey + "=" + APPLICATION_KEY;
        String fragment = null;
        URI uri = null;

        try {
            uri = new URI(scheme, schemeSpecificPart, fragment);
        } catch (URISyntaxException e) {
            fail();
        } // should not exception

        // ********** UP TO HERE **********
        FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
    }

    @Test
    void newFileSystemNoUriTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            String scheme = "";
            String schemeSpecificPart = "";
            String fragment = "";
            URI uri = null;

            try { uri = new URI(scheme, schemeSpecificPart, fragment); } catch (URISyntaxException e) { fail(); } // should not exception

            // all below produce an InvalidArgumentException
            // null uri
            // null env
            // no scheme
            // not b2 scheme
            // scheme specific part starting without a /
            // null host
            // empty host
            // no query
            // empty query
            // query without accountQueryKey entry
            // query with blank value for accountQueryKey entry

            FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
        });
    }

    @Test
    void newFileSystemNoEnvTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            String scheme = "";
            String schemeSpecificPart = "";
            String fragment = "";
            URI uri = null;

            try { uri = new URI(scheme, schemeSpecificPart, fragment); } catch (URISyntaxException e) { fail(); } // should not exception

            // all below produce an InvalidArgumentException
            // null uri
            // null env
            // no scheme
            // not b2 scheme
            // scheme specific part starting without a /
            // null host
            // empty host
            // no query
            // empty query
            // query without accountQueryKey entry
            // query with blank value for accountQueryKey entry

            FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
        });
    }

    @Test
    void newFileSystemNoSchemeTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            String scheme = "";
            String schemeSpecificPart = "";
            String fragment = "";
            URI uri = null;

            try { uri = new URI(scheme, schemeSpecificPart, fragment); } catch (URISyntaxException e) { fail(); } // should not exception

            // all below produce an InvalidArgumentException
            // null uri
            // null env
            // no scheme
            // not b2 scheme
            // scheme specific part starting without a /
            // null host
            // empty host
            // no query
            // empty query
            // query without accountQueryKey entry
            // query with blank value for accountQueryKey entry

            FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
        });
    }

    @Test
    void newFileSystemNotB2SchemeTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            String scheme = "";
            String schemeSpecificPart = "";
            String fragment = "";
            URI uri = null;

            try { uri = new URI(scheme, schemeSpecificPart, fragment); } catch (URISyntaxException e) { fail(); } // should not exception

            // all below produce an InvalidArgumentException
            // null uri
            // null env
            // no scheme
            // not b2 scheme
            // scheme specific part starting without a /
            // null host
            // empty host
            // no query
            // empty query
            // query without accountQueryKey entry
            // query with blank value for accountQueryKey entry

            FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
        });
    }

    @Test
    void newFileSystemSchemeSpecificPartStartingWithoutForwardSlashTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            String scheme = "";
            String schemeSpecificPart = "";
            String fragment = "";
            URI uri = null;

            try { uri = new URI(scheme, schemeSpecificPart, fragment); } catch (URISyntaxException e) { fail(); } // should not exception

            // all below produce an InvalidArgumentException
            // null uri
            // null env
            // no scheme
            // not b2 scheme
            // scheme specific part starting without a /
            // null host
            // empty host
            // no query
            // empty query
            // query without accountQueryKey entry
            // query with blank value for accountQueryKey entry

            FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
        });
    }

    @Test
    void newFileSystemNoHostTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            String scheme = "";
            String schemeSpecificPart = "";
            String fragment = "";
            URI uri = null;

            try { uri = new URI(scheme, schemeSpecificPart, fragment); } catch (URISyntaxException e) { fail(); } // should not exception

            // all below produce an InvalidArgumentException
            // null uri
            // null env
            // no scheme
            // not b2 scheme
            // scheme specific part starting without a /
            // null host
            // empty host
            // no query
            // empty query
            // query without accountQueryKey entry
            // query with blank value for accountQueryKey entry

            FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
        });
    }

    @Test
    void newFileSystemEmptyHostTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            String scheme = "";
            String schemeSpecificPart = "";
            String fragment = "";
            URI uri = null;

            try { uri = new URI(scheme, schemeSpecificPart, fragment); } catch (URISyntaxException e) { fail(); } // should not exception

            // all below produce an InvalidArgumentException
            // null uri
            // null env
            // no scheme
            // not b2 scheme
            // scheme specific part starting without a /
            // null host
            // empty host
            // no query
            // empty query
            // query without accountQueryKey entry
            // query with blank value for accountQueryKey entry

            FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
        });
    }

    @Test
    void newFileSystemNoQueryTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            String scheme = "";
            String schemeSpecificPart = "";
            String fragment = "";
            URI uri = null;

            try { uri = new URI(scheme, schemeSpecificPart, fragment); } catch (URISyntaxException e) { fail(); } // should not exception

            // all below produce an InvalidArgumentException
            // null uri
            // null env
            // no scheme
            // not b2 scheme
            // scheme specific part starting without a /
            // null host
            // empty host
            // no query
            // empty query
            // query without accountQueryKey entry
            // query with blank value for accountQueryKey entry

            FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
        });
    }

    @Test
    void newFileSystemEmptyQueryTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            String scheme = "";
            String schemeSpecificPart = "";
            String fragment = "";
            URI uri = null;

            try { uri = new URI(scheme, schemeSpecificPart, fragment); } catch (URISyntaxException e) { fail(); } // should not exception

            // all below produce an InvalidArgumentException
            // null uri
            // null env
            // no scheme
            // not b2 scheme
            // scheme specific part starting without a /
            // null host
            // empty host
            // no query
            // empty query
            // query without accountQueryKey entry
            // query with blank value for accountQueryKey entry

            FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
        });
    }

    @Test
    void newFileSystemTestQueryWithoutAccountQueryKeyEntry() {
        assertThrows(IllegalArgumentException.class, () -> {
            String scheme = "";
            String schemeSpecificPart = "";
            String fragment = "";
            URI uri = null;

            try { uri = new URI(scheme, schemeSpecificPart, fragment); } catch (URISyntaxException e) { fail(); } // should not exception

            // all below produce an InvalidArgumentException
            // null uri
            // null env
            // no scheme
            // not b2 scheme
            // scheme specific part starting without a /
            // null host
            // empty host
            // no query
            // empty query
            // query without accountQueryKey entry
            // query with blank value for accountQueryKey entry

            FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
        });
    }

    @Test
    void newFileSystemQueryWithBlankAccountQueryKeyValueTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            String scheme = "";
            String schemeSpecificPart = "";
            String fragment = "";
            URI uri = null;

            try { uri = new URI(scheme, schemeSpecificPart, fragment); } catch (URISyntaxException e) { fail(); } // should not exception

            // all below produce an InvalidArgumentException
            // null uri
            // null env
            // no scheme
            // not b2 scheme
            // scheme specific part starting without a /
            // null host
            // empty host
            // no query
            // empty query
            // query without accountQueryKey entry
            // query with blank value for accountQueryKey entry

            FileSystem fileSystem = provider.newFileSystem(uri, provider.getDefaultEnv());
        });
    }
}
