package me.evrooij.admin.data;

import android.content.Context;
import me.evrooij.admin.rest.ClientInterface;
import me.evrooij.admin.rest.ResponseMessage;
import me.evrooij.admin.rest.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * Author: eddy
 * Date: 15-12-16.
 */

public class FeedbackManager {

    private Context context;

    public FeedbackManager(Context context) {
        this.context = context;

    }

    public List<Feedback> getAllFeedback() {
        // Create a rest adapter
        ClientInterface client = ServiceGenerator.createService(context, ClientInterface.class);

        // Fetch and print a list of the contributors to this library.
        Call<List<Feedback>> call = client.getAllFeedback();

        // Execute the call
        Response<List<Feedback>> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return response.body();
    }

    public ResponseMessage deleteFeedback(int id) {
        // Create a rest adapter
        ClientInterface client = ServiceGenerator.createService(context, ClientInterface.class);

        // Fetch and print a list of the contributors to this library.
        Call<ResponseMessage> call = client.deleteFeedback(id);

        // Execute the call
        Response<ResponseMessage> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return response.body();
    }
}
