package com.example.task05;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class AnimateFragment extends Fragment {

    private static final String TEXT = "text";

    private String mText;
    private OnFragmentInteractionListener mListener;
    private EditText editTextFragment;
    private Button buttonFragment;

    public AnimateFragment() {
    }

    @org.jetbrains.annotations.NotNull
    public static AnimateFragment newInstance(String text) {
        AnimateFragment fragment = new AnimateFragment();
        Bundle args = new Bundle();
        args.putString(TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString(TEXT);
        }
    }
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.animate_fragment, container, false);
        buttonFragment = view.findViewById(R.id.button_fragment);
        editTextFragment = view.findViewById(R.id.edittext_fragment);
        editTextFragment.setText(mText);
        editTextFragment.requestFocus();
        buttonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendBackText = editTextFragment.getText().toString();
                sendBack(sendBackText);
            }
        });
        return view;
    }

    public void sendBack(String sendBackText) {
        if (mListener != null) {
            mListener.onFragmentInteraction(sendBackText);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String sendBackText);
    }
}
