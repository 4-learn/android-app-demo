package com.example.bmicalpart_4;

import android.view.View;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 ActivityResultLauncher
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Bundle bundle = data.getExtras();
                            if (bundle != null) {
                                String bmi = bundle.getString("BMI");
                                ((TextView) findViewById(R.id.result)).setText("Result: " + bmi);
                            }
                        }
                    }
                }
        );

        getSupportActionBar().setTitle("MyBMI");

        Button button = findViewById(R.id.button);
        button.setOnClickListener(calcBMI);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            openOptionsDialog();
        } else if (id == R.id.toast) {
            openOptionsToast();
        } else if (id == R.id.new_activity) {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("KEY_HEIGHT", ((EditText) findViewById(R.id.height)).getText().toString());
            bundle.putString("KEY_WEIGHT", ((EditText) findViewById(R.id.weight)).getText().toString());
            intent.putExtras(bundle);

            // 使用 ActivityResultLauncher 啟動新活動
            resultLauncher.launch(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void openOptionsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("關於Android BMI")
                .setMessage("Android BMI 計算")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    private void openOptionsToast() {
        Toast popup = Toast.makeText(this, "BMI計算機", Toast.LENGTH_SHORT);
        popup.show();
    }

    private View.OnClickListener calcBMI = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");
            EditText fieldheight = findViewById(R.id.height);
            EditText fieldweight = findViewById(R.id.weight);

            try {
                double height = Double.parseDouble(fieldheight.getText().toString()) / 100;   // 身高
                double weight = Double.parseDouble(fieldweight.getText().toString());         // 體重
                double BMI = weight / (height * height);                                      // BMI 值

                // 結果
                TextView result = findViewById(R.id.result);
                result.setText(getText(R.string.bmi_result) + nf.format(BMI));

                // 建議
                TextView fieldsuggest = findViewById(R.id.suggest);
                if (BMI > 25.0D)         // 太重了
                    fieldsuggest.setText(R.string.advice_heavy);
                else if (BMI < 20.0D)    // 太輕了
                    fieldsuggest.setText(R.string.advice_light);
                else                    // 剛剛好
                    fieldsuggest.setText(R.string.advice_average);
            } catch (Exception error) {
                Toast msg = Toast.makeText(MainActivity.this, "要先輸入身高體重", Toast.LENGTH_SHORT);
                msg.show();
            }
        }
    };
}