package com.example.afinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeastFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Feast> feastList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FeastAdapter mAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeastFragment newInstance(String param1, String param2) {
        FeastFragment fragment = new FeastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feast, container, false);
        prepareDate();
        recyclerView = (RecyclerView) v.findViewById(R.id.r_view);
        recyclerView.setHasFixedSize(true);
        mAdapter = new FeastAdapter(feastList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        return v;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareDate();
    }

    private void prepareDate() {
        feastList.add(new Feast("이불 : 시작", "기간 2021.03.02 ~ 2021.05.16", "가격 무료"));
        feastList.add(new Feast("호민과 재환", "기간 2021.03.02 ~ 2021.05.16", "가격 무료"));
        feastList.add(new Feast("기후 미술관 : 우리 집의 생애", "기간 2021.03.02 ~ 2021.05.16", "가격 무료"));
    }
}