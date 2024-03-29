package com.example.preetumajorproject.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.preetumajorproject.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfileFragment newInstance(String param1, String param2) {
        MyProfileFragment fragment = new MyProfileFragment();
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
        View root= inflater.inflate(R.layout.fragment_my_profile, container, false);
try {
             EditText txtname = root.findViewById(R.id.txtname);
            EditText txtemail = root.findViewById(R.id.txtemail);
            EditText txtmobile = root.findViewById(R.id.txtmobile);
            EditText txtpassword = root.findViewById(R.id.txtpassword);
            EditText txtaddress = root.findViewById(R.id.txtaddress);
            EditText txtcity = root.findViewById(R.id.txtcity);
            ImageView userimage=root.findViewById(R.id.userimage);
            //shared preference
            SharedPreferences data = getActivity().getSharedPreferences("logindata",MODE_PRIVATE);
            txtname.setText(data.getString("name", ""));
            txtemail.setText(data.getString("emailid", ""));
            txtmobile.setText(data.getString("mobile", ""));
            txtpassword.setText(data.getString("password", ""));
            txtaddress.setText(data.getString("address", ""));
            txtcity.setText(data.getString("city", ""));
    Glide.with(userimage.getContext()).load(Uri.parse(data.getString("picture",""))).into(userimage);

}
catch (Exception ex)
{

}

return root;
    }
}