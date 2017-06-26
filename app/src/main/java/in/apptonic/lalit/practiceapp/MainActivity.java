package in.apptonic.lalit.practiceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    TestParceble testParceble = new TestParceble();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void download(View view) {

        testParceble.execute("https://www.iso.org/iso/annual_report_2009.pdf");

    }
}
