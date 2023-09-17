package cis3334.tipcalculatormvp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Poor example of manually implementing a MVP model in Android.
 * You should not set up apps this way, use ViewModel instead
 * This version will often fail on screen rotation
 */
public class MainActivity extends AppCompatActivity  implements updateViewInterface {

    EditText editTextBill;
    EditText editTextNumInParty;
    TextView textViewTotalTip;
    TextView textViewTipPerPerson;
    Button buttonCalculate;
    CheckBox checkBoxService;
    SeekBar seekBarNumInParty;
    double tipPercent = 0.15;
    CalculatorInterface tipCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBill = findViewById(R.id.editTextBillAmount);
        editTextNumInParty = findViewById(R.id.editTextNumInParty);
        textViewTotalTip = findViewById(R.id.textViewTotalTip);
        textViewTipPerPerson = findViewById(R.id.textViewTipPerPerson);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        checkBoxService = findViewById(R.id.checkBoxService);
        seekBarNumInParty = findViewById(R.id.seekBarNumInParty);
        tipCalc = new TipCalculator(this);                  // instatiate the tip calculator

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double bill = Double.parseDouble(editTextBill.getText().toString());
                Integer numInParty = Integer.parseInt(editTextNumInParty.getText().toString());

                tipCalc.calculate(bill, numInParty, checkBoxService.isChecked());

                //textViewTotalTip.setText(NumberFormat.getCurrencyInstance().format(tip));
                //textViewTipPerPerson.setText(NumberFormat.getCurrencyInstance().format(tip / numInParty));
            }
        });

        seekBarNumInParty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editTextNumInParty.setText(Integer.toString(progress + 1));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void updateView(Double totalTip, Double tipPerPerson) {
        textViewTotalTip.setText(NumberFormat.getCurrencyInstance().format(totalTip));
        textViewTipPerPerson.setText( NumberFormat.getCurrencyInstance().format(tipPerPerson));
    }
}