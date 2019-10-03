package es.iessaladillo.pedrojoya.pr01.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.iessaladillo.pedrojoya.pr01.R;
import es.iessaladillo.pedrojoya.pr01.bmi.BmiCalculator;
import es.iessaladillo.pedrojoya.pr01.utils.SoftInputUtils;

public class MainActivity extends AppCompatActivity {

    private BmiCalculator bmiCalculator = new BmiCalculator();
    private EditText txtWeight;
    private EditText txtHeight;
    private Button btnReset;
    private Button btnCalculate;
    private TextView lblResult;
    private ImageView imgBmi;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setupViews();
    }

    private void setupViews() {
        txtWeight = findViewById(R.id.txtWeight);
        txtHeight = findViewById(R.id.txtHeight);
        btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(v -> resetButtonListener());
        btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(v -> calculatorButtonListener());
        lblResult = findViewById(R.id.lblResult);
        imgBmi = findViewById(R.id.imgBmi);
    }

    private void calculatorButtonListener() {
        float weight = validateInput(txtWeight, getString(R.string.main_invalid_weight));
        float height = validateInput(txtHeight, getString(R.string.main_invalid_height));
        if (weight > 0 && height > 0){
            calculateMass(weight, height);
        }
    }

    private float validateInput(EditText txt, String errorText) {
        float num;
        if (String.valueOf(txt.getText()).equals("") || String.valueOf(txt.getText()).equals(".")) {
            txt.setError(errorText);
            txt.requestFocus();
            return -1;
        }
        num = Float.parseFloat(String.valueOf(txt.getText()));
        if (num <= 0) {
            txt.setError(errorText);
            txt.requestFocus();
            return -1;
        }
        return num;
    }

    private void calculateMass(float weight, float height) {
        String type = "";
        float result = bmiCalculator.calculateBmi(weight, height);
        switch (bmiCalculator.getBmiClasification(result)) {
            case LOW_WEIGHT:
                type = getString(R.string.main_low);
                imgBmi.setImageResource(R.drawable.underweight);
                break;
            case NORMAL_WEIGHT:
                type = getString(R.string.main_normal);
                imgBmi.setImageResource(R.drawable.normal_weight);
                break;
            case OVERWWEIGHT:
                type = getString(R.string.main_overweight);
                imgBmi.setImageResource(R.drawable.overweight);
                break;
            case OBESITY_GRADE_1:
                type = getString(R.string.main_obesity1);
                imgBmi.setImageResource(R.drawable.obesity1);
                break;
            case OBESITY_GRADE_2:
                type = getString(R.string.main_obesity2);
                imgBmi.setImageResource(R.drawable.obesity2);
                break;
            case OBESITY_GRADE_3:
                type = getString(R.string.main_obesity3);
                imgBmi.setImageResource(R.drawable.obesity3);
                break;
        }
        lblResult.setText(String.format(getString(R.string.main_bmi), result, type));
        SoftInputUtils.hideKeyboard(this.btnCalculate);
    }

    private void resetButtonListener() {
        txtWeight.setText("");
        txtWeight.setError(null);
        txtHeight.setText("");
        txtHeight.setError(null);
        lblResult.setText("");
        imgBmi.setImageResource(R.drawable.bmi);
        SoftInputUtils.hideKeyboard(this.btnReset);
    }

}
