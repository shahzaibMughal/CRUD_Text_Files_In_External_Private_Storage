package com.shahzaib.crudtextfilesinexternalprivatestorage;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadTextFile extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_text_file);
        textView = findViewById(R.id.textView);

        //NOTE:  we know, we have stored the file in external storage private directory
        File directory = getExternalFilesDir(CONSTANTS.FOLDER_NAME);
        File file = new File(directory, CONSTANTS.TEXT_FILE_NAME);

        String data = getDataFromTextFile(file);
        textView.setText(data);
    }

    private String getDataFromTextFile(File file) {
        if (file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(file);

                StringBuilder data = new StringBuilder();
                int read = -1;
                read = fin.read();
                while (read != -1) {
                    data.append((char) read);
                    read = fin.read();
                }
                Toast.makeText(this, "Data Successfully Retrieve", Toast.LENGTH_SHORT).show();
                return data.toString();

            } catch (IOException e) {
                e.printStackTrace();
                CONSTANTS.SHOW_LOG("Exception Occur: " + e.toString());
                return "Exception Occur: " + e.toString();
            }
        } else {
            return "File Not Exists";
        }
    }
}
