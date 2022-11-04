package com.example.scrollingtext3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_COMMENTS = "comments";

    private TextView mCommentsView;
    private Button mAddCommentButton;
    private EditText mAddCommentText;
    private Button mAcceptCommentButton;

    private ArrayList<String> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCommentsView = findViewById(R.id.comments);

        mAddCommentButton = findViewById(R.id.buttonAddComment);
        mAddCommentButton.setOnClickListener(e -> createComment());

        mAddCommentText = findViewById(R.id.addText);
        mAddCommentText.setVisibility(View.GONE);

        mAcceptCommentButton = findViewById(R.id.buttonAcceptComment);
        mAcceptCommentButton.setVisibility(View.GONE);
        mAcceptCommentButton.setOnClickListener(e -> addComment());

        if(savedInstanceState != null) {
            comments = savedInstanceState.getStringArrayList(KEY_COMMENTS);
            int i;
            for(i=0; i<comments.size()-1; ++i) {
                mCommentsView.append(comments.get(i) + "\n\n");
            }
            mCommentsView.append(comments.get(i));
        } else {
            comments = new ArrayList<>();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(KEY_COMMENTS, comments);
    }

    private void createComment() {
        mAddCommentButton.setVisibility(View.GONE);
        mAddCommentText.setVisibility(View.VISIBLE);
        mAcceptCommentButton.setVisibility(View.VISIBLE);
    }

    private void addComment() {
        mAddCommentButton.setVisibility(View.VISIBLE);
        mAddCommentText.setVisibility(View.GONE);
        mAcceptCommentButton.setVisibility(View.GONE);

        String newComment = mAddCommentText.getText().toString();
        if(mCommentsView.getText().length() > 0) {
            mCommentsView.append("\n\n");
        }
        mCommentsView.append(newComment);
        comments.add(newComment);

        mAddCommentText.getText();

        mAddCommentText.setText("");
    }
}