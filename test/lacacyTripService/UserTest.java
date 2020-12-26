package lacacyTripService;

import legacyTripService.user.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserTest {

    private static final User BOB = new User();
    private static final User PAUL= new User();

    @Test
    public void should_inform_when_users_are_not_friends() {
        User user = UserBuilder.aUser()
                        .friendsWith(BOB)
                        .build();

        assertThat(user.isFriendsWith(PAUL), is(false));
    }

    @Test
    public void should_inform_when_users_are_friends() {
        User user = UserBuilder.aUser()
                        .friendsWith(BOB, PAUL)
                        .build();

        assertThat(user.isFriendsWith(PAUL), is(true));
    }

}