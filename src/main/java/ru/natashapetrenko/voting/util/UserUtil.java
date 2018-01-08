package ru.natashapetrenko.voting.util;

import ru.natashapetrenko.voting.model.User;
import ru.natashapetrenko.voting.to.UserTo;

public class UserUtil {

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

}
