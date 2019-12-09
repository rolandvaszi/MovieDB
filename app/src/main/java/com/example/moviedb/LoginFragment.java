package com.example.moviedb;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private Button register;
    private Button login;
    private EditText editName;
    private EditText editPass;
    DatabaseHelper db;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        db = new DatabaseHelper(getContext());
        editName = (EditText) v.findViewById(R.id.edit_name);
        editPass = (EditText) v.findViewById(R.id.edit_password);
        register = (Button) v.findViewById(R.id.register_btn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new RegisterFragment()).addToBackStack(null).commit();
            }
        });
        login = (Button) v.findViewById(R.id.log_in_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = editName.getText().toString();
                String s2 = editPass.getText().toString();
                boolean chkData = db.checkData(s1, s2);
                if(chkData) {
                    Toast.makeText(getContext(), "Login successfull!", Toast.LENGTH_SHORT).show();
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new MainFragment()).addToBackStack(null).commit();
                } else {
                    Toast.makeText(getContext(), "Wrong name or password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
}
