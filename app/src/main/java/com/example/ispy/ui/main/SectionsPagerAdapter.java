package main;


import android.content.Context;
import java.util.*;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.ListFragment;

import com.example.ispy.R;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mfragmentList = new ArrayList<>();
    private final List<String> mfragmentTitleList = new ArrayList<>();


    public void addfragment(Fragment fragment,String title)
    {
        mfragmentList.add(fragment);
        mfragmentTitleList.add(title);
    }


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);

    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mfragmentTitleList.get(position);
    }
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return mfragmentList.get(position);
    }



    @Override
    public int getCount() {
        // Show 2 total pages.
        return mfragmentList.size();
    }
}