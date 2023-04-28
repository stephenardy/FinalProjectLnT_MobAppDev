package com.example.lntfinalproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.lntfinalproject.fragmentluas.LuasLingkaranFragment;
import com.example.lntfinalproject.fragmentluas.LuasPersegiFragment;
import com.example.lntfinalproject.R;
import com.example.lntfinalproject.databinding.FragmentHomeBinding;
import com.example.lntfinalproject.fragmentluas.LuasSegitigaFragment;
import com.google.android.material.tabs.TabLayout;




public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    ViewPager luas_viewPager;
    TabLayout luas_tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        luas_viewPager = root.findViewById(R.id.pagerLuas);
        luas_tabLayout = root.findViewById(R.id.tab_layoutLuas);

        LuasPagerAdapter adapter = new LuasPagerAdapter(getChildFragmentManager());

        luas_viewPager.setAdapter(adapter);

        luas_tabLayout.setupWithViewPager(luas_viewPager);

        luas_tabLayout.getTabAt(0).setText("Persegi");
        luas_tabLayout.getTabAt(1).setText("Segitiga");
        luas_tabLayout.getTabAt(2).setText("Lingkaran");

        return root;
    }

    private class LuasPagerAdapter extends FragmentPagerAdapter {

        LuasPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // Return the fragment for the corresponding tab
            switch (position) {
                case 0:
                    return new LuasPersegiFragment();
                case 1:
                    return new LuasSegitigaFragment();
                case 2:
                    return new LuasLingkaranFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}