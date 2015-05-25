package uhuhuhu.tabtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends FragmentActivity {

    private HomeFragment homeFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(findViewById(R.id.content_fragment) != null) {
            if (savedInstanceState != null) {
                return;
            }
            loadAllFragments();

        }
    }

    private void loadAllFragments() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction t = fm.beginTransaction();

        if(homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        t.add(R.id.content_fragment, homeFragment);
        t.commit();
    }

    public void showSlider(View view) {
        ViewGroup p1View = (ViewGroup) view.getParent();
        ViewGroup p2View = (ViewGroup) p1View.getParent();
        RelativeLayout childLayout = (RelativeLayout) p1View.findViewById(R.id.feedback_slider_layout);

        if(childLayout.getVisibility() == View.GONE) {
            for (int i = 0; i < p2View.getChildCount(); ++i) {
                RelativeLayout childLayout2 = (RelativeLayout) p2View.getChildAt(i).findViewById(R.id.feedback_slider_layout);
                childLayout2.setVisibility(View.GONE);
                childLayout2.animate().translationY(0.0f).alpha(0.0f);
            }

            childLayout.setVisibility(View.VISIBLE);
            childLayout.setAlpha(0.0f);
            childLayout.animate().translationY(0.5f).alpha(1.0f);
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.content_fragment);

        if (currentFragment instanceof MoreFragment) {
            fm.beginTransaction().remove(currentFragment).commit();
            fm.popBackStack();
        } else if (currentFragment instanceof HomeFragment) {
            finish();
        } else {
            fm.beginTransaction().hide(FragmentHolder.getActualFragment()).commit();
        }
    }
}
