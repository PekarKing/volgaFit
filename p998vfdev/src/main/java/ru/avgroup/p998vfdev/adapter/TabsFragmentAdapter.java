package ru.avgroup.p998vfdev.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import ru.avgroup.p998vfdev.fragment.AbstractTabFragment;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////с этими вкладками я ебался неделю//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Context context;
    private Map<Integer, AbstractTabFragment> tabs;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabsMap(context);

    }

    public CharSequence getPageTitle ( int position){
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }


    @Override
    public int getCount() {
        return 7;
    }

    private void initTabsMap(Context context) {

        tabs = new HashMap<>();
        AbstractTabFragment.getPos(0);
        tabs.put(0, AbstractTabFragment.MondayFragment.getInstance(context));
        AbstractTabFragment.getPos(1);
        tabs.put(1, AbstractTabFragment.TuesdayFragment.getInstance(context));
        AbstractTabFragment.getPos(2);
        tabs.put(2, AbstractTabFragment.WednesdayFragment.getInstance(context));
        AbstractTabFragment.getPos(3);
        tabs.put(3, AbstractTabFragment.ThursdayFragment.getInstance(context));
        AbstractTabFragment.getPos(4);
        tabs.put(4, AbstractTabFragment.FridayFragment.getInstance(context));
        AbstractTabFragment.getPos(5);
        tabs.put(5, AbstractTabFragment.SaturdayFragment.getInstance(context));
        AbstractTabFragment.getPos(6);
        tabs.put(6, AbstractTabFragment.SundayFragment.getInstance(context));
    }

}
