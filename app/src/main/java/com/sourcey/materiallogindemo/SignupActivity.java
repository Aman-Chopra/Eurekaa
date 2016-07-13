package com.sourcey.materiallogindemo;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private static final int REQUEST_SIGNUP = 0;
    private MobileServiceClient mClient;
    //private MobileServiceTable<TodoItem> mToDoTable;

    @Bind(R.id.input_name) EditText _nameText;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_signup) Button _signupButton;
    @Bind(R.id.link_login) TextView _loginLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        try{
            mClient = new MobileServiceClient(
                    "https://eurekaa.azurewebsites.net",
                    this
            );}
        catch (MalformedURLException e) {
        }

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    signup();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                //finish();
            }
        });
    }

    public void signup() throws InterruptedException {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        final int[] flag = new int[1];


        TodoItem item = new TodoItem();
        item.text = name;
        item.email = email;
        item.password = password;


        flag[0] = 0;
        mClient.getTable(TodoItem.class).insert(item, new TableOperationCallback<TodoItem>() {
            public void onCompleted(TodoItem entity, Exception exception, ServiceFilterResponse response) {
                if (exception == null) {
                    Toast.makeText(getBaseContext(), "Account created successfully", Toast.LENGTH_LONG).show();
                    flag[0] = 1;
                    // Insert succeeded
                } else {
                    //Toast.makeText(getBaseContext(), "Insertion failed, please retry!", Toast.LENGTH_LONG).show();
                    // Insert failed
                }
            }
        });








        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        if (flag[0] == 1)
                            onSignupSuccess();
                        else
                          onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 7000);
    }


    public void onSignupSuccess() {

        _signupButton.setEnabled(true);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Sign-up failed\nMay be Network Issues.!", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        TextInputLayout nil = (TextInputLayout) findViewById(R.id.name_input_layout);
        TextInputLayout eil = (TextInputLayout) findViewById(R.id.email_input_layout);
        TextInputLayout pil = (TextInputLayout) findViewById(R.id.passw_input_layout);

        if (name.isEmpty() || name.length() < 3) {
             nil.setErrorEnabled(true);
             nil.setError("At least 3 characters.");
            //_nameText.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
             valid = false;
        } else {
             nil.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
             eil.setErrorEnabled(true);
             eil.setError("Enter a valid email address.");
            //_emailText.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
             valid = false;
        } else {
             eil.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
             pil.setErrorEnabled(true);
             pil.setError("4-10 alphanumeric characters.");
             valid = false;
        } else {
             pil.setError(null);
        }

        return valid;
    }
}