package com.nikvay.cnp_master.utils;

import com.nikvay.cnp_master.model.ProductModel;

public interface SelectedProductInterface {
    public void addSelectedProduct(ProductModel productModel);
    public void subSelectedProduct(ProductModel productModel);
    public void removeSelectedProduct();
    public void quantityNotify();
}
