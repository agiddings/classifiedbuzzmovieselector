package classified.classifiedbuzzmovieselector;

import org.junit.Before;
import org.junit.Test;

import classified.classifiedbuzzmovieselector.model.User;
import classified.classifiedbuzzmovieselector.model.UserManager;

import static org.junit.Assert.assertEquals;

/**
 * Created by justeen(Zhendong) on 4/3/16.
 *
 * test passwordMatched(String entry)
 *
 */
public class passwordMatchedTest {

    private User testUser;
    private UserManager manager;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        if (manager == null) {
            manager = new UserManager();
        }
        if (manager.findUserByName("Julia") == null) {
            try {
                manager.addUser("Julia", "hi@julia.com", "julia");
            } catch (Exception e) {

            }
        }
        testUser = manager.findUserByName("Julia");
    }

    //password length < 1, fail
    @Test(timeout = TIMEOUT)
    public void testLength() {
        boolean expected = false;
        boolean actual = testUser.passwordMatched("");
        assertEquals(expected, actual);
    }


    //password length = null, fail
    @Test(timeout = TIMEOUT)
    public void testNull() {
        boolean expected = false;
        boolean actual = testUser.passwordMatched(null);
        assertEquals(expected, actual);
    }

    //testWrongPassword
    @Test(timeout = TIMEOUT)
    public void testWrongPassword() {
        boolean expected = false;
        boolean actual = testUser.passwordMatched("juliaa");
        assertEquals(expected, actual);
    }

    //testCorrectPassword
    @Test(timeout = TIMEOUT)
    public void testCorrectPassword() {
        boolean expected = true;
        boolean actual = testUser.passwordMatched("julia");
        assertEquals(expected, actual);
    }

    //testLocked
    @Test(timeout = TIMEOUT)
    public void testLocked() {
        boolean expected = true;
        testUser.passwordMatched("julia1");
        testUser.passwordMatched("julia2");
        testUser.passwordMatched("julia3");
        testUser.passwordMatched("julia4");
        boolean actual = testUser.isLocked();
        assertEquals(expected, actual);
    }
}
