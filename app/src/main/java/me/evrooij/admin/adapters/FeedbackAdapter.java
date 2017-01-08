package me.evrooij.admin.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import me.evrooij.admin.R;
import me.evrooij.admin.data.Feedback;

import java.util.ArrayList;

/**
 * Author: eddy
 * Date: 20-11-16.
 */

public class FeedbackAdapter extends ArrayAdapter<Feedback> {
    public FeedbackAdapter(Context context, ArrayList<Feedback> feedback) {
        super(context, 0, feedback);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Feedback feedback = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_feedback, parent, false);
        }
        // Lookup view for data population
        TextView tvType = (TextView) convertView.findViewById(R.id.tvType);
        TextView tvMessage = (TextView) convertView.findViewById(R.id.tvMessage);
        TextView tvSender = (TextView) convertView.findViewById(R.id.tvSender);
        // Populate the data into the template view using the data object
        if (feedback != null) {
            tvType.setText(feedback.getType().name());
            tvMessage.setText(feedback.getMessage());
            tvSender.setText(feedback.getSender().getUsername());
        }

        // Return the completed view to render on screen
        return convertView;
    }
}

