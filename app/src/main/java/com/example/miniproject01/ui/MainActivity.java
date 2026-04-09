package com.example.miniproject01.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.miniproject01.R;
import com.example.miniproject01.base.BaseActivity;
import com.example.miniproject01.data.local.AppDatabase;
import com.example.miniproject01.data.local.entity.Movie;

import java.util.List;

public class MainActivity extends BaseActivity {

    private TextView tvStatus;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tvStatus = findViewById(R.id.tvStatus);
    }

    @Override
    protected void initData() {
        showLoading();
        // Kiểm tra database trong background thread
        AppDatabase.databaseWriteExecutor.execute(() -> {
            AppDatabase db = AppDatabase.getDatabase(this);
            List<Movie> movies = db.movieDao().getAllMovies();

            runOnUiThread(() -> {
                hideLoading();
                if (movies != null && !movies.isEmpty()) {
                    StringBuilder sb = new StringBuilder("Database OK!\nDanh sách phim:\n");
                    for (Movie m : movies) {
                        sb.append("- ").append(m.getTitle()).append("\n");
                    }
                    tvStatus.setText(sb.toString());
                    showToast("Đã tải " + movies.size() + " phim từ DB");
                } else {
                    tvStatus.setText("Database rỗng hoặc lỗi!");
                }
            });
        });
    }
}
