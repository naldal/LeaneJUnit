package legacyTripService.trip;

import legacyTripService.exception.UserNotLoggedInException;
import legacyTripService.user.User;
import legacyTripService.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
        User loggedUser = getLoggedUser();
        boolean isFriend = false;

        if (loggedUser != null) {
            for (User friend : user.getFriends()) {
                if (friend.equals(loggedUser)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                tripList = tripBy(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

    protected List<Trip> tripBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}