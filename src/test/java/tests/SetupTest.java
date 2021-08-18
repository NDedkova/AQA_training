package tests;

import databaseConnect.JDBCConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class SetupTest {

    private static final Logger logger = LogManager.getLogger(SetupTest.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("------- Started test: " + testInfo.getDisplayName() + " -------");
        Assertions.assertNotNull(JDBCConnection.connectDB());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        JDBCConnection.closeConnect();
        logger.info("------- Finished test: " + testInfo.getDisplayName() + " -------");
    }
}
