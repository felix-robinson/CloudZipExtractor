package au.newton.cloudzipextractor.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class B2InputStreamTest {

    protected static final String APPLICATION_KEY_ID = "001440aed7310bd0000000006";
    protected static final String APPLICATION_KEY = "K001107NDDlaukcpSDebx8awJGP/8aY";
    protected static final int BUFFER_SIZE = 64*1024*1024; // 64MB

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void read() {
        B2InputStream b2In = new B2InputStream(APPLICATION_KEY_ID, APPLICATION_KEY);
        BufferedInputStream bufferedB2In = new BufferedInputStream(b2In, BUFFER_SIZE);
    }
}