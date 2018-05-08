package com.example.usuario.app_navegation.buisnessLogic.blItemCateogry;

import android.util.JsonReader;

import com.example.usuario.app_navegation.Entities.Item;
import com.example.usuario.app_navegation.Entities.ItemCategory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ItemCateogryService implements IItemCateogryService{
    @Override
    public ArrayList<ItemCategory> getCategories() {
        ArrayList<ItemCategory>categories = new ArrayList<>();

        try {
            URL apiURL = new URL("http://vmdev1.nexolink.com:90/TruequeAppAPI/api/ItemCategoriesApp");

            HttpURLConnection httpURLConnection = (HttpURLConnection)apiURL.openConnection();

            if (httpURLConnection.getResponseCode()==200){

                InputStream inputStream = httpURLConnection.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");

                JsonReader jsonReader = new JsonReader(inputStreamReader);

                jsonReader.beginArray();

                while (jsonReader.hasNext()){

                    jsonReader.beginObject();
                    int id  = 0;
                    String descrip = null;

                    while (jsonReader.hasNext()){
                        String property  = jsonReader.nextName();

                        switch (property.toLowerCase()){
                            case "id": id = jsonReader.nextInt(); break;
                            case "description" : descrip =  jsonReader.nextString(); break;
                            default:jsonReader.skipValue(); break;

                        }

                    }

                    ItemCategory itemCategory = new ItemCategory(id,descrip);
                    categories.add(itemCategory);
                    jsonReader.endObject();

                }
                jsonReader.endArray();
                jsonReader.close();
                httpURLConnection.disconnect();

            }else{

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  categories;
    }
}
