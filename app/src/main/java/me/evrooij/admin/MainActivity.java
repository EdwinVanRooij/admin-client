package me.evrooij.admin;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.evrooij.admin.adapters.FeedbackAdapter;
import me.evrooij.admin.data.Feedback;
import me.evrooij.admin.data.FeedbackManager;
import me.evrooij.admin.rest.ResponseMessage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lvFeedback)
    ListView listView;

    ArrayList<Feedback> data;
    FeedbackAdapter adapter;

    private FeedbackManager feedbackManager;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        feedbackManager = new FeedbackManager(this);

        try {
            new Thread(() -> {
                List<Feedback> feedbackList = feedbackManager.getAllFeedback();

                runOnUiThread(() ->
                        refreshListView(feedbackList));

            }).start();

            listView.setOnItemClickListener((adapter1, v, position, id) -> {
                Feedback feedback = (Feedback) adapter1.getItemAtPosition(position);

                ReturnBoolean r = result -> {
                    if (result) {
                        new Thread(() -> {
                            ResponseMessage message = feedbackManager.deleteFeedback(feedback.getId());

                            MainActivity.this.runOnUiThread(() -> {
                                adapter.remove(feedback);

                                Snackbar.make(listView, message.toString(), Snackbar.LENGTH_LONG).show();
                            });
                        }).start();
                    }
                };
                new ConfirmDialog(MainActivity.this, r).show(MainActivity.this.getResources().getString(R.string.feedback_delete_warning));
            });
        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
            Toast.makeText(this, String.format("Exception: %s", e.getMessage()), Toast.LENGTH_SHORT).show();
        }

    }

    private void refreshListView(List<Feedback> feedbackList) {
        // Construct the data source
        data = new ArrayList<>(feedbackList);
        // Create the adapter to convert the array to views
        adapter = new FeedbackAdapter(this, data);

        runOnUiThread(() ->
                // Attach the adapter to a ListView
                listView.setAdapter(adapter));
    }
}
