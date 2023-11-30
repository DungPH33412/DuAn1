package com.example.duan1.Screen;


import android.os.Bundle;

import android.view.animation.Animation;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.duan1.Fragment.Home.CartFragment;
import com.example.duan1.Fragment.Home.ExploreFragment;
import com.example.duan1.Fragment.Home.HistoryFragment;
import com.example.duan1.R;
import com.example.duan1.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_home);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getFragment(new ExploreFragment());

        binding.homeBtn.setOnClickListener(v -> {
            getFragment(new ExploreFragment());
            binding.Explorer.setTextColor(getResources().getColor(R.color.green));
            binding.cart.setTextColor(getResources().getColor(R.color.black));
            binding.History.setTextColor(getResources().getColor(R.color.black));
        });

        binding.cartBtn.setOnClickListener(v -> {
            getFragment(new CartFragment());
            binding.Explorer.setTextColor(getResources().getColor(R.color.black));
            binding.cart.setTextColor(getResources().getColor(R.color.green));
            binding.History.setTextColor(getResources().getColor(R.color.black));
        });

        binding.historyBtn.setOnClickListener(v -> {
            getFragment(new HistoryFragment());
            binding.Explorer.setTextColor(getResources().getColor(R.color.black));
            binding.cart.setTextColor(getResources().getColor(R.color.black));
            binding.History.setTextColor(getResources().getColor(R.color.green));
        });
    }

    private void getFragment(Fragment fragment) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fade_in);
        binding.fragmentContainerView.startAnimation(animation);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.commit();
    }
}