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

public class Temporada_completa extends Fragment {

    public static Fragment newInstance(Context context) {
        Temporada_completa fragment_t_c = new Temporada_completa();

        return fragment_t_c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.temporada_completa, null);
        return root;
    }

}
