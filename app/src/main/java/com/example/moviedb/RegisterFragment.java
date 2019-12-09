package com.example.moviedb;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private Button register;
    private EditText editName;
    private EditText editPass;
    private EditText editConfPass;
    DatabaseHelper db;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        register = (Button) v.findViewById(R.id.register1_btn);
        editName = (EditText) v.findViewById(R.id.edit_name1);
        editPass = (EditText) v.findViewById(R.id.edit_password1);
        editConfPass = (EditText) v.findViewById(R.id.edit_confirm_password);
        db = new DatabaseHelper(getContext());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = editName.getText().toString();
                String s2 = editPass.getText().toString();
                String s3 = editConfPass.getText().toString();
                if(s1.equals("") || s2.equals("") || s3.equals("")) {
                    Toast.makeText(getContext(), "Fields are empty!", Toast.LENGTH_SHORT).show();
                } else {
                    if(s2.equals(s3)) {
                        boolean chkData = db.checkName(s1);
                        if (chkData) {
                            boolean insert = db.insert(s1, s2);
                            if (insert) {
                                Toast.makeText(getContext(), "Registered successfully!", Toast.LENGTH_SHORT).show();
                                FragmentTransaction fr = getFragmentManager().beginTransaction();
                                fr.replace(R.id.fragment_container, new LoginFragment()).addToBackStack(null).commit();
                            }
                        } else {
                            Toast.makeText(getContext(), "Name already exists!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Password don't match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }

}
