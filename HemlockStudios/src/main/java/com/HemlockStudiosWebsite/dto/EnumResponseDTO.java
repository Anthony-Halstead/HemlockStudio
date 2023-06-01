package com.HemlockStudiosWebsite.dto;

public class EnumResponseDTO {
    private String[] categories;
    private String[] subcategories;
    private String[] sizes;
    public String[] getCategories() {
        return categories;
    }
    public void setCategories(String[] categories) {
        this.categories = categories;
    }
    public String[] getSubcategories() {
        return subcategories;
    }
    public void setSubcategories(String[] subcategories) {
        this.subcategories = subcategories;
    }
    public String[] getSizes() {
        return sizes;
    }
    public void setSizes(String[] sizes) {
        this.sizes = sizes;
    }

    
}
