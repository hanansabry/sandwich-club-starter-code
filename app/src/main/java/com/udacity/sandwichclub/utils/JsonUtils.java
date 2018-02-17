package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        final String NAME = "name";
        final String MAIN_NAME = "mainName";
        final String ALSO_KNOWN_AS = "alsoKnownAs";
        final String PLACE_OF_ORIGIN = "placeOfOrigin";
        final String DESCRIPTION = "description";
        final String IMAGE = "image";
        final String INGREDIENTS = "ingredients";

        JSONObject sandwichObj;
        try {
            //Get the JSON object representing the sandwich
            sandwichObj = new JSONObject(json);

            //Get the name of the sandeich
            JSONObject sandwichName = sandwichObj.getJSONObject(NAME);
            //Get the MainName of the sandwich and assign it to the Sandwich Object
            String mainName = sandwichName.getString(MAIN_NAME);
            sandwich.setMainName(mainName);

            //Get the alsoKnownAs of the sandwich and assign it to the Sandwich Object
            JSONArray alsoKnownAsArr = sandwichName.getJSONArray(ALSO_KNOWN_AS);
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsArr.length(); i++) {
                alsoKnownAs.add(alsoKnownAsArr.getString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownAs);

            //Get placeOfOrigin and assign it to the Sandwich Object
            String placeOfOrigin = sandwichObj.getString(PLACE_OF_ORIGIN);
            sandwich.setPlaceOfOrigin(placeOfOrigin);

            //Get description and assign it to the Sandwich Object
            String description = sandwichObj.getString(DESCRIPTION);
            sandwich.setDescription(description);

            //get image
            String image = sandwichObj.getString(IMAGE);
            sandwich.setImage(image);

            //get ingredients array
            JSONArray ingredientsARR = sandwichObj.getJSONArray(INGREDIENTS);
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsARR.length(); i++){
                ingredients.add(ingredientsARR.getString(i));
            }
            sandwich.setIngredients(ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return sandwich;
    }
}
