package com.example.lntfinalproject.fragmentvolume;

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
 * Use the {@link VolumeTabungFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VolumeTabungFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText inputtedJariNo, inputtedTinggiNo;
    private Button calculateVolumeTabungButton;
    private TextView jariNo, tinggiNo, hasilVolume;
    SharedPreferences sharedPref;
    CardView jawabanLayout;


    public VolumeTabungFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VolumeTabungFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VolumeTabungFragment newInstance(String param1, String param2) {
        VolumeTabungFragment fragment = new VolumeTabungFragment();
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
        View view = inflater.inflate(R.layout.fragment_volume_tabung, container, false);

        inputtedJariNo = view.findViewById(R.id.et_inputtedJariNo);
        inputtedTinggiNo = view.findViewById(R.id.et_inputtedTinggiNo);
        calculateVolumeTabungButton = view.findViewById(R.id.btn_calculateVolumeTabung);

        jariNo = view.findViewById(R.id.tv_jariNumber);
        tinggiNo = view.findViewById(R.id.tv_tinggiNumber);

        jawabanLayout = view.findViewById(R.id.cv_jawaban);

        hasilVolume = view.findViewById(R.id.tv_hasilVolumeTabung);


        calculateVolumeTabungButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sharedPref = getActivity().getSharedPreferences("tabung", Context.MODE_PRIVATE);

                try {
                    int jari_jari = Integer.parseInt(inputtedJariNo.getText().toString());
                    int tinggi = Integer.parseInt(inputtedTinggiNo.getText().toString());

                    double volumeTabung = Math.PI*Math.pow(jari_jari, 2)*tinggi;

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("tabung_jarijari", jari_jari);
                    editor.putInt("tabung_tinggi", tinggi);
                    editor.apply();

                    jawabanLayout.setVisibility(View.VISIBLE);

                    jariNo.setText(String.valueOf(jari_jari));
                    tinggiNo.setText(String.valueOf(tinggi));

                    hasilVolume.setText(String.valueOf(volumeTabung));
                } catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Only numbers can be inputted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}