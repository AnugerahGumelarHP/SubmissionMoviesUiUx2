package id.battistrada.submissionmoviesuiux;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;


public class SearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MoviesData>> {
    private static final String TAG = "TAG";
    ListMoviesAdapter adapter;
    SearchView edtSearch;
    RecyclerView recyclerViewList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search,container,false);
        edtSearch = rootView.findViewById(R.id.edt_search);
        edtSearch.setOnQueryTextListener(myListener);
        recyclerViewList = rootView.findViewById(R.id.rv_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new ListMoviesAdapter(getActivity());
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerViewList.setAdapter(adapter);
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = new Bundle();
        getLoaderManager().initLoader(2, bundle, this);
    }

    @Override
    public Loader<ArrayList<MoviesData>> onCreateLoader(int id, Bundle args) {
        String mNamaMovie =null;
        if (args != null){
            mNamaMovie = args.getString(EXTRAS_SEARCH);
        }
        return new Query(getContext(), mNamaMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MoviesData>> loader, ArrayList<MoviesData> moviesData) {
        adapter.setlistMovie(moviesData);
        adapter.notifyDataSetChanged();
        Log.d(TAG,"onLoadFinished: " + moviesData.size());
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MoviesData>> loader) {
        adapter.setlistMovie(null);
    }

    private String EXTRAS_SEARCH = "pencarian";
    SearchView.OnQueryTextListener myListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            String pencarian = edtSearch.getQuery().toString();
            String fixpencarian = null;
            fixpencarian = pencarian.replace(" ","%20");

            if (TextUtils.isEmpty(pencarian))
            return false;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_SEARCH,fixpencarian);
            getLoaderManager().restartLoader(2, bundle, SearchFragment.this);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        return fragment;
    }

}
