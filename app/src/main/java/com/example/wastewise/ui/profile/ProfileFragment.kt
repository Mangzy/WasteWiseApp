package com.example.wastewise.ui.profile

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.wastewise.databinding.FragmentProfileBinding
import com.example.wastewise.utils.ViewModelFactory
import com.example.wastewise.data.Result
import com.example.wastewise.data.remote.response.profile.UpdateProfileResponse
import com.example.wastewise.ui.login.LoginActivity
import com.example.wastewise.utils.Preference
import com.example.wastewise.utils.getImageUri
import com.example.wastewise.utils.uriToFile
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null


    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    private var currentImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        profileViewModel.getProfile().observe(viewLifecycleOwner,  {
            if (it != null) {
                when(it) {
                    is Result.Success -> {
                        showLoading(false)
                        Log.d("ProfileFragment", "onCreateView: ${it.data.data.photoURL}")
                        if(it.data.data.photoURL != null) {
                            Glide.with(requireContext())
                                .load(it.data.data.photoURL)
                                .timeout(6000) // timeout dalam milidetik
                                .into(binding.profileImage)
                        }
                        binding.editUsername.setText(it.data.data.displayName)
                        binding.textEmail.setText(it.data.data.email)
                    }
                    is Result.Loading -> {
                        showLoading(true)
                    }
                    is Result.Error -> {
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        binding.buttonSave.setOnClickListener {
            val username = binding.editUsername.text.toString()
            profileViewModel.updateProfile(username).observe(viewLifecycleOwner, {
                if (it != null) {
                    when(it) {
                        is Result.Success -> {
                            showLoading(false)
                            processSave(it.data)
                        }
                        is Result.Loading -> {
                            showLoading(true)
                        }
                        is Result.Error -> {
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        }

        binding.buttonLogout.setOnClickListener {
            Preference.logOut(requireContext())
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        binding.changePicture.setOnClickListener {
            onChangeProfilePictureClick()
        }


        return root
    }

    private fun onChangeProfilePictureClick() {
        val options = arrayOf("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose an option")
        builder.setItems(options) { dialog, item ->
            when {
                options[item] == "Take Photo" -> {
                    startCamera()
                }
                options[item] == "Choose from Gallery" -> {
                    startGallery()
                }
                options[item] == "Cancel" -> {
                    dialog.dismiss()
                }
            }
        }
        builder.show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicatorProfil.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            uploadPhoto(it)
            binding.profileImage.setImageURI(it)
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun uploadPhoto(image: Uri) {
        val imageFile = uriToFile(image, requireContext())
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "profilePicture",
            imageFile.name,
            requestImageFile
        )
        profileViewModel.updateProfilePicture(multipartBody).observe(viewLifecycleOwner, {
            if (it != null) {
                when(it) {
                    is Result.Success -> {
                        showLoading(false)
                        Toast.makeText(requireContext(), "Upload Success", Toast.LENGTH_LONG).show()
                    }
                    is Result.Loading -> {
                        showLoading(true)
                    }
                    is Result.Error -> {
//                        print di logcat erronya
                        print(it.error)
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun processSave(data: UpdateProfileResponse) {
        if (data.status == "error") {
            Toast.makeText(requireContext(), data.message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}