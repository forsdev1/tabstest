package uhuhuhu.tabtest;

import android.app.Fragment;

final public class FragmentHolder {
    static public Fragment actualFragment = null;

    private FragmentHolder() {
    }

    static public Fragment getActualFragment() {
        return actualFragment;
    }

    static public void setActualFragment(Fragment af) {
        actualFragment = af;
    }
}
