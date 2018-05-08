package com.example.usuario.app_navegation.buisnessLogic.blItemCateogry;

import com.example.usuario.app_navegation.Entities.Item;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ItemService implements IItemService {
    @Override
    public Boolean saveItem(Item item) {
        Boolean result = false;

        item.setStatus("Created");
        try{
            URL apiUrl =
                    new URL("http://vmdev1.nexolink.com:90/TruequeAppAPI/api/Items");

            HttpURLConnection httpURLConnection = (HttpURLConnection) apiUrl.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setRequestProperty("Accept","application/json");

            String itemJson = item.toJson();

            httpURLConnection.getOutputStream().write(itemJson.getBytes());

            if (httpURLConnection.getResponseCode()==200){

                result = true;
            }else{

            }
            httpURLConnection.disconnect();


        }catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }
}
