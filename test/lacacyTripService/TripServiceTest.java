package lacacyTripService;

import legacyTripService.exception.UserNotLoggedInException;
import legacyTripService.trip.Trip;
import legacyTripService.trip.TripService;
import legacyTripService.user.User;
import org.junit.Before;
import org.junit.Test;

import static lacacyTripService.UserBuilder.aUser;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

public class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_BRAZIL = new Trip();
    private static final Trip TO_LONDON = new Trip();
    private User loggedInUser;

    private TripService tripService;

    @Before
    public void setup() {
        tripService = new TestableTripService();
        loggedInUser = REGISTERED_USER;
        loggedInUser = REGISTERED_USER;
    }


    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_on_exception_when_user_is_not_logged_in() {
        loggedInUser = GUEST;

        tripService.getTripsByUser(UNUSED_USER);
    }

    @Test
    public void should_not_return_any_trips_when_users_are_not_friends() {
        User friend = aUser()
                            .friendsWith(ANOTHER_USER)
                            .withTrips(TO_BRAZIL)
                            .build();

        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        assertThat(friendTrips.size(), is(0));
    }

    @Test
    public void should_return_friend_trip_when_users_are_friends() {
        User friend = aUser()
                            .friendsWith(ANOTHER_USER, loggedInUser)
                            .withTrips(TO_BRAZIL, TO_LONDON)
                            .build();

        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        assertThat(friendTrips.size(), is(2));
    }

    private class TestableTripService extends TripService {

        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> tripBy(User user) {
            return user.trips();
        }
    }
}
