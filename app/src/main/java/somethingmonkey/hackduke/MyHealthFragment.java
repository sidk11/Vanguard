package somethingmonkey.hackduke;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.db.chart.model.LineSet;
import com.db.chart.view.LineChartView;

import java.util.List;

public class MyHealthFragment extends Fragment {
    DataDataBase myDatabase;
    LineChartView linechart;
    LineSet lineset;

    public MyHealthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_health, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDatabase = Room.databaseBuilder(getContext(),
                DataDataBase.class, "database-name").build();
        // call findViewByID here
        linechart = getView().findViewById(R.id.linechart);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("database created","");
                Log.d("datasize",String.valueOf(myDatabase.daoAccess().getSize()));
                /*List<DataEntity> data = myDatabase.daoAccess().getAll();
                int dbsize = myDatabase.daoAccess().getSize();
                int numtoget = dbsize;
                if(dbsize>10){
                    numtoget = 10;
                }
                String[] labels = new String[numtoget];
                float[] values = new float[numtoget];*/
                int numtoget = 10;
                String[] labels = new String[numtoget];
                float[] values = new float[numtoget];
                for(int i=numtoget-1;i>=0;i--){
                    labels[numtoget-i-1]="lol";
                    values[numtoget-i-1]=i;
                    /*labels[numtoget-i-1]=data.get(i).getDate();
                    values[numtoget-i-1]=data.get(i).getHealth_score();*/
                }
                lineset = new LineSet(labels,values);
                linechart.addData(lineset);
                linechart.show();
            }
        }) .start();
    }
}
