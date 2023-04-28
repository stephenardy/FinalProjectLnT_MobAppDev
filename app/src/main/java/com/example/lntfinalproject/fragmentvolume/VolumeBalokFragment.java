package com.example.lntfinalproject.fragmentvolume;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lntfinalproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VolumeBalokFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VolumeBalokFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText inputtedPanjangNo, inputtedLebarNo, inputtedTinggiNo;
    private Button calculateVolumeBalokButton;
    private TextView panjangNo, lebarNo, tinggiNo, hasilVolume;
    SharedPreferences sharedPref;
    CardView jawabanLayout;

    public VolumeBalokFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VolumeBalokFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VolumeBalokFragment newInstance(String param1, String param2) {
        VolumeBalokFragment fragment = new VolumeBalokFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume_balok, container, false);

        inputtedPanjangNo = view.findViewById(R.id.et_inputtedPanjangNo);
        inputtedLebarNo = view.findViewById(R.id.et_inputtedLebarNo);
        inputtedTinggiNo = view.findViewById(R.id.et_inputtedTinggiNo);
        calculateVolumeBalokButton = view.findViewById(R.id.btn_calculateVolumeBalok);

        panjangNo = view.findViewById(R.id.tv_panjangNumber);
        lebarNo = view.findViewById(R.id.tv_lebarNumber);
        tinggiNo = view.findViewById(R.id.tv_tinggiNumber);

        jawabanLayout = view.findViewById(R.id.cv_jawaban);

        hasilVolume = view.findViewById(R.id.tv_hasilVolumeBalok);


        calculateVolumeBalokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sharedPref = getActivity().getSharedPreferences("balok", Context.MODE_PRIVATE);

                try {
                    int panjang = Integer.parseInt(inputtedPanjangNo.getText().toString());
                    int lebar = Integer.parseInt(inputtedLebarNo.getText().toString());
                    int tinggi = Integer.parseInt(inputtedTinggiNo.getText().toString());

                    int volumeBalok = panjang*lebar*tinggi;

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("balok_panjang", panjang);
                    editor.putInt("balok_lebar", lebar);
                    editor.putInt("balok_tinggi", tinggi);
                    editor.apply();

                    jawabanLayout.setVisibility(View.VISIBLE);

                    panjangNo.setText(String.valueOf(panjang));
                    lebarNo.setText(String.valueOf(lebar));
                    tinggiNo.setText(String.valueOf(tinggi));

                    hasilVolume.setText(String.valueOf(volumeBalok));
                } catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Only numbers can be inputted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}