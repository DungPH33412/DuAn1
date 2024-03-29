package com.example.duan1.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.duan1.Fragment.Admin.AddBrandFragment;
import com.example.duan1.Fragment.ListProductFragment;
import com.example.duan1.Model.Brand;

import com.example.duan1.R;
import com.example.duan1.Screen.HomeActivity;
import com.example.duan1.databinding.ItemBrandBinding;

import java.util.List;


public class AdapterBrand extends RecyclerView.Adapter<AdapterBrand.ViewHolder>{

    private List<Brand> brandList;
    private Context context;

    public AdapterBrand(List<Brand> brandList, Context context) {
        this.brandList = brandList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterBrand.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_brand, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBrand.ViewHolder holder, int position) {
        Brand brand = brandList.get(position);
        holder.binding.textBrand.setText(brand.getName());
        Glide.with(context).load(brand.getImage()).into(holder.binding.imageBrand);
        SharedPreferences sharedPreferences = context.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String role = sharedPreferences.getString("role", "");
        if (role.equals("admin")) {
            holder.itemView.setOnLongClickListener(v -> {
                getFragment(new AddBrandFragment(), brand);
                return false;
            });
        }

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("brand", brand);
            Fragment fragment = new ListProductFragment();
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = ((HomeActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragmentContainerView, fragment).commit();
        });

    }
    private void getFragment(Fragment fragment, Brand brand) {
        Bundle bundle = new Bundle();
        bundle.putString("status", "edit");
        bundle.putSerializable("brand", brand);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = ((HomeActivity) context).getSupportFragmentManager();
        fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .replace(R.id.fragmentContainerView, fragment).commit();
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemBrandBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemBrandBinding.bind(itemView);
        }
    }
}
