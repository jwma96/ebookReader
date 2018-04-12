package com.example.d.ebookreader.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.d.ebookreader.R;
import com.example.d.ebookreader.model.SectionsPagerAdapter;

import java.util.ArrayList;

import static com.example.d.ebookreader.R.id.addbtn;

//import android.app.Fragment;

//

public class BookShelf  extends AppCompatActivity implements View.OnClickListener {
    //searchBooks sbook=new searchBooks();
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    //private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_shelf);
        Button addbtn = (Button) findViewById(R.id.addbtn);
        addbtn.setOnClickListener(this);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ArrayList<Fragment> datas = new ArrayList<>();
        datas.add(new ReadListFragment());
        datas.add(new FindBooksFragment());
        mSectionsPagerAdapter.setData(datas);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        Log.i("inf","hahah");
        View pager1=getLayoutInflater().inflate(R.layout.pager_book_list,null);
        Button btnAdd=(Button)pager1.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

    }
    public void onClick(View v){

        //sbook.searchBooks();
        Button btn=(Button)v;
        if(btn.getId()==addbtn){
            Log.i("haha","haha");
          //  Intent intent=new Intent(bookshelf.this,searchBooks.class);
           // startActivity(intent);
            finish();
        }
    }
    public static class FindBooksFragment extends Fragment {

        public FindBooksFragment() {
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.page_book_find, container, false);

            return rootView;
        }

    }
    public static class ReadListFragment extends Fragment {

        public ReadListFragment() {
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.pager_book_list, container, false);

            return rootView;
        }

    }
}

