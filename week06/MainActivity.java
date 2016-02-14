package jack.week06;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private void setUpListeners() {

        ProgressBar pb = (ProgressBar) findViewById(R.id.progress_bar);
        pb.setVisibility(View.INVISIBLE);


        Button createButton = (Button) findViewById(R.id.create_b);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateFileTask().execute(new File(getFilesDir(), "numbers.txt"));
            }
        });

        Button loadButton = (Button) findViewById(R.id.load_b);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadFileTask().execute(new File(getFilesDir() + "/" + "numbers.txt"));
            }
        });

        Button clearButton = (Button) findViewById(R.id.clear_b);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ClearListViewTask().execute();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setUpListeners();
    }

    private class CreateFileTask extends AsyncTask {

        private ProgressBar pb;

        public CreateFileTask() {
            pb = (ProgressBar) findViewById(R.id.progress_bar);
        }

        @Override
        protected Void doInBackground(Object[] params) {
            File file = (File) params[0];
            BufferedWriter bw = null;
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                bw = new BufferedWriter(fw);

                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(250);
                    float percentage = ((float) i / (float) 10) * 100;
                    publishProgress(new Float(percentage).intValue());
                    bw.write(i + "\n");
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            pb.setProgress((Integer) values[0]);
        }

        @Override
        protected void onPostExecute(Object o) {

            pb.setVisibility(View.INVISIBLE);
            super.onPostExecute(o);
        }
    }

    private class LoadFileTask extends AsyncTask {

        private ProgressBar pb;
        private ListView lv;
        private ArrayAdapter<String> arrayAdapter;

        public LoadFileTask() {
            pb = (ProgressBar) findViewById(R.id.progress_bar);
            lv = (ListView) findViewById(R.id.number_list);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            File file = (File) params[0];
            FileInputStream fis = null;
            BufferedReader br = null;
            try {
                fis = new FileInputStream(file);
                br = new BufferedReader(new InputStreamReader(fis));
                String line = null;

                ArrayList<String> list = new ArrayList<String>();
                int i = 1;
                while ((line = br.readLine()) != null) {

                    Thread.sleep(250);
                    float percentage = ((float) i++ / (float) 10) * 100;
                    publishProgress(new Float(percentage).intValue());
                    list.add(line);
                }

                arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.text_black, list);
            } catch (InterruptedException | IOException e) {
                System.out.println(file.getAbsolutePath() + " not found.");
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            pb.setProgress((Integer) values[0]);
        }

        @Override
        protected void onPostExecute(Object o) {

            lv.setAdapter(arrayAdapter);
            pb.setVisibility(View.INVISIBLE);
            super.onPostExecute(o);
        }
    }

    private class ClearListViewTask extends AsyncTask {

        private ProgressBar pb;
        private ListView lv;
        private ArrayAdapter<String> arrayAdapter;

        public ClearListViewTask() {
            pb = (ProgressBar) findViewById(R.id.progress_bar);
            lv = (ListView) findViewById(R.id.number_list);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(250);
                    float percentage = ((float) i / (float) 10) * 100;
                    publishProgress(new Float(percentage).intValue());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            pb.setProgress((Integer) values[0]);
        }

        @Override
        protected void onPostExecute(Object o) {
            lv.setAdapter(null);
            pb.setVisibility(View.INVISIBLE);
            super.onPostExecute(o);
        }
    }
}