package com.santirodriguezlorenzo.arbofs.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by Santi on 03/06/2015.
 */
public interface ChangeFragment {
    void onChangeFragment(int identifier, boolean addToBackStack, Fragment fragment);
}
