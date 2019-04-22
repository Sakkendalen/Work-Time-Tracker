package fi.tuni.work_time_tracker;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for setuping fragmentlayout tabs for mainactivity.
 *
 * @author      Saku Tynjala saku.tynjala@tuni.fi
 * @version     0.3
 * @since       0.2
 */

public class SectionsPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    /**
     * Method for adding fragment tabs for Layout.
     *
     * @param fragment saved to fragment List.
     * @param title Saved to fragment Title List.
     */
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    /**
     * Basic constructor.
     *
     * @param fm Fragmentmanager passed to super method.
     */
    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Method for getting Fragment Title from mFragmentTitleList.
     *
     * @param position Title position from list.
     * @return CharSequence of Title.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    /**
     * Method for Getting Fragment form mFragmentList.
     *
     * @param position Fragment position from List.
     * @return Fragment from FragmentList.
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * Method for getting count of Fragments in list.
     *
     * @return int size of list.
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}