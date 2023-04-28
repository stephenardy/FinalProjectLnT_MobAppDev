package com.example.lntfinalproject.fragmentluas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lntfinalproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LuasPersegiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LuasPersegiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText inputtedSisiNo;
    private Button calculateLuasPersegiButton;
    private TextView sisiNo, secSisiNo, hasilVolume;
    SharedPreferences sharedPref;
    CardView jawabanLayout;



    public LuasPersegiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LuasPersegiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LuasPersegiFragment newInstance(String param1, String param2) {
        LuasPersegiFragment fragment = new LuasPersegiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luas_persegi, container, false);

        inputtedSisiNo = view.findViewById(R.id.et_inputtedSisiNo);
        calculateLuasPersegiButton = view.findViewById(R.id.btn_calculateLuasPersegi);

        sisiNo = view.findViewById(R.id.tv_sisiNumber);
        secSisiNo = view.findViewById(R.id.tv_sisiNumber2);

        jawabanLayout = view.findViewById(R.id.cv_jawaban);

        hasilVolume = view.findViewById(R.id.tv_hasilLuasPersegi);


        calculateLuasPersegiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sharedPref = getActivity().getSharedPreferences("persegi", Context.MODE_PRIVATE);

                try {
                    int sisi = Integer.parseInt(inputtedSisiNo.getText().toString());

                    int luasPersegi = sisi * sisi;

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("persegi_sisi", sisi);
                    editor.apply();

                    jawabanLayout.setVisibility(View.VISIBLE);

                    sisiNo.setText(String.valueOf(sisi));
                    secSisiNo.setText(String.valueOf(sisi));

                    hasilVolume.setText(String.valueOf(luasPersegi));
                } catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Only numbers can be inputted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}