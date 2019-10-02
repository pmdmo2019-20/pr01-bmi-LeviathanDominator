package es.iessaladillo.pedrojoya.pr01.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

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
        if (validateInput()) {
            float weight = (float) Double.parseDouble(String.valueOf(txtWeight.getText()));
            float height = (float) Double.parseDouble(String.valueOf(txtHeight.getText()));
            if (isNotZero(weight, height)) {
                calculateMass(weight, height);
            }
        }
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

    private boolean validateInput() {
        boolean validate = true;
        if (String.valueOf(txtWeight.getText()).equals("") || String.valueOf(txtWeight.getText()).equals(".")) {
            txtWeight.setError(String.format(getString(R.string.main_invalid_weight), "empty"));
            validate = false;
        }
        if (String.valueOf(txtHeight.getText()).equals("") || String.valueOf(txtHeight.getText()).equals(".")) {
            txtHeight.setError(String.format(getString(R.string.main_invalid_height), "empty"));
            validate = false;
        }
        return validate;
    }

    private boolean isNotZero(float weight, float height) {
        boolean validate = true;
        if (weight == 0) {
            txtWeight.setError(String.format(getString(R.string.main_invalid_weight), "zero"));
            validate = false;
        }
        if (height == 0) {
            txtHeight.setError(String.format(getString(R.string.main_invalid_height), "zero"));
            validate = false;
        }
        return validate;
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
