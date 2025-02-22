import java.util.Map;
import java.util.HashMap;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import io.appwrite.Client;
import io.appwrite.services.Account;
import io.appwrite.services.Avatars;
import io.appwrite.services.Database;
import io.appwrite.services.Functions;
import io.appwrite.services.Health;
import io.appwrite.services.Locale;
import io.appwrite.services.Storage;
import io.appwrite.services.Teams;
import io.appwrite.services.Users;

final Gson gson = new Gson();

public RuntimeResponse main(RuntimeRequest req, RuntimeResponse res) throws Exception {
    var client = new Client();

    // You can remove services you don't use
    var account = new Account(client);
    var avatars = new Avatars(client);
    var database = new Database(client);
    var functions = new Functions(client);
    var health = new Health(client);
    var locale = new Locale(client);
    var storage = new Storage(client);
    var teams = new Teams(client);
    var users = new Users(client);

    var env = req.getEnv();

    if (env == null
        || !env.containsKey("APPWRITE_FUNCTION_ENDPOINT")
        || !env.containsKey("APPWRITE_FUNCTION_API_KEY")
        || env.get("APPWRITE_FUNCTION_ENDPOINT") == null
        || env.get("APPWRITE_FUNCTION_API_KEY") == null) {
        System.out.println("Environment variables are not set. Function cannot use Appwrite SDK.");
    } else {
        client
          .setEndpoint(env.get("APPWRITE_FUNCTION_ENDPOINT"))
          .setProject(env.get("APPWRITE_FUNCTION_PROJECT_ID"))
          .setKey(env.get("APPWRITE_FUNCTION_API_KEY"));
    }

    return res.json(Map.of(
        "areDevelopersAwesome", true
    ));
}
