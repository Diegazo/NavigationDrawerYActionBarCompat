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

public class Jornada_completa extends Fragment {

    public static Fragment newInstance(Context context) {
        Jornada_completa fragment_j_c = new Jornada_completa();

        return fragment_j_c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.jornada_completa, null);
        return root;
    }

}
