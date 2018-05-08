package com.example.usuario.app_navegation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.app_navegation.Entities.Item;
import com.example.usuario.app_navegation.Entities.ItemCategory;
import com.example.usuario.app_navegation.SpinnerAdapters.CategoriesSpinnerAdapter;
import com.example.usuario.app_navegation.SpinnerAdapters.ItemsSpinnerAdapter;
import com.example.usuario.app_navegation.buisnessLogic.blItemCateogry.IItemCateogryService;
import com.example.usuario.app_navegation.buisnessLogic.blItemCateogry.IItemService;
import com.example.usuario.app_navegation.buisnessLogic.blItemCateogry.ItemCateogryService;
import com.example.usuario.app_navegation.buisnessLogic.blItemCateogry.ItemService;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link itemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link itemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class itemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private OnAddItemFragmentListListner AddItemFragmentListListner;



    private OnFragmentInteractionListener mListener;

    public itemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment itemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static itemFragment newInstance(String param1, String param2) {
        itemFragment fragment = new itemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_item,container,false);
        final Spinner spinner = view.findViewById(R.id.spinner_category);




        Button btnShowItems = view.findViewById(R.id.btn_show_list);
        btnShowItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItemFragmentListListner.onAddItemListListener();

            }
        });





        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final GetTask getTask = new GetTask();
        getTask.execute();
        loadPost();


        Button btnSave = getView().findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int udesId = 1;

                Spinner categories = getView().findViewById(R.id.spinner_category);
                int cate = ((ItemCategory)categories.getSelectedItem()).getId();

                TextView txtName = getView().findViewById(R.id.item_text_name);
                String name = txtName.getText().toString();

                TextView txtDes = getView().findViewById(R.id.item_text_description);
                String des = txtDes.getText().toString();

                TextView txtR = getView().findViewById(R.id.item_text_referencial_value);
                Double ref = Double.valueOf(txtR.getText().toString());

                CheckBox txtT = getView().findViewById(R.id.item_check_tradable);
                Boolean tra = txtT.isChecked();

                //POSTEANDO EN LA API
                Item item = new Item();
                item.setuserId(udesId);
                item.setCategoryId(cate);
                item.setName(name);
                item.setDescription(des);
                item.setReferencialValue(ref);
                item.setTradable(tra);

                PostTask postTask = new PostTask();
                postTask.execute(item);

            }
        });


        Button btnSaveSugar = getView().findViewById(R.id.btn_save_and_sugar);
        btnSaveSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int udesId = 1;

                Spinner categories = getView().findViewById(R.id.spinner_category);
                int cate = ((ItemCategory)categories.getSelectedItem()).getId();

                TextView txtName = getView().findViewById(R.id.item_text_name);
                String name = txtName.getText().toString();

                TextView txtDes = getView().findViewById(R.id.item_text_description);
                String des = txtDes.getText().toString();

                TextView txtR = getView().findViewById(R.id.item_text_referencial_value);
                Double ref = Double.valueOf(txtR.getText().toString());

                CheckBox txtT = getView().findViewById(R.id.item_check_tradable);
                Boolean tra = txtT.isChecked();

                //guardando en el fono
                Item item = new Item();
                item.setuserId(udesId);
                item.setCategoryId(cate);
                item.setName(name);
                item.setDescription(des);
                item.setReferencialValue(ref);
                item.setTradable(tra);
                item.setStatus("New");
                item.save();

                loadPost();
            }
        });


    }

    public void loadPost(){
        Spinner spinner = getView().findViewById(R.id.Spinner_items_list);
        ItemsSpinnerAdapter adapter = new ItemsSpinnerAdapter(getContext(),android.R.layout.simple_spinner_item,Item.listAll(Item.class));
        spinner.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }




        if (context instanceof OnAddItemFragmentListListner) {
            AddItemFragmentListListner = (OnAddItemFragmentListListner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddItemListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public  void fillSpinner(ArrayList<ItemCategory>categories){
        Spinner spinner = getView().findViewById(R.id.spinner_category);

        //ArrayList<ItemCategory>categories = new ArrayList<>();
        CategoriesSpinnerAdapter categoriesSpinnerAdapter = new CategoriesSpinnerAdapter(getContext(),android.R.layout.simple_spinner_item,categories);
        spinner.setAdapter(categoriesSpinnerAdapter);


    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class GetTask extends AsyncTask<String,ArrayList<ItemCategory>,ArrayList<ItemCategory>>
    {


        @Override
        protected ArrayList<ItemCategory> doInBackground(String... strings) {

            IItemCateogryService iItemCateogryService = new ItemCateogryService();
            ArrayList<ItemCategory>categories = iItemCateogryService.getCategories();
            return categories;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemCategory> itemCategories) {
            super.onPostExecute(itemCategories);
            fillSpinner(itemCategories);
        }
    }

class PostTask extends AsyncTask<Item,Void,Void>{
    @Override
    protected Void doInBackground(Item... items) {

        IItemService iItemService = new ItemService();
        iItemService.saveItem(items[0]);
        return  null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(getContext(), "EXITO PAPPU :V",Toast.LENGTH_LONG);
    }
}


    public interface OnAddItemFragmentListListner {
        // TODO: Update argument type and name
        void onAddItemListListener();
    }


}
