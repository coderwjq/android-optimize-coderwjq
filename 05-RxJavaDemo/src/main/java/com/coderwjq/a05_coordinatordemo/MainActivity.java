package com.coderwjq.a05_coordinatordemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.litesuits.common.assist.Toastor;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText mEtSubmit;
    private Toastor mToastor;
    private Button mBtnCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToastor = new Toastor(this);

        mEtSubmit = (EditText) findViewById(R.id.et_submit);
        mBtnCommit = (Button) findViewById(R.id.btn_commit);

        setEditTextBinding();
        setButtonCommitBinding();
    }

    private void setButtonCommitBinding() {
        RxView.clicks(mBtnCommit)
                // 避免按钮在短时间内的重复点击
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mToastor.showSingletonToast("click:" + mEtSubmit.getText().toString() + System.currentTimeMillis());
                    }
                });
    }

    private void setEditTextBinding() {
        RxTextView.textChanges(mEtSubmit).debounce(1000, TimeUnit.MILLISECONDS)
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        return charSequence.length() > 0;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        mToastor.showSingletonToast(charSequence.toString());
                    }
                });

        RxTextView.textChanges(mEtSubmit).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        mBtnCommit.setEnabled(charSequence.length() == 0 ? false : true);
                    }
                });
    }
}
