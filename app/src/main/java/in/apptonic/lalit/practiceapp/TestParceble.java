package in.apptonic.lalit.practiceapp;

import android.os.AsyncTask;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by lalitkumarsonawane on 25/06/17.
 */

public class TestParceble extends AsyncTask<String, Integer, Long> {


    @Override
    protected Long doInBackground(String... strings) {
        HttpsURLConnection connection = null;
        InputStream input = null;
        OutputStream output = null;

        try {
            URL url = new URL(strings[0]);
            connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() != HttpsURLConnection.HTTP_OK) {

                return Long.valueOf("Server returned HTTP "
                        + connection.getResponseCode() + " " + connection.getResponseMessage());
            }

            int fileLength = connection.getContentLength();
            input = connection.getInputStream();
            output = new FileOutputStream("/sdcard/downloadedfile.pdf");

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                if (isCancelled()) {

                    input.close();
                    return null;
                }

                total += count;
                if (fileLength > 0)
                    publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (output != null)
                    output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (connection != null)
                connection.disconnect();
        }

        return null;
    }
}
