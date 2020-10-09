package cat.itb.conversorunitats;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostraCalcul();
            }
        });

        inputData.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 66) mostraCalcul();
                return false;
            }
        });
    }

    public void mostraCalcul() {
        String input = String.valueOf(inputData.getText());
        double inputNumber, res;

        if (!input.isEmpty()) {
            inputNumber = Double.parseDouble(input);
            res = calcularConversio(inputNumber, dropDown.getSelectedItemPosition());
            resTextView.setText(String.format("%.2f", res));
            resTextView.setVisibility(View.VISIBLE);
        }
    }


    public double calcularConversio(double input, int tipus) {
        double res;

        switch (tipus) {
            case 0 : res = input * 2.54; break;
            case 1 : res = input / 1.094; break;
            case 2 : res = input * 1.609; break;
            case 3 : res = input / 2.54; break;
            case 4 : res = input * 1.094; break;
            case 5 : res = input / 1.609; break;

            default : res = 0;
        }

        return res;
    }
}
