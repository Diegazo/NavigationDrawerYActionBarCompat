package com.castraservilia;

/**
 * Created by diego on 3/08/13.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Proximo_partido extends Fragment {

    public static Fragment newInstance(Context context) {
        Proximo_partido fragment_p_p = new Proximo_partido();

        return fragment_p_p;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.proximo_partido, null);
        return root;
    }

}
