package com.example.preetumajorproject.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.preetumajorproject.R;
import com.example.preetumajorproject.dbadapter;
import com.example.preetumajorproject.dbmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServiceManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceManagementFragment extends Fragment {
    RecyclerView recycle;
    dbadapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServiceManagementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServiceManagementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServiceManagementFragment newInstance(String param1, String param2) {
        ServiceManagementFragment fragment = new ServiceManagementFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_service_management, container, false);
        recycle =view.findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ShopDetail");
        SharedPreferences shareddata=getActivity().getSharedPreferences("logindata",MODE_PRIVATE);
        String name=shareddata.getString("mobile","");
        Query query = myRef.orderByChild("executiveid").equalTo(name);

        FirebaseRecyclerOptions<dbmodel> options =
                new FirebaseRecyclerOptions.Builder<dbmodel>()
                        .setQuery(query, dbmodel.class)
                        .build();
        adapter = new dbadapter(options);
        recycle.setAdapter(adapter);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}