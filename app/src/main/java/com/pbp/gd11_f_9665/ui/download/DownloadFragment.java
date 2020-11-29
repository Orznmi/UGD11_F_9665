package com.pbp.gd11_f_9665.ui.download;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pbp.gd11_f_9665.Download;
import com.pbp.gd11_f_9665.DownloadAdapter;
import com.pbp.gd11_f_9665.R;

import java.util.ArrayList;
import java.util.List;

public class DownloadFragment extends Fragment {
    private DownloadViewModel slideshowViewModel;
    private List<Download> downloadList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(DownloadViewModel.class);
        View root = inflater.inflate(R.layout.fragment_download, container, false);
        downloadList = new ArrayList<>();
        downloadList.add(new Download("Kesiapan Pustakawan Memanfaatkannya", ".pdf", "http://eprints.rclis.org/9347/1/Ardoni-Pustaha-des2005-05.pdf",0.50,"Dokumen"));
        downloadList.add(new Download("Sea", ".jpg", "https://i.pinimg.com/originals/79/8d/ed/798dede03ef79dfa7688e1ab5a01d2ce.jpg" ,0.166,"Gambar"));
        downloadList.add(new Download("Something-The Beatles", ".mp3", "https://dl2.freemp3downloads.online/file/youtubeUelDrZ1aFeY128.mp3?fn=The%20Beatles%20-%20Something.mp3",2.86,"Musik"));

        RecyclerView recyclerView = root.findViewById(R.id.rv_download);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new DownloadAdapter(getContext(), downloadList));
        return root;
    }


}


