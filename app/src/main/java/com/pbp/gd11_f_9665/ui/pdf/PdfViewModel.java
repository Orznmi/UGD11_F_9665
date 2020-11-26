package com.pbp.gd11_f_9665.ui.pdf;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PdfViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PdfViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is pdf fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}