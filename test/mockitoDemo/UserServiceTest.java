package mockitoDemo;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void testThenReturn() throws Exception {
        UserManager manager = Mockito.mock(UserManager.class);
        Mockito.when(manager.getUserCount()).thenReturn(50);

        UserService userService = new UserService(manager);
        Assert.assertEquals(50, userService.getUserCount());
    }

    @Test
    public void testThenThrow() throws Exception {
        UserManager manager = Mockito.mock(UserManager.class);
        Mockito.when(manager.getUserCount()).thenThrow(new RuntimeException());
        UserService userService = new UserService(manager);
        assertEquals(-1, userService.getUserCount());
    }

    @Test
    public void testThenAnswer() {
        UserManager manager = Mockito.mock(UserManager.class);
        
        Mockito.when(manager.getUserCount()).thenAnswer(new Answer(){

            private int count = 0;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return ++count;
            }
        });
        
        UserService userService = new UserService(manager);
        assertEquals(1, userService.getUserCount());
        assertEquals(2, userService.getUserCount());
        assertEquals(3, userService.getUserCount());
    }

}