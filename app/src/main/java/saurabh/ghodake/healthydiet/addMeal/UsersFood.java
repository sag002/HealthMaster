package saurabh.ghodake.healthydiet.addMeal;

import android.graphics.Bitmap;

public class UsersFood {

    private static int User3foodCount = 0;

    public String foodname,quantity,category;
    public String calorie;
    public Bitmap icon;

    public UsersFood(){}

    public UsersFood(String foodname,String calorie,Bitmap icon){
        this.foodname = foodname;
        this.calorie = calorie;
        this.icon = icon;
    }

    public static void incrementFoodCount() {
        User3foodCount = User3foodCount +1;
    }

    public static int getFoodCount() {
        return User3foodCount;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getFoodname() {
        return foodname;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getCalorie() {
        return calorie;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public UsersFood(String foodname, String quantity, String category){
        this.foodname = foodname;
        this.quantity = quantity;
        this.category = category;

    }
}
