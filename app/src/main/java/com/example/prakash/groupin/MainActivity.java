package com.example.prakash.groupin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.net.*;
public class MainActivity extends AppCompatActivity {


    EditText username, password;
    Button login_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        login_btn = (Button) findViewById(R.id.btn_login);
        login_btn.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {

            try {
                sendPOST();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    final String POST_PARAMS = "name";

    private void sendPOST() throws IOException {


        String requestURL = "http://groupin.orgfree.com/Authenticate.php";
        /*try {
            HttpUtility.sendGetRequest(requestURL);
            String[] response = HttpUtility.readMultipleLinesRespone();
            for (String line : response) {
                Log.i("Line->",line);
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
        //HttpUtility.disconnect();


        Log.i("Line->","=====================================");
            String pass=password.getText().toString();
            String usr=username.getText().toString();
        // test sending POST request
        Map<String, String> params = new HashMap<String, String>();
        requestURL = "http://groupin.orgfree.com/Authenticate.php";
        //params.put("email", "prakashps26@gmail.com");
        params.put("password", pass);
        params.put("name",usr);

        try {
            HttpUtility.sendPostRequest(requestURL, params);
            String[] response = HttpUtility.readMultipleLinesRespone();
            int i=0;
            for (String line : response) {
                System.out.println(line);
            }
            if(response.length>=1){
                System.out.println(response[0]);
                if (response[0].contains("#User ID not present in database#") || response[0].contains("#Incorrect Password#") ) {
                    Log.i("Error:",response[0]);
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

                    dlgAlert.setMessage("wrong password or username");
                    dlgAlert.setTitle("Error Message...");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                }
                else
                {   //When login Successfull
                    callActivity();
                }

            }
            else
                Log.i("Error","No Response");


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        HttpUtility.disconnect();
    }
        private void callActivity(){
            Intent intent=new Intent(this,Category.class);
            startActivity(intent);
        }

}






class HttpUtility {

    /**
     * Represents an HTTP connection
     */
    private static HttpURLConnection httpConn;

    /**
     * Makes an HTTP request using GET method to the specified URL.
     *
     * @param requestURL
     *            the URL of the remote server
     * @return An HttpURLConnection object
     * @throws IOException
     *             thrown if any I/O error occurred
     */
    public static HttpURLConnection sendGetRequest(String requestURL)
            throws IOException {
        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setRequestMethod("GET");
        httpConn.setDoInput(true); // true if we want to read server's response
        httpConn.setDoOutput(false); // false indicates this is a GET request

        return httpConn;
    }

    /**
     * Makes an HTTP request using POST method to the specified URL.
     *
     * @param requestURL
     *            the URL of the remote server
     * @param params
     *            A map containing POST data in form of key-value pairs
     * @return An HttpURLConnection object
     * @throws IOException
     *             thrown if any I/O error occurred
     */
    public static HttpURLConnection sendPostRequest(String requestURL,
                                                    Map<String, String> params) throws IOException {
        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);


//        httpConn.setRequestProperty("Cookie",cookies);
        httpConn.setRequestProperty("User-Agent", "Mozilla");
        httpConn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        httpConn.addRequestProperty("Referer", "google.com");
        httpConn.setDoInput(true); // true indicates the server returns response
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        //String cookies = httpConn.getHeaderField("Set-Cookie");
        //  System.out.println("Cookies:"+cookies);
        StringBuffer requestParams = new StringBuffer();
        if (params != null && params.size() > 0) {

            httpConn.setDoOutput(true); // true indicates POST request

            // creates the params string, encode them using URLEncoder
            Iterator<String> paramIterator = params.keySet().iterator();
            boolean a=true;
            while (paramIterator.hasNext()) {
                if(a)
                {
                    a=false;

                }
                else
                    requestParams.append("&");
                String key = paramIterator.next();
                String value = params.get(key);
                requestParams.append(URLEncoder.encode(key, "UTF-8"));
                requestParams.append("=").append(
                        URLEncoder.encode(value, "UTF-8"));

            }

            // sends POST data
            OutputStreamWriter writer = new OutputStreamWriter(
                    httpConn.getOutputStream());
            writer.write(requestParams.toString());
            writer.flush();
        }

        return httpConn;
    }

    /**
     * Returns only one line from the server's response. This method should be
     * used if the server returns only a single line of String.
     *
     * @return a String of the server's response
     * @throws IOException
     *             thrown if any I/O error occurred
     */
    public static String readSingleLineRespone() throws IOException {
        InputStream inputStream = null;
        if (httpConn != null) {
            inputStream = httpConn.getInputStream();
        } else {
            throw new IOException("Connection is not established.");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));

        String response = reader.readLine();
        reader.close();

        return response;
    }

    /**
     * Returns an array of lines from the server's response. This method should
     * be used if the server returns multiple lines of String.
     *
     * @return an array of Strings of the server's response
     * @throws IOException
     *             thrown if any I/O error occurred
     */
    public static String[] readMultipleLinesRespone() throws IOException {
        InputStream inputStream = null;
        if (httpConn != null) {
            inputStream = httpConn.getInputStream();
        } else {
            throw new IOException("Connection is not established.");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        List<String> response = new ArrayList<String>();

        String line = "";
        while ((line = reader.readLine()) != null) {
            response.add(line);
        }
        reader.close();

        return (String[]) response.toArray(new String[0]);
    }

    /**
     * Closes the connection if opened
     */
    public static void disconnect() {
        if (httpConn != null) {
            httpConn.disconnect();
        }
    }
}