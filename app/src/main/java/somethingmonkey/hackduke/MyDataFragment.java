package somethingmonkey.hackduke;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import somethingmonkey.hackduke.Impute.Imputer;
import somethingmonkey.hackduke.Impute.Map;
import somethingmonkey.hackduke.Model.CSPPTModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDataFragment extends Fragment {

    private int sex;
    private int smoke1;
    private int smoke2;
    private int alcohol1;
    private int alcohol2;
//    private int dm;
    private String c677t1 = "NA";
    private String c677t2 = "NA";
    private String dm = "0";

    Context mContext;
    TextInputEditText bmiInput;
    TextInputEditText sbpInput;
    TextInputEditText dbpInput;
    TextInputEditText tchoInput;
//    TextInputEditText dmInput;
    Switch dmSwitch;
    TextInputEditText faInput;
    TextInputEditText ncyInput;
    FloatingActionButton saveButton;

    public MyDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lifestyle_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bmiInput = getView().findViewById(R.id.BMI);
        sbpInput = getView().findViewById(R.id.SBP);
        dbpInput = getView().findViewById(R.id.DBP);
        tchoInput = getView().findViewById(R.id.TCHO);
//        dmInput = getView().findViewById(R.id.DM);
        dmSwitch = getView().findViewById(R.id.DM);

        faInput = getView().findViewById(R.id.FA);
        ncyInput = getView().findViewById(R.id.NCY);
        saveButton = getView().findViewById(R.id.saveButton);



        dmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                        dm = "1";
                } else {
                       dm = "0";
                }
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map myMap = new Map();
                Imputer imputer = new Imputer(myMap);

                String[] vars = new String[15];
                double[] imputedVars = new double[15];

                String fa = faInput.getText().toString().length()==0?"NA":faInput.getText().toString();
                vars[13] = fa;
                String hcy = ncyInput.getText().toString().length()==0?"NA":ncyInput.getText().toString();
                vars[14] = hcy;
                String bmi = bmiInput.getText().toString().length()==0?"NA":bmiInput.getText().toString();
                vars[6] = bmi;
                String sbp = sbpInput.getText().toString().length()==0?"NA":sbpInput.getText().toString();
                vars[7] = sbp;
                String dbp = dbpInput.getText().toString().length()==0?"NA":dbpInput.getText().toString();
                vars[8] = dbp;
                String tchp = tchoInput.getText().toString().length()==0?"NA":tchoInput.getText().toString();
                vars[9] = tchp;

                vars[10] = dm;

//                String dm = dmInput.getText().toString().length()==0?"NA":dmInput.getText().toString();
//                vars[10] = dm;



                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                String age = prefs.getString("Age", null);
                vars[0] = age;
                String sex = prefs.getString("Sex", null);
                vars[1] = sex;

                vars[2] = String.valueOf(smoke1);
                vars[3] = String.valueOf(smoke2);
                vars[4] = String.valueOf(alcohol1);
                vars[5] = String.valueOf(alcohol2);
                vars[11] = c677t1;
                vars[12] = c677t2;

                double[] result = imputer.impute(new String[][]{{
                        age, sex, String.valueOf(smoke1), String.valueOf(smoke2),
                        String.valueOf(alcohol1), String.valueOf(alcohol2),
                        bmi, sbp, dbp, tchp, dm,
                        c677t1, c677t2, fa, hcy
                }});

                List<Integer> imputedIndex = new ArrayList<>();

                for(int i = 0; i < vars.length; i++){
                    if(vars[i].equals("NA")){
                        imputedIndex.add(i);
                    }
                }

                int index = (int) result[0];

                for(int i = 0; i < imputedVars.length; i++){
                    if(!vars[i].equals("NA")) imputedVars[i] = Double.parseDouble(vars[i]);
                }

                for(int i: imputedIndex){
                    imputedVars[i] = myMap.getEntry(index, i);
                }

                CSPPTModel model = new CSPPTModel();
                double risk = model.calculateRisk(imputedVars);
                int score = (int)(risk*100);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("Score", score);
                editor.commit();
                Log.d("Risk", String.valueOf(risk));
            }
        });

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
                            smoke2 = 0;
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
                            alcohol1 = 1;
                            alcohol2 = 1;
                        case "I used to be a drinker":
                            alcohol1 = 0;
                            alcohol2 = 0;
                        case "I am a drinker":
                            alcohol1 = 0;
                            alcohol2 = 1;
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
                            c677t1 = "1";
                            c677t2 = "0";
                        case "CT":
                            c677t1 = "1";
                            c677t2 = "1";
                        case "TT":
                            c677t1 = "0";
                            c677t2 = "1";
                    }
                    // Changes the textview's text to "Checked: example radiobutton text"
                    Log.d("Created", checkedRadioButton.getText().toString());
                }
            }
        });

    }


}
