package cn.edu.bistu.cs.cnse.edu.bistu.cs.se.file;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
import java.io.OutputStream;
 import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private final static String FileName = "myFile";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button read = (Button) findViewById(R.id.btn_read);
        Button write = (Button) findViewById(R.id.btn_write);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in=null;
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File file = Environment.getExternalStorageDirectory();
                        File myFile = new File(file.getCanonicalPath() + "/" + FileName);
                        FileInputStream fileInputStream = new FileInputStream(myFile);
                        in = new BufferedInputStream(fileInputStream);
                        int c;
                        StringBuilder stringBuilder = new StringBuilder("");
                        try {
                            while ((c = in.read()) != -1) {
                                stringBuilder.append((char) c);
                            }
                            Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                        } finally {
                            if (in != null)
                                in.close();
                        }
                    }}
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }

        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out=null;
                try {
                    FileOutputStream fileOutputStream=openFileOutput(FileName,MODE_PRIVATE);
                    out=new BufferedOutputStream(fileOutputStream);
                    String content="Infomation";
                    try {
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                    }
                    finally {
                        if(out!=null)
                            out.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
