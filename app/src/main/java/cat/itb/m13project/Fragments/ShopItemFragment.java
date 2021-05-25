package cat.itb.m13project.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.itb.m13project.R;

import static cat.itb.m13project.ConstantVariables.CONTEXT;


public class ShopItemFragment extends Fragment {

    public ShopItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shop_item, container, false);

        CONTEXT = getContext();

        return v;
    }
}