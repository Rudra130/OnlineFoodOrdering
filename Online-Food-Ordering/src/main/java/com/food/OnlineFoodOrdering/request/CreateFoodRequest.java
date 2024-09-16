package com.food.OnlineFoodOrdering.request;

import java.util.List;

import com.food.OnlineFoodOrdering.Model.Category;
import com.food.OnlineFoodOrdering.Model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;

    private Long price;
    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarin;
    private boolean seasional;

    private List<IngredientsItem> ingredients;




}
