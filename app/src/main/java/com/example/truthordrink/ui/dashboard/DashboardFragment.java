package com.example.truthordrink.ui.dashboard;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
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

import com.example.truthordrink.CustomImageButton;
import com.example.truthordrink.R;
import com.example.truthordrink.databinding.FragmentDashboardBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private final List<Integer> OTRList = new ArrayList<Integer>();
    private final List<Integer> HOList = new ArrayList<Integer>();
    private final List<Integer> EDList = new ArrayList<Integer>();
    private final List<Integer> LCList = new ArrayList<Integer>();

    private final List<String> pickDeckList = Arrays.asList("OTR", "HR", "ED", "LC");
    private int pickDeck = 0;

    private Random rand = new Random();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // DashboardViewModel lines were here

        final MediaPlayer press = MediaPlayer.create(getActivity(), R.raw.button);
        final MediaPlayer flip = MediaPlayer.create(getActivity(), R.raw.flipping);

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

        CustomImageButton otrButton = binding.imageButtonOTR;
        CustomImageButton hrButton = binding.imageButtonHR;
        CustomImageButton edButton = binding.imageButtonED;
        CustomImageButton lcButton = binding.imageButtonLC;
        Button drawButton = binding.drawButton;

//        otrButton.setOnClickListener(this);
//        hrButton.setOnClickListener(this);
//        edButton.setOnClickListener(this);
//        lcButton.setOnClickListener(this);

        otrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View otrButton) {
                imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.deck_example));
                pickDeck = 1;
                press.start();
                otrButton.setEnabled(false);
                hrButton.setEnabled(true);
                edButton.setEnabled(true);
                lcButton.setEnabled(true);
            }
        });

        hrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View hrButton) {
                imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.deck_example));
                pickDeck = 2;
                press.start();
                otrButton.setEnabled(true);
                hrButton.setEnabled(false);
                edButton.setEnabled(true);
                lcButton.setEnabled(true);
            }
        });

        edButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View edButton) {
                imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.deck_example));
                pickDeck = 3;
                press.start();
                otrButton.setEnabled(true);
                hrButton.setEnabled(true);
                edButton.setEnabled(false);
                lcButton.setEnabled(true);
            }
        });

        lcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View lcButton) {
                imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.deck_example));
                pickDeck = 4;
                press.start();
                otrButton.setEnabled(true);
                hrButton.setEnabled(true);
                edButton.setEnabled(true);
                lcButton.setEnabled(false);
            }
        });

        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View drawButton) {
                switch (pickDeck) {
                    case 1:
                        flip.start();
                        imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), OTRList.get(rand.nextInt(OTRList.size()))));
                        break;
                    case 2:
                        flip.start();
                        imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), HOList.get(rand.nextInt(HOList.size()))));
                        break;
                    case 3:
                        flip.start();
                        imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), EDList.get(rand.nextInt(EDList.size()))));
                        break;
                    case 4:
                        flip.start();
                        imageView2.setImageDrawable(ContextCompat.getDrawable(requireContext(), LCList.get(rand.nextInt(LCList.size()))));
                        break;
                    default:
                        break;
                }
            }
        });


        return root;
    }

//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public void onClick(View view) {
//        press.start();
//        switch (view.getId()) {
//            case R.id.imageButtonOTR:
//                pickDeck = 1;
//                break;
//            case R.id.imageButtonHR:
//                pickDeck = 2;
//                break;
//            case R.id.imageButtonED:
//                pickDeck = 3;
//                break;
//            case R.id.imageButtonLC:
//                pickDeck = 4;
//                break;
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}