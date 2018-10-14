package somethingmonkey.hackduke;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import somethingmonkey.hackduke.Impute.Imputer;
import somethingmonkey.hackduke.Impute.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDataFragment extends Fragment {

    private int sex;
    private int smoke1;
    private int smoke2;
    private int alcohol1;
    private int alcohol2;
    private int dm;
    private int c677t1;
    private int c677t2;

    private double fa;
    private double hcy;
    private double bmi;
    private double sbp;
    private double dbp;
    private double tcho;
    private double age;

    TextInputEditText bmiInput;
    TextInputEditText sbpInput;
    TextInputEditText dbpInput;
    TextInputEditText tchoInput;
    TextInputEditText dmInput;
    TextInputEditText faInput;
    TextInputEditText ncyInput;

    public MyDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bmiInput = getView().findViewById(R.id.BMI);





        RadioGroup smokeGroup = getView().findViewById(R.id.smoke);

        smokeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    switch (checkedRadioButton.getText().toString()){
                        case "I never smoke":
                            smoke1 = 0;
                            smoke2 = 1;
                        case "I used to smoke":
                            smoke1 = 1;
                            smoke2 = 1;
                        case "I currently smoke":
                            smoke1 = 1;
                            smoke2 = 0;
                    }

                    Log.d("Created", checkedRadioButton.getText().toString());
                }
            }
        });


        RadioGroup drinkGroup = getView().findViewById(R.id.drink);
        drinkGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    switch (checkedRadioButton.getText().toString()){
                        case "I'm not a drinker":
                            alcohol1 = 0;
                            alcohol2 = 1;
                        case "I used to be a drinker":
                            alcohol1 = 1;
                            alcohol2 = 1;
                        case "I am a drinker":
                            alcohol1 = 1;
                            alcohol2 = 0;
                    }
                    // Changes the textview's text to "Checked: example radiobutton text"
                    Log.d("Created", checkedRadioButton.getText().toString());
                }
            }
        });


        RadioGroup genomeGroup = getView().findViewById(R.id.genome);
        genomeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    switch (checkedRadioButton.getText().toString()){
                        case "CC":
                            c677t1 = 0;
                            c677t2 = 1;
                        case "CT":
                            c677t1 = 1;
                            c677t2 = 1;
                        case "TT":
                            c677t1 = 1;
                            c677t2 = 0;
                    }
                    // Changes the textview's text to "Checked: example radiobutton text"
                    Log.d("Created", checkedRadioButton.getText().toString());
                }
            }
        });




    }

    // handle save button pressed
    public void saveData(View v){
        Map myMap = new Map();
        Imputer imputer = new Imputer(myMap);
        double[] result = imputer.impute(new String[][]{{}});

    }

}
