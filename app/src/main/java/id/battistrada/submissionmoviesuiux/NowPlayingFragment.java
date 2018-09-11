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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class NowPlayingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MoviesData>>{
    private static final String TAG = "TAG";
    ListMoviesAdapter adapter;
    RecyclerView recyclerViewList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_now_playing, container, false);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = new Bundle();
        getLoaderManager().initLoader(0, bundle,this);
    }

    @Override
    public Loader<ArrayList<MoviesData>> onCreateLoader(int id, @Nullable Bundle args) {
        String mNamaMovie = null;
        return new Query(getContext(), mNamaMovie);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MoviesData>> loader, ArrayList<MoviesData> moviesData) {
        adapter.setlistMovie(moviesData);
        adapter.notifyDataSetChanged();
        Log.d(TAG,"onLoadFinished: "+moviesData.size());
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MoviesData>> loader) {
        adapter.setlistMovie(null);
    }

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    public static NowPlayingFragment newInstance() {
        NowPlayingFragment fragment = new NowPlayingFragment();
        Bundle bundle = new Bundle();
        return fragment;
    }

}
