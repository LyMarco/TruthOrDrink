package com.example.truthordrink.ui.dashboard;

import android.annotation.SuppressLint;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.truthordrink.R;
import com.example.truthordrink.databinding.FragmentDashboardBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    private FragmentDashboardBinding binding;

    private List<Integer> OTRList = new ArrayList<Integer>();
    private List<Integer> HOList = new ArrayList<Integer>();
    private List<Integer> EDList = new ArrayList<Integer>();
    private List<Integer> LCList = new ArrayList<Integer>();

    private List<String> pickDeckList = Arrays.asList("OTR", "HR", "ED", "LC");
    private int pickDeck = 0;


    private Random rand = new Random();
//    R.drawable.deck_example

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // DashboardViewModel lines were here

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageView imageView2 = binding.imageView2;

        // @D.Snap Stack overflow
        Field[] drawables = R.drawable.class.getDeclaredFields();

        for (Field f : drawables) {
            //if the drawable name contains "pic" in the filename...
            if (f.getName().contains("tod_otr"))
                OTRList.add(getResources().getIdentifier(f.getName(), "drawable", getActivity().getPackageName()));
            else if (f.getName().contains("tod_ho"))
                HOList.add(getResources().getIdentifier(f.getName(), "drawable",  getActivity().getPackageName()));
            else if (f.getName().contains("tod_ed"))
                EDList.add(getResources().getIdentifier(f.getName(), "drawable",  getActivity().getPackageName()));
            else if (f.getName().contains("tod_lc"))
                LCList.add(getResources().getIdentifier(f.getName(), "drawable",  getActivity().getPackageName()));
        }

        ImageButton otrButton = binding.imageButtonOTR;
        ImageButton hrButton = binding.imageButtonHR;
        ImageButton edButton = binding.imageButtonED;
        ImageButton lcButton = binding.imageButtonLC;
        Button drawbutton = binding.drawButton;

        otrButton.setOnClickListener(this);
        hrButton.setOnClickListener(this);
        edButton.setOnClickListener(this);
        lcButton.setOnClickListener(this);

//        otrButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View otrButton) {
//                imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.deck_example));
//                pickDeck = 1;
//            }
//        });
//
//        hrButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View hrButton) {
//                imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.deck_example));
//                pickDeck = 2;
//            }
//        });
//
//        edButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View edButton) {
//                imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.deck_example));
//                pickDeck = 3;
//            }
//        });
//
//        lcButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View lcButton) {
//                imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.deck_example));
//                pickDeck = 4;
//            }
//        });

        drawbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View otrButton) {
                switch (pickDeck) {
                    case 1:
                        imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), OTRList.get(rand.nextInt(OTRList.size()))));
                        break;
                    case 2:
                        imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), HOList.get(rand.nextInt(HOList.size()))));
                        break;
                    case 3:
                        imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), EDList.get(rand.nextInt(EDList.size()))));
                        break;
                    case 4:
                        imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), LCList.get(rand.nextInt(LCList.size()))));
                        break;
                    default:
                        break;
                }
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonOTR:
                pickDeck = 1;
                break;
            case R.id.imageButtonHR:
                pickDeck = 2;
                break;
            case R.id.imageButtonED:
                pickDeck = 3;
                break;
            case R.id.imageButtonLC:
                pickDeck = 4;
                break;
        }
    }
}