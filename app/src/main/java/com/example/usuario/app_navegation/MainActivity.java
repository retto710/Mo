package com.example.usuario.app_navegation;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.usuario.app_navegation.Entities.Item;

public class MainActivity extends FragmentActivity implements
ItemsFragment.OnFragmentInteractionListener,
My_ItemsFragment.OnFragmentInteractionListener,
RequestFragment.OnFragmentInteractionListener,
ProfileFragment.OnFragmentInteractionListener,
itemFragment.OnFragmentInteractionListener,
My_ItemsFragment.OnAddItemListener,
itemFragment.OnAddItemFragmentListListner,
ItemFragmentList.OnListFragmentInteractionListener{

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            //setear que mensaje mostrar
            switch (item.getItemId()) {
                case R.id.navigation_items:
                    mTextMessage.setText(R.string.items_title);
                    break;
                case R.id.navigation_myItems:
                    mTextMessage.setText(R.string.title_MyItems);
                    break;
                case R.id.navigation_Request:
                    mTextMessage.setText(R.string.title_Request);
                    break;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_Profile);
                    break;
            }
            //hacer el update del gragmente
            updateFragment(item);
            return true;
        }
    };

    public void updateFragment(MenuItem item){
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_items:
                fragment = new ItemsFragment();
                break;
            case R.id.navigation_myItems:
                fragment = new My_ItemsFragment();
                break;
            case R.id.navigation_Request:
                fragment = new RequestFragment();
                break;
            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ItemsFragment itemsFragment = new ItemsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,itemsFragment).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

        //esto es para ponelro en el logcat -_-

        //Log.d("fragment interaction", uri.getPath());
    }

    //function to open a window to open a fragment which is out of the bar
    public void openItemFragment(){
        itemFragment itemFragment = new itemFragment();
        getSupportFragmentManager().beginTransaction().
              replace(R.id.fragment_container,itemFragment).addToBackStack("back to my items pappu ;V").commit();
    }


    public void openItemFragmentList(){
        ItemFragmentList itemFragment = new ItemFragmentList();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container,itemFragment).addToBackStack("back to my items pappu ;V").commit();
    }

    public void onAddItemListener(){
        this.openItemFragment();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onAddItemListListener() {
        this.openItemFragmentList();
    }

    @Override
    public void onListFragmentInteraction(Item item) {
        Log.d("Item cargado con exito:", item.getName());
    }
}
