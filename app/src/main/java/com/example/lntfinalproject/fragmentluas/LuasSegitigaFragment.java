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
 * Use the {@link LuasSegitigaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LuasSegitigaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText inputtedAlasNo, inputtedTinggiNo;
    private Button calculateLuasSegitigaButton;
    private TextView alasNo, tinggiNo, hasilVolume;
    SharedPreferences sharedPref;
    CardView jawabanLayout;


    public LuasSegitigaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LuasSegitigaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LuasSegitigaFragment newInstance(String param1, String param2) {
        LuasSegitigaFragment fragment = new LuasSegitigaFragment();
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
        View view = inflater.inflate(R.layout.fragment_luas_segitiga, container, false);

        inputtedAlasNo = view.findViewById(R.id.et_inputtedAlasNo);
        inputtedTinggiNo = view.findViewById(R.id.et_inputtedTinggiNo);
        calculateLuasSegitigaButton = view.findViewById(R.id.btn_calculateLuasSegitiga);

        alasNo = view.findViewById(R.id.tv_alasNumber);
        tinggiNo = view.findViewById(R.id.tv_tinggiNumber);

        jawabanLayout = view.findViewById(R.id.cv_jawaban);

        hasilVolume = view.findViewById(R.id.tv_hasilLuasSegitiga);


        calculateLuasSegitigaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sharedPref = getActivity().getSharedPreferences("segitiga", Context.MODE_PRIVATE);

                try {
                    int alas = Integer.parseInt(inputtedAlasNo.getText().toString());
                    int tinggi = Integer.parseInt(inputtedTinggiNo.getText().toString());

                    double luasSegitiga = (1.0/2.0) * alas * tinggi;

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("segitiga_alas", alas);
                    editor.putInt("segitiga_tinggi", tinggi);
                    editor.apply();

                    jawabanLayout.setVisibility(View.VISIBLE);

                    alasNo.setText(String.valueOf(alas));
                    tinggiNo.setText(String.valueOf(tinggi));

                    hasilVolume.setText(String.valueOf(luasSegitiga));
                } catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Only numbers can be inputted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}