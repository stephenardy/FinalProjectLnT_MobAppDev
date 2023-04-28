package com.example.lntfinalproject.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.lntfinalproject.R;
import com.example.lntfinalproject.databinding.FragmentDashboardBinding;
import com.example.lntfinalproject.fragmentvolume.VolumeBalokFragment;
import com.example.lntfinalproject.fragmentvolume.VolumePiramidFragment;
import com.example.lntfinalproject.fragmentvolume.VolumeTabungFragment;
import com.google.android.material.tabs.TabLayout;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    ViewPager volume_viewPager;
    TabLayout volume_tabLayout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        volume_viewPager = root.findViewById(R.id.pagerVolume);
        volume_tabLayout = root.findViewById(R.id.tab_layoutVolume);

        DashboardFragment.VolumePagerAdapter adapterVolume = new DashboardFragment.VolumePagerAdapter(getChildFragmentManager());

        volume_viewPager.setAdapter(adapterVolume);

        volume_tabLayout.setupWithViewPager(volume_viewPager);

        volume_tabLayout.getTabAt(0).setText("Balok");
        volume_tabLayout.getTabAt(1).setText("Piramid");
        volume_tabLayout.getTabAt(2).setText("Tabung");

        return root;
    }

    private class VolumePagerAdapter extends FragmentPagerAdapter {

        VolumePagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // Return the fragment for the corresponding tab
            switch (position) {
                case 0:
                    return new VolumeBalokFragment();
                case 1:
                    return new VolumePiramidFragment();
                case 2:
                    return new VolumeTabungFragment();
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