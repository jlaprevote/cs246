package jack.week05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    Button mButton;

    EditText mEditBook;
    EditText mEditChapter;
    EditText mEditVerse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void display(View v) {
        Intent intent = new Intent(InputActivity.this, DisplayActivity.class);

        mEditBook = (EditText)findViewById(R.id.editText);
        mEditChapter = (EditText)findViewById(R.id.editText2);
        mEditVerse = (EditText)findViewById(R.id.editText3);

        String textBook = mEditBook.getText().toString();
        String textChapter = mEditChapter.getText().toString();
        String textVerse = mEditVerse.getText().toString();

        String display_all = "Your favorite scripture is: " + textBook +
                " " + textChapter + ":" + textVerse;

        intent.putExtra("toDisplay",display_all);

        startActivity(intent);
    }

}
