package com.bigfriedicecream.recipes.observables;

import android.content.Context;

import com.bigfriedicecream.recipes.models.RecipeDataModel;
import com.bigfriedicecream.recipes.models.RecipeResponseDataModel;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Observable;

public class RecipeRepository extends Observable {

    private static RecipeRepository instance;
    private RecipeResponseDataModel model = null;

    private RecipeRepository() {}

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public void loadList(Context c) {
        if (model == null) {
            try {
                InputStream inputStream = c.getAssets().open("recipes.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                String json = new String(buffer, "UTF-8");

                model = new GsonBuilder().create().fromJson(json, RecipeResponseDataModel.class);
                setChanged();
                notifyObservers();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            setChanged();
            notifyObservers();
        }
    }

    public Map<String, RecipeDataModel> getList() {
        return model.recipes;
    }
}
