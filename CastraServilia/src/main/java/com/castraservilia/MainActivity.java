package com.castraservilia;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity
{
    //Declaración de variables del Navigation Drawer + ActionBarCompact
    private int selection = 0;
    private int oldSelection = -1;

    private String[] names = null;  //Array con los nombres de las opciones del NavDraw
    private String[] classes = null;//Array con los nombres de los paquetes de los fragments

    private ActionBarDrawerToggle drawerToggle = null;
    private DrawerLayout drawer = null;
    private ListView navList = null;

    private static final String OPENED_KEY = "OPENED_KEY";
    private SharedPreferences prefs = null;
    private Boolean opened = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Código del ND + ABC. No tocar.

        names = getResources().getStringArray(R.array.opciones_navigation_drawer);
        classes = getResources().getStringArray(R.array.fragments_navigation_drawer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getSupportActionBar()
                .getThemedContext(), android.R.layout.simple_list_item_1, names);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navList = (ListView) findViewById(R.id.drawer);
        navList.setAdapter(adapter);
        drawerToggle = new ActionBarDrawerToggle(this, drawer,
                R.drawable.ic_drawer, R.string.open, R.string.close)
        {
            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
                updateContent();
                supportInvalidateOptionsMenu();
                if(opened != null && opened == false)
                {
                    opened = true;
                    if(prefs != null)
                    {
                        Editor editor = prefs.edit();
                        editor.putBoolean(OPENED_KEY, true);
                        //editor.apply() daba error en APIs < 10
                        editor.commit();
                    }
                }
            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.app_name);
                supportInvalidateOptionsMenu();
            }
        };
        drawer.setDrawerListener(drawerToggle);

        navList.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int pos, long id)
            {
                selection = pos;
                drawer.closeDrawer(navList);
            }
        });

        updateContent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                prefs = getPreferences(MODE_PRIVATE);
                opened = prefs.getBoolean(OPENED_KEY, false);
                if(opened == false)
                {
                    drawer.openDrawer(navList);
                }
            }

        }).start();

        //Fin del código del ND + ABC
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    //Los botones de la zona del ActionBarCompact. Se definen en el menu/main.xml
    //Aquí se tienen que ir poniendo para hacerlos visibles.
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        if(drawer != null && navList != null)
        {
            menu.findItem(R.id.action_web).setVisible(!drawer.isDrawerOpen(navList));
        }

        return super.onPrepareOptionsMenu(menu);
    }

    //Este método es el encargado de llamar a los fragments cuando se pulsa sobre el ND
    //Llama al fragment cuya posición en el vector es la misma que la del vector de nombres
    private void updateContent()
    {
        getSupportActionBar().setTitle(names[selection]);
        if (selection != oldSelection)
        {
            FragmentTransaction tx = getSupportFragmentManager()
                    .beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, classes[selection]));
            tx.commit();
            oldSelection = selection;
        }
    }


    //Menú superior derecho (el de siempre)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Acciones sobre el menú superior derecho e iconos de la ActionBarCompact
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        switch(item.getItemId()) {

            case R.id.action_settings:
                Toast.makeText(this, "Has pulsado sobre 'Opciones'", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_web:
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.castraservilia.com"));
                startActivity(viewIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}