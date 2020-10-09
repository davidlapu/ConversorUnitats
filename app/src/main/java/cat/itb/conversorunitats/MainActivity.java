package cat.itb.conversorunitats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner dropDown;
    ArrayAdapter<CharSequence> adapter;
    EditText inputData;
    Button calcular;
    TextView resTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropDown = findViewById(R.id.spinnerConversio);
        inputData = findViewById(R.id.inputUnitat);
        calcular = findViewById(R.id.calcularButton);
        resTextView = findViewById((R.id.resTextView));

        //Spinner
        adapter = ArrayAdapter.createFromResource(this,
                R.array.options, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(adapter);
        dropDown.setOnItemSelectedListener(new SpinnerActivity());

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = String.valueOf(inputData.getText());
                Double inputNumber, res;

                if (!input.isEmpty()) {
                    inputNumber = Double.parseDouble(input);
                    res = calcularConversio(inputNumber, dropDown.getSelectedItemPosition());
                    resTextView.setText(String.format("%.2f", res));
                    resTextView.setVisibility(View.VISIBLE);
                    //Toast.makeText(MainActivity.this, String.valueOf(inputNumber), Toast.LENGTH_SHORT).show();
                }
                //
            }
        });
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            /*
            parent.getItemAtPosition(pos);
            Toast.makeText(MainActivity.this, String.valueOf(pos), Toast.LENGTH_SHORT).show();
             */
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    public double calcularConversio(double input, int tipus) {
        double res;

        switch (tipus) {
            case 0 : res = input * 2.54; break;
            case 1 : res = input / 1.094; break;
            case 2 : res = input * 1.609; break;
            case 3 : res = input / 2.54; break;
            //case 4
            default : res = 0;
        }

        return res;
    }
}
