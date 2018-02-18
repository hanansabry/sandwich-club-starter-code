package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView mAlsoKnownAsContentTv = findViewById(R.id.alsoKnownAs_content);
        TextView mPlaceOfOriginContentTv = findViewById(R.id.placeOfOrigin_content);
        TextView mDescriptionContentTv = findViewById(R.id.description_content);
        TextView mIngredientsContentTv = findViewById(R.id.ingredients_content);

        //get alsoKnownAs Names Value
        StringBuilder alsoKnownAs = new StringBuilder();
        List alsoKnownAsList = sandwich.getAlsoKnownAs();
        for (int i = 0; i < alsoKnownAsList.size(); i++) {
            alsoKnownAs.append(i + 1).append(".").append(alsoKnownAsList.get(i)).append(". ");
        }

        mAlsoKnownAsContentTv.setText(alsoKnownAs.toString());
        mPlaceOfOriginContentTv.setText(sandwich.getPlaceOfOrigin());
        mDescriptionContentTv.setText(sandwich.getDescription());

        //get ingredients value
        StringBuilder ingredients = new StringBuilder();
        List ingredientsList = sandwich.getIngredients();
        for (int i = 0; i < ingredientsList.size(); i++) {
            ingredients.append(i + 1).append(".").append(ingredientsList.get(i)).append(". ");
        }
        mIngredientsContentTv.setText(ingredients.toString());
    }
}
