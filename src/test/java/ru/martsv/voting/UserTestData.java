package ru.martsv.voting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.martsv.voting.matcher.ModelMatcher;
import ru.martsv.voting.model.Role;
import ru.martsv.voting.model.User;
import ru.martsv.voting.util.PasswordUtil;

import java.util.Objects;

import static ru.martsv.voting.model.BaseEntity.START_SEQ;

/**
 * mart
 * 28.08.2016
 */
public class UserTestData {
    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);

    public static final int USER1_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 3;

    public static final User USER1 = new User(USER1_ID, "Bill", "bill@microsoft.com", "password", Role.ROLE_USER);
    public static final User USER2 = new User(USER1_ID + 1, "Jeff", "jeff@amazon.com", "password", Role.ROLE_USER);
    public static final User USER3 = new User(USER1_ID + 2, "Larry", "larry@oracle.com", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    public static final ModelMatcher<User> MATCHER = new ModelMatcher<>(User.class,
            (expected, actual) -> {
                if (expected == actual) {
                    return true;
                }
                boolean cmp = comparePassword(expected.getPassword(), actual.getPassword())
                        && Objects.equals(expected.getId(), actual.getId())
                        && Objects.equals(expected.getName(), actual.getName())
                        && Objects.equals(expected.getEmail(), actual.getEmail())
                        && Objects.equals(expected.isEnabled(), actual.isEnabled())
                        && Objects.equals(expected.getRole(), actual.getRole());
                return cmp;
            }
    );

    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }

}
