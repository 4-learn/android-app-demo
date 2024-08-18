package com.example.bmicalpart_4;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity
{
    private double result_BMI = -1.0D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.result_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = this.getIntent().getExtras();
        //身高
        double height = Double.parseDouble(bundle.getString("KEY_HEIGHT"))/100;
        //體重
        double weight = Double.parseDouble(bundle.getString("KEY_WEIGHT"));
        //計算出BMI值
        double BMI = weight / (height*height);
        result_BMI = BMI;

        ((TextView)findViewById(R.id.result)).setText(String.valueOf(BMI));
    }

    @Override
    public void onBackPressed()
    {
        Bundle bundle = new Bundle();
        bundle.putString("BMI", String.valueOf(this.result_BMI));

        Intent intent = new Intent();
        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }
}