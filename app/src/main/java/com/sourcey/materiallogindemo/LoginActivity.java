package com.sourcey.materiallogindemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private MobileServiceClient mClient;
    private MobileServiceTable<TodoItem> mToDoTable;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        try{
            mClient = new MobileServiceClient(
                    "https://eurekaa.azurewebsites.net",
                    this
            );}
        catch (MalformedURLException e) {
        }


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }
    public void clickMe(View notused) {
        Intent intent = new Intent(this, SignupActivity.class);
        this.startActivity(intent);
    }



    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();


            mToDoTable = mClient.getTable(TodoItem.class);
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    int flag = 0;
                    try {
                        final MobileServiceList<TodoItem> result =
                                mToDoTable.execute().get();
                        String s = " ";
                        for (TodoItem item : result) {
                            //Log.i(TAG, "Read object with ID " + item.id);
                            s = item.email;
                            if(s.equals("am@fs.com")) {
                                flag = 1;
                                break;
                            }

                        }
                        if(flag==0){
                        Toast.makeText(getBaseContext(), "User ID incorrect!! ", Toast.LENGTH_LONG).show();
                            onLoginFailed();

                        }

                    } catch (Exception exception) {
                        //createAndShowDialog(exception, "Error");
                    }
                    return null;
                }
            }.execute();



        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();




        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        //finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed!", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        TextInputLayout til = (TextInputLayout) findViewById(R.id.text_input_layout);


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            til.setErrorEnabled(true);
            til.setError("Enter a valid email address.");
           // _emailText.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            valid = false;
        } else {

            til.setError(null);
        }
        TextInputLayout till = (TextInputLayout) findViewById(R.id.password_input_layout);
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            till.setErrorEnabled(true);
            till.setError("4-10 alphanumeric characters.");
            //_passwordText.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            valid = false;
        } else {
            till.setError(null);
        }

        return valid;
    }
}
