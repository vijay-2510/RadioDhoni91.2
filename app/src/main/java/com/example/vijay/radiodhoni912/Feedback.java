package com.example.vijay.radiodhoni912;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.content.Intent.ACTION_SENDTO;
import static android.content.Intent.EXTRA_SUBJECT;
import static android.content.Intent.EXTRA_TEXT;
import static android.text.TextUtils.isEmpty;

public class Feedback extends AppCompatActivity {

    EditText feedback_edit;
    Button feedback_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedback_edit = (EditText)findViewById(R.id.feedback_edit);
        feedback_btn = (Button)findViewById(R.id.feedback_btn);

        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String feedback = feedback_edit.getText().toString();
                Intent intent = new Intent(ACTION_SENDTO);
                intent.setType("message/rfc822");
                String mailTo = "kumarvijay2510@gmail.com";
                String mailCC = "";
                String subject = "User Feedback";
                if (mailTo == null) {
                    mailTo = "";
                }
                intent.setData(Uri.parse("mailto:" + mailTo));
                if (!isEmpty(mailCC)) {
                    intent.putExtra(Intent.EXTRA_CC, new String[]{mailCC});
                }
                if (!isEmpty(subject)) {
                    intent.putExtra(EXTRA_SUBJECT, subject);
                }
                if (!isEmpty(feedback)) {
                    intent.putExtra(EXTRA_TEXT, feedback);
                }

                startActivity(Intent.createChooser(intent, "Sending"));

            }
        });

    }
}
