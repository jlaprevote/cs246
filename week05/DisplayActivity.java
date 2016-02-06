package jack.week05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {
/*

*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
/*

*/
        Bundle extras = getIntent().getExtras();
        String display_all = extras.getString("toDisplay");

        TextView textView = (TextView)findViewById(R.id.textView1);

        textView.setText(display_all);
    }

    public void input (View v) {
        Intent intent = new Intent(this,InputActivity.class);
        startActivity(intent);
    }
}
