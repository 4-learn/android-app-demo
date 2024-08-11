package my.project;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setTitle("MyBMI");

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(calcBMI);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == R.id.action_settings) openOptionsDialog();
        else if(id == R.id.toast) openOptionsToast();

        return super.onOptionsItemSelected(item);
    }

    private void openOptionsDialog()
    {
        new AlertDialog.Builder(this)
            .setTitle("關於Android BMI")
            .setMessage("Android BMI 計算")
            .setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                }
            })
            .show();
    }

    private void openOptionsToast()
    {
        Toast popup = Toast.makeText(this, "BMI計算機", Toast.LENGTH_SHORT);
        popup.show();
    }

    private View.OnClickListener calcBMI = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            DecimalFormat nf = new DecimalFormat("0.00");
            EditText fieldheight = (EditText)findViewById(R.id.height);
            EditText fieldweight = (EditText)findViewById(R.id.weight);

            try
            {
                double height = Double.parseDouble(fieldheight.getText().toString()) / 100;   // 身高
                double weight = Double.parseDouble(fieldweight.getText().toString());         // 體重
                double BMI = weight / (height * height);                                      // BMI 值

                // 結果
                TextView result = (TextView)findViewById(R.id.result);
                result.setText(getText(R.string.bmi_result) + nf.format(BMI));

                // 建議
                TextView fieldsuggest = (TextView)findViewById(R.id.suggest);
                if(BMI > 25.0D)         // 太重了
                    fieldsuggest.setText(R.string.advice_heavy);
                else if(BMI < 20.0D)    // 太輕了
                    fieldsuggest.setText(R.string.advice_light);
                else                    // 剛剛好
                    fieldsuggest.setText(R.string.advice_average);
            }
            catch(Exception error)
            {
                Toast msg = Toast.makeText(MainActivity.this, "要先輸入身高體重", Toast.LENGTH_SHORT);

                msg.show();

                return;
            }
        }
    };
}