package be.hcpl.android.restclient;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by hcpl on 01/09/14.
 */
public class ApiClient {

    private static final String SERVER_PATH = "http://protected-citadel-1353.herokuapp.com";

    private static ItemApiInterface itemService;

    public static ItemApiInterface getItemApiClient() {
        if (itemService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(SERVER_PATH)
                    .build();

            itemService = restAdapter.create(ItemApiInterface.class);
        }

        return itemService;
    }

    public interface ItemApiInterface {

        // get json list of items example
        // TODO allow data scrolling
        @GET("/items/json")
        void getItems(Callback<List<Item>> callback);

        // posting a new item example
        @FormUrlEncoded
        @POST("/items/create")
        void createItem(@Field("title") String first, @Field("description") String description, @Field("checked") boolean checked);
    }

}
