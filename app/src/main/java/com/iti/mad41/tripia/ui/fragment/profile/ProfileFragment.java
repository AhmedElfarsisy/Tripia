package com.iti.mad41.tripia.ui.fragment.profile;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.databinding.ProfileFragmentBinding;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private ProfileFragmentBinding binding;
    FirebaseRepo firebaseRepo;
    Uri pickedImageUri = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false);
        firebaseRepo = new FirebaseRepo(getActivity());
        Toolbar signupToolbar = binding.profileToolbar;
        signupToolbar.setNavigationIcon(R.drawable.ic_arrow);
        signupToolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ProfileViewModelFactory(firebaseRepo)).get(ProfileViewModel.class);
        binding.setProfileViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        mViewModel.isOpenGallery.observe(getViewLifecycleOwner(), isOPenGallery -> {
            if (isOPenGallery) {
                checkAndRequestForPermission();
            }
        });
    }


    private void checkAndRequestForPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(getActivity(), "permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.GALLERY_PERMISSION_REQUSET_CODE);
            }
        } else {
            openGallery();
        }
    }

    private void openGallery() {
        Intent GalleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        GalleryIntent.setType("image/*");
        startActivityForResult(GalleryIntent, Constants.GAllery_INTENT_REQUSET_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constants.GAllery_INTENT_REQUSET_CODE && data != null) {
            pickedImageUri = data.getData();
            Glide.with(getActivity()).load(pickedImageUri).into(binding.changeProfileImageView);
            storeProfileImageInFile(pickedImageUri);
        }
    }

    private void storeProfileImageInFile(Uri pickedImageUri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), pickedImageUri);
            FileOutputStream out = out = getActivity().openFileOutput(Constants.PROFILE_IMAGE_KEY, MODE_PRIVATE);
            boolean isCompressed = bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}