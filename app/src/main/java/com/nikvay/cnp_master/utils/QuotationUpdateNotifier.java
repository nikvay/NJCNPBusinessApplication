package com.nikvay.cnp_master.utils;

public interface QuotationUpdateNotifier {
    public void updateQuotation(int mPosition,String mQuantity);
    public void deleteQuotation(int mPosition);
}
