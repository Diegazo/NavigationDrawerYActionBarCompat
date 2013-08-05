package com.castraservilia;

/**
 * Created by diego on 5/08/13.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Clasificacion extends Fragment {

    public static Fragment newInstance(Context context) {
        Clasificacion fragment_c = new Clasificacion();

        return fragment_c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.clasificacion, null);
        return root;
    }

}
