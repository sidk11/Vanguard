package somethingmonkey.hackduke;


import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProgressBar progressBar = getView().findViewById(R.id.progressBar);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        int score = prefs.getInt("Score", 0);
        Log.d("Score", String.valueOf(score));
        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar, progressBar.getProgress(), score);
        anim.setDuration(1000);
        progressBar.startAnimation(anim);

        TextView progressTextView = getView().findViewById(R.id.progressTextView);
        progressTextView.setText(score+"%");
    }
}
