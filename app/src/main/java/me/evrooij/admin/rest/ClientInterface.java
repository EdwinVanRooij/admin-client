package me.evrooij.admin.rest;

import me.evrooij.admin.data.Feedback;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by eddy
 * Date: 11-12-16.
 */

public interface ClientInterface {
    // Feedback
    @GET("/feedback")
    Call<List<Feedback>> getAllFeedback();

    @DELETE("/feedback/{id}")
    Call<ResponseMessage> deleteFeedback(@Path("id") int id);
}
