package com.example.lntfinalproject.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lntfinalproject.R;
import com.example.lntfinalproject.databinding.FragmentNotificationsBinding;


public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        ImageButton minusButton, plusButton;
        Button resetButton;
        TextView countNumber;
        SharedPreferences sharedPref;
        int savedNumber;

        minusButton = rootView.findViewById(R.id.btn_minLogo);
        plusButton = rootView.findViewById(R.id.btn_addLogo);
        countNumber = rootView.findViewById(R.id.tv_countNumber);
        resetButton = rootView.findViewById(R.id.btn_reset);

        sharedPref = requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);

        savedNumber = sharedPref.getInt("number",0);

        countNumber.setText(String.valueOf(savedNumber));



        minusButton.setOnClickListener(v -> {
            // Get the current number from the TextView
            int currentNumber = Integer.parseInt(countNumber.getText().toString());
            // Increment the number
            currentNumber--;
            // Set the new number in the TextView
            countNumber.setText(String.valueOf(currentNumber));
            // Save the new count number to SharedPreferences
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("number", currentNumber);
            editor.apply();
        });

        plusButton.setOnClickListener(v -> {
            // Get the current number from the TextView
            int currentNumber = Integer.parseInt(countNumber.getText().toString());
            // Increment the number
            currentNumber++;
            // Set the new number in the TextView
            countNumber.setText(String.valueOf(currentNumber));
            // Save the new count number to SharedPreferences
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("number", currentNumber);
            editor.apply();
        });

        resetButton.setOnClickListener(v -> {
            // Reset the count number
            countNumber.setText("0");
            // Save the new count number to SharedPreferences
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("number", 0);
            editor.apply();
        });

        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}