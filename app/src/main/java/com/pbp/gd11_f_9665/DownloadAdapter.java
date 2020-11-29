package com.pbp.gd11_f_9665;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.downloader.Status;
import com.pbp.gd11_f_9665.ui.download.UtilityPR;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.DownloadAdapterHolder>{
    private Context context;
    private List<Download> downloadList;

    public DownloadAdapter(Context context,
                           List<Download> downloadList) {
        this.context  = context;
        this.downloadList = downloadList;
    }

    @NonNull
    @Override
    public DownloadAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.download_adapter, parent, false);

        return new DownloadAdapterHolder(view);
    }

    @Override
    public int getItemCount() {
        return downloadList.size();
    }

    class DownloadAdapterHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView txtNama, txtSize;
        Button btnCancel, btnStart;

        public DownloadAdapterHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.pb1);
            txtNama = itemView.findViewById(R.id.tvNama);
            txtSize = itemView.findViewById(R.id.tvSize);
            btnCancel = itemView.findViewById(R.id.btnCancel);
            btnStart = itemView.findViewById(R.id.btnStart);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadAdapterHolder holder, int position) {

        final Download download = downloadList.get(position);
        final int[] id = {-1};

        PRDownloader.initialize(context);
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();

        PRDownloader.initialize(context, config);
        String dirPath = UtilityPR.getRootDirPath((Activity) context);

        holder.txtNama.setText(download.getTipe());
        holder.txtSize.setText((double) download.getSize()+" MB");

        holder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Status.RUNNING == PRDownloader.getStatus(id[0])) {
                    PRDownloader.pause(id[0]);
                    return;
                }

                holder.progressBar.setIndeterminate(true);
                holder.progressBar.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(id[0])) {
                    PRDownloader.resume(id[0]);
                    return;
                }

                id[0] = PRDownloader.download(download.getUrl(), dirPath,
                        download.getName() + download.getPath())
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                holder.progressBar.setIndeterminate(false);
                                holder.btnStart.setEnabled(true);
                                holder.btnCancel.setEnabled(true);
                                holder.btnStart.setText("Hentikan");
                                FancyToast.makeText(context,
                                        "Download dimulai!",
                                        FancyToast.LENGTH_SHORT,FancyToast.INFO,false)
                                        .show();
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                holder.btnStart.setText("Teruskan");
                                FancyToast.makeText(context,
                                        "Download dihentikan sementara!",
                                        FancyToast.LENGTH_SHORT,FancyToast.INFO,false)
                                        .show();
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                holder.btnStart.setEnabled(true);
                                holder.btnCancel.setEnabled(false);
                                holder.btnStart.setText("Download");
                                holder.txtSize.setText("");
                                id[0]=0;
                                holder.progressBar.setProgress(0);
                                holder.progressBar.setIndeterminate(false);
                                FancyToast.makeText(context,
                                        "File batal didownload !",
                                        FancyToast.LENGTH_LONG,FancyToast.WARNING,false)
                                        .show();
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {

                                holder.txtSize.setText(UtilityPR.getProgressDisplayLine(progress.currentBytes,
                                        progress.totalBytes));
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;

                                holder.progressBar.setProgress((int) progressPercent);
                                holder.progressBar.setIndeterminate(false);
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                holder.btnStart.setEnabled(false);
                                holder.btnCancel.setEnabled(false);
                                holder.btnStart.setBackgroundColor(Color.GRAY);
                                holder.btnCancel.setText("Berhasil");
                                holder.btnStart.setText("Downloaded");
                                FancyToast.makeText(context,
                                        "File berhasil didownload!",
                                        FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,false)
                                        .show();
                            }

                            @Override
                            public void onError(Error error) {
                                holder.btnStart.setEnabled(true);
                                holder.btnCancel.setEnabled(false);
                                holder.btnStart.setText("Download");
                                holder.txtSize.setText("");
                                id[0]=0;
                                holder.progressBar.setIndeterminate(false);
                                holder.progressBar.setProgress(0);
                                FancyToast.makeText(context,
                                        "Kesalahan Jaringan!",
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR,false)
                                        .show();
                            }
                        });
            }
        });
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PRDownloader.cancel(id[0]);
            }
        });
    }

}
