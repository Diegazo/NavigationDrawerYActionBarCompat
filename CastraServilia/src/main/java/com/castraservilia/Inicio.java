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

public class Inicio extends Fragment {

    public static Fragment newInstance(Context context) {
        Inicio fragment_inicio = new Inicio();

        return fragment_inicio;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.inicio, null);
        return root;
    }

}
