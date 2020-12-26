package legacyTripService.trip;

import legacyTripService.exception.UserNotLoggedInException;
import legacyTripService.user.User;
import legacyTripService.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if(getLoggedUser()== null) {
            throw new UserNotLoggedInException();
        }
        return user.isFriendsWith(getLoggedUser())
                        ? tripBy(user)
                        : noTrips();
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<Trip>();
    }

    protected List<Trip> tripBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}