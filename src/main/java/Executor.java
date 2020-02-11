import models.Account;
import models.Profile;
import models.Querier;

import java.sql.SQLException;

public class Executor {
    private static final String ACCOUNTS = "dev_profiles_db.public.accounts";
    private static final String PROFILES = "dev_profiles_db.public.profiles";

    public static void start() {
        DbUtil dbUtil = new DbUtil();

        Querier rona = new Account(1001, "Tetyana", "Bakhmat", "Lviv",
                "Female", "rona_t");
        Querier ronaProfile = new Profile(1001, "shovkova", "master",
                "Fairy Tales", "Cursor", "magician");


        dbUtil.insertData(ACCOUNTS, rona);
        dbUtil.insertData(PROFILES, ronaProfile);

        try {
            dbUtil.read(ACCOUNTS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbUtil.update(ACCOUNTS, "city", "Lviv", "Tel Aviv", 1001);
        dbUtil.delete(PROFILES, 1001);
    }
}
