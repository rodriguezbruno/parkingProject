package com.example.parkingproyect.mvp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;

public class FragmentView {
    private final WeakReference<Fragment> fragmentRef;

    public FragmentView(Fragment fragmentRef) {
        this.fragmentRef = new WeakReference<>(fragmentRef);
    }

    @Nullable
    public Activity getActivity() {
        if (getFragment() == null) {
            return null;
        }
        return getFragment().getActivity();
    }

    @Nullable
    public Fragment getFragment() {
        return fragmentRef.get();
    }

    @Nullable
    public Context getContext() {
        return getActivity();
    }

    @Nullable
    public FragmentManager getFragmentManager() {
        Activity activity = getActivity();
        return (activity != null) ? activity.getFragmentManager() : null;
    }

    public void showToast(String text) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }
    }
}
